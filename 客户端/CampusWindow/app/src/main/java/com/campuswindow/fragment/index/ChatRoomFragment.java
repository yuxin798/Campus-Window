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
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.campuswindow.R;
import com.campuswindow.Result;
import com.campuswindow.chat.ChatDetailActivity;
import com.campuswindow.chat.ChatListAdapter;
import com.campuswindow.chat.ChatMessageGroupVo;
import com.campuswindow.chat.ChatUserDto;
import com.campuswindow.constant.UserConstant;
import com.campuswindow.entity.ChatListVo;
import com.campuswindow.entity.User;
import com.campuswindow.interfaces.OnChatItemClickListener;
import com.campuswindow.server.API;
import com.campuswindow.service.index.SearchOneSelfService;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

//ctrl + p 方法参数提示信息
public class ChatRoomFragment extends Fragment implements OnChatItemClickListener {
    private ImageView chatBtnImg,chatImgHeader,chatImgSex;
    private TextView chatName;

    private RecyclerView chatRv;
    private String fromUserId;
    private ChatListAdapter chatListAdapter;
    private List<ChatListVo> chatLists = new ArrayList<>();
    private OkHttpClient okHttpClient;
    public static WebSocket socket;
    public static final String action = "jason.broadcast.action";
    private ChatUserDto chatUserDto;
    private User user;

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
        openConnection(UserConstant.USER_ID);

        //查询自己的所有聊天列表
        queryChatList();
        queryMySelfNameAndAvatar();

        //初始化Adapter
        chatListAdapter = new ChatListAdapter(chatLists);
        //绑定RecyclerView的点击事件监听器
        chatListAdapter.setOnItemClickListener(this);
        //RecyclerView绑定Adapter
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        chatRv.setLayoutManager(manager);
        chatRv.setAdapter(chatListAdapter);
        //查询自己的头像和姓名
        selectMyselfNameAndAvatar(UserConstant.USER_ID);
        return page;
    }

    private void queryMySelfNameAndAvatar() {
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
                    chatUserDto = gson.fromJson(gson.toJson(result.getData()), ChatUserDto.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    private void openConnection(String userId) {
        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(300, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(300, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(300, TimeUnit.SECONDS)//设置连接超时时间
                .pingInterval(10, TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(API.WEB_SOCKET + fromUserId)
                .build();
        socket = okHttpClient.newWebSocket(request, new EchoWebSocketListener());
        okHttpClient.dispatcher().executorService().shutdown();
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
                SearchOneSelfService service = new SearchOneSelfService();
                Result result = service.getUserData();
                System.out.println(result+"66666");
                Gson gson = new Gson();
                user = gson.fromJson(gson.toJson(result.getData()),User.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(getContext())
                                .load(user.getAvatar())
                                .circleCrop()
                                .into(chatImgHeader);
                        chatName.setText(user.getUserName());
                        if(user.getGender() == 0.0){
                            Glide.with(getContext())
                                    .load(R.drawable.nan)
                                    .circleCrop()
                                    .into(chatImgSex);
                        }else if(user.getGender() == 1.0){
                            Glide.with(getContext())
                                    .load(R.drawable.nv)
                                    .circleCrop()
                                    .into(chatImgSex);
                        }else{
                            Glide.with(getContext())
                                    .load(R.drawable.nulls)
                                    .circleCrop()
                                    .into(chatImgSex);
                        }
                    }
                });
            }
        }).start();

    }

    /*
     * 查询自己的所有聊天列表
     */
    private void queryChatList() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request build = new Request.Builder()
                        .get()
                        .url(API.FIND_CHAT_LIST + "?userId=" + UserConstant.USER_ID)
                        .build();
                Call call = client.newCall(build);
                try {
                    Response response = call.execute();
                    Gson gson = new Gson();
                    Result result = gson.fromJson(response.body().string(), Result.class);
                    Type type = new TypeToken<ArrayList<ChatListVo>>(){}.getType();
                    chatLists = gson.fromJson(gson.toJson(result.getData()), type);
                    System.out.println(chatLists);
                    Log.i("chatLists",chatLists.toString());
                    runOnMainThread(chatLists);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    public void runOnMainThread (List<ChatListVo> chatLists) {
        // 在主线程上执行一段代码
        handler.post(new Runnable() {
            @Override
            public void run() {
                chatListAdapter.setChatLists(chatLists);
                chatListAdapter.notifyDataSetChanged();
            }
        });
    }

    /*
     * 重写RecyclerView的点击事件处理方法
     */
    @Override
    public void onItemClick(List<ChatListVo> chatLists, View view, int position) {
        ChatListVo chatList = chatLists.get(position);
        //页面跳转到聊天窗口
        Intent intent = new Intent();
        intent.setClass(getContext(), ChatDetailActivity.class);
        //设置所需的参数
        intent.putExtra("linkId", chatList.getLinkId());
        intent.putExtra("chatUserDto", chatUserDto);
        updateEnterWindows(chatList.getLinkId());
        startActivity(intent);
    }

    private void updateEnterWindows(String linkId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request build = new Request.Builder()
                        .get()
                        .url(API.UPDATE_CHAT_LIST_ENTER_WINDOW + "?linkId=" + linkId + "&userId=" + UserConstant.USER_ID)
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
    @Override
    public void onResume() {
        if (chatLists != null){
            chatLists.clear();
            queryChatList();
        }
        chatListAdapter.setChatLists(chatLists);
        super.onResume();
    }

    /*
     * 点击进入窗口，同时删除与该对象的未读消息
     */
//    private void clearUnRead(String fromUserId, String toUserId) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                OkHttpClient client = new OkHttpClient();
//                Request build = new Request.Builder()
//                        .get()
//                        .url(API.UPDATE_CHAT_LIST_UNREAD + "/" + fromUserId + "/" + toUserId)
//                        .build();
//                Call call = client.newCall(build);
//                try {
//                    Response response = call.execute();
//                    Gson gson = new Gson();
//                    Result result = gson.fromJson(response.body().string(), Result.class);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        }).start();
//    }

    private void getViews(View page) {
        chatImgHeader = page.findViewById(R.id.chat_room_img);
        chatName = page.findViewById(R.id.chat_room_name);
        chatImgSex = page.findViewById(R.id.chat_room_img_sex);
        chatBtnImg = page.findViewById(R.id.chat_room_btn_img);
        chatRv = page.findViewById(R.id.rv);
    }

    /**
     * 内部类，监听web socket回调
     */
    public final class EchoWebSocketListener extends WebSocketListener {

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
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (chatLists != null){
                        chatLists.clear();
                        queryChatList();
                    }
                    chatListAdapter.setChatLists(chatLists);
                    Gson gson = new Gson().newBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                        @Override
                        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                            return new Date(json.getAsLong());
                        }
                    }).create();
                    ChatMessageGroupVo chatMessage = gson.fromJson(text, ChatMessageGroupVo.class);
                    Intent intent = new Intent(action);
                    intent.putExtra("chatMessage", chatMessage);
                    LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                    chatListAdapter.notifyDataSetChanged();
                }
            });
        }

        @Override
        public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
            super.onOpen(webSocket, response);
        }
    }

    @Override
    public void onDestroy() {
        okHttpClient.dispatcher().executorService().shutdownNow();
        socket.close(1000, "正常关闭");
        super.onDestroy();
    }
}

