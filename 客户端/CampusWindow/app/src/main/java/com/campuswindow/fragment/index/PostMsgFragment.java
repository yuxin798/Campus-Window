package com.campuswindow.fragment.index;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.campuswindow.R;
import com.campuswindow.richeditor.PostMsgActivity;

public class PostMsgFragment extends Fragment {
    private View view;
    private ImageView postImg;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.activity_post_msg_fragment,null);
        getViews(page);
        postImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PostMsgActivity.class);
                startActivity(intent);
            }
        });
        return page;
    }

    private void getViews(View page) {
        view = page.findViewById(R.id.post_msg_fg_view);
        postImg = page.findViewById(R.id.post_msg_fg_img);
    }

}
