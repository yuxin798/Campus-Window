package com.campuswindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.campuswindow.constant.UserConstant;
import com.campuswindow.entity.ModifyInformationDto;
import com.campuswindow.entity.User;
import com.campuswindow.service.index.ModifyUserDataService;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class EditUserDataActivity extends Activity {
    private Button backBtn;
    private ImageView imgHeader;
    private TextView name,sex,num;
    private EditText label;
    private Button save;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_data);
        findViews();
        user = (User) getIntent().getSerializableExtra("user");
        initViewsValue();
        setListenerMethods();
//        name.setText(getIntent().getStringExtra("setName"));
        if(getIntent().getStringExtra("setName")!=null){
            name.setText(getIntent().getStringExtra("setName"));
        }
    }

    private void initViewsValue() {
        name.setText(user.getUserName());
        label.setText(user.getSignature());
//        sex.setText(user.getGender());
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
                                sex.setText(rb1.getText().toString());
                                bottomSheetDialog.dismiss();
                                break;
                            case R.id.bottom_sheet_rb2:
                                Toast.makeText(EditUserDataActivity.this, "你选择了"+rb2.getText().toString(), Toast.LENGTH_SHORT).show();
                                sex.setText(rb2.getText().toString());
                                bottomSheetDialog.dismiss();
                                break;
                            case R.id.bottom_sheet_rb3:
                                Toast.makeText(EditUserDataActivity.this, "你选择了"+rb3.getText().toString(), Toast.LENGTH_SHORT).show();
                                sex.setText(rb3.getText().toString());
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String mName = name.getText().toString();
                        int gender;
                        if("男".equals(sex.getText().toString())){
                            gender = 0;
                        } else if ("女".equals(sex.getText().toString())) {
                            gender = 1;
                        }else{
                            gender = 3;
                        }
                        String mLabel = label.getText().toString();
                        ModifyInformationDto modifyInformationDto = new ModifyInformationDto(UserConstant.USER_ID,mName,gender,mLabel);
                        ModifyUserDataService service = new ModifyUserDataService();
                        Result result = service.getUserData(modifyInformationDto);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if("成功".equals(result.getMsg())){
                                    Toast.makeText(EditUserDataActivity.this, "信息修改成功", Toast.LENGTH_SHORT).show();
                                    finish();
                                }else{
                                    Toast.makeText(EditUserDataActivity.this, "信息修改失败", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }).start();
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