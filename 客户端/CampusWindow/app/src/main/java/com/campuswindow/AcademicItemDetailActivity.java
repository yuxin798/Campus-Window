package com.campuswindow;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.campuswindow.adapter.CommentRecyclerViewAdapter;
import com.campuswindow.entity.Activities;
import com.campuswindow.entity.Comment;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.richeditor.RichEditor;

public class AcademicItemDetailActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView postTime,userName,postTitle,tvComment;
    private RichEditor richEditor;
    private Activities entertainmentActivity;
    private Button btRuturn ,btComments ;
    private CommentRecyclerViewAdapter commentRecyclerViewAdapter;
    private List<Comment> commentList = new ArrayList<>();
    //标记点赞button的状态
    private int flag = 0;
    private PopupWindow mPopWindow;
    private RecyclerView recyclerView;
    private TextView thumbsupNum,commentsNum,collectNum;
    private CheckBox cbThumbsup,cbCollect;

    private LinearLayout activityItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_academic_item_detail);
        findByView();
        entertainmentActivity = (Activities)getIntent().getSerializableExtra("data");
        Log.i("entertainmentActivity",entertainmentActivity.toString());
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getApplicationContext().getResources().getDisplayMetrics());
        Glide.with(this)
                .load(entertainmentActivity.getAvatar())
                .override(width,width)
                .circleCrop()
                .into(imageView);
        userName.setText(entertainmentActivity.getUserName());
        //postTime.setText(entertainmentActivity.getDate().toString());
        postTitle.setText(entertainmentActivity.getActivityTitle());
        System.out.println(entertainmentActivity.getActivityTitle());

        System.out.println(entertainmentActivity.getActivityContent());
        richEditor.setHtml(entertainmentActivity.getActivityContent());

        richEditor.setInputEnabled(false);

        if(entertainmentActivity.getLove()==0){
            thumbsupNum.setText("推荐");
        }else {
            thumbsupNum.setText(""+entertainmentActivity.getLove());
        }
        cbThumbsup.setChecked(entertainmentActivity.isLoved());

        cbCollect.setChecked(entertainmentActivity.isCollected());
        System.out.println(entertainmentActivity.isCollected()+"666666");

        commentRecyclerViewAdapter = new CommentRecyclerViewAdapter(commentList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(commentRecyclerViewAdapter);
        btRuturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cbThumbsup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    entertainmentActivity.setLove(entertainmentActivity.getLove() + 1);
                    entertainmentActivity.setLoved(isChecked);
                } else {
                    entertainmentActivity.setLove(entertainmentActivity.getLove() - 1);
                    entertainmentActivity.setLoved(isChecked);
                    if (entertainmentActivity.getLove() == 0) {
                        thumbsupNum.setText("推荐");
                        return;
                    }
                }
               thumbsupNum.setText("" + entertainmentActivity.getLove());
            }
        });
        /*评论功能*/
        tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();

            }
        });
        //设置点击事件，跳转到发帖人的主页面
        activityItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcademicItemDetailActivity.this,ClickPostedUserActivity.class);
                intent.putExtra("activity",entertainmentActivity);
                startActivity(intent);
            }
        });

    }
    private void findByView() {
        imageView = findViewById(R.id.detailed_user_image);
        postTime = findViewById(R.id.detailed_time);
        userName = findViewById(R.id.detailed_username);
        postTitle = findViewById(R.id.detailed_title);
        richEditor =findViewById(R.id.detailed_re);
        btRuturn = findViewById(R.id.bt_return);
        tvComment = findViewById(R.id.et_comments);
        recyclerView =findViewById(R.id.rv_popup);
        cbThumbsup = findViewById(R.id.bt_thumbsup);
        btComments = findViewById(R.id.bt_comments);
        cbCollect = findViewById(R.id.bt_collect_detailed);
        thumbsupNum = findViewById(R.id.thumbsup_num);
        commentsNum = findViewById(R.id.comments_num);
        collectNum = findViewById(R.id.comments_num);
        activityItem = findViewById(R.id.activity_item);
    }

    //写评论弹窗
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(AcademicItemDetailActivity.this).inflate(R.layout.activity_comment_popup, null);
        mPopWindow = new PopupWindow(contentView,
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        mPopWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        //设置各个控件的点击响应
        Button sendButton = contentView.findViewById(R.id.bt_popup);
        EditText mEditText = contentView.findViewById(R.id.et_popup);
        mEditText.requestFocus();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEditText.getText().toString().equals("")) {
                    Toast.makeText(AcademicItemDetailActivity.this, "请输入想说的话", Toast.LENGTH_SHORT).show();
                }
                else{
                    commentList.add(commentList.size(),new Comment(mEditText.getText().toString()));
                    commentRecyclerViewAdapter.notifyItemInserted(commentList.size()+1);
                }
                mPopWindow.dismiss();
            }
        });
        //显示PopupWindow
        View rootview = LayoutInflater.from(AcademicItemDetailActivity.this).inflate(R.layout.activity_academic_item_detail, null);
        mPopWindow.showAtLocation(rootview, Gravity.BOTTOM, 0, 0);
    }
}