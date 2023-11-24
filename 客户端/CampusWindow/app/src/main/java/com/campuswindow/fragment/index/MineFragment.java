package com.campuswindow.fragment.index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.campuswindow.R;
import com.campuswindow.adapter.MineAdapter;
import com.campuswindow.fragment.mine.CollectFragment;
import com.campuswindow.fragment.mine.CommentFragment;
import com.campuswindow.fragment.mine.IssueFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MineFragment extends Fragment {
    private TabLayout mineFgTbl;
    private ViewPager2 mineFgVp2;
    private List<Fragment> fragments;
    private MineAdapter mineAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.activity_mine_fragment,null);
        initPages();
        getViews(page);
        mineAdapter = new MineAdapter(fragments,getActivity());
        mineFgVp2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mineFgVp2.setAdapter(mineAdapter);
        defineMediator();
        return page;
    }

    private void defineMediator() {
        TabLayoutMediator mediator = new TabLayoutMediator(
                mineFgTbl, mineFgVp2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position){
                    case 0:
                        tab.setText("发布");
                        break;
                    case 1:
                        tab.setText("评论");
                        break;
                    case 2:
                        tab.setText("收藏");
                        break;
                }
            }
        }
        );
    }

    private void getViews(View page) {
        mineFgTbl = page.findViewById(R.id.mine_fg_tbl);
        mineFgVp2 = page.findViewById(R.id.mine_fg_vp2);
    }

    private void initPages() {
        fragments = new ArrayList<>();
        fragments.add(new IssueFragment());
        fragments.add(new CommentFragment());
        fragments.add(new CollectFragment());
    }
}
