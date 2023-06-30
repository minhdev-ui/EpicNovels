package com.example.appepicnovels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appepicnovels.DefaultActivity;
import com.example.appepicnovels.ManagenActivity;
import com.example.appepicnovels.R;

import kotlin.random.Random;

public class NotificationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ImageView homeIcon = findViewById(R.id.homeIcon);
        ImageView accountIcon = findViewById(R.id.accountIcon);

        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến trang HomeActivity
                Intent intent = new Intent(NotificationActivity.this, DefaultActivity.class);
                startActivity(intent);
            }
        });

        accountIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến trang AccountActivity
                Intent intent = new Intent(NotificationActivity.this, ManagenActivity.class);
                startActivity(intent);
            }
        });
    }
}
