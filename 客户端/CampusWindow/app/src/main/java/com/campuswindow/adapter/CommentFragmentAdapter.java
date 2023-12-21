package com.campuswindow.adapter;

import android.content.Context;
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
import com.campuswindow.entity.CommentImage;
import com.campuswindow.entity.CommentUserVo;
import com.campuswindow.entity.User;
import com.campuswindow.interfaces.OnCommentItemClickListener;

import java.util.List;

public class CommentFragmentAdapter extends RecyclerView.Adapter<CommentFragmentAdapter.MyViewHolder> {
    private List<CommentUserVo> commentList;
    private List<CommentImage> commentImages;
    private User user;
    private Context mContext;
    private View view;
    private OnCommentItemClickListener mOnCommentItemClickListener;
    private ImageAdapter imageAdapter;

    public CommentFragmentAdapter(List<CommentUserVo> commentList, Context mContext) {
        this.commentList = commentList;
        this.mContext = mContext;
    }

    public CommentFragmentAdapter(List<CommentUserVo> commentList, User user, Context mContext) {
        this.commentList = commentList;
        this.user = user;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.mine_comment_fragment_recycle_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //加载资源:
        holder.time.setText(commentList.get(position).getSendTime()+"");
        holder.content.setText(commentList.get(position).getContent());
        holder.title.setText("主贴:" + commentList.get(position).getActivityTitle());
        holder.name.setText(user.getUserName());
        Glide.with(mContext)
                .load(user.getAvatar())
                .circleCrop()
                .into(holder.imgHeader);

        //TODO 评论的加载图片
        imageAdapter = new ImageAdapter(mContext,commentImages);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView.setAdapter(imageAdapter);
//        Glide.with(mContext)
//                .load(commentImages.get(3))
//                .thumbnail(0.2f)
//                .override(50,50)
//                .into(holder.overImage);
    }


    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgHeader;
        private TextView name,time,content,title;
        private RecyclerView recyclerView;
        private ImageView overImage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHeader = itemView.findViewById(R.id.mine_comment_fg_ry_item_img);
            name = itemView.findViewById(R.id.mine_comment_fg_ry_item_name);
            time = itemView.findViewById(R.id.mine_comment_fg_ry_item_time);
            content = itemView.findViewById(R.id.mine_comment_fg_ry_item_content);
            title = itemView.findViewById(R.id.mine_comment_fg_ry_item_title);
            recyclerView = itemView.findViewById(R.id.rcv_image);
            overImage = itemView.findViewById(R.id.iv_overmuch);
        }
    }

    public void setOnItemClickListener(OnCommentItemClickListener onItemClickListener) {
        this.mOnCommentItemClickListener = onItemClickListener;
    }


    public List<CommentUserVo> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentUserVo> commentList) {
        this.commentList = commentList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
