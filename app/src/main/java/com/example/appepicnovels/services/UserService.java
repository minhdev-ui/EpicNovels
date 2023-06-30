package com.example.appepicnovels.services;

import android.util.Log;
import androidx.annotation.NonNull;
import com.example.appepicnovels.interfaces.UserCallback;
import com.example.appepicnovels.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService {
    private static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private static CollectionReference collectionReference = firebaseFirestore.collection("Users");

    public static List<User> getAll() {
        List<User> users = new ArrayList<>();
        collectionReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    if(!task.getResult().isEmpty()) {
                        users.clear();
                        for(DocumentSnapshot documentSnapshot : task.getResult()) {
                            users.add(new User(documentSnapshot.getId(), documentSnapshot.getString("username"), null, documentSnapshot.getString("email"), documentSnapshot.getString("role")));
                        }
                    }
                }
            }
        });
        return users;
    }

    public static void getUserById(String id, UserCallback callback) {
        DocumentReference documentReference = collectionReference.document(id);

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    if (doc.exists()) {
                        Map<String, Object> data = doc.getData();
                        User user = new User(null, null, null, null, null);
                        user.setId(doc.getId());
                        user.setEmail(data.get("email").toString());
                        user.setUsername(data.get("username").toString());
                        user.setRole(data.get("role").toString());
                        callback.onUserLoaded(user);
                    } else {
                        callback.onFailure("Document not found");
                    }
                } else {
                    callback.onFailure("Error getting document: " + task.getException().getMessage());
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                callback.onFailure("Error getting document: " + e.getMessage());
            }
        });
    }
}
