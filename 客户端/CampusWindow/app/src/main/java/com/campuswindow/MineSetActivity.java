package com.campuswindow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.campuswindow.fragment.index.MineFragment;

public class MineSetActivity extends AppCompatActivity {
    private ImageView btnSetRoll;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_fragment_set);
        findViews();
        btnClickMethods();
    }

    private void btnClickMethods() {
        btnSetRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MineSetActivity.this, IndexActivity.class);
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
    }


    private void findViews() {
        btnSetRoll = findViewById(R.id.mine_set_rollback);
    }
}