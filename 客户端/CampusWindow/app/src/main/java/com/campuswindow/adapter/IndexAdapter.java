package com.campuswindow.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class IndexAdapter extends FragmentStateAdapter {

    private List<Fragment> fragmentList;

    public IndexAdapter(List<Fragment> fragments,@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragmentList = fragments;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
