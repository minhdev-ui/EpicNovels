package com.example.appepicnovels;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DURATION = 2000; // 2 seconds

    private View splashView;

    private Button btn_login;
    private Button btn_signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        splashView = findViewById(R.id.splash_view);
        btn_login = findViewById(R.id.login_button);
        btn_signIn = findViewById(R.id.signIn_button);
        btn_login.setVisibility(View.GONE);
        btn_signIn.setVisibility(View.GONE);

        // Optional: Customize the splash screen, e.g., perform some initialization

        splashView.setAlpha(0f);
        splashView.animate()
                .alpha(1f)
                .setDuration(1000) // Fade in duration
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                SharedPreferences sharedPreferences = getSharedPreferences("AccountPreference", Context.MODE_PRIVATE);
                                if(sharedPreferences.getString("id", null) != null) {
                                    Intent intent;
                                    if(sharedPreferences.getString("role", null).equals("ADMIN")) {
                                        intent = new Intent(SplashActivity.this, AdminActivity.class);
                                    } else {
                                        intent = new Intent(SplashActivity.this, DefaultActivity.class);
                                    }
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // Start the main activity or perform other actions
                                    btn_login.setVisibility(View.VISIBLE);
                                    btn_signIn.setVisibility(View.VISIBLE);
                                }
                            }
                        }, SPLASH_DURATION);
                    }
                });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
