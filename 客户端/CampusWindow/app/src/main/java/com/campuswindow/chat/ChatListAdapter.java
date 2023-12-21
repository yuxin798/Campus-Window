package com.campuswindow.chat;

import android.content.Context;
import android.content.DialogInterface;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.campuswindow.R;
import com.campuswindow.Result;
import com.campuswindow.constant.UserConstant;
import com.campuswindow.entity.ChatListVo;
import com.campuswindow.interfaces.OnChatItemClickListener;
import com.campuswindow.server.API;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    private List<ChatListVo> chatLists = new ArrayList<>();
    private Context context;
    private OnChatItemClickListener onChatItemClickListener;

    public void setChatLists(List<ChatListVo> chatLists) {
        this.chatLists = chatLists;
    }

    public ChatListAdapter() {
    }

    public ChatListAdapter(List<ChatListVo> chatLists) {
        this.chatLists = chatLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载每一个Chat_list的Item布局
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chat_room_fragment_item, null);
        //获取上下文环境
        context = parent.getContext();
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //根据下标，获取每一个ChatList
        ChatListVo chatList = chatLists.get(position);
        //设置头像、时间
        holder.name.setText(chatList.getName());
        System.out.println(chatList.getLastMsgTime());

        //获取日期差，并显示出来:
        holder.time.setText(getDateLag(chatList.getLastMsgTime()));
        //设置最后一次发送的消息
        holder.msg.setText(chatList.getLastMsg());
        holder.unread.setText(chatList.getUnread() + "");
        //设置图片宽高
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 75, context.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 75, context.getResources().getDisplayMetrics());
        //设置对象头像
        Glide.with(context)
                .load(chatList.getAvatar())
                .circleCrop()
                .override(width, height)
                .into(holder.img);


        //设置RecyclerView的点击事件监听器
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onChatItemClickListener != null){
                    onChatItemClickListener.onItemClick(chatLists, v, holder.getAdapterPosition());
                }
            }
        });
        //设置RecyclerView的长按事件
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext())
                        .setTitle("提示")
                        .setMessage("你确定要删除该联系人吗")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               //发送请求，更改与用户的状态
                                updateChatListStatus(chatList.getLinkId(), UserConstant.USER_ID, holder.getAdapterPosition());
                            }
                        })
                        .setNegativeButton("取消", null);
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });
    }

    //获取时间差，判断显示日期
    private String getDateLag(Date date) {
        Date currentDate = new Date(); // 当前日期时间
        Date lastMsgTime = date; // 获取Date类型的数据（最后一条消息时间）

        // 计算日期差
        Calendar currentCal = Calendar.getInstance();
        currentCal.setTime(currentDate);
        int currentDay = currentCal.get(Calendar.DAY_OF_YEAR);
        int currentYear = currentCal.get(Calendar.YEAR);

        Calendar lastMsgCal = Calendar.getInstance();
        lastMsgCal.setTime(lastMsgTime);
        int lastMsgDay = lastMsgCal.get(Calendar.DAY_OF_YEAR);
        int lastMsgYear = lastMsgCal.get(Calendar.YEAR);

        int dayDiff = currentDay - lastMsgDay;
        int yearDiff = currentYear - lastMsgYear;

        // 定义日期格式
        SimpleDateFormat sdf;
        SimpleDateFormat chineseSdf ;

        // 根据日期差显示不同的文本
        if (dayDiff < 1) {
            // 小于一天，显示时分
            sdf = new SimpleDateFormat("HH:mm");
            return sdf.format(lastMsgTime);
        } else if (dayDiff >= 1 && dayDiff < 2) {
            // 大于等于一天但小于两天，显示昨天
            return "昨天";
        } else if (dayDiff >= 2 && dayDiff < 7) {
            // 大于两天但小于一周，显示星期几
            chineseSdf = new SimpleDateFormat("E", Locale.CHINESE);
            return chineseSdf.format(lastMsgTime);
        } else if (yearDiff == 0) {
            // 大于一周，显示年月日
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(lastMsgTime);
        } else {
            // 大于一年
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(lastMsgTime);
        }
    }

    private void updateChatListStatus(String linkId, String toUserId, int position) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request build = new Request.Builder()
                        .get()
                        .url(API.UPDATE_CHAT_LIST_STATUS + "/" + linkId + "/" + toUserId)
                        .build();
                Call call = client.newCall(build);
                try {
                    Response response = call.execute();
                    Gson gson = new Gson();
                    Result result = gson.fromJson(response.body().string(), Result.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
        chatLists.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return chatLists.size();
    }

    public void setOnItemClickListener(OnChatItemClickListener onItemClickListener) {
        this.onChatItemClickListener = onItemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView name;
        private TextView time;
        private TextView msg;
        private TextView unread;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.tv_name);
            time = itemView.findViewById(R.id.tv_time);
            msg = itemView.findViewById(R.id.tv_msg);
            unread = itemView.findViewById(R.id.tv_unread);
        }
    }
}
