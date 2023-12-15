package com.campuswindow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.campuswindow.R;
import com.campuswindow.entity.Activities;

import java.util.List;

public class CommentFragmentAdapter extends RecyclerView.Adapter<CommentFragmentAdapter.MyViewHolder> {
    private List<Activities> commentList;
    private Context mContext;
    private View view;
    private AcademicFragmentListAdapter.OnItemClickListener mOnItemClickListener;

    public CommentFragmentAdapter(List<Activities> commentList, Context mContext) {
        this.commentList = commentList;
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
        holder.name.setText(commentList.get(position).getUserName());
        holder.time.setText(commentList.get(position).getDate());
        Glide.with(mContext)
                .load(commentList.get(position).getAvatar())
                .circleCrop()
                .into(holder.imgHeader);
        
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgHeader;
        private TextView name,time,content,title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHeader = itemView.findViewById(R.id.mine_comment_fg_ry_item_img);
            name = itemView.findViewById(R.id.mine_comment_fg_ry_item_name);
            time = itemView.findViewById(R.id.mine_comment_fg_ry_item_time);
            content = itemView.findViewById(R.id.mine_comment_fg_ry_item_content);
            title = itemView.findViewById(R.id.mine_comment_fg_ry_item_title);
        }
    }
}
