package com.example.appepicnovels;

import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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

        TabHost tabHost = (TabHost)findViewById(R.id.tabhost);
        tabHost.setup(activityManager);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("For You");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Discover");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Posts");

        tab1.setIndicator("For You");
        tab1.setContent(R.id.tab1);

        tab2.setIndicator("Discover");
        tab2.setContent(new Intent(this, DiscoverActivity.class));

        tab3.setIndicator("Posts");
        tab3.setContent(R.id.tab3);

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            tabHost.setCurrentTab(extras.getInt("tabActive"));
        }

        View manageAccount = findViewById(R.id.accountIcon);
        manageAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DefaultActivity.this, ManagenActivity.class);
                startActivity(intent);
            }
        });
    }
}
