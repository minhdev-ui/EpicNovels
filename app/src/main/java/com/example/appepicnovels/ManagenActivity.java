package com.example.appepicnovels;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class ManagenActivity extends AppCompatActivity {

    private Button btnInfor;
    private Button btnChannelFollow;
    private Button btnSavedContent;
    private Button btnHistory;
    private Button btnGeneralSettings;
    private Button btnBlockedChannels;
    private Button btnVersion;
    private Button btnContact;
    private Button btnDeleteAccount;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);

        btnInfor = findViewById(R.id.btnInfor);
        btnChannelFollow = findViewById(R.id.btnChannelFollow);
        btnSavedContent = findViewById(R.id.btnSavedContent);
        btnHistory = findViewById(R.id.btnHistory);
        btnGeneralSettings = findViewById(R.id.btnGeneralSettings);
        btnBlockedChannels = findViewById(R.id.btnBlockedChannels);
        btnVersion = findViewById(R.id.btnVersion);
        btnContact = findViewById(R.id.btnContact);
        btnDeleteAccount = findViewById(R.id.btnDeleteAccount);
        btnLogout = findViewById(R.id.btnLogout);
        ImageView notificationIcon = findViewById(R.id.notificationIcon);

        btnInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến trang thông tin tài khoản
                Intent intent = new Intent(ManagenActivity.this, InformationActivity.class);
                startActivity(intent);
            }
        });

        notificationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagenActivity.this, NotificationActivity.class);
                startActivity(intent);
            }
        });
        btnChannelFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến trang theo dõi các kênh
                Intent intent = new Intent(ManagenActivity.this, ChannelFollowActivity.class);
                startActivity(intent);
            }
        });

        btnSavedContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến trang nội dung đã lưu
                Intent intent = new Intent(ManagenActivity.this, SavedContentActivity.class);
                startActivity(intent);
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến trang lịch sử
                Intent intent = new Intent(ManagenActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        btnGeneralSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến trang cài đặt chung
                Intent intent = new Intent(ManagenActivity.this, GeneralSettingsActivity.class);
                startActivity(intent);
            }
        });

        btnBlockedChannels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển đến trang các kênh đã chặn
                Intent intent = new Intent(ManagenActivity.this, BlockedChannelsActivity.class);
                startActivity(intent);
            }
        });
        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManagenActivity.this);
                builder.setTitle("Confirm account deletion");
                builder.setMessage("Are you sure you want to delete the account?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Xóa tài khoản và chuyển đến màn hình LoginActivity
                        Intent intent = new Intent(ManagenActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hủy xóa tài khoản
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ManagenActivity.this);
                builder.setTitle("Confirm exit");
                builder.setMessage("Are you sure you want to log out of the account?");
                builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Thoát khỏi tài khoản và chuyển đến màn hình LoginActivity
                        Intent intent = new Intent(ManagenActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Hủy thoát khỏi tài khoản
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        View homeIcon = findViewById(R.id.homeIcon);
        homeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ManagenActivity.this, DefaultActivity.class);
                startActivity(intent);
            }
        });
    }
}