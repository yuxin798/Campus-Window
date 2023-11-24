package com.campuswindow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.campuswindow.entity.User;
import com.campuswindow.service.user.RegisterService;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtName,edtEmail,edtPwd,edtPwd2,edtSchool,edtVerify;
    private Button btnReYes,btnReNo,btnSendCode;
    private Result register;
    private RegisterService registerService = new RegisterService();
    private String code;
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

                User user = new User();
                user.setUserName(name);
                user.setEmail(email);
                user.setPassword(pwd);
                user.setSchool(school);

                Log.i("user1:",user.toString());

                //TODO Android端：提示邮箱是否正确
                boolean a = !userCode.isEmpty() && userCode.equals(code+"");
                boolean b = !pwd.isEmpty() && pwd.equals(pwd2);
                Log.i("code:",""+code);
                Log.i("a&b:",""+a+b);
                if(register!=null && a && b) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("user2:",user.toString());
                            register = registerService.register(user);
                        }
                    }).start();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
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
                        code = ((Double)register.getData()).intValue()+"" ;
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


    //检验密码的合理性
//    public boolean isPasswordValids(String password) {
//        // 密码长度必须在6到15位之间
//        if (password.length() < 6 || password.length() > 15) {
//            return false;
//        }
//
//        // 密码必须包含至少一个大写字母、一个小写字母和一个数字
//        boolean hasUpperCase = false;
//        boolean hasLowerCase = false;
//        boolean hasDigit = false;
//
//        for (char ch : password.toCharArray()) {
//            if (Character.isUpperCase(ch)) {
//                hasUpperCase = true;
//            } else if (Character.isLowerCase(ch)) {
//                hasLowerCase = true;
//            } else if (Character.isDigit(ch)) {
//                hasDigit = true;
//            }
//        }
//
//        return hasUpperCase && hasLowerCase && hasDigit;
//    }

    //要求密码必须包含至少一个数字、一个小写字母、一个大写字母、一个特殊字符（@#$%^&+=），并且长度至少为8个字符。
//    private static final String PASSWORD_PATTERN =
//            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
//
//    public static boolean isPasswordValid(String password) {
//        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
//        return pattern.matcher(password).matches();
//    }
}