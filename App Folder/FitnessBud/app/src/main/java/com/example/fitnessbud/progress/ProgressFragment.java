package com.example.fitnessbud.progress;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessbud.R;

import java.util.ArrayList;

public class ProgressFragment extends Fragment {

    DatabaseHelper db;
    TextView start, bmi, bmiresult;
    Button btn;
    EditText height, current;
    RecyclerView resultList;
    ArrayList<String> weightdata, bmidata;
    RecycleAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress, container, false);


        db = new DatabaseHelper(getActivity().getApplicationContext());
        start = view.findViewById(R.id.startText);
        resultList = view.findViewById(R.id.Entries);
        bmiresult = view.findViewById(R.id.BMIResult);
        bmi = view.findViewById(R.id.bmiText);
        height = view.findViewById(R.id.heightText);
        current = view.findViewById(R.id.currentText);
        resultList = view.findViewById(R.id.Entries);
        btn = view.findViewById(R.id.btn);

        weightdata = new ArrayList<>();
        bmidata = new ArrayList<>();

        adapter = new RecycleAdapter(getActivity(), weightdata, bmidata); //fix next
        if (!weightdata.isEmpty() || !bmidata.isEmpty()) {
            Filldata();
        }

        resultList.setAdapter(adapter);
        resultList.setLayoutManager(new LinearLayoutManager(getActivity()));

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                process(view);
                data();
                Filldata();
            }
        });
        return view;
    }

    public void process (View v) {
        if (height.getText().equals(null)) {
            showMessage ("Error", "Height is Blank. Please fill out");
        }
        double temp = Double.parseDouble(current.getText().toString().trim());
        double temp2 = Double.parseDouble(height.getText().toString().trim());
        boolean isInserted = db.insertData(temp, temp2);
        if (isInserted) {
            showMessage ("Success", "Data Inserted successfully");
        }
        else {
            showMessage ("Failure", "Data not Inserted");
        }
    }

    private void showMessage(String data, String toString) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(data);
        builder.setCancelable(true);
        builder.setMessage(toString);
        builder.show();
    }

    private boolean data() {
        weightdata.clear();     bmidata.clear();
        Cursor res = db.getALlData();
        if(res.getCount() == 0){
            showMessage ("Error", "No Entries Yet");
            return false;
        }
        boolean once = true;
        while(res.moveToNext()) {
            if (once) {
                height.setText(res.getString(1));
                once = false;
            }
            weightdata.add(res.getString(0));
            bmidata.add(res.getString(2));
        }
        bmiresult.setText(bmiweight());
        return true;
    }

    private String bmiweight() {
        double bmi2 = Double.parseDouble(bmidata.get(bmidata.size() - 1));
        if (bmi2 >= 30) {
            return "You are Obese";
        }
        else if (bmi2 < 30 && bmi2 >= 25) {
            return "You are Overweight";
        }
        else if (bmi2 < 25 && bmi2 >= 18.5) {
            return "You have a normal weight";
        }
        else if (bmi2 < 18.5) {
            return "You are underweight";
        }
        else {
            return "Error";
        }
    }

    private void Filldata () {
        start.setText(weightdata.get(0));
        current.setText(weightdata.get(weightdata.size() - 1));
        bmi.setText(bmidata.get(weightdata.size() - 1));
    }
}