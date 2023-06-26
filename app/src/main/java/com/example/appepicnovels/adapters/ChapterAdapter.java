package com.example.appepicnovels.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.appepicnovels.R;
import com.example.appepicnovels.models.Chapter;

import java.util.List;

public class ChapterAdapter extends ArrayAdapter<Chapter> {
    private Context context;
    private List<Chapter> items;
    public ChapterAdapter(Context context, List<Chapter> items) {
        super(context, R.layout.list_chapter_item, items);
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_chapter_item, parent, false);
        }
        if(items.size() > 0) {
            Chapter chapter = this.items.get(position);
            TextView itemTextView = convertView.findViewById(R.id.nameChapter);
            TextView itemAmountView = convertView.findViewById(R.id.amountView);
            itemTextView.setText(chapter.getName());
            itemAmountView.setText(chapter.getViews().toString());
        }

        return convertView;
    }
}
