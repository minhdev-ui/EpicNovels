package com.example.appepicnovels.adapters;

import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.example.appepicnovels.LoginActivity;
import com.example.appepicnovels.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.*;
import org.jetbrains.annotations.NotNull;
import org.mindrot.jbcrypt.BCrypt;

public class UserAdapter {
    private static LoginActivity loginActivity; // Thêm biến static LoginActivity

    // Setter để thiết lập LoginActivity
    public static void setLoginActivity(LoginActivity activity) {
        loginActivity = activity;
    }

//    public static void login(String username, String password) {
//        // Kiểm tra đăng nhập
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        CollectionReference usersCollection = db.collection("Users");
//        Query userRef = usersCollection.whereEqualTo("username", username);
//        userRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                if(!queryDocumentSnapshots.isEmpty()) {
//                    DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
//                    String hashedPassword = documentSnapshot.getString("password");
//
//                    if (BCrypt.checkpw(password, hashedPassword)) {
//                        User user = new User(username, hashedPassword);
//                        // Gọi phương thức thành công đăng nhập từ View
//                        if (loginActivity != null) {
//                            loginActivity.onLoginSuccess(user);
//                        }
//                    } else {
//                        // Gọi phương thức thất bại đăng nhập từ View
//                        if (loginActivity != null) {
//                            loginActivity.onLoginFailure();
//                        }
//                    }
//                } else {
//                    Toast.makeText(LoginActivity.this, "", Toast.LENGTH_SHORT).show();
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull @NotNull Exception e) {
//                Log.e("Firestore", "Error: " + e.getMessage());
//            }
//        });
//    }
}

