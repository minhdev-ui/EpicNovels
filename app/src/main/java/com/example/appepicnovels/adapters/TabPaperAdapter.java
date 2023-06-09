package com.example.appepicnovels.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.appepicnovels.views.ExploreView;
import com.example.appepicnovels.views.RecommendView;

public class TabPaperAdapter extends FragmentStateAdapter {
    public TabPaperAdapter(@NonNull FragmentActivity fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new RecommendView();
            case 1:
                return new ExploreView();
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
