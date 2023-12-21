package com.campuswindow.fragment.person;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.campuswindow.R;
import com.campuswindow.adapter.CollectFragmentAdapter;
import com.campuswindow.entity.Activities;

import java.util.ArrayList;
import java.util.List;

public class PersonCollectFragment extends Fragment {
    private RecyclerView collectRy;
    private CollectFragmentAdapter collectFragmentAdapter;
    private Handler handler;
    private List<Activities> collectList = new ArrayList<>();

    public PersonCollectFragment() {
        handler = new Handler(Looper.getMainLooper());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.mine_collect_fragment,null);
        collectRy = page.findViewById(R.id.mine_collect_fg_ry);
        getCollectList();
        return page;
    }

    private void getCollectList() {
    }
}
