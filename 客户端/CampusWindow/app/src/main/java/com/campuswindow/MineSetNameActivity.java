package com.campuswindow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MineSetNameActivity extends AppCompatActivity {
    private EditText setName;
    private Button nullBtn,backBtn;
    private TextView rule1;
    private Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_set_name);
        findViews();
        setName.setText(getIntent().getStringExtra("name"));
        setListenerMethods();
    }

    private void setListenerMethods() {
        setName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //文本变化时要进行的操作
                if(s.length() == 0){
                    rule1.setTextColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //文本变化后要进行的操作
//                if(s.length()>0 && s.length()<2){
//                    Toast.makeText(MineSetNameActivity.this, "输入的字符不能少于2个！！！", Toast.LENGTH_SHORT).show();
//                }
//                if(s.length() == 0){
//                    rule1.setTextColor(Color.RED);
//                }else{
//                    rule1.setTextColor(Color.BLACK);
//                }
            }
        });
        nullBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setName.setText("");
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(setName.getText()==null){
                    Toast.makeText(MineSetNameActivity.this, "昵称不能为空", Toast.LENGTH_SHORT).show();
                }else if(setName.getText().toString().length()<3){
                    Toast.makeText(MineSetNameActivity.this, "用户名不能少于三个字符！", Toast.LENGTH_SHORT).show();
                } else{
                    Intent intent = new Intent(MineSetNameActivity.this,EditUserDataActivity.class);
                    intent.putExtra("setName",setName.getText().toString());
                    startActivity(intent);
                    finish();
                }
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineSetNameActivity.this,EditUserDataActivity.class);
                intent.putExtra("setName",getIntent().getStringExtra("name"));
                startActivity(intent);
                finish();
            }
        });
    }

    private void findViews() {
        setName = findViewById(R.id.mine_set_name_edt);
        nullBtn = findViewById(R.id.mine_set_name_btn);
        rule1 = findViewById(R.id.mine_set_name_rule1);
        save = findViewById(R.id.mine_set_save);
        backBtn = findViewById(R.id.edit_user_data_back_btn);
    }
}