package com.example.fitnessbud.diary;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.fitnessbud.R;

import java.util.ArrayList;

public class ViewEntriesActivity extends AppCompatActivity {

    private final static String TAG = "listData";

    DatabaseHelper dbHelper;

    private ListView displayData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entries);

        displayData = findViewById(R.id.listData);
        dbHelper = new DatabaseHelper(this);
        
        populateListView();

    }

    private void populateListView() {

        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        Cursor data = dbHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()) {
            listData.add(data.getString(1));
            listData.add(data.getString(2));
        }

        ListAdapter adapter  = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        displayData.setAdapter(adapter);

    }
}