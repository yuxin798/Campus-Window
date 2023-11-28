package com.campuswindow.fragment.index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.campuswindow.R;
import com.campuswindow.adapter.ChatRoomListAdapter;
import com.campuswindow.entity.User;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

//ctrl + p 方法参数提示信息
public class ChatRoomFragment extends Fragment {
    private ImageView chatImg, chatBtnImg;
    private TextView chatName, chatState;
    private ListView chatList;
    private List<User> friend = new ArrayList<>();
    private ChatRoomListAdapter chatRoomListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.activity_chat_room_fragment, null);
        getViews(page);
        listenerMethods();
        chatRoomListAdapter = new ChatRoomListAdapter(friend, R.layout.activity_chat_room_fragment_item, getContext());
        chatList.setAdapter(chatRoomListAdapter);
        return page;
    }

    private void listenerMethods() {
        chatBtnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getActivity(),chatBtnImg);
                popupMenu.getMenuInflater().inflate(R.menu.chat_room_option_menu,popupMenu.getMenu());
                
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.menu1:
                                Toast.makeText(getActivity(), "点击了第" + 1 + "个", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.menu2:
                                Toast.makeText(getActivity(), "点击了第" + 2 + "个", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.menu3:
                                Toast.makeText(getActivity(), "点击了第" + 3 + "个", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.menu4:
                                Toast.makeText(getActivity(), "点击了第" + 4 + "个", Toast.LENGTH_SHORT).show();
                                return true;
                            case R.id.menu5:
                                Toast.makeText(getActivity(), "点击了第" + 5 + "个", Toast.LENGTH_SHORT).show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });
    }

    private void getViews(View page) {
        chatImg = page.findViewById(R.id.chat_room_img);
        chatName = page.findViewById(R.id.chat_room_name);
        chatState = page.findViewById(R.id.chat_room_state);
        chatList = page.findViewById(R.id.chat_room_lv);
        chatBtnImg = page.findViewById(R.id.chat_room_btn_img);
    }


}

