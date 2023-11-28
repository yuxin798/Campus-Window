package com.campuswindow.fragment.index;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.campuswindow.R;

public class PostMsgFragment extends Fragment {
    private Spinner spinner;
    private Button btnCancel,btnIssue;
    private ImageView imgUpImg,imgUpFile,imgUpVideo;
    private EditText edtTitle,edtContent;

    //拍照：
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page = inflater.inflate(R.layout.activity_post_msg_fragment,null);
        getViews(page);
        //设置下拉列表功能方法：
        spinnerListener();
        setListeners();
        return page;
    }

    private void setListeners() {
        imgUpFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgUpImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });
        imgUpVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        imgUpImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void spinnerListener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String result = parent.getItemAtPosition(position).toString();
                Toast.makeText(getActivity(),result,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getViews(View page) {
        spinner = page.findViewById(R.id.post_msg_spinner);
        btnCancel = page.findViewById(R.id.post_msg_cancel);
        btnIssue = page.findViewById(R.id.post_msg_issue);
        imgUpImg = page.findViewById(R.id.post_msg_img_upload);
        imgUpFile = page.findViewById(R.id.post_msg_file_upload);
        imgUpVideo = page.findViewById(R.id.post_msg_video_upload);
        edtTitle = page.findViewById(R.id.post_msg_edt_title);
        edtContent = page.findViewById(R.id.post_msg_edt_content);
    }
}
