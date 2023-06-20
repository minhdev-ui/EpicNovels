package com.example.appepicnovels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.FirebaseApp;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_posts);

        TextView danhChoBanTextView = findViewById(R.id.danhChoBanTextView);
        danhChoBanTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi ấn vào "dành cho bạn"
                Intent intent = new Intent(MainActivity.this, RecommendedPostsActivity.class);
                startActivity(intent);
            }
        });
    }

}