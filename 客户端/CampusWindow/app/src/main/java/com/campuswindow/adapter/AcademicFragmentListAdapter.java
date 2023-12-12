package com.campuswindow.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.TypedValue;
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
import com.campuswindow.entity.ActivityImage;

import java.util.ArrayList;
import java.util.List;

public class AcademicFragmentListAdapter extends RecyclerView.Adapter<AcademicFragmentListAdapter.MyViewHolder> {
    private List<Activities> academicList;

    private Context mContext;
    private List<ActivityImage> activityImages;
    private View view;
    private OnItemClickListener mOnItemClickListener;
    private ImageAdapter imageAdapter;


    private OnItemChildClickListener mOnItemChildClickListener;


    public AcademicFragmentListAdapter(List<Activities> academicList, Context mContext) {
        this.academicList = academicList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view =LayoutInflater.from(mContext).inflate(R.layout.index_academic_fragment_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.userName.setText(academicList.get(position).getUserName());
        holder.postTime.setText(academicList.get(position).getDate());

        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, mContext.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, mContext.getResources().getDisplayMetrics());

        Glide.with(mContext)
                .load(academicList.get(position).getAvatar())
                .override(width,height)
                .circleCrop()
                .into(holder.userImage);
        holder.postTitle.setText(academicList.get(position).getActivityTitle());

        //加载图片:
        activityImages = new ArrayList<>();
        activityImages = academicList.get(position).getActivityImages();
        System.out.println("zhuangjm"+activityImages.size());

        imageAdapter = new ImageAdapter(activityImages,mContext);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView.setAdapter(imageAdapter);

        if(activityImages.size()>3){
            Glide.with(mContext)
                    .load(activityImages.get(3))
                    .thumbnail(0.2f)
                    .override(50,50)
                    .into(holder.overImage);
            holder.overText.setText("+");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null != mOnItemClickListener){
                    mOnItemClickListener.onItemClick(v,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return academicList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView userName,overText;
        private TextView postTime;
        private ImageView userImage ,overImage;
        private TextView postTitle;
        private RecyclerView recyclerView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name);
            userImage = itemView.findViewById(R.id.image_user);
            postTime = itemView.findViewById(R.id.post_time);
            postTitle = itemView.findViewById(R.id.post_title);
            recyclerView = itemView.findViewById(R.id.rcv_image);
            overText = itemView.findViewById(R.id.tv_overmuch);
            overImage =itemView.findViewById(R.id.iv_overmuch);
        }
    }


    //点击事件接口
    public interface OnItemClickListener {
        public void onItemClick(View view,int position);
    }
    public interface OnItemChildClickListener {
        void onItemChildClick(View view, int position, String type);
    }
    //get and set
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public void setmOnItemChildClickListener(OnItemChildClickListener mOnItemChildClickListener) {
        this.mOnItemChildClickListener = mOnItemChildClickListener;
    }
}
