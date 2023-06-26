package com.example.appepicnovels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        Toolbar toolbar = findViewById(R.id.materialToolbar);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_close_clear_cancel);

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostActivity.this, InformationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        EditText editTextPost = findViewById(R.id.editTextPost);
        Button buttonPost = findViewById(R.id.buttonPost);

        buttonPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postContent = editTextPost.getText().toString();

                if (!postContent.isEmpty()) {
                    sendPostToServer(postContent);
                } else {
                    Toast.makeText(PostActivity.this, "Please enter post content", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void sendPostToServer(String postContent) {
        // Send the post content to the server
        // Write your code to send the post content to the server here
        // ...

        // After successful sending, you can display a notification or navigate back to the previous screen
        Intent resultIntent = new Intent();
        resultIntent.putExtra("posted_content", postContent);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
