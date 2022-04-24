package com.example.fitnessbud.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessbud.R;

public class AddActivity extends AppCompatActivity {

    DatabaseHelper mDbhelper;
    TextView addFood, addCals;
    Button addEntry, deleteEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addFood = findViewById(R.id.addFoodItemTextView);
        addCals = findViewById(R.id.addCaloriesTextView);
        addEntry = findViewById(R.id.addBtn);
        deleteEntry = findViewById(R.id.deleteBtn);
        mDbhelper =  new DatabaseHelper(this);

        addEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foodEntry = addFood.getText().toString();
                String calsEntry = addCals.getText().toString();

                if (foodEntry.length() != 0) {
                    addData(foodEntry);
                    addFood.setText("");
                } else {
                    toastMessage("You have to add data in the food/meal text field!");
                }

                if (calsEntry.length() != 0) {
                    addData(calsEntry);
                    addCals.setText("");
                } else {
                    toastMessage("You have to add data in the calorie text field");
                }
            }
        });

    }

    public void addData(String newEntry) {

        boolean insertData = mDbhelper.addData(newEntry);

        if (insertData) {
           toastMessage("Added to entries");
        } else {
            toastMessage("Something went wrong");
        }

    }

    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}