package com.example.appepicnovels;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appepicnovels.R;

public class DefaultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_layout);

        // Thực hiện chuyển đến ManagenActivity
        Intent intent = new Intent(DefaultActivity.this, ManagenActivity.class);
        startActivity(intent);
        finish(); // Tùy chọn: Đóng DefaultActivity sau khi chuyển đến ManagenActivity
    }
}
