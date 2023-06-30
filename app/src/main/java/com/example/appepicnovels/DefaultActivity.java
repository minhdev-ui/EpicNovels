package com.example.appepicnovels;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DefaultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.default_layout);
        LocalActivityManager activityManager = new LocalActivityManager(this, false);
        activityManager.dispatchCreate(savedInstanceState);

//        TextView discoverTextView = findViewById(R.id.discoverTextView);
//        discoverTextView.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(DefaultActivity.this, DiscoverActivity.class);
//                startActivity(intent);
//            }
//        });
        startActivity(new Intent(DefaultActivity.this, DiscoverActivity.class));





    }
}
