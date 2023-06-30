package com.example.appepicnovels.adapters;

import android.content.Context;
import android.os.Build;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appepicnovels.R;
import com.example.appepicnovels.interfaces.UserCallback;
import com.example.appepicnovels.models.Chapter;
import com.example.appepicnovels.models.Comment;
import com.example.appepicnovels.models.User;
import com.example.appepicnovels.services.UserService;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.Timestamp;
import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class CommentAdapter extends ArrayAdapter<Comment> {
    private Context context;
    private List<Comment> items;
    public CommentAdapter(Context context, List<Comment> items) {
        super(context, R.layout.list_comment_item, items);
        this.context = context;
        this.items = items;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_comment_item, parent, false);
        }
        if(items.size() > 0) {
            Comment comment = this.items.get(position);
            TextView userName = convertView.findViewById(R.id.nameAccount);
            UserService.getUserById(comment.getUserId(), new UserCallback() {
                @Override
                public void onUserLoaded(User user) {
                    userName.setText(user.getUsername());
                }

                @Override
                public void onFailure(String error) {}
            });
            TextView timeComment = convertView.findViewById(R.id.timeComment);
            CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(comment.getTimestamp().toDate().getTime(), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS);
            timeComment.setText(timeAgo.toString());
            TextView content = convertView.findViewById(R.id.content);
            content.setText(comment.getContent());
        }

        return convertView;
    }
}
