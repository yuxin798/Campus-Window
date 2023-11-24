package com.campuswindow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.campuswindow.entity.User;
import com.campuswindow.server.API;
import com.campuswindow.service.user.RegisterService;
import com.campuswindow.service.user.ResetPwdService;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ForgetPwdActivity extends AppCompatActivity {
    private EditText edtFgEmail,edtFgCode;
    private Button btnFgSend,btnFgNext;
    private String code;
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
                        code = ((Double) verify.getData()).intValue() + "";
                    }
                }).start();
            }
        });
        btnFgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userCode = edtFgCode.getText().toString().trim();
                String email = edtFgEmail.getText().toString().trim();
                if(!userCode.isEmpty() && code.equals(userCode)){
                    Intent intent = new Intent(ForgetPwdActivity.this,ResetPwdActivity.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
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