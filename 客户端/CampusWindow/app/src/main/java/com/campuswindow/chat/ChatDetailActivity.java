package com.campuswindow.chat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.campuswindow.R;
import com.campuswindow.Result;
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
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class ChatDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText mInputText;//输入框
    private Button mSend,btnReturn;//发送按钮

    private OkHttpClient okHttpClient;
    private WebSocket socket;

    private ChatDetailAdapter chatDetailAdapter;
    private List<ChatMessage> chatMessages = new ArrayList<>();

    private String fromUserId;
    private String toUserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_chat_detail);
        //初始化控件
        initView();
        //初始化OkHttpClient
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(300, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(300, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(300, TimeUnit.SECONDS)//设置连接超时时间
                .pingInterval(10, TimeUnit.SECONDS)
                .build();
        //获取页面跳转传送的数据
        Intent intent = getIntent();
        fromUserId = intent.getStringExtra("fromUserId");
        toUserId = intent.getStringExtra("toUserId");
        Log.i("fromUserId-toUserId:",fromUserId+""+toUserId);
        String toUserName = intent.getStringExtra("toUserName");
        String toUserAvatar = intent.getStringExtra("toUserAvatar");
        String fromUserName = intent.getStringExtra("fromUserName");
        String fromUserAvatar = intent.getStringExtra("fromUserAvatar");
        //与服务构建WebSocket连接
        openConnection(fromUserId, toUserId);
        //查询用户和某个用户的所有聊天记录
        queryChatMessage(fromUserId, toUserId);
        //初始化adapter并绑定Recyclerview
        chatDetailAdapter = new ChatDetailAdapter(chatMessages, fromUserId, toUserName, toUserAvatar, fromUserName, fromUserAvatar);
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        manager.setStackFromEnd(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(chatDetailAdapter);
        //设置发送按钮监听器
        setListener();

//        recyclerView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
//            @Override
//            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
//                recyclerView.scrollToPosition(chatDetailAdapter.getItemCount() - 1);
//            }
//        });
    }

    /*
     * 初始化控件
     */
    private void initView() {
        recyclerView = findViewById(R.id.rv_details);
        mInputText = findViewById(R.id.input_text);
        mSend = findViewById(R.id.send);
        btnReturn = findViewById(R.id.chat_room_return);
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
                    ChatMessage chatMessage = new ChatMessage(UUID.randomUUID().toString().replaceAll("-", ""), null, fromUserId, toUserId, content, new Timestamp(System.currentTimeMillis()), 0);
                    socket.send(JSON.toJSONString(chatMessage));
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
    private void queryChatMessage(String fromUserId, String toUserId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request build = new Request.Builder()
                        .get()
                        .url(API.FIND_CHAT_MESSAGE + "?fromUserId=" + fromUserId + "&toUserId=" + toUserId)
                        .build();
                Call call = client.newCall(build);
                try {
                    Response response = call.execute();
                    Gson gson = new Gson().newBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                    Result result = gson.fromJson(response.body().string(), Result.class);
                    Type type = new TypeToken<ArrayList<ChatMessage>>(){}.getType();
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

    /*
     * 与服务构建WebSocket连接
     */
    private void openConnection(String fromUserId, String toUserId) {
        Request request = new Request.Builder()
                .url(API.WEB_SOCKET + fromUserId + "/" + toUserId)
                .build();
        socket = okHttpClient.newWebSocket(request, new EchoWebSocketListener());
        okHttpClient.dispatcher().executorService().shutdown();
    }

    /**
     * 对返回键的处理
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        okHttpClient.dispatcher().executorService().shutdownNow();
        socket.close(1000, "正常关闭");
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 内部类，监听web socket回调
     */
    private final class EchoWebSocketListener extends WebSocketListener {

        @Override
        public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
            super.onClosed(webSocket, code, reason);
        }

        @Override
        public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable Response response) {
            super.onFailure(webSocket, t, response);
        }

        @Override
        public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
            super.onMessage(webSocket, text);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Gson gson = new Gson().newBuilder().registerTypeAdapter(Timestamp.class, new JsonDeserializer<Timestamp>() {
                        @Override
                        public Timestamp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                            return new Timestamp(json.getAsLong());
                        }
                    }).create();
                    ChatMessage chatMessage = gson.fromJson(text, ChatMessage.class);
                    chatMessages.add(chatMessage);
                    updateRecyclerView();
                }
            });
        }

        @Override
        public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
            super.onOpen(webSocket, response);
        }
    }

    @Override
    protected void onDestroy() {
        okHttpClient.dispatcher().executorService().shutdownNow();
        socket.close(1000, "正常关闭");
        super.onDestroy();
    }
}