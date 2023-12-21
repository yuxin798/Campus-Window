package com.campuswindow.interfaces;

import android.view.View;

import com.campuswindow.entity.ChatListVo;

import java.util.List;

public interface OnChatItemClickListener {
    void onItemClick(List<ChatListVo> chatLists, View view, int position);
}
