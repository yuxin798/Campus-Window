package com.campuswindow.fragment.index;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.campuswindow.R;
import com.campuswindow.SearchPostActivity;
import com.campuswindow.adapter.HomeAdapter;
import com.campuswindow.fragment.home.AcademicActivityFragment;
import com.campuswindow.fragment.home.AmusementFragment;
import com.campuswindow.fragment.home.LugFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private Button search;
    private TabLayout indexFgTab;
    private ViewPager2 indexFgVp2;
    private List<Fragment> fragments;
    private HomeAdapter homeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.activity_index_fragment,null);
        setFragments();
        getViews(page);
        homeAdapter = new HomeAdapter(fragments,getActivity());
        indexFgVp2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        indexFgVp2.setAdapter(homeAdapter);
        defineMediator();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchPostActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        return page;
    }

    private void defineMediator() {
        TabLayoutMediator mediator = new TabLayoutMediator(indexFgTab, indexFgVp2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch(position){
                    case 0:
                        tab.setText("学习");
                        break;
                    case 1:
                        tab.setText("娱乐");
                        break;
                    case 2:
                        tab.setText("搭子");
                        break;
                }
            }
        });
        mediator.attach();
    }

    private void setFragments() {
        fragments = new ArrayList<>();
        fragments.add(new AcademicActivityFragment());
        fragments.add(new AmusementFragment());
        fragments.add(new LugFragment());
    }

    private void getViews(View page) {
        indexFgTab = page.findViewById(R.id.index_fg_tbl);
        indexFgVp2 = page.findViewById(R.id.index_fg_vp2);
        search = page.findViewById(R.id.home_search);
    }
}
