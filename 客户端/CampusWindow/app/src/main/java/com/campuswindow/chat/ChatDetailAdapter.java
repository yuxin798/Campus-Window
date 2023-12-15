package com.campuswindow.chat;

import android.content.Context;
import android.net.Uri;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.campuswindow.R;

import java.util.List;

public class ChatDetailAdapter extends RecyclerView.Adapter<ChatDetailAdapter.ViewHolder> {

    private Context context;
    private List<ChatMessage> chatMessages;
    private String fromUserId;
    private String toUserName;
    private String toUserAvatar;

    private String fromUserName;
    private String fromUserAvatar;
    public void setChatMessages(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public ChatDetailAdapter(List<ChatMessage> chatMessages, String fromUserId, String toUserName, String toUserAvatar, String fromUserName, String fromUserAvatar) {
        this.chatMessages = chatMessages;
        this.fromUserId = fromUserId;
        this.toUserName = toUserName;
        this.toUserAvatar = toUserAvatar;
        this.fromUserName = fromUserName;
        this.fromUserAvatar =fromUserAvatar;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载每一个Chat_detail的Item布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_detail_room_info, null);
        //获取上下文环境
        context = parent.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //根据下标，获取每一个ChatMessage对象
        ChatMessage chatMessage = chatMessages.get(position);
        //设置图片宽高
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, context.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60, context.getResources().getDisplayMetrics());
        //判断是否是自己发的信息，还是对象发的信息
        if (fromUserId.equals(chatMessage.getFromUserId())) {//发送消息
            //自己的控件生效，对象的控件失效
            holder.mRelativeLayoutReceive.setVisibility(View.GONE);
            holder.mRelativeLayoutSend.setVisibility(View.VISIBLE);
            //加载自己的头像
            //TODO 此处显示的用户头像不对！现在用具体的地址代替。
            Glide.with(context)
                    .load(Uri.parse("http://192.168.144.132:9000/campus-bucket/default.jpg"))
                    .override(width, height)
                    .circleCrop()
                    .into(holder.mImageViewSend);
            //加载自己的名字
//            holder.mTextViewNameSend.setText(fromUserName);
            //加载自己发送的内容
            holder.mTextViewContentSend.setText(chatMessage.getContent());
        }else {
            //对象的控件生效，自己的控件失效
            holder.mRelativeLayoutReceive.setVisibility(View.VISIBLE);
            holder.mRelativeLayoutSend.setVisibility(View.GONE);
            //加载对面用户的头像
            Glide.with(context)
                    .load(Uri.parse(toUserAvatar))
                    .override(width, height)
                    .circleCrop()
                    .into(holder.mImageViewReceive);
            //加载对面用户的名字
            holder.mTextViewNameReceive.setText(toUserName);
            //加载对面用户的内容
            holder.mTextViewContentReceive.setText(chatMessage.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //绑定所有视图
        RelativeLayout mRelativeLayoutSend,mRelativeLayoutReceive;
        ImageView mImageViewReceive,mImageViewSend;
        TextView mTextViewNameReceive,mTextViewNameSend,mTextViewContentReceive,mTextViewContentSend;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mRelativeLayoutReceive = itemView.findViewById(R.id.receive_relative);
            mRelativeLayoutSend = itemView.findViewById(R.id.send_relative);

            mImageViewReceive = itemView.findViewById(R.id.image_receive);
            mImageViewSend = itemView.findViewById(R.id.image_send);

            mTextViewNameReceive = itemView.findViewById(R.id.text_name_receive);
//            mTextViewNameSend = itemView.findViewById(R.id.text_name_send);
            mTextViewContentReceive = itemView.findViewById(R.id.text_content_receive);
            mTextViewContentSend = itemView.findViewById(R.id.text_content_send);
        }
    }
}
