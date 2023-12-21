package com.campuswindow.chat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.campuswindow.R;
import com.campuswindow.Result;
import com.campuswindow.constant.UserConstant;
import com.campuswindow.fragment.index.ChatRoomFragment;
import com.campuswindow.server.API;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class ChatDetailActivity extends AppCompatActivity {
    private TextView toUserName;
    private RecyclerView recyclerView;
    private EditText mInputText;//输入框
    private Button mSend,btnReturn;//发送按钮

    private String linkId;
    private ChatDetailAdapter chatDetailAdapter;
    private List<ChatMessageGroupVo> chatMessages = new ArrayList<>();
    private ChatUserDto chatUserDto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_chat_detail);
        //初始化控件
        initView();
        //获取页面跳转传送的数据
        Intent intent = getIntent();
        linkId = intent.getStringExtra("linkId");
        chatUserDto = (ChatUserDto) intent.getSerializableExtra("chatUserDto");

        //查询用户和某个用户的所有聊天记录
        queryChatMessage();

        //初始化adapter并绑定Recyclerview
        chatDetailAdapter = new ChatDetailAdapter(chatMessages, linkId, chatUserDto);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(chatDetailAdapter);

        //设置发送按钮监听器
        setListener();

        IntentFilter filter = new IntentFilter(ChatRoomFragment.action);
        registerReceiver(broadcastReceiver, filter);

        //给控件赋值：
        toUserName.setText(getIntent().getStringExtra("toUserName"));

//        //获取页面跳转传送的数据
//        Intent intent = getIntent();
//        linkId = intent.getStringExtra("linkId");
//        toUserId = intent.getStringExtra("toUserId");
//        Log.i("fromUserId-toUserId:",linkId+""+toUserId);
//        String toUserName = intent.getStringExtra("toUserName");
//        String toUserAvatar = intent.getStringExtra("toUserAvatar");
//        String fromUserName = intent.getStringExtra("fromUserName");
//        String fromUserAvatar = intent.getStringExtra("fromUserAvatar");
//        //与服务构建WebSocket连接
//        openConnection(linkId, toUserId);

    }

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ChatMessageGroupVo chatMessage = (ChatMessageGroupVo)intent.getSerializableExtra("chatMessage");
            if (linkId.equals(chatMessage.getLinkId())){
                chatMessages.add(chatMessage);
                chatDetailAdapter.notifyDataSetChanged();
            }
        }
    };

    /*
     * 初始化控件
     */
    private void initView() {
        recyclerView = findViewById(R.id.rv_details);
        mInputText = findViewById(R.id.input_text);
        mSend = findViewById(R.id.send);
        btnReturn = findViewById(R.id.chat_room_return);
        toUserName = findViewById(R.id.chat_room_name);
    }

    /*
     * 设置发送按钮监听器
     */
    private void setListener() {
        mSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mInputText.getText().toString();
                if (!"".equals(content)) {
                    ChatMessageGroupVo chatMessage = new ChatMessageGroupVo(UUID.randomUUID().toString().replaceAll("-", ""), linkId, UserConstant.USER_ID, chatUserDto.getUserName(), chatUserDto.getAvatar(), content, new Timestamp(System.currentTimeMillis()), 0);
                    ChatRoomFragment.socket.send(JSON.toJSONString(chatMessage));
                    chatMessages.add(chatMessage);
                    updateRecyclerView();//刷新RecyclerView
                    //清空输入栏
                    mInputText.setText("");
                }
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    /*
     * 数据更新，刷新RecyclerView
     */
    private void updateRecyclerView() {
        //当有新消息时，刷新RecyclerView中的显示
        chatDetailAdapter.notifyItemInserted(chatMessages.size() - 1);
        //将RecyclerView定位到最后一行
        recyclerView.scrollToPosition(chatMessages.size() - 1);
    }
    /*
     * 查询用户和某个用户的所有聊天记录
     */
    private void queryChatMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request build = new Request.Builder()
                        .get()
                        .url(API.FIND_CHAT_MESSAGE + "?linkId=" + linkId)
                        .build();
                Call call = client.newCall(build);
                try {
                    Response response = call.execute();
                    Gson gson = new Gson().newBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                    Result result = gson.fromJson(response.body().string(), Result.class);
                    Type type = new TypeToken<ArrayList<ChatMessageGroupVo>>(){}.getType();
                    chatMessages = gson.fromJson(gson.toJson(result.getData()), type);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            chatDetailAdapter.notifyDataSetChanged();
                            chatDetailAdapter.setChatMessages(chatMessages);
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }


    /**
     * 对返回键的处理
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        okHttpClient.dispatcher().executorService().shutdownNow();
//        socket.close(1000, "正常关闭");
        updateEnterWindows();
        return super.onKeyDown(keyCode, event);
    }

    /*
     * 点击离开窗口
     */
    private void updateEnterWindows() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request build = new Request.Builder()
                        .get()
                        .url(API.UPDATE_CHAT_LIST_LEAVE_WINDOW + "?linkId=" + linkId + "&userId=" + UserConstant.USER_ID)
                        .build();
                Call call = client.newCall(build);
                try {
                    Response response = call.execute();
                    Gson gson = new Gson();
                    Result result = gson.fromJson(response.body().string(), Result.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }


}