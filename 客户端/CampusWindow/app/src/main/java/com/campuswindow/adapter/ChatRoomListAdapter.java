package com.campuswindow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.campuswindow.R;
import com.campuswindow.entity.User;

import java.util.List;

public class ChatRoomListAdapter extends BaseAdapter {
    private List<User> chatList;
    private int layoutId;
    private Context context;

    public ChatRoomListAdapter(List<User> chatList, int layoutId, Context context) {
        this.chatList = chatList;
        this.layoutId = layoutId;
        this.context = context;
    }

    @Override
    public int getCount() {
        return chatList.size();
    }

    @Override
    public Object getItem(int position) {
        return chatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(layoutId,null);
        }
        ImageView chatImg = convertView.findViewById(R.id.chat_room_img);
        TextView chatName = convertView.findViewById(R.id.chat_room_name);
        TextView chatLastContent = convertView.findViewById(R.id.chat_room_last_content);

        User user = chatList.get(position);
        //TODO  设置聊天室人物的头像和最近一次聊天内容
        chatName.setText(user.getUserName());
        return convertView;
    }
}
