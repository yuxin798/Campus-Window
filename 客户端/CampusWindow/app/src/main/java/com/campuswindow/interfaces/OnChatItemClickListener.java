package com.campuswindow.interfaces;

import android.view.View;

import com.campuswindow.chat.ChatList;

import java.util.List;

public interface OnChatItemClickListener {
    void onItemClick(List<ChatList> chatLists, View view, int position);
}
