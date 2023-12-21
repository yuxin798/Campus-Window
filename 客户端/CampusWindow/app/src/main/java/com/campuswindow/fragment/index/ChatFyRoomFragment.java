package com.campuswindow.fragment.index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.campuswindow.R;

public class ChatFyRoomFragment extends Fragment {
    private RecyclerView chatFyRoomRy;
    private ImageView imgHeader;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.activity_chat_fy_room_fragment,null);
        getViews();
        return page;
    }

    private void getViews() {

    }
}
