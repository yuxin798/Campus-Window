package com.campuswindow;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.campuswindow.fragment.index.ChatFyRoomFragment;
import com.campuswindow.fragment.index.ChatRoomFragment;
import com.campuswindow.fragment.index.HomeFragment;
import com.campuswindow.fragment.index.MineFragment;
import com.campuswindow.richeditor.PostMsgActivity;

import java.util.List;

import butterknife.ButterKnife;

public class NewActivity extends FragmentActivity {
    private RadioGroup radioGroup;
//    private ViewPager2 newVp;
    private List<Fragment> list;

    private ChatFyRoomFragment chatFyRoomFragment;
    private ChatRoomFragment chatRoomFragment;
    private HomeFragment homeFragment;
    private MineFragment mineFragment;
//    private ImageView newImg;
    private FrameLayout container;
    private Button newImg;
    private SparseArray<Fragment> mFragmentSparseArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        ButterKnife.bind(this);
        initViews();

//        newVp.setUserInputEnabled(false);
//        newVp.setAdapter(new MyViewPager(list,this));
//
//        int id = getIntent().getIntExtra("flag",0);
//        if(id==4){
//            newVp.setCurrentItem(id);
//        }else{
//            newVp.setCurrentItem(id);
//        }
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch(checkedId){
//                    case R.id.rb_home:
//                        newVp.setCurrentItem(0);
//                        break;
//                    case R.id.rb_community:
//                        newVp.setCurrentItem(1);
//                        break;
//                    case R.id.rb_chat:
//                        newVp.setCurrentItem(2);
//                        break;
//                    case R.id.rb_mine:
//                        newVp.setCurrentItem(3);
//                        break;
//                }
//            }
//        });

        newImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = getIntent().getStringExtra("userId");
                Intent intent = new Intent(NewActivity.this, PostMsgActivity.class);
//                intent.putExtra("userId",userId);
                startActivity(intent);
            }
        });


    }

    private void initViews() {
//        list = new ArrayList<>();
//        chatFyRoomFragment = new ChatFyRoomFragment();
//        chatRoomFragment = new ChatRoomFragment();
//        homeFragment = new HomeFragment();
//        mineFragment = new MineFragment();
//        list.add(homeFragment);
//        list.add(chatFyRoomFragment);
//        list.add(chatRoomFragment);
//        list.add(mineFragment);

        radioGroup = findViewById(R.id.radioGroup);
        container = findViewById(R.id.main_page_container);

//        newVp = findViewById(R.id.new_vp);
        newImg = findViewById(R.id.new_img);

        mFragmentSparseArray = new SparseArray<>();
        mFragmentSparseArray.append(R.id.rb_home,new HomeFragment());
        mFragmentSparseArray.append(R.id.rb_community,new ChatFyRoomFragment());
        mFragmentSparseArray.append(R.id.rb_chat,new ChatRoomFragment());
        mFragmentSparseArray.append(R.id.rb_mine,new MineFragment());
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_page_container,
                        mFragmentSparseArray.get(checkedId)).commit();
            //TODO 切换RadioButton时，切换字体颜色
                // 获取被选中的RadioButton
//                RadioButton radioButton = findViewById(checkedId);
//
//                // 根据选中状态设置文本颜色
//                if (radioButton.isChecked()) {
//                    radioButton.setTextColor(Color.BLUE); // 设置选中时的文本颜色
//                } else {
//                    radioButton.setTextColor(Color.BLACK); // 设置未选中时的文本颜色
//                }
            }
        });
        getSupportFragmentManager().beginTransaction().add(R.id.main_page_container,
                mFragmentSparseArray.get(R.id.rb_home)).commit();

    }
}