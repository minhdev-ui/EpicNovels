package com.example.appepicnovels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.appepicnovels.models.Story;

public class StoryDescription extends AppCompatActivity {
    private TextView storyTitle;
    private TextView storyDescription;
    private TextView storyStatus;
    private ImageView storyImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.activity_story_description);
        ImageView backButton = findViewById(R.id.myIcon);
        RelativeLayout viewListChapter = findViewById(R.id.viewListChapter);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển về trang Discover
                Intent intent = new Intent(StoryDescription.this, DefaultActivity.class);
                intent.putExtra("tabActive", 1);
                startActivity(intent);
                finish(); // Đóng Activity hiện tại
            }
        });



        // Ánh xạ các thành phần giao diện từ file XML

         storyTitle = findViewById(R.id.storyTitle);
         storyDescription = findViewById(R.id.storyDescription);
         storyStatus = findViewById(R.id.Status);
         storyImage = findViewById(R.id.storyImage);

        if(bundle != null) {
            Story story = (Story) bundle.getSerializable("story");
            setDetail(story);
            viewListChapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StoryDescription.this, ListChapterActivity.class);
                    intent.putExtra("storyId", story.getId());
                    intent.putExtra("nameStory", story.getName());
                    startActivity(intent);
                }
            });
        }

        // Thiết lập dữ liệu cho các thành phần giao diện
    }

    public void setDetail(Story story) {
        storyTitle.setText(story.getName());
        storyDescription.setText(story.getDescription());
        storyStatus.setText(story.getStatus());
        Glide.with(this).load(story.getLinkImg()).into(storyImage);

    }

}

