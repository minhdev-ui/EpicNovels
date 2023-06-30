package com.example.appepicnovels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appepicnovels.adapters.ChapterAdapter;
import com.example.appepicnovels.models.Chapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.*;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.util.ArrayList;

public class ListChapterActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Chapter> chapters = new ArrayList<>();
    ChapterAdapter chapterAdapter;

    @Override
    public void onCreate(Bundle savedInstantState) {
        super.onCreate(savedInstantState);
        setContentView(R.layout.activity_listview_chapter);

        listView = findViewById(R.id.listview);
        TextView nameNovel = findViewById(R.id.nameNovel);
        ImageView back = findViewById(R.id.back);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nameNovel.setText(extras.getString("nameStory"));
            getAllChaptersByStoryId(extras.getString("storyId"));
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void getAllChaptersByStoryId(String id) {
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        CollectionReference chapterCollection = firebaseFirestore.collection("Chapters");
        Query query = chapterCollection.whereEqualTo("storyId", id.trim());

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    if(!chapters.isEmpty()){
                        chapters.clear();
                    }
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        chapters.add(
                                new Chapter(
                                        documentSnapshot.getId(),
                                        documentSnapshot.getString("name"),
                                        documentSnapshot.getString("content"),
                                        documentSnapshot.getString("storyId"),
                                        documentSnapshot.getLong("views")
                                )
                        );
                    }
                    chapterAdapter = new ChapterAdapter(ListChapterActivity.this, chapters);
                    listView.setAdapter(chapterAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Intent intent = new Intent(ListChapterActivity.this, ChapterActivity.class);
                            intent.putExtra("chapters", chapters);
                            intent.putExtra("pos", position);
                            startActivity(intent);
                        }
                    });
                }
            }
        });
    }
}
