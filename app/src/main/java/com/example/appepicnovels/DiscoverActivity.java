package com.example.appepicnovels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.GridView;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appepicnovels.adapters.DiscoverAdapter;
import com.example.appepicnovels.interfaces.StoriesCallback;
import com.example.appepicnovels.models.Story;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DiscoverActivity extends AppCompatActivity {
GridView grvListStory;
DiscoverAdapter adapter;
ArrayList<Story> DiscoverArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        init();
        setClick();
    }

    public void getAllStories(final StoriesCallback callback) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference usersCollection = db.collection("Stories");
        Task<QuerySnapshot> query = usersCollection.get();
        ArrayList<Story> storyArrayList = new ArrayList<>();
        query.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        storyArrayList.add(new Story(documentSnapshot.getString("name"), documentSnapshot.getString("lastestChap"), documentSnapshot.getString("img")));
                    }
                    callback.onStoriesLoad(storyArrayList);
                } else {
                    Toast.makeText(DiscoverActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    };

    private void init(){
        DiscoverArrayList = new ArrayList<>();
        grvListStory = findViewById(R.id.grvListStory);
        getAllStories(new StoriesCallback() {
            @Override
            public void onStoriesLoad(ArrayList<Story> stories) {
                for (Story story : stories) {
                    DiscoverArrayList.add(new Story(story.getName(), story.getChap(), story.getLinkImg()));
                }
                adapter = new DiscoverAdapter(DiscoverActivity.this, 0, DiscoverArrayList);
                grvListStory.setAdapter(adapter);
            }
        });
    }

    private void setClick(){};
}
