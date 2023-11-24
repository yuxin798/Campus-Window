package com.campuswindow.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class HomeAdapter extends FragmentStateAdapter {
    private List<Fragment> fragments;

    public HomeAdapter(List<Fragment> fragment,@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        this.fragments = fragment;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
