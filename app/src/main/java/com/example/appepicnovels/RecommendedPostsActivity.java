package com.example.appepicnovels;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appepicnovels.adapters.RecommendedPostsAdapter;

import java.util.ArrayList;

import java.util.List;



public class RecommendedPostsActivity extends AppCompatActivity {
    private RecyclerView recommendedPostsRecyclerView;
    private RecommendedPostsAdapter adapter;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommended_posts);

        TextView discoverTextView = findViewById(R.id.discoverTextView);// Thêm dòng này
        discoverTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi TextView "Khám phá" được nhấn
                Intent intent = new Intent(RecommendedPostsActivity.this, DiscoverActivity.class);
                startActivity(intent);
            }
        });
        recommendedPostsRecyclerView = findViewById(R.id.recommendedPostsRecyclerView);
        recommendedPostsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<String> posts = getRecommendedPosts(); // Hàm lấy danh sách các bài đăng

        adapter = new RecommendedPostsAdapter(posts);
        recommendedPostsRecyclerView.setAdapter(adapter);
    }



    // Hàm lấy danh sách các bài đăng (giả sử đã có dữ liệu)
    private List<String> getRecommendedPosts() {
        List<String> posts = new ArrayList<>();
        // Thêm các bài đăng vào danh sách
        posts.add("Bài đăng 1");
        posts.add("Bài đăng 2");
        posts.add("Bài đăng 3");
        // ...
        return posts;
    }
    private class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

        private List<String> posts;

        public PostAdapter(List<String> posts) {
            this.posts = posts;
        }

        @NonNull
        @Override
        public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recommended_post, parent, false);
            return new PostViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
            String post = posts.get(position);
            holder.bind(post);
        }

        @Override
        public int getItemCount() {
            return posts.size();
        }

        private class PostViewHolder extends RecyclerView.ViewHolder {

            private TextView postTitleTextView;

            public PostViewHolder(@NonNull View itemView) {
                super(itemView);
                postTitleTextView = itemView.findViewById(R.id.recommendedPostsRecyclerView);
            }

            public void bind(String post) {
                postTitleTextView.setText(post);
            }
        }
    }
}




