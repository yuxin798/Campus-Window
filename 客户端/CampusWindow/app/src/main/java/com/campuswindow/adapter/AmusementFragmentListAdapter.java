//package com.campuswindow.adapter;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.util.TypedValue;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.campuswindow.R;
//import com.campuswindow.entity.Activities;
//import com.campuswindow.entity.ActivityImage;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class AmusementFragmentListAdapter extends RecyclerView.Adapter<AmusementFragmentListAdapter.MyViewHolder> {
//    private List<Activities> amusementList;
//    private Context mContext;
//    private List<ActivityImage> activityImages;
//    private View view;
//    private OnItemClickListener mOnItemClickListener;
//    private ImageAdapter imageAdapter;
//    private OnItemChildClickListener mOnItemChildClickListener;
//
//
//    public AmusementFragmentListAdapter(List<Activities> amusementList, Context context) {
//        this.amusementList = amusementList;
//        this.mContext = context;
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        view = LayoutInflater.from(mContext).inflate(R.layout.index_amusement_fragment_item,parent,false);
//        AmusementFragmentListAdapter.MyViewHolder myViewHolder = new AmusementFragmentListAdapter.MyViewHolder(view);
//        return myViewHolder;
//    }
//
//    @SuppressLint("RecyclerView")
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.userName.setText(amusementList.get(position).getUserName());
//        holder.postTime.setText(amusementList.get(position).getDate());
//
//        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, mContext.getResources().getDisplayMetrics());
//        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, mContext.getResources().getDisplayMetrics());
//
//        Glide.with(mContext)
//                .load(amusementList.get(position).getAvatar())
//                .override(width,height)
//                .circleCrop()
//                .into(holder.userImage);
//        holder.postTitle.setText(amusementList.get(position).getActivityTitle());
//        //
//        activityImages = new ArrayList<>();
//        activityImages = amusementList.get(position).getActivityImages();
//        System.out.println("zhuangjm"+activityImages.size());
//        imageAdapter = new ImageAdapter(activityImages,mContext);
//
//        holder.cbthumbsup.setChecked(amusementList.get(position).isLoved());
//        holder.cbCollect.setChecked(amusementList.get(position).isCollected());
//
//        holder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
//        holder.recyclerView.setAdapter(imageAdapter);
//        if(activityImages.size()>3){
//            Glide.with(mContext)
//                    .load(activityImages.get(3))
//                    .thumbnail(0.2f)
//                    .override(50,50)
//                    .into(holder.overImage);
//            holder.overText.setText("+");
//        }
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(null != mOnItemClickListener){
//                    mOnItemClickListener.onItemClick(v,position);
//                }
//            }
//        });
//        holder.cbthumbsup.setOnCheckedChangeListener((view, isChecked) -> {
//            Activities academicActivity = amusementList.get(position);
//            if(isChecked){
//                academicActivity.setLove(academicActivity.getLove() + 1);
//            } else {
//                amusementList.get(position).setLove(amusementList.get(position).getLove() - 1);
//                if (academicActivity.getLove() == 0) {
//                    holder.thumbsupNum.setText("推荐");
//                    return;
//                }
//            }
//            holder.thumbsupNum.setText("" + academicActivity.getLove());
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return amusementList.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder{
//        private TextView userName, overText,  postTitle, postTime;
//        private TextView thumbsupNum, commentsNum, collectNum;
//        private ImageView userImage ,overImage;
//        private RecyclerView recyclerView;
//        private Button cbComments;
//        private CheckBox cbthumbsup,  cbCollect;
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            userName = itemView.findViewById(R.id.user_name);
//            userImage = itemView.findViewById(R.id.image_user);
//            postTime = itemView.findViewById(R.id.post_time);
//            postTitle = itemView.findViewById(R.id.post_title);
//            recyclerView = itemView.findViewById(R.id.rcv_image);
//            overText = itemView.findViewById(R.id.tv_overmuch);
//            overImage =itemView.findViewById(R.id.iv_overmuch);
//            //
//            cbthumbsup = itemView.findViewById(R.id.bt_thumbsup_post);
//            cbComments = itemView.findViewById(R.id.bt_comments_post);
//            cbCollect =  itemView.findViewById(R.id.bt_collect_post);
//            thumbsupNum = itemView.findViewById(R.id.thumbsup_num_post);
//            commentsNum = itemView.findViewById(R.id.comments_num_post);
//            collectNum = itemView.findViewById(R.id.collect_num_post);
//        }
//    }
//
//    //点击事件接口
//    public interface OnItemClickListener {
//        public void onItemClick(View view,int position);
//    }
//    public interface OnItemChildClickListener {
//        void onItemChildClick(View view, int position, String type);
//    }
//    //get and set
//    public void setOnItemClickListener(AmusementFragmentListAdapter.OnItemClickListener onItemClickListener) {
//        this.mOnItemClickListener = onItemClickListener;
//    }
//    public void setmOnItemChildClickListener(AmusementFragmentListAdapter.OnItemChildClickListener mOnItemChildClickListener) {
//        this.mOnItemChildClickListener = mOnItemChildClickListener;
//    }
//}
