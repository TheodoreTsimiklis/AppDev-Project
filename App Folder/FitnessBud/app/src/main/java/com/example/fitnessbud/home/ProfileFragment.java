package com.example.fitnessbud.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.fitnessbud.R;
import com.example.fitnessbud.diary.DatabaseHelper;

public class ProfileFragment extends Fragment {

    TextView goalCals;
    private TextView displayCalGoals;

    DatabaseHelper db;

    public ProfileFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        getParentFragmentManager().setFragmentResultListener("dataFrom1", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                String data = result.getString("df1");
                goalCals = v.findViewById(R.id.addGoalCalories);
                goalCals.setText(data);

                setCalGoals(v);

            }
        });

        return v;
    }

    private void setCalGoals(View view) {
        goalCals = view.findViewById(R.id.addGoalCalories);
        displayCalGoals = view.findViewById(R.id.displayCalorieGoal);
        ProgressBar progressBar = view.findViewById(R.id.trackerBar);

        db = new DatabaseHelper(getActivity().getApplicationContext());

        String temp = goalCals.getText().toString().trim();

        int allCals = db.addCalories();

        int max = Integer.parseInt(temp);
        progressBar.setMax(max);

        int totalLeftCals = max - allCals;

        progressBar.setProgress(totalLeftCals);
        displayCalGoals.setText(totalLeftCals + " calories Remaining");

    }

}