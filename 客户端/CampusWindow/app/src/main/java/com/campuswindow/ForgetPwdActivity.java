package com.campuswindow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.campuswindow.entity.User;
import com.campuswindow.server.API;
import com.campuswindow.service.user.ResetPwdService;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ForgetPwdActivity extends AppCompatActivity {
    private EditText edtFgEmail,edtFgCode;
    private Button btnFgSend,btnFgNext;
    private String emailCodeKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        getViews();
        setListeners();
    }

    private void setListeners() {
        btnFgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtFgEmail.getText().toString().trim();
                User user = new User();
                user.setEmail(email);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ResetPwdService service = new ResetPwdService();
                        Result verify = service.verify(user);
                        emailCodeKey = (String)verify.getData();
                    }
                }).start();
            }
        });
        btnFgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userCode = edtFgCode.getText().toString().trim();
                String email = edtFgEmail.getText().toString().trim();

                if (userCode.isEmpty()) {
                    Toast.makeText(ForgetPwdActivity.this, "请填写验证码", Toast.LENGTH_SHORT).show();
                } else if (email.isEmpty()) {
                    Toast.makeText(ForgetPwdActivity.this, "请填写邮箱", Toast.LENGTH_SHORT).show();
                } else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            OkHttpClient client = new OkHttpClient();
                            FormBody formBody = new FormBody.Builder()
                                    .add("code", userCode)
                                    .add("emailCodeKey", emailCodeKey)
                                    .build();

                            Request request = new Request.Builder()
                                    .url(API.SERVER_URL + "checkEmailCode")//请求的url
                                    .post(formBody)
                                    .build();

                            //创建/Call
                            Call call = client.newCall(request);
                            try {
                                Response response = call.execute();
                                Gson gson = new Gson();
                                Result result = gson.fromJson(response.body().string(), Result.class);
                                if (result.getMsg().equals("邮箱验证码错误")) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(ForgetPwdActivity.this, "邮箱验证码错误", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    Intent intent = new Intent(ForgetPwdActivity.this, ResetPwdActivity.class);
                                    intent.putExtra("email", email);
                                    startActivity(intent);
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }).start();
                }
            }
        });
    }

    private void getViews() {
        edtFgEmail = findViewById(R.id.edt_fg_email);
        edtFgCode = findViewById(R.id.edt_fg_code);
        btnFgSend = findViewById(R.id.btn_fg_send);
        btnFgNext = findViewById(R.id.btn_fg_next);
    }
}