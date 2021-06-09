package com.songxuanyi209050926.personalfinance.activity.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.songxuanyi209050926.personalfinance.R;
import com.songxuanyi209050926.personalfinance.activity.BookKeepActivity;


public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        Intent intent = new Intent(getActivity(), BookKeepActivity.class);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.in_from_up, android.R.anim.fade_out);

        return root;
    }

}