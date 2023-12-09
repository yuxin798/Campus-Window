package com.campuswindow;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.campuswindow.constant.UserConstant;
import com.campuswindow.service.user.LoginService;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail,edtPwd;
    private Button btnReg,btnFgPwd;
    private MyListener myListener;
    private TextView lgUserTxt;
    private RadioButton lgRadioBtn;
    private Button login1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getViews();
        Log.i("isChecked",lgRadioBtn.isChecked()+"");
        setListeners();
        setEditColor();

    }

    private void setEditColor() {
        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    GradientDrawable gd = new GradientDrawable();
                    gd.setStroke(3,Color.RED);
                    edtEmail.setBackground(gd);
                }else{
                    edtEmail.setBackground(null);
                }
            }
        });
        edtPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    GradientDrawable gd = new GradientDrawable();
                    gd.setStroke(3,Color.RED);
                    edtPwd.setBackground(gd);
                }else{
                    edtPwd.setBackground(null);
                }
            }
        });
    }

    private void setListeners() {
        MyListener myListener = new MyListener();
        btnReg.setOnClickListener(myListener);
        btnFgPwd.setOnClickListener(myListener);
        lgRadioBtn.setOnClickListener(myListener);

        login1.setOnClickListener(myListener);
    }

    private void getViews() {
        edtEmail = findViewById(R.id.edt_lg_email);
        edtPwd = findViewById(R.id.edt_lg_pwd);
        btnReg = findViewById(R.id.btn_lg_register);
        btnFgPwd = findViewById(R.id.btn_lg_forget);
        lgUserTxt = findViewById(R.id.login_user_txt);
        lgRadioBtn = findViewById(R.id.login_radio_btn);

        login1 = findViewById(R.id.btn_lg_login1);
    }

    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_lg_login1:
                    userLogin();
//                    Intent intent = new Intent(LoginActivity.this, NewActivity.class);
//                    startActivity(intent);
                    break;
                case R.id.btn_lg_register:
                    //注册事件：
                    userRegister();
                    break;
                case R.id.btn_lg_forget:
                    //忘记事件：
                    userFgPwd();
                    break;
                case R.id.login_radio_btn:
                    Log.i("isChecked",lgRadioBtn.isChecked()+"");
                    lgRadioBtn.setChecked(!lgRadioBtn.isChecked());
                    break;
            }
        }
    }



    //登录事件：
    private void userLogin() {
        String email = edtEmail.getText().toString().trim();
        String pwd = edtPwd.getText().toString().trim();
        Log.i("email,pwd",email.toString()+""+pwd.toString());
        if(email.isEmpty()||pwd.isEmpty()){
            Toast.makeText(getApplicationContext(),"邮箱或密码不能为空",Toast.LENGTH_SHORT).show();
        } else if(!lgRadioBtn.isChecked()){
            //确认用户协议：
            confirmUserPrivacyAgreement();
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.i("email,pwd", email.toString() + "" + pwd.toString());
                    Result login = LoginService.login(email, pwd, LoginActivity.this);
                    if ("成功".equals(login.getMsg())) {
                        String userId = login.getData().toString();
                        UserConstant.USER_ID = userId;
                        Intent intent = new Intent(LoginActivity.this, NewActivity.class);
//                        intent.putExtra("userId",userId);
                        startActivity(intent);
                    }else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(LoginActivity.this, "邮箱或密码错误", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }).start();
        }
    }

    //确认用户协议
    private void confirmUserPrivacyAgreement() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View page = inflater.inflate(R.layout.activity_login_user_agreement,null);
        WebView user = page.findViewById(R.id.login_user_web);
        Button btnNo = page.findViewById(R.id.login_btn_no);
        Button btnYes = page.findViewById(R.id.login_btn_yes);
        user.loadUrl("file:///user/user.html");
        final Dialog dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(page);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("isChecked",lgRadioBtn.isChecked()+"");
                lgRadioBtn.setChecked(true);

                dialog.dismiss();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lgRadioBtn.setChecked(false);
                Log.i("isChecked",lgRadioBtn.isChecked()+"");
                dialog.dismiss();
            }
        });
    }

    //注册事件
    private void userRegister() {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
    //忘记密码事件
    private void userFgPwd() {
        Intent intent = new Intent(LoginActivity.this,ForgetPwdActivity.class);
        startActivity(intent);
    }
}