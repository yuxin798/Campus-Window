package com.campuswindow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.campuswindow.entity.User;
import com.campuswindow.service.user.ResetPwdService;

public class ResetPwdActivity extends AppCompatActivity {
    private EditText edtRtPwd,edtRtPwd2;
    private Button btnRtPwd;
    private ResetPwdService service;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pwd);

        edtRtPwd = findViewById(R.id.edt_rt_pwd);
        edtRtPwd2 = findViewById(R.id.edt_rt_pwd2);
        btnRtPwd = findViewById(R.id.btn_rt_pwd);
        btnRtPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = getIntent();
                String email = intent.getStringExtra("email");
                String pwd = edtRtPwd.getText().toString();
                String pwd2 = edtRtPwd2.getText().toString();
                service = new ResetPwdService();
                User user = new User();
                user.setEmail(email);
                user.setPassword(pwd);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Result result = service.sendRtPwd(user);
                        boolean a = !pwd.isEmpty() && pwd.equals(pwd2);
                        Log.i("result.getMsg()",result.getMsg());
                        if(result.getMsg().equals("修改成功") && a){

                            intent = new Intent(ResetPwdActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                }).start();
            }
        });
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(ResetPwdActivity.this,"修改成功，快去登陆吧！",Toast.LENGTH_SHORT).show();
            }
        });
    }

}