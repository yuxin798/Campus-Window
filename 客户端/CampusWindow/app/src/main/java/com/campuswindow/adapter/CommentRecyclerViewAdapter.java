package com.campuswindow.adapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.campuswindow.R;
import com.campuswindow.entity.Activities;
import com.campuswindow.entity.Comment;
import java.util.List;

public class CommentRecyclerViewAdapter extends RecyclerView.Adapter<CommentRecyclerViewAdapter.MyViewHolder> {
    private List<Comment> commentList;
    private View inflater;
    private Activity mContext;
    private ImageAdapter imageAdapter;

    public CommentRecyclerViewAdapter(List<Comment> commentList, Activity mContext) {
        this.commentList = commentList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(mContext).inflate(R.layout.activity_comment_interface,parent, false);
        return new MyViewHolder(inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.commentContext.setText(commentList.get(position).getContent());
        // TODO 使用用户id访问服务端接口获取用户头像，名字，或者获取用户实体类
        holder.userName.setText(commentList.get(position).getUserName());
//        holder.postTime.setText(commentList.get(position).getSendTime().toString());
//        Glide.with(mContext).load(commentList.get(position).getAvatar()).into(holder.userImage);
//        Glide.with(mContext).load(commentList.get(position).getCommentImages());


        imageAdapter = new ImageAdapter(mContext,commentList.get(position).getCommentImages());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView.setAdapter(imageAdapter);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView userName, postTime, commentContext;
        private ImageView userImage;
        private RecyclerView recyclerView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userImage = itemView.findViewById(R.id.comment_userimage);
            userName = itemView.findViewById(R.id.cm_username);
            postTime = itemView.findViewById(R.id.cm_posttime);
            commentContext = itemView.findViewById(R.id.cm_context);
            recyclerView = itemView.findViewById(R.id.rcv_comment_image);

        }
    }
}
