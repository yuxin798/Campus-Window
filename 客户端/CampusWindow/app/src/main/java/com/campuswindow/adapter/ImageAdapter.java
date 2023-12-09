package com.campuswindow.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.campuswindow.R;
import com.campuswindow.entity.ActivityImage;
import com.campuswindow.entity.CommentImage;

import java.util.Collections;
import java.util.List;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.MyViewHolder> {
    private List<ActivityImage> activityImages;
    private List<CommentImage> commentImages;
    private Context mContext;
    private View inflater;
    int type;


    public ImageAdapter(List<ActivityImage> activityImages, Context mContext) {
        this.activityImages = activityImages;
        this.mContext = mContext;
    }

    public ImageAdapter(Context mContext, List<CommentImage> commentImages) {
        this.commentImages = commentImages;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ImageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_image,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(inflater);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.MyViewHolder holder, int position) {
        if (activityImages ==null || activityImages.size() - 1 < position){
            return;
        }

        activityImages.size();
        type = activityImages.get(position).getType();

        Glide.with(mContext)
                .load(activityImages.get(position).getImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        int i = 3;
//        if(1 == type)
//            i=1;
//        else if(activityImages.size()<3 && activityImages.size()!=0)
//            i=activityImages.size();
        return i;
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.post_image);
        }
    }
}
