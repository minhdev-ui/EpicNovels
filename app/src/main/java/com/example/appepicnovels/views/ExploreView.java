package com.example.appepicnovels.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.appepicnovels.R;

public class ExploreView extends Fragment {
    public ExploreView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstantState) {
        return inflater.inflate(R.layout.explore, container, false);
    }
}
