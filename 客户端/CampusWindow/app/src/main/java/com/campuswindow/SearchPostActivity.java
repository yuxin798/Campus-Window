package com.campuswindow;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.campuswindow.adapter.SearchPostAdapter;
import com.campuswindow.entity.Activities;
import com.campuswindow.interfaces.OnItemClickListener;
import com.campuswindow.service.home.SearchService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchPostActivity extends AppCompatActivity implements OnItemClickListener {
    private Button returnBtn;
    private EditText search;
    private RecyclerView searchRe;
    private SearchPostAdapter searchPostAdapter;
    private List<Activities> activitiesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_post);
        getViews();
        getActivityList("");
        searchPostAdapter = new SearchPostAdapter();
        setListener();
        searchPostAdapter.setOnItemClickListener(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        searchRe.setLayoutManager(manager);
        searchRe.setAdapter(searchPostAdapter);
    }

    private void setListener() {
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPostActivity.this,NewActivity.class);
                startActivity(intent);
            }
        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String search = SearchPostActivity.this.search.getText().toString();
                System.out.println(search);

                getActivityList(search);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void getActivityList(String search) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SearchService service = new SearchService();
                Result result = service.getActivityList(search);
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Activities>>(){}.getType();

                activitiesList = gson.fromJson(gson.toJson(result.getData()), type);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        searchPostAdapter.setActivitiesList(activitiesList);
                        searchPostAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }


    private void getViews() {
        returnBtn = findViewById(R.id.search_post_btn);
        search = findViewById(R.id.search);
        searchRe = findViewById(R.id.search_post_re);
    }

    @Override
    public void onItemClick(List<Activities> activitiesList, View view, int position) {
        Activities activities = activitiesList.get(position);
        Intent intent = new Intent(SearchPostActivity.this,AcademicItemDetailActivity.class);
        intent.putExtra("data",activities);
        startActivity(intent);
    }
}