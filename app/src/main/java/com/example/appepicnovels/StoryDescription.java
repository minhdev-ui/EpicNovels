package com.example.appepicnovels;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.appepicnovels.Firebase.FirebaseStore;
import com.example.appepicnovels.adapters.ChapterAdapter;
import com.example.appepicnovels.adapters.CommentAdapter;
import com.example.appepicnovels.interfaces.CommentsCallback;
import com.example.appepicnovels.models.Chapter;
import com.example.appepicnovels.models.Comment;
import com.example.appepicnovels.models.Rating;
import com.example.appepicnovels.models.Story;
import com.example.appepicnovels.services.CommentService;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.*;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class StoryDescription extends AppCompatActivity {
    private TextView storyTitle;
    private TextView storyDescription;
    private TextView storyStatus;
    private ImageView storyImage;
    CommentAdapter commentAdapter;
    ArrayList<Comment> comments;
    ListView listComment;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    ImageButton postCommentButton;
    EditText commentInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.activity_story_description);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        ImageView backButton = findViewById(R.id.myIcon);
        RelativeLayout viewListChapter = findViewById(R.id.viewListChapter);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển về trang Discover
                Intent intent = new Intent(StoryDescription.this, DefaultActivity.class);
                intent.putExtra("tabActive", 1);
                startActivity(intent);
                finish(); // Đóng Activity hiện tại
            }
        });


        // Ánh xạ các thành phần giao diện từ file XML

         storyTitle = findViewById(R.id.storyTitle);
         storyDescription = findViewById(R.id.storyDescription);
         storyStatus = findViewById(R.id.Status);
         storyImage = findViewById(R.id.storyImage);
         postCommentButton = findViewById(R.id.postCommentButton);
         commentInput = findViewById(R.id.commentEditText);
         RatingBar ratingBar = findViewById(R.id.ratingBar);
        SharedPreferences sharedPreferences = getSharedPreferences("AccountPreference", MODE_PRIVATE);
        String userId = sharedPreferences.getString("id", "");
        if(bundle != null) {
            Story story = (Story) bundle.getSerializable("story");
            init(story.getId());
            setDetail(story);
            viewListChapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(StoryDescription.this, ListChapterActivity.class);
                    intent.putExtra("storyId", story.getId());
                    intent.putExtra("nameStory", story.getName());
                    startActivity(intent);
                }
            });
            postCommentButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Comment comment = new Comment(null, userId, commentInput.getText().toString(), story.getId());
                    postNewComment(comment);
                    commentInput.setText("");
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            });
            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    try {
                        addRatingToStory(story, rating, userId);
                        Toast.makeText(StoryDescription.this, "Rating Success", Toast.LENGTH_SHORT).show();
                    }catch (Exception e) {
                        Toast.makeText(StoryDescription.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        View view = getCurrentFocus();
        if(view != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void setDetail(Story story) {
        storyTitle.setText(story.getName());
        storyDescription.setText(story.getDescription());
        storyStatus.setText(story.getStatus());
        Glide.with(this).load(story.getLinkImg()).into(storyImage);
    }

    public void addRatingToStory(Story story, float star, String userId) {
        DocumentReference query = firebaseFirestore.collection("Stories").document(story.getId());
        query.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    ArrayList<Rating> oldRating = (ArrayList<Rating>) documentSnapshot.get("ratingStar");
                    try {
                        Rating ratingStar = new Rating(userId, story.getId(), star);
                        oldRating.add(ratingStar);
                        query.update(
                                "ratingStar",
                                oldRating
                                );
                    } catch (Exception e) {
                        Log.e("error", e.getMessage());
                    }
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Log.e("error", e.getMessage());
            }
        });
    }

    public void postNewComment(Comment comment) {
        Map<String, Object> data = new HashMap<>();
        data.put("userId", comment.getUserId());
        data.put("content", comment.getContent());
        data.put("storyId", comment.getStoryId());
        data.put("timestamp", FieldValue.serverTimestamp());
        CollectionReference collectionReference = firebaseFirestore.collection("Comments");
        Task<DocumentReference> insertComment = collectionReference.add(data);
        insertComment.addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {
                if(task.isSuccessful()) {
                    init(comment.getStoryId());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Log.e("error", e.getMessage());
            }
        });
    }

    public void getAllCommentByStoryId(final CommentsCallback callback, String id) {
        Query query = firebaseFirestore.collection("Comments")
                .whereEqualTo("storyId", id.trim());

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    comments = new ArrayList<Comment>();
                    QuerySnapshot commentsList = task.getResult();
                    for (QueryDocumentSnapshot documentSnapshot : commentsList) {
                        Comment comment = new Comment(documentSnapshot.getId(), documentSnapshot.getString("userId"), documentSnapshot.getString("content"), documentSnapshot.getString("storyId"));
                        comment.setTimestamp(documentSnapshot.getTimestamp("timestamp"));
                        comments.add(comment);
                    }
                    Comparator<Comment> timeAgoComparator = (obj1, obj2) -> Long.compare(obj2.getTimestamp().toDate().getTime(), obj1.getTimestamp().toDate().getTime());
                    Collections.sort(comments, timeAgoComparator);
                    callback.onCommentsLoaded(comments);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Log.e("error", e.getMessage());
            }
        });
    }

    public void init(String id) {
        listComment = findViewById(R.id.commentList);
        getAllCommentByStoryId(new CommentsCallback() {
            @Override
            public void onCommentsLoaded(ArrayList<Comment> comments) {
                commentAdapter = new CommentAdapter(StoryDescription.this, comments);
                listComment.setAdapter(commentAdapter);
            }
        }, id);
    }

}

