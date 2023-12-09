//package com.campuswindow.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.campuswindow.R;
//import com.campuswindow.entity.User;
//
//import java.util.List;
//
//public class ListChatRoomAdapter extends BaseAdapter {
//    private Context context;
//    private int layoutId;
//    private List<User> userList;
//    public ListChatRoomAdapter(Context context,int layoutId,List<User> userList) {
//        this.context = context;
//        this.layoutId = layoutId;
//        this.userList = userList;
//    }
//
//    @Override
//    public int getCount() {
//        return userList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return userList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if(convertView == null){
//            convertView = LayoutInflater.from(context).inflate(layoutId,null);
//        }
//        ImageView imageView = convertView.findViewById(R.id.chat_room_img);
//        TextView chatName = convertView.findViewById(R.id.chat_room_name);
//        TextView chatContent = convertView.findViewById(R.id.chat_room_last_content);
//        User user = userList.get(position);
//        //TODO 设置头像和聊天内容
//        chatName.setText(user.getUserName());
//        return convertView;
//    }
//}
