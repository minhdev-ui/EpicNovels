package com.example.appepicnovels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DiscoverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        TextView danhChoBanTextView = findViewById(R.id.danhChoBanTextView);
        danhChoBanTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to RecommendedPostsActivity
                Intent intent = new Intent(DiscoverActivity.this, RecommendedPostsActivity.class);
                startActivity(intent);
                finish(); // Optionally, you can finish DiscoverActivity if needed
            }
        });

        // Other code for DiscoverActivity
        // ...
    }
}
