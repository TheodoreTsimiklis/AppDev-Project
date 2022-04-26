package com.example.fitnessbud.diary;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessbud.R;

public class DiaryFragment extends Fragment {

    private EditText goalCals;
    private TextView breakfastText, lunchText, dinnerText, displayCalGoals;
    private Button updateBtn, viewEntries;

    DatabaseHelper db;

    public DiaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v = inflater.inflate(R.layout.fragment_diary, container, false);

       // add food buttons
        addBreakfast(v);
        addLunch(v);
        addDinner(v);

        //view Entries
        viewEntries(v);

        updateBtn = v.findViewById(R.id.sendDataBtn);
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // setCalGoals
                setCalGoals(v);


                goalCals = v.findViewById(R.id.addGoalCalories);
                Bundle res = new Bundle();
                res.putString("df1", goalCals.getText().toString());
                getParentFragmentManager().setFragmentResult("dataFrom1", res);
                goalCals.setText("");


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

        displayCalGoals.setText(temp + " calories remaining");

        int max = Integer.parseInt(temp);
        progressBar.setMax(max);

        int totalLeftCals = max - allCals;

        progressBar.setProgress(totalLeftCals);


    }

    private void addBreakfast(View view) {
        breakfastText = view.findViewById(R.id.addBreakfastTextView);

        breakfastText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addLunch(View view) {
        lunchText = view.findViewById(R.id.addLunchTextView);

        lunchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addDinner(View view) {
        dinnerText = view.findViewById(R.id.addDinnerTextview);

        dinnerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddActivity.class);
                startActivity(intent);
            }
        });

    }

    private void viewEntries(View view) {
        viewEntries = view.findViewById(R.id.viewEntriesButton);

        viewEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ViewEntriesActivity.class);
                startActivity(i);
            }
        });
    }
}