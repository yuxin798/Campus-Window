package com.campuswindow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.campuswindow.adapter.IndexAdapter;
import com.campuswindow.fragment.index.ChatFyRoomFragment;
import com.campuswindow.fragment.index.ChatRoomFragment;
import com.campuswindow.fragment.index.HomeFragment;
import com.campuswindow.fragment.index.MineFragment;
import com.campuswindow.fragment.index.PostMsgFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class IndexActivity extends AppCompatActivity {
    private TabLayout indexTbl;
    private ViewPager2 indexVp2;
    private IndexAdapter indexAdapter;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        initPages();
        getViews();

        indexAdapter = new IndexAdapter(fragmentList,this);
        indexVp2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        indexVp2.setAdapter(indexAdapter);

        defineMediator();
        //“我的”设置中跳转回来
        //mineSetRollBack();
    }

    private void mineSetRollBack() {
//        int id = getIntent().getIntExtra("id", 0);
//        if (id == 1) {
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.mine_fg_dra,new MineFragment())
//                    .addToBackStack(null)
//                    .commit();
//        }
    }

    private void defineMediator() {
        TabLayoutMediator mediator = new TabLayoutMediator(
                indexTbl, indexVp2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position){
                    case 0:
                        Drawable drawable1 = getResources().getDrawable(R.drawable.home);
                        tab.setIcon(drawable1);
                        tab.setText("首页");
                        break;
                    case 1:
                        Drawable drawable2 = getResources().getDrawable(R.drawable.community);
                        tab.setIcon(drawable2);
                        tab.setText("畅聊室");
                        break;
                    case 2:
                        Drawable drawable = getResources().getDrawable(R.drawable.add2);
//                        tab.setText("发帖");
                        tab.setIcon(drawable);
                        break;
                    case 3:
                        Drawable drawable3 = getResources().getDrawable(R.drawable.chat);
                        tab.setIcon(drawable3);
                        tab.setText("聊天室");
                        break;
                    case 4:
                        Drawable drawable4 = getResources().getDrawable(R.drawable.mine);
                        tab.setIcon(drawable4);
                        tab.setText("我的");
                        break;
                }
            }
        }
        );
        mediator.attach();
    }

    private void initPages() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new ChatFyRoomFragment());
        fragmentList.add(new PostMsgFragment());
        fragmentList.add(new ChatRoomFragment());
        fragmentList.add(new MineFragment());
    }

    private void getViews() {
        indexTbl = findViewById(R.id.index_tbl);
        indexVp2 = findViewById(R.id.index_vp2);
    }
}