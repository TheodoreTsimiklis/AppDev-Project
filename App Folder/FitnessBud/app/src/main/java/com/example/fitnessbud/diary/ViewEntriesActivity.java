package com.example.fitnessbud.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fitnessbud.R;

import java.util.ArrayList;

public class ViewEntriesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> food,calories;
    DatabaseHelper db;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entries);

        db = new DatabaseHelper(this);
        food = new ArrayList<>();
        calories = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new MyAdapter(this,food,calories);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        displayData();

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            }
        };
    }

    private void displayData() {

        Cursor cursor = db.getData();

        if (cursor.getCount() == 0) {
            toastMessage("No entry exists");
            return;
        } else {
            while(cursor.moveToNext()) {
                food.add(cursor.getString(1));
                calories.add(cursor.getString(2));
            }
        }

    }

    private void toastMessage(String message) {
        Toast.makeText(ViewEntriesActivity.this, message, Toast.LENGTH_SHORT).show();
    }

}