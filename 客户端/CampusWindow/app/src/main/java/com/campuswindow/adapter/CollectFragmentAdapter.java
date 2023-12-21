package com.campuswindow.adapter;

import android.annotation.SuppressLint;
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
import com.campuswindow.interfaces.OnItemClickListener;

import java.util.List;

public class CollectFragmentAdapter extends RecyclerView.Adapter<CollectFragmentAdapter.MyViewHolder>{
    private List<Activities> collectList;
    private Context mContext;
    private View view;
    private OnItemClickListener mOnItemClickListener;

    public CollectFragmentAdapter(List<Activities> collectList, Context mContext) {
        this.collectList = collectList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CollectFragmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(mContext).inflate(R.layout.mine_collect_fragment_recycle_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull CollectFragmentAdapter.MyViewHolder holder, int position) {
        holder.name.setText(collectList.get(position).getUserName());
        holder.time.setText(collectList.get(position).getDate());
        holder.title.setText(collectList.get(position).getActivityTitle());
        Glide.with(mContext)
                .load(collectList.get(position).getAvatar())
                .circleCrop()
                .into(holder.imgHeader);
        if(collectList.get(position).getType()==0){
            holder.attribute.setText("学习");
        }else if(collectList.get(position).getType()==1){
            holder.attribute.setText("娱乐");
        }else{
            holder.attribute.setText("搭子");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mOnItemClickListener){
                    mOnItemClickListener.onItemClick(collectList,v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return collectList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgHeader;
        private TextView name,time,attribute,title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHeader = itemView.findViewById(R.id.mine_collect_fg_ry_item_img);
            name = itemView.findViewById(R.id.mine_collect_fg_ry_item_name);
            time = itemView.findViewById(R.id.mine_collect_fg_ry_item_time);
            attribute = itemView.findViewById(R.id.mine_collect_fg_item_to);
            title = itemView.findViewById(R.id.mine_collect_fg_item_title);
        }
    }

    public List<Activities> getCollectList() {
        return collectList;
    }

    public void setCollectList(List<Activities> collectList) {
        this.collectList = collectList;
    }

    public OnItemClickListener getmOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
