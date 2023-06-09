package com.example.appepicnovels.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.appepicnovels.R;

public class RecommendView extends Fragment {
    public RecommendView() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstantState) {
        return inflater.inflate(R.layout.recommend, container, false);
    }

//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstantState) {
//        Bundle args = getArguments();
//        ((TextView) view.findViewById(R.layout))
//    }
}
