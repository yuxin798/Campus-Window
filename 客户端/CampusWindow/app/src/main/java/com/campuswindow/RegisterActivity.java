package com.campuswindow;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.campuswindow.entity.RegisterDto;
import com.campuswindow.entity.User;
import com.campuswindow.service.user.RegisterService;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtName,edtEmail,edtPwd,edtPwd2,edtSchool,edtVerify;
    private Button btnReYes,btnReNo,btnSendCode;
    private Result register;
    private RegisterService registerService = new RegisterService();
    private String emailCodeKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getViews();
        setListeners();
    }


    private void setListeners() {
        btnReYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String pwd = edtPwd.getText().toString().trim();
                String school = edtSchool.getText().toString().trim();
                String userCode = edtVerify.getText().toString().trim();
                String pwd2 = edtPwd2.getText().toString().trim();
                RegisterDto user = new RegisterDto(name, email, pwd, school, userCode, emailCodeKey);

                //TODO Android端：提示邮箱是否正确
                boolean a = !userCode.isEmpty();
                boolean b = !pwd.isEmpty() && pwd.equals(pwd2);
                Log.i("a&b:",""+a+b);
                if(register!=null && a && b) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("user2:",user.toString());
                            register = registerService.register(user);
                            if ("邮箱已存在".equals(register.getMsg())){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, "邮箱已存在", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else if (register.getMsg().equals("验证码错误")){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }
                    }).start();
                    System.out.println("register.getMsg()"  +  register.getMsg());
                }
            }
        });
        btnSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                User user = new User();
                user.setEmail(email);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        register = registerService.verify(user);
                        Log.i("register:",register.toString());
                        emailCodeKey = (String)register.getData();
                    }
                }).start();
            }
        });
        btnReNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void getViews() {
        edtName = findViewById(R.id.edt_re_name);
        edtEmail = findViewById(R.id.edt_re_email);
        edtPwd = findViewById(R.id.edt_re_pwd);
        edtPwd2 = findViewById(R.id.edt_re_pwd2);
        edtSchool = findViewById(R.id.edt_re_school);
        edtVerify = findViewById(R.id.edt_re_verify);
        btnReYes = findViewById(R.id.btn_re_yes);
        btnReNo = findViewById(R.id.btn_re_no);
        btnSendCode = findViewById(R.id.btn_re_send);
    }
}