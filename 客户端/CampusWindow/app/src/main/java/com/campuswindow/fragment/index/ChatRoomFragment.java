package com.campuswindow.fragment.index;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.campuswindow.R;
import com.campuswindow.Result;
import com.campuswindow.chat.ChatDetailActivity;
import com.campuswindow.chat.ChatList;
import com.campuswindow.chat.ChatListAdapter;
import com.campuswindow.chat.ChatUserDto;
import com.campuswindow.constant.UserConstant;
import com.campuswindow.interfaces.OnChatItemClickListener;
import com.campuswindow.server.API;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

//ctrl + p 方法参数提示信息
public class ChatRoomFragment extends Fragment implements OnChatItemClickListener {
    private ImageView chatBtnImg,chatImgHeader;
    private TextView chatName, chatState;
    private RecyclerView chatRv;
    private String fromUserId;
    private ChatListAdapter chatListAdapter;
    private List<ChatList> chatLists = new ArrayList<>();

    private ChatUserDto chatUserDto;

    private Handler handler;

    public ChatRoomFragment() {
        handler = new Handler(Looper.getMainLooper());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.activity_chat_room_fragment, null);
        getViews(page);
        listenerMethods();

        //从android保存的一份UserId 获取 TODO
//        fromUserId = "2";
        fromUserId = UserConstant.USER_ID;
        //查询自己的所有聊天列表
        queryChatList(fromUserId);
        //初始化Adapter
        chatListAdapter = new ChatListAdapter(chatLists);
        //绑定RecyclerView的点击事件监听器
        chatListAdapter.setOnItemClickListener(this);
        //RecyclerView绑定Adapter
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        chatRv.setLayoutManager(manager);
        chatRv.setAdapter(chatListAdapter);
        //查询自己的头像和姓名
        selectMyselfNameAndAvatar(fromUserId);

        return page;
    }

    private void listenerMethods() {
        chatBtnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getActivity(),chatBtnImg);
                popupMenu.getMenuInflater().inflate(R.menu.chat_room_option_menu,popupMenu.getMenu());
                
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                Toast.makeText(getActivity(), "点击了第" + 1 + "个", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.menu2:
                                Toast.makeText(getActivity(), "点击了第" + 2 + "个", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.menu3:
                                Toast.makeText(getActivity(), "点击了第" + 3 + "个", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.menu4:
                                Toast.makeText(getActivity(), "点击了第" + 4 + "个", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.menu5:
                                Toast.makeText(getActivity(), "点击了第" + 5 + "个", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
    }

    /*
     * 根据UserId查询自己的头像和姓名
     */
    private void selectMyselfNameAndAvatar(String fromUserId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request build = new Request.Builder()
                        .get()
                        .url(API.FIND_CHAT_USER_DTO + "?userId=" + UserConstant.USER_ID)
                        .build();
                Call call = client.newCall(build);
                try {
                    Response response = call.execute();
                    Gson gson = new Gson();
                    Result result = gson.fromJson(response.body().string(), Result.class);
                    Type type = new TypeToken<ChatUserDto>(){}.getType();
                    chatUserDto = gson.fromJson(gson.toJson(result.getData()), type);
                    Log.i("chatUserDto",chatUserDto.toString());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Glide.with(getContext())
                                    .load(chatUserDto.getAvatar())
                                    .circleCrop()
                                    .into(chatImgHeader);
                            chatName.setText(chatUserDto.getUserName());
                        }
                    });
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    /*
     * 查询自己的所有聊天列表
     */
    private void queryChatList(String fromUserId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request build = new Request.Builder()
                        .get()
                        .url(API.FIND_CHAT_LIST + "?fromUserId=" + fromUserId)
                        .build();
                Call call = client.newCall(build);
                try {
                    Response response = call.execute();
                    Gson gson = new Gson();
                    Result result = gson.fromJson(response.body().string(), Result.class);
                    Type type = new TypeToken<ArrayList<ChatList>>(){}.getType();
                    chatLists = gson.fromJson(gson.toJson(result.getData()), type);
                    runOnMainThread(chatLists);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void runOnMainThread (List<ChatList> chatLists) {
        // 在主线程上执行一段代码
        handler.post(new Runnable() {
            @Override
            public void run() {
                chatListAdapter.notifyDataSetChanged();
                chatListAdapter.setChatLists(ChatRoomFragment.this.chatLists);
            }
        });
    }

    /*
     * 重写RecyclerView的点击事件处理方法
     */
    @Override
    public void onItemClick(List<ChatList> chatLists, View view, int position) {
        ChatList chatList = chatLists.get(position);
        String fromUserId = chatList.getFromUserId();//发送者
        String toUserId = chatList.getToUserId();//接收者
        //页面跳转到聊天窗口
        Intent intent = new Intent();
        intent.setClass(getContext(), ChatDetailActivity.class);
        //设置所需的参数
        intent.putExtra("fromUserId", fromUserId);
        intent.putExtra("toUserId", toUserId);
        intent.putExtra("toUserName", chatList.getToUserName());
        intent.putExtra("toUserAvatar", chatList.getToUserAvatar());
        intent.putExtra("fromUserName", chatUserDto.getUserName());
        intent.putExtra("fromUserAvatar", chatUserDto.getAvatar());
        clearUnRead(fromUserId, toUserId);
        startActivity(intent);
    }

    /*
     * 点击进入窗口，同时删除与该对象的未读消息
     */
    private void clearUnRead(String fromUserId, String toUserId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request build = new Request.Builder()
                        .get()
                        .url(API.UPDATE_CHAT_LIST_UNREAD + "/" + fromUserId + "/" + toUserId)
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

    private void getViews(View page) {
        chatImgHeader = page.findViewById(R.id.chat_room_img);
        chatName = page.findViewById(R.id.chat_room_name);
        chatState = page.findViewById(R.id.chat_room_state);
        chatBtnImg = page.findViewById(R.id.chat_room_btn_img);
        chatRv = page.findViewById(R.id.rv);
    }
}

