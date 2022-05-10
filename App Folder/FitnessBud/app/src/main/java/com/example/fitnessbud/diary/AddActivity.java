package com.example.fitnessbud.diary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessbud.R;

public class AddActivity extends AppCompatActivity {

    DatabaseHelper mDbhelper;
    EditText addFood, addCals;
    Button addEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        getSupportActionBar().hide();

        addFood = findViewById(R.id.addFoodItemEditText);
        addCals = findViewById(R.id.addCaloriesEditText);
        addEntry = findViewById(R.id.addBtn);

        mDbhelper =  new DatabaseHelper(this);

        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String addFoodEntry = addFood.getText().toString().trim();
                String addCalsEntry = addCals.getText().toString().trim();

                Boolean checkData = mDbhelper.insertData(addFoodEntry,addCalsEntry);

                if(checkData == true) {
                    toastMessage("Entries Inserted");
                    addFood.setText("");
                    addCals.setText("");
                } else {
                    toastMessage("Something went wrong");
                }
            }
        });

    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}