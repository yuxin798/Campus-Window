package com.campuswindow.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.campuswindow.R;
import com.campuswindow.entity.Activities;
import com.campuswindow.interfaces.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class SearchPostAdapter extends RecyclerView.Adapter<SearchPostAdapter.ViewHolder>{
    private List<Activities> activitiesList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }

    public SearchPostAdapter() {
    }

    public void setActivitiesList(List<Activities> activitiesList) {
        this.activitiesList = activitiesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_post_recycle_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchPostAdapter.ViewHolder holder, int position) {
        Activities activities = activitiesList.get(position);
        holder.name.setText(activities.getUserName());
        holder.title.setText(activities.getActivityTitle());
        holder.time.setText(activities.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(activitiesList, v, holder.getAdapterPosition());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return activitiesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name,title,time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            title = itemView.findViewById(R.id.item_txt);
            time = itemView.findViewById(R.id.item_txt2);
        }
    }

}
