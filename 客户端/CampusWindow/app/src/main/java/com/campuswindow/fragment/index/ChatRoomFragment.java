package com.campuswindow.fragment.index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.campuswindow.R;
import com.campuswindow.adapter.ChatRoomListAdapter;
import com.campuswindow.entity.User;

import java.util.ArrayList;
import java.util.List;

//ctrl + p 方法参数提示信息
public class ChatRoomFragment extends Fragment {
    private ImageView chatImg;
    private TextView chatName,chatState;
    private ListView chatList;
    private List<User> friend = new ArrayList<>();
    private ChatRoomListAdapter chatRoomListAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.activity_chat_room_fragment,null);
        getViews(page);
        chatRoomListAdapter = new ChatRoomListAdapter(friend,R.layout.activity_chat_room_fragment_item,getContext());
        chatList.setAdapter(chatRoomListAdapter);
        return page;
    }

    private void getViews(View page) {
        chatImg = page.findViewById(R.id.chat_room_img);
        chatName = page.findViewById(R.id.chat_room_name);
        chatState = page.findViewById(R.id.chat_room_state);
        chatList = page.findViewById(R.id.chat_room_lv);
    }
}
