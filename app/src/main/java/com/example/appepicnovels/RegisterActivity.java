package com.example.appepicnovels;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appepicnovels.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.*;
import org.jetbrains.annotations.NotNull;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnRegister;
    private TextView tvLogin;
    private RadioButton rbtnRegister;

    private boolean isRadioButtonChecked = false;

    private LinearLayout loadingOverlay;

    private void registerUser() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
//        String email = etEmail.getText().toString().trim();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference usersCollection = db.collection("Users");
        Query query = usersCollection.whereEqualTo("username", username);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                loadingOverlay.setVisibility(View.VISIBLE);
                if(task.isSuccessful()) {
                    if(task.getResult() != null && !task.getResult().isEmpty()) {
                        Toast.makeText(RegisterActivity.this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
                        Task<DocumentReference> insertQuery = usersCollection.add(new User(username, hashPassword));
                        insertQuery.addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull @NotNull Task<DocumentReference> task) {
                                if(task.isComplete()) {
                                    loadingOverlay.setVisibility(View.GONE);
                                    Toast.makeText(RegisterActivity.this, "Tài khoản đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    loadingOverlay.setVisibility(View.GONE);
                                    Toast.makeText(RegisterActivity.this, "Tài khoản đăng ký thất bại!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                } else {
                    loadingOverlay.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this, "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        etConfirmPassword = findViewById(R.id.et_confirmpassword);
        btnRegister = findViewById(R.id.btn_register);
        tvLogin = findViewById(R.id.tv_login);
        rbtnRegister = findViewById(R.id.rbtn_register);
        loadingOverlay = findViewById(R.id.loadingLayout);
        loadingOverlay.setVisibility(View.GONE);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FirebaseApp.initializeApp(RegisterActivity.this);
                    if(rbtnRegister.isChecked()) {
                        if(etConfirmPassword.equals(etPassword)) {
                            Toast.makeText(RegisterActivity.this, "Not match password", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        registerUser();
                    } else {
                        Toast.makeText(RegisterActivity.this, "You need to agree to terms of use", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to LoginActivity
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        rbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRadioButtonChecked) {
                    rbtnRegister.setChecked(false);
                    isRadioButtonChecked = false;
                } else {
                    rbtnRegister.setChecked(true);
                    isRadioButtonChecked = true;
                }
            }
        });

        etConfirmPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                TextView error = findViewById(R.id.error_confirmpassword);
//                String pass = etPassword.getText().toString();
//                if(s.length() > 0 && pass.length() > 0) {
//                    if(!etConfirmPassword.getText().toString().equals(pass)) {
//                        error.setText("Not match password");
//                    } else {
//                        error.setText("");
//                        error.setVisibility(View.INVISIBLE);
//                    }
//                }
            }
        });
    }
}