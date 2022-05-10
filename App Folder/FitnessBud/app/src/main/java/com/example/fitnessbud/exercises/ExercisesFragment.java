package com.example.fitnessbud.exercises;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessbud.R;
import com.example.fitnessbud.diary.AddActivity;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ExercisesFragment extends Fragment {

    TextView countdownText, displayExercise;
    Button countdownButton, updateBtn;
    EditText addTime, enterExercise;

    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds;
    private boolean timerRunning;

    String timeLeftText;
    Context ctx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_exercises, container, false);

        countdownText = view.findViewById(R.id.countdownText);
        countdownButton = view.findViewById(R.id.startBtn);
        addTime = view.findViewById(R.id.addTime);
        enterExercise = view.findViewById(R.id.enterExercise);
        displayExercise = view.findViewById(R.id.displayExercise);
        updateBtn = view.findViewById(R.id.updateBtn);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My notification", "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addExer = enterExercise.getText().toString().trim();
                String timer = addTime.getText().toString().trim();
                if (addExer.isEmpty()) {
                    enterExercise.setError("Please Enter an exercise!");
                } else {
                    displayExercise.setText(enterExercise.getText().toString().trim());
                }

                if (timer.isEmpty()) {
                    addTime.setError("Enter a time amount!");
                }

            }
        });

        //start btn
        countdownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addExer = enterExercise.getText().toString().trim();
                String timer = addTime.getText().toString().trim();
                if (!addExer.equals("") && !timer.equals("")) {
                    startStop();
                }
            }
        });

        return view;
    }

    public void startStop() {
        if (timerRunning) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    private void startTimer() {
        long userInput = Long.parseLong(addTime.getText().toString().trim());
        countDownTimer = new CountDownTimer(userInput * 60000, 1000) {
            @Override
            public void onTick(long remaining) {
                timeLeftInMilliseconds = remaining;
                updateTimer();
            }

            @Override
            public void onFinish() {
                notified();
            }
        }.start();

        countdownButton.setText("Stop Timer");
        timerRunning = true;
    }

    private void stopTimer() {
        countDownTimer.cancel();
        countdownButton.setText("Start Timer");
        timerRunning = false;
    }

    private void updateTimer() {
        int min = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;

        timeLeftText = "" + min;
        timeLeftText += ":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        countdownText.setText(timeLeftText);
    }

    private void notified() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "My notification");
        builder.setContentTitle("Countdown Timer");
        builder.setContentText("Your timer with a time of " +addTime.getText().toString().trim() + " min has ended");
        builder.setSmallIcon(R.drawable.ic_baseline_notifications_24);
        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getActivity());
        managerCompat.notify(1,builder.build());
    }
}