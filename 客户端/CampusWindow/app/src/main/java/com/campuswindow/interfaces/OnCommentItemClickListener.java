package com.campuswindow.interfaces;

import android.view.View;

import com.campuswindow.entity.CommentUserVo;

import java.util.List;

public interface OnCommentItemClickListener {
    void onItemClick(List<CommentUserVo> commentList, View view, int position);
}
