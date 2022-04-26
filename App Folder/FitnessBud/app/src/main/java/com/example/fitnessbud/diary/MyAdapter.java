package com.example.fitnessbud.diary;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessbud.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context ctx;
    private ArrayList food_id, calorie_id;

    public MyAdapter(Context ctx, ArrayList food_id, ArrayList calorie_id) {
        this.ctx = ctx;
        this.food_id = food_id;
        this.calorie_id = calorie_id;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.userentry, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.food_id.setText(String.valueOf(food_id.get(position)));
        holder.calorie_id.setText(String.valueOf(calorie_id.get(position)));

    }

    @Override
    public int getItemCount() {
        return food_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView food_id, calorie_id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            food_id = itemView.findViewById(R.id.textFood);
            calorie_id = itemView.findViewById(R.id.textCals);
        }
    }
}
