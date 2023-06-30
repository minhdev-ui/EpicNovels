package com.example.appepicnovels.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView;


import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import com.example.appepicnovels.DiscoverActivity;
import com.example.appepicnovels.R;
import com.example.appepicnovels.StoryDescription;
import com.example.appepicnovels.models.Story;

import java.util.ArrayList;
import java.util.List;

public class DiscoverAdapter extends ArrayAdapter<Story> {
private Context ct;
private ArrayList<Story> arr;
    public DiscoverAdapter(@NonNull Context context, int resource, @NonNull List<Story> objects) {
        super(context, resource, objects);
        this.ct = context;
        this.arr = new ArrayList<>(objects);
    }
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)ct.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_story, parent, false);
        }
        if(arr.size()>0){
            Story discoverActivity = this.arr.get(position);
            TextView nameStory = convertView.findViewById(R.id.nameStory);
            TextView nameChap = convertView.findViewById(R.id.nameChap);
            ImageView imgStory = convertView.findViewById(R.id.imgStory);

            nameStory.setText(discoverActivity.getName());
            nameChap.setText(discoverActivity.getChap());
            Glide.with(this.ct).load(discoverActivity.getLinkImg()).into(imgStory);
        }
        return convertView;
    }
}
