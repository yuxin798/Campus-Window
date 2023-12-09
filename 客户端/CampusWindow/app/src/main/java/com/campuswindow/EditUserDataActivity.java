package com.campuswindow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.shehuan.niv.NiceImageView;

public class EditUserDataActivity extends AppCompatActivity {
    private Button backBtn;
    private NiceImageView imgHeader;
    private TextView name,sex,num;
    private EditText label;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_data);
        findViews();
        initViewsValue();
        setListenerMethods();
//        name.setText(getIntent().getStringExtra("setName"));
        if(getIntent().getStringExtra("setName")!=null){
            name.setText(getIntent().getStringExtra("setName"));
        }
    }

    private void initViewsValue() {
        name.setText(getIntent().getStringExtra("name"));
        label.setText(getIntent().getStringExtra("label"));
    }

    private void setListenerMethods() {
        //回退
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //设置名字
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditUserDataActivity.this,MineSetNameActivity.class);
                intent.putExtra("name",name.getText().toString());
                startActivity(intent);
                finish();
            }
        });
        //设置性别
        sex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(EditUserDataActivity.this,R.style.BottomSheetDialog);
                View bottomView = LayoutInflater.from(EditUserDataActivity.this).inflate(R.layout.bottom_sheet_layout,null);
                Button no = bottomView.findViewById(R.id.bottom_sheet_no);
                RadioGroup radioGroup = bottomView.findViewById(R.id.bottom_sheet_rp);
                RadioButton rb1 = bottomView.findViewById(R.id.bottom_sheet_rb1);
                RadioButton rb2 = bottomView.findViewById(R.id.bottom_sheet_rb2);
                RadioButton rb3 = bottomView.findViewById(R.id.bottom_sheet_rb3);
                bottomSheetDialog.setContentView(bottomView);
                bottomSheetDialog.show();
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId){

                            case R.id.bottom_sheet_rb1:
                                Toast.makeText(EditUserDataActivity.this, "你选择了"+rb1.getText().toString(), Toast.LENGTH_SHORT).show();
                                bottomSheetDialog.dismiss();
                                break;
                            case R.id.bottom_sheet_rb2:
                                Toast.makeText(EditUserDataActivity.this, "你选择了"+rb2.getText().toString(), Toast.LENGTH_SHORT).show();
                                bottomSheetDialog.dismiss();
                                break;
                            case R.id.bottom_sheet_rb3:
                                Toast.makeText(EditUserDataActivity.this, "你选择了"+rb3.getText().toString(), Toast.LENGTH_SHORT).show();
                                bottomSheetDialog.dismiss();
                                break;
                        }
                    }
                });
                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });
        //设置个性签名
        label.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int charNum = s.length();
                num.setText(charNum + "/100");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //提交用户信息，保存
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void findViews() {
        backBtn = findViewById(R.id.edit_user_data_back_btn);
        name = findViewById(R.id.edit_user_name);
        sex = findViewById(R.id.edit_user_sex);
        num = findViewById(R.id.edit_user_label_num);
        label = findViewById(R.id.edit_user_label);
        imgHeader = findViewById(R.id.edit_user_img);
        save = findViewById(R.id.edit_user_data_save_btn);
    }
}