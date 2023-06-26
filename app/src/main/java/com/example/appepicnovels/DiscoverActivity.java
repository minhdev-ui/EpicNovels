package com.example.appepicnovels;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appepicnovels.adapters.DiscoverAdapter;
import com.example.appepicnovels.interfaces.StoriesCallback;
import com.example.appepicnovels.models.Story;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DiscoverActivity extends AppCompatActivity {
    GridView grvListStory;
    DiscoverAdapter adapter;
    ArrayList<Story> DiscoverArrayList;
    ProgressBar progressBar;

    private Handler handler = new Handler();
    private Handler handlerProcess = new Handler();
    private static final int DEBOUNCE_DELAY = 1000;

    long updateIntervalInMillis = (long) DEBOUNCE_DELAY / 90;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);
        progressBar = findViewById(R.id.loading_view);
        init();
    }

    public void getAllStories(final StoriesCallback callback) {
        progressBar.setVisibility(View.VISIBLE);
        handlerProcess.removeCallbacksAndMessages(null);
        handlerProcess.postDelayed(new Runnable() {
            int currentProcess = 0;
            @Override
            public void run() {
                progressBar.setProgress(currentProcess);
                currentProcess++;
                handlerProcess.postDelayed(this, updateIntervalInMillis);
            }
        }, updateIntervalInMillis);
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference usersCollection = db.collection("Stories");
                Task<QuerySnapshot> query = usersCollection.get();
                ArrayList<Story> storyArrayList = new ArrayList<>();
                query.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            QuerySnapshot queryDocument = task.getResult();
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                storyArrayList.add(new Story(documentSnapshot.getId(), documentSnapshot.getString("name"), documentSnapshot.getString("lastestChap"), documentSnapshot.getString("img"), documentSnapshot.getString("description"), documentSnapshot.getString("status")));
                            }
                            callback.onStoriesLoad(storyArrayList);
                        } else {
                            Toast.makeText(DiscoverActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }, DEBOUNCE_DELAY);
    }

    ;

    private void init() {
        DiscoverArrayList = new ArrayList<>();
        grvListStory = findViewById(R.id.grvListStory);
        grvListStory.setVisibility(View.GONE);
        getAllStories(new StoriesCallback() {
            @Override
            public void onStoriesLoad(ArrayList<Story> stories) {
                for (Story story : stories) {
                    DiscoverArrayList.add(new Story(story.getId(), story.getName(), story.getChap(), story.getLinkImg(), story.getDescription(), story.getStatus()));
                }
                adapter = new DiscoverAdapter(DiscoverActivity.this, 0, DiscoverArrayList);
                grvListStory.setAdapter(adapter);
                grvListStory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Story currentStory = DiscoverArrayList.get(position);
                        Story story = new Story(currentStory.getId(), currentStory.getName(), currentStory.getChap(), currentStory.getLinkImg(), currentStory.getDescription(), currentStory.getStatus());
                        Intent intent = new Intent(DiscoverActivity.this, StoryDescription.class);
                        intent.putExtra("story", story);
                        startActivity(intent);
                        finish();
                    }
                });
                progressBar.setProgress(100);
                progressBar.setVisibility(View.GONE);
                grvListStory.setVisibility(View.VISIBLE);
            }
        });
    }

    ;
}
