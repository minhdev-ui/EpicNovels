package com.example.appepicnovels;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appepicnovels.adapters.UserAdapter;
import com.example.appepicnovels.models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.*;
import org.jetbrains.annotations.NotNull;
import org.mindrot.jbcrypt.BCrypt;


public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private TextView tvForgotPassword;
    private  TextView  tvRegister;

    public LinearLayout loadingOverlay;

    public void login(String username, String password) {
        loadingOverlay.setVisibility(View.VISIBLE);
        // Kiểm tra đăng nhập
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference usersCollection = db.collection("Users");
        Query userRef = usersCollection.whereEqualTo("username", username);
        userRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                loadingOverlay.setVisibility(View.GONE);
                if(!queryDocumentSnapshots.isEmpty()) {
                    DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                    String hashedPassword = documentSnapshot.getString("password");

                    if (BCrypt.checkpw(password, hashedPassword)) {
                        User user = new User(documentSnapshot.getId(), username, hashedPassword, null, documentSnapshot.get("role").toString());
                        // Gọi phương thức thành công đăng nhập từ View
                        if (LoginActivity.this != null) {
                            Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            LoginActivity.this.onLoginSuccess(user);
                        }
                    } else {
                        // Gọi phương thức thất bại đăng nhập từ View
                        if (LoginActivity.this != null) {
                            Toast.makeText(LoginActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                            LoginActivity.this.onLoginFailure();
                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Login Fail! Please Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull @NotNull Exception e) {
                loadingOverlay.setVisibility(View.GONE);
                Log.e("Firestore", "Error: " + e.getMessage());
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UserAdapter.setLoginActivity(this);
        loadingOverlay = findViewById(R.id.loadingLayout);
        loadingOverlay.setVisibility(View.GONE);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);
        tvRegister = findViewById(R.id.tv_register);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                // Gọi phương thức đăng nhập của Controller
                LoginActivity.this.login(username, password);
            }
        });

        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gắn điều hướng sang màn hình ForgotPasswordActivity
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gắn điều hướng sang màn hình RegisterActivity
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onLoginSuccess(User user) {
        SharedPreferences sharedPreferences = getSharedPreferences("AccountPreference", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("id", user.getId());
        editor.putString("username", user.getUsername());
        editor.putString("email", user.getEmail());
        editor.putString("role", user.getRole());
        editor.apply();
        Intent intent;
        if(user.getRole().equals("ADMIN")) {
            intent = new Intent(this, AdminActivity.class);
        } else {
            intent = new Intent(this, DefaultActivity.class);
        }
        startActivity(intent);
        finish();
    }
    public void onLoginFailure() {

    }
}
