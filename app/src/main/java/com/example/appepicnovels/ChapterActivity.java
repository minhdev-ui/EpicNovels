package com.example.appepicnovels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appepicnovels.models.Chapter;

import java.util.ArrayList;

public class ChapterActivity extends AppCompatActivity {
    ArrayList<Chapter> chapters = new ArrayList<>();
    int position;
    @Override
    public void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.content_chapter);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            chapters = (ArrayList<Chapter>) extras.getSerializable("chapters");
            position = extras.getInt("pos");
            Chapter chapter = chapters.get(position);
            setChapter(chapter);
        }
    }

    public void setChapter(Chapter chapter) {
        TextView nameChapter = findViewById(R.id.nameChapter);
        TextView btnNameChapter = findViewById(R.id.btnNameChapter);
        TextView contentChapter = findViewById(R.id.contentChapter);
        ImageView backButton = findViewById(R.id.backChapter);
        ImageView nextButton = findViewById(R.id.nextChapter);
        ImageView backScreen = findViewById(R.id.back);
        backScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        nameChapter.setText(chapter.getName());
        btnNameChapter.setText(chapter.getName());
        contentChapter.setText(chapter.getContent());

        if(position == 0) {
            backButton.setEnabled(false);
            backButton.setVisibility(View.INVISIBLE);
        } else {
            backButton.setEnabled(true);
            backButton.setVisibility(View.VISIBLE);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = position - 1;
                    Intent intent = new Intent(ChapterActivity.this, ChapterActivity.class);
                    intent.putExtra("chapters", chapters);
                    intent.putExtra("pos", pos);
                    startActivity(intent);
                    finish();
                }
            });
        }

        if(position == chapters.size() - 1) {
            nextButton.setEnabled(false);
            nextButton.setVisibility(View.INVISIBLE);
        } else {
            nextButton.setEnabled(true);
            nextButton.setVisibility(View.VISIBLE);
            nextButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = position + 1;
                    Intent intent = new Intent(ChapterActivity.this, ChapterActivity.class);
                    intent.putExtra("chapters", chapters);
                    intent.putExtra("pos", pos);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}
