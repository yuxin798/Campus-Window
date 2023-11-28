package com.campuswindow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.campuswindow.service.user.LoginService;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail,edtPwd;
    private Button btnLogin,btnReg,btnFgPwd;
    private MyListener myListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getViews();
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
        btnLogin.setOnClickListener(myListener);
        btnReg.setOnClickListener(myListener);
        btnFgPwd.setOnClickListener(myListener);
    }

    private void getViews() {
        edtEmail = findViewById(R.id.edt_lg_email);
        edtPwd = findViewById(R.id.edt_lg_pwd);
        btnLogin = findViewById(R.id.btn_lg_login);
        btnReg = findViewById(R.id.btn_lg_register);
        btnFgPwd = findViewById(R.id.btn_lg_forget);
    }

    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_lg_login:
                    //登录事件：
                    userLogin();
                    break;
                case R.id.btn_lg_register:
                    //注册事件：
                    userRegister();
                    break;
                case R.id.btn_lg_forget:
                    //忘记事件：
                    userFgPwd();
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
            Toast.makeText(this,"邮箱或密码不能为空",Toast.LENGTH_SHORT).show();
        }else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.i("email,pwd", email.toString() + "" + pwd.toString());
                    Result login = LoginService.login(email, pwd, LoginActivity.this);
                    if (login.getMsg().equals("成功")) {
                        Intent intent = new Intent(LoginActivity.this, IndexActivity.class);
                        startActivity(intent);
                    }
                }
            }).start();
        }
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