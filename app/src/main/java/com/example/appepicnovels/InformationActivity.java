package com.example.appepicnovels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class InformationActivity extends AppCompatActivity {
    private boolean noContentAvailable = true;
    private boolean noPostAvailable = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        TabHost tabHost = findViewById(R.id.tabHost2);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("Content");
        tab1.setIndicator("Content");

        tab1.setContent(R.id.Content);

        TabHost.TabSpec tab2 = tabHost.newTabSpec("Post");
        tab2.setIndicator("Post");
        tab2.setContent(R.id.Posts);

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);

        Toolbar toolbar = findViewById(R.id.materialToolbar);


        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert); // Đặt icon mũi tên quay về

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InformationActivity.this, ManagenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
