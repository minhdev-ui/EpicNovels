package com.example.appepicnovels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SavedContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_content);
        Toolbar toolbar = findViewById(R.id.materialToolbar);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_revert);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavedContentActivity.this, ManagenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}