package com.campuswindow;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.campuswindow.constant.UserConstant;
import com.campuswindow.service.user.LoginService;

import java.io.ByteArrayInputStream;

public class LoginActivity extends AppCompatActivity {
    private EditText edtEmail,edtPwd;
    private Button btnReg,btnFgPwd;
    private MyListener myListener;
    private TextView lgUserTxt;
    private RadioButton lgRadioBtn;
    private CheckBox lgCheckBtn;
    private Button login1;
    boolean a;
//    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getViews();

        setListeners();
        lgCheckBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = lgCheckBtn.isChecked();
            }
        });
        if(lgCheckBtn.isChecked()){
            lgCheckBtn.setChecked(!a);
        }else{
            lgCheckBtn.setChecked(!a);
        }

    }



    private void setListeners() {
        MyListener myListener = new MyListener();
        btnReg.setOnClickListener(myListener);
        btnFgPwd.setOnClickListener(myListener);
//        lgRadioBtn.setOnClickListener(myListener);

        login1.setOnClickListener(myListener);
    }

    private void getViews() {
        edtEmail = findViewById(R.id.edt_lg_email);
        edtPwd = findViewById(R.id.edt_lg_pwd);
        btnReg = findViewById(R.id.btn_lg_register);
        btnFgPwd = findViewById(R.id.btn_lg_forget);
        lgUserTxt = findViewById(R.id.login_user_txt);
        lgCheckBtn = findViewById(R.id.login_check_btn);
        login1 = findViewById(R.id.btn_lg_login1);
    }

    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_lg_login1:
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
        String obj  = "abc";
        byte b[] = obj.getBytes();
        ByteArrayInputStream obj1 = new ByteArrayInputStream(b);
        for (int i = 0; i < 2; ++ i) {
            int c;
            while ((c = obj1.read()) != -1) {
                if (i == 0) {
                    System.out.print(Character.toUpperCase((char)c));
                }
            }
        }

        String email = edtEmail.getText().toString().trim();
        String pwd = edtPwd.getText().toString().trim();
        Log.i("email,pwd",email+pwd);
        if(email.isEmpty()||pwd.isEmpty()){
            Toast.makeText(getApplicationContext(),"邮箱或密码不能为空",Toast.LENGTH_SHORT).show();
        } else if(!lgCheckBtn.isChecked()){
            //确认用户协议：
            confirmUserPrivacyAgreement();
        } else {
//            RequestUtil.submit(() -> {
//                Request request = new Request.Builder().build();
//                OkHttpClient httpClient = new OkHttpClient();
//                Response response = httpClient.newCall(request).execute();
//                if (response.code() == 200) {
//                    Result<Map<String, Map<String, List<User>>>> result = RequestUtil.fromResponse(response, new TypeToken<Result<Map<String, Map<String, List<User>>>>>() {}.getType());
//                    result.getData().get("aaa").get("aaa").stream()
//                                    .filter(v -> true)
//                                            .forEach(System.out::println);
//                    runOnUiThread(() -> {
//
//                    });
//                }
//                response.close();
//                return response;
//            });

            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.i("email,pwd", email.toString() + "" + pwd.toString());
                    Result login = LoginService.login(email, pwd, LoginActivity.this);
                    if ("成功".equals(login.getMsg())) {
                        String userId = login.getData().toString();
                        UserConstant.USER_ID = userId;
                        Intent intent = new Intent(LoginActivity.this, NewActivity.class);
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
                Log.i("isChecked",lgCheckBtn.isChecked()+"");
                lgCheckBtn.setChecked(true);
                dialog.dismiss();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lgCheckBtn.setChecked(false);
                Log.i("isChecked",lgCheckBtn.isChecked()+"");
                dialog.dismiss();
            }
        });
    }

    //注册事件
    private void userRegister() {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
    //忘记密码事件
    private void userFgPwd() {
        Intent intent = new Intent(LoginActivity.this,ForgetPwdActivity.class);
        startActivity(intent);
    }
}