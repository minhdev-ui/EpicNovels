package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import com.example.myapplication.SQLite.SQLiteDatabaseHandler;
import com.example.myapplication.adapters.TabPaperAdapter;
import com.example.myapplication.adapters.UserAdapter;
import com.example.myapplication.models.User;
import com.example.myapplication.views.UserView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView userListView;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabaseHandler sqLiteDatabaseHandler = new SQLiteDatabaseHandler(this);
        SQLiteDatabase sqLiteDatabase = sqLiteDatabaseHandler.getWritableDatabase();

//        List<User> users = new ArrayList<>();
//        users.add(new User("Minh", null, null, null, null));
//        users.add(new User("Nhung", null, null, null, null));
//        users.add(new User("Linh", null, null, null, null));
//
//        userListView = findViewById(R.id.user_list_view);
//        userAdapter = new UserAdapter(this, users);
//        userListView.setAdapter(userAdapter);

        ViewPager2 viewPager = findViewById(R.id.view_paper);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        TabPaperAdapter paperAdapter = new TabPaperAdapter(this);
        viewPager.setAdapter(paperAdapter);

        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("Dành cho bạn");
                    break;
                case 1:
                    tab.setText("Khám phá");
                    break;
            }
        }).attach();
    }
}