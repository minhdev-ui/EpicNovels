package com.example.appepicnovels;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appepicnovels.adapters.DiscoverAdapter;
import com.example.appepicnovels.interfaces.StoriesCallback;
import com.example.appepicnovels.models.Rating;
import com.example.appepicnovels.models.Story;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DiscoverActivity extends AppCompatActivity {
    GridView grvListStory;
    DiscoverAdapter adapter;
    ArrayList<Story> DiscoverArrayList;
    ProgressBar progressBar;

    private Handler handler = new Handler();
    private Handler handlerProcess = new Handler();
    private static final int DEBOUNCE_DELAY = 1000;

    long updateIntervalInMillis = (long) DEBOUNCE_DELAY / 90;
    private EditText searchEditText;

    private Button searchButton;

    private String search = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.default_layout);
        progressBar = findViewById(R.id.loading_view);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        init();
        searchEditText = findViewById(R.id.searchEditText);
        searchButton = findViewById(R.id.searchButton);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search = searchEditText.getText().toString();
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                init();
            }
        });

        View manageAccount = findViewById(R.id.accountIcon);
        manageAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiscoverActivity.this, ManagenActivity.class);
                startActivity(intent);
                finish();
            }
        });

        View notification = findViewById(R.id.notificationIcon);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiscoverActivity.this, NotificationActivity.class);
                startActivity(intent);
                finish();
            }
        });
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
                                if(search.trim() == "") {
                                    storyArrayList.add(
                                            new Story(
                                            documentSnapshot.getId(),
                                            documentSnapshot.getString("name"),
                                            documentSnapshot.getString("lastestChap"),
                                            documentSnapshot.getString("img"),
                                            documentSnapshot.getString("description"),
                                            documentSnapshot.getString("status"),
                                            (List<Rating>) documentSnapshot.get("ratingStar"),
                                            documentSnapshot.getDouble("totalRate")
                                    ));
                                } else {
                                    if (documentSnapshot.get("name").toString().toLowerCase().contains(search)) {
                                        storyArrayList.add(
                                                new Story(
                                                        documentSnapshot.getId(),
                                                        documentSnapshot.getString("name"),
                                                        documentSnapshot.getString("lastestChap"),
                                                        documentSnapshot.getString("img"),
                                                        documentSnapshot.getString("description"),
                                                        documentSnapshot.getString("status"),
                                                        (List<Rating>) documentSnapshot.get("ratingStar"),
                                                        documentSnapshot.getDouble("totalRate")
                                                ));
                                    }
                                }
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
                    DiscoverArrayList.add(new Story(story.getId(), story.getName(), story.getChap(), story.getLinkImg(), story.getDescription(), story.getStatus(), story.getRatingStar(), story.getTotalRate()));
                }
                adapter = new DiscoverAdapter(DiscoverActivity.this, 0, DiscoverArrayList);
                grvListStory.setAdapter(adapter);
                grvListStory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Story currentStory = DiscoverArrayList.get(position);
                        Story story = new Story(currentStory.getId(), currentStory.getName(), currentStory.getChap(), currentStory.getLinkImg(), currentStory.getDescription(), currentStory.getStatus(), currentStory.getRatingStar(), currentStory.getTotalRate());
                        Intent intent = new Intent(DiscoverActivity.this, StoryDescription.class);
                        intent.putExtra("story", story);
                        startActivity(intent);
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
