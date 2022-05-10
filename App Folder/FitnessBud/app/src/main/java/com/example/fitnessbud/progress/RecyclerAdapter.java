package com.example.fitnessbud.progress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitnessbud.R;
import com.example.fitnessbud.progress.RecycleAdapter;

import java.util.ArrayList;

class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    private Context ctx;
    private ArrayList weight, bmi;

    public RecycleAdapter (Context ctx, ArrayList weight, ArrayList bmi) {
        this.ctx = ctx;
        this.weight = weight;
        this.bmi = bmi;
    }

    @NonNull
    @Override
    public RecycleAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.progress_list, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleAdapter.MyViewHolder holder, int position) {
        holder.weightid.setText(String.valueOf(weight.get(position)));
        holder.bmiid.setText(String.valueOf(bmi.get(position)));
    }

    @Override
    public int getItemCount() {
        return bmi.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView weightid, bmiid;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            weightid = itemView.findViewById(R.id.weight_id);
            bmiid = itemView.findViewById(R.id.bmi_id);
        }
    }
}
