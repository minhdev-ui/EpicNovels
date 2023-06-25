package com.example.appepicnovels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.appepicnovels.Firebase.FirebaseStore;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.*;
import org.jetbrains.annotations.NotNull;
import org.mindrot.jbcrypt.BCrypt;

public class ForgotPasswordActivity extends AppCompatActivity {

    private TextView tvBackToLogin;
    private Button btn_reset_password;
    private EditText et_email;
    private EditText et_newPassword;
    private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        tvBackToLogin = findViewById(R.id.tv_back_to_login);
        et_email = findViewById(R.id.et_email);
        et_newPassword = findViewById(R.id.et_newPassword);
        btn_reset_password = findViewById(R.id.btn_reset_password);

        tvBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đổi về LoginActivity
                Intent intent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CollectionReference user = FirebaseStore.db.collection("Users");
                Query query = user.whereEqualTo("email", et_email.getText().toString());
                query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            DocumentSnapshot documentSnapshot = queryDocumentSnapshots.getDocuments().get(0);
                            DocumentReference documentReference = user.document(documentSnapshot.getId());
                            try {
                                documentReference.update(
                                        "password", BCrypt.hashpw(et_newPassword.getText().toString(), BCrypt.gensalt(10))
                                );
                                Toast.makeText(ForgotPasswordActivity.this, "Password has changed", Toast.LENGTH_SHORT).show();
                            } catch (Exception e){
                                Toast.makeText(ForgotPasswordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ForgotPasswordActivity.this, "Email not existed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(ForgotPasswordActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
