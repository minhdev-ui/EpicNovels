package com.example.appepicnovels;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StoryDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_description);

        // Ánh xạ các thành phần giao diện từ file XML

        TextView storyTitle = findViewById(R.id.storyTitle);
        TextView storyDescription = findViewById(R.id.storyDescription);
        TextView storyStatus = findViewById(R.id.storyStatus);

        // Thiết lập dữ liệu cho các thành phần giao diện
        storyTitle.setText("Tên truyện");
        storyDescription.setText("Đoạn mô tả truyện");
        storyStatus.setText("Trạng thái truyện");
    }
}

