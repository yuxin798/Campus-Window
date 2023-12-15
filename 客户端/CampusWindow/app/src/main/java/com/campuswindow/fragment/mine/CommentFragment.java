package com.campuswindow.fragment.mine;

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
import com.campuswindow.adapter.CommentFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class CommentFragment extends Fragment {
    private RecyclerView commentRy;
    private CommentFragmentAdapter commentFragmentAdapter;
    private List<?> commentList = new ArrayList<>();
    private Handler handler;
    private CommentFragment(){
        handler = new Handler(Looper.getMainLooper());
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.mine_all_fragment,null);
        commentRy = page.findViewById(R.id.mine_all_fg_ry);
        getCommentList();
        return page;
    }

    private void getCommentList() {
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }
}
