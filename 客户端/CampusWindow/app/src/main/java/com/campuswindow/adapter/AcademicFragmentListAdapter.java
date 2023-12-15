package com.campuswindow.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.campuswindow.R;
import com.campuswindow.Result;
import com.campuswindow.constant.UserConstant;
import com.campuswindow.entity.Activities;
import com.campuswindow.entity.ActivityImage;
import com.campuswindow.entity.ActivityLove;
import com.campuswindow.server.API;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AcademicFragmentListAdapter extends RecyclerView.Adapter<AcademicFragmentListAdapter.MyViewHolder> {
    private List<Activities> academicList;
    private Context mContext;
    private List<ActivityImage> activityImages;
    private View view;
    private OnItemClickListener mOnItemClickListener;
    private ImageAdapter imageAdapter;
    private Result result;
    private OnItemChildClickListener mOnItemChildClickListener;
//    private Handler handler;
//
//    public AcademicFragmentListAdapter() {
//        handler = new Handler(Looper.getMainLooper());
//    }

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

        //
        System.out.println("55555555555555555"+academicList.get(position).isLoved());
        holder.cbthumbsup.setChecked(academicList.get(position).isLoved());

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

        holder.cbthumbsup.setOnCheckedChangeListener((view, isChecked) -> {
            Activities academicActivity = academicList.get(position);
            if(isChecked){
                academicActivity.setLove(academicActivity.getLove() + 1);
                holder.cbthumbsup.setChecked(isChecked);
                academicActivity.setLoved(isChecked);
                ActivityLove activityLove = new ActivityLove(UserConstant.USER_ID,academicActivity.getActivityId());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();
                        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8")
                                ,new Gson().toJson(activityLove));
                        Request request = new Request.Builder()
                                .url(API.SERVER_URL+API.ADD_LOVE_ACTIVITY)
                                .post(body).build();
                        try {
                            Response execute = client.newCall(request).execute();
                            Response response = client.newCall(request).execute();
                            String string = response.body().string();
                            Result result = new Gson().fromJson(string, Result.class);

                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
            } else {
                academicList.get(position).setLove(academicList.get(position).getLove() - 1);
                holder.cbthumbsup.setChecked(isChecked);
                academicActivity.setLoved(isChecked);
                ActivityLove activityLove = new ActivityLove(UserConstant.USER_ID,academicActivity.getActivityId());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        OkHttpClient client = new OkHttpClient();
                        RequestBody body = RequestBody.create(MediaType.parse("application/json;charset=utf-8")
                                ,new Gson().toJson(activityLove));
                        Request request = new Request.Builder()
                                .url(API.SERVER_URL+API.DECREASE_LOVE_ACTIVITY)
                                .post(body).build();
                        try {
                            Response execute = client.newCall(request).execute();
                            Response response = client.newCall(request).execute();
                            String string = response.body().string();
                             Result result = new Gson().fromJson(string, Result.class);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }).start();
                if (academicActivity.getLove() == 0) {
                    holder.thumbsupNum.setText("推荐");
                    return;
                }
            }
//            Gson gson = new Gson();
//            Activities activities = gson.fromJson(gson.toJson(result.getData()),Activities.class);
//            academicList.get(position).setLoved(activities.isLoved());
//            holder.thumbsupNum.setText("" + academicActivity.getLove());
//            holder.cbthumbsup.setChecked(activities.isLoved());
        });


    }


    @Override
    public int getItemCount() {
        return academicList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView userName, overText,  postTitle, postTime;
        private TextView thumbsupNum, commentsNum, collectNum;
        private ImageView userImage ,overImage;
        private RecyclerView recyclerView;
        private Button cbComments;
        private CheckBox cbthumbsup,  cbCollect;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name);
            userImage = itemView.findViewById(R.id.image_user);
            postTime = itemView.findViewById(R.id.post_time);
            postTitle = itemView.findViewById(R.id.post_title);
            recyclerView = itemView.findViewById(R.id.rcv_image);
            overText = itemView.findViewById(R.id.tv_overmuch);
            overImage =itemView.findViewById(R.id.iv_overmuch);
            //
            cbthumbsup = itemView.findViewById(R.id.bt_thumbsup_post);
            cbComments = itemView.findViewById(R.id.bt_comments_post);
            cbCollect =  itemView.findViewById(R.id.bt_collect_post);
            thumbsupNum = itemView.findViewById(R.id.thumbsup_num_post);
            commentsNum = itemView.findViewById(R.id.comments_num_post);
            collectNum = itemView.findViewById(R.id.collect_num_post);
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

    public List<Activities> getAcademicList() {
        return academicList;
    }

    public void setAcademicList(List<Activities> academicList) {
        this.academicList = academicList;
    }

    public List<ActivityImage> getActivityImages() {
        return activityImages;
    }

    public void setActivityImages(List<ActivityImage> activityImages) {
        this.activityImages = activityImages;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
