package com.campuswindow.interfaces;

import android.view.View;

import com.campuswindow.entity.Activities;

import java.util.List;

public interface OnItemClickListener {
    void onItemClick(List<Activities> activitiesList, View view, int position);
}
