package com.campuswindow.adapter;

import android.content.Context;
import android.util.Log;
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

import java.util.List;

import jp.wasabeef.richeditor.RichEditor;


public class ImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_IMAGE = 0;
    private static final int TYPE_VIDEO = 1;
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        inflater = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_image,parent,false);
        LayoutInflater inflater1 = LayoutInflater.from(parent.getContext());
        if(viewType == TYPE_VIDEO){
            inflater = inflater1.inflate(R.layout.post_video,parent,false);
            return new VideoViewHolder(inflater);
        }else{
            inflater = inflater1.inflate(R.layout.post_image,parent,false);
            return new ImageViewHolder(inflater);
        }

//        MyViewHolder myViewHolder = new MyViewHolder(inflater);
//        return myViewHolder;
    }

//    @Override
//    public void onBindViewHolder(@NonNull ImageAdapter.MyViewHolder holder, int position) {
//        if (activityImages ==null || activityImages.size() - 1 < position){
//            return;
//        }
//
//        activityImages.size();
//        type = activityImages.get(position).getType();
//        Log.i("type",type+"");
//        if(type == 1){
//            Log.i("video66666",activityImages.get(position).getImage());
//            Log.i("video66666",Uri.parse(activityImages.get(position).getImage())+"");
//            holder.videoView.setVideoURI(Uri.parse(activityImages.get(position).getImage()));
//
//            MediaController mediaController = new MediaController(mContext);
//            holder.videoView.setMediaController(mediaController);
//            holder.videoView.requestFocus();
//            //            holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
////                @Override
////                public void onPrepared(MediaPlayer mp) {
////                    mp.start();
////                }
////            });
//        }else{
//            Glide.with(mContext)
//                    .load(activityImages.get(position).getImage())
//                    .into(holder.imageView);
//        }
//    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (activityImages ==null || activityImages.size() - 1 < position){
            return;
        }

        activityImages.size();
        type = activityImages.get(position).getType();
        Log.i("type",type+"");


        if (holder instanceof ImageViewHolder) {
            // 处理图片的ViewHolder
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            // 加载图片到ImageView
            if(activityImages.size()==1){
//                WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
//                DisplayMetrics displayMetrics = new DisplayMetrics();
//                wm.getDefaultDisplay().getMetrics(displayMetrics);
//                int width = displayMetrics.widthPixels;
//                int height = displayMetrics.heightPixels;
                Glide.with(imageViewHolder.imageView.getContext())
                        .load(activityImages.get(position).getImage())
                        .override(30, 30)
                        .into(imageViewHolder.imageView);
            }else {
                Glide.with(imageViewHolder.imageView.getContext())
                        .load(activityImages.get(position).getImage())
                        .into(imageViewHolder.imageView);
            }

        } else if (holder instanceof VideoViewHolder) {
            // 处理视频的ViewHolder
            VideoViewHolder videoViewHolder = (VideoViewHolder) holder;
//            // 设置视频播放路径
//            videoViewHolder.videoView.setVideoURI(Uri.parse(activityImages.get(position).getImage()));
//            // 开始播放视频
//            MediaController mediaController = new MediaController(mContext);
//            videoViewHolder.videoView.setMediaController(mediaController);
//            videoViewHolder.videoView.seekTo(1);
//            videoViewHolder.videoView.requestFocus();
//            videoViewHolder.videoView.start();

            videoViewHolder.richEditor.setEditorHeight(100);
            videoViewHolder.richEditor.setPadding(10,10,10,10);
            videoViewHolder.richEditor.setHtml("<video src=\"" + activityImages.get(position).getImage()  + "#t=0.01\" preload=“metadata” " + "\" style=\"max-width:100%\" controls=\"\"></video><br>");
        }
    }

    public int getItemViewType(int position) {
        if (activityImages ==null || activityImages.size() - 1 < position){
            return 0;
        }
        if (activityImages.get(position).getType()==1) {
            return TYPE_VIDEO;
        } else if (activityImages.get(position).getType() ==0) {
            return TYPE_IMAGE;
        }
        return super.getItemViewType(position);
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

//    class MyViewHolder extends RecyclerView.ViewHolder {
//        private ImageView imageView;
//        private VideoView videoView;
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.post_image);
//            videoView = itemView.findViewById(R.id.post_video);
//        }
//    }


    static class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ImageViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.post_image);
        }
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {
//        VideoView videoView;
        RichEditor richEditor;
        VideoViewHolder(View itemView) {
            super(itemView);
//            videoView = itemView.findViewById(R.id.post_video);
            richEditor = itemView.findViewById(R.id.post_video);
        }
    }
}
