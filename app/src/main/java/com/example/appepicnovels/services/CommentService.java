package com.example.appepicnovels.services;

import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.example.appepicnovels.interfaces.CommentsCallback;
import com.example.appepicnovels.models.Comment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CommentService {
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = firebaseFirestore.collection("Comments");
    ArrayList<Comment> comments = new ArrayList<>();

    public ArrayList<Comment> getAllByStoryId(String id) {
        ArrayList<Comment> comments = new ArrayList<>();
        getAllByStoryId(new CommentsCallback() {
            @Override
            public void onCommentsLoaded(ArrayList<Comment> comments) {
                for(Comment comment : comments) {
                    comments.add(new Comment(comment.getId(), comment.getUserId(), comment.getContent(), comment.getStoryId()));
                }
            }
        }, id);
        return comments;
    }

    public void getAllByStoryId(final CommentsCallback callback, String id) {
        collectionReference.whereEqualTo("storyId", id).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    comments.clear();
                    for(QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        comments.add(new Comment(documentSnapshot.getId(), documentSnapshot.getString("userId"), documentSnapshot.getString("content"), documentSnapshot.getString("storyId")));
                    }
                    callback.onCommentsLoaded(comments);
                }
            }
        });
    }

    public void createNew(Comment comment) {
        collectionReference.add(comment).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {
                if(task.isSuccessful()) {
                    Log.e("success", "Post new comment successful");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                Log.e("error", "Post new comment failure");
            }
        });
    }
}
