package com.example.appepicnovels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appepicnovels.adapters.DiscoverAdapter;
import com.example.appepicnovels.models.Story;

import java.util.ArrayList;

public class DiscoverActivity extends AppCompatActivity {
GridView grvListStory;
DiscoverAdapter adapter;
ArrayList<Story> DiscoverArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        init();
        reference();
        setUp();
        setClick();

        View account = findViewById(R.id.accountIcon);
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiscoverActivity.this, ManagenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    private void init(){
        DiscoverArrayList = new ArrayList<>();
        DiscoverArrayList.add(new Story("Danh môn chi ái", "Chap01", "https://st.nettruyenio.com/data/comics/251/danh-mon-chi-ai.jpg"));
        DiscoverArrayList.add(new Story("BA CHỊ EM NHÀ MIKADONO DỄ ĐỐI PHÓ THẬT ĐẤY", "Chap01", "https://st.nettruyenio.com/data/comics/169/ba-chi-em-nha-mikadono-de-doi-pho-that-d-2969.jpg"));
        DiscoverArrayList.add(new Story("VÕ LUYỆN ĐỈNH PHONG", "Chap01", "https://st.nettruyenio.com/data/comics/32/vo-luyen-dinh-phong.jpg"));
        DiscoverArrayList.add(new Story("Đích nữ vi mưu", "Chap01", "https://st.nettruyenio.com/data/comics/143/dich-nu-vi-muu.jpg"));

        adapter = new DiscoverAdapter(this ,0,DiscoverArrayList );

    }
    private void reference(){
        grvListStory = findViewById(R.id.grvListStory);
    }
    private void setUp(){
        grvListStory.setAdapter(adapter);
    };
    private void setClick(){};
}
