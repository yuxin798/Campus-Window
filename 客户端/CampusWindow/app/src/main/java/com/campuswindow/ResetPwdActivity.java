package com.campuswindow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.campuswindow.entity.PasswordDto;
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
                if (pwd.isEmpty()){
                    Toast.makeText(ResetPwdActivity.this, "输入密码", Toast.LENGTH_SHORT).show();
                }else if (!pwd.equals(pwd2)){
                    Toast.makeText(ResetPwdActivity.this, "两次密码不一样", Toast.LENGTH_SHORT).show();
                }else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            service = new ResetPwdService();
                            PasswordDto passwordDto = new PasswordDto(email, pwd);
                            Result result = service.sendRtPwd(passwordDto);
                            if(result.getMsg().equals("成功")){
                                intent = new Intent(ResetPwdActivity.this,LoginActivity.class);
                                startActivity(intent);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(ResetPwdActivity.this,"修改成功，快去登陆吧！",Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    }).start();
                }
            }
        });
    }
}