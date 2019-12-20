package com.wd.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.view.FindActivity;

public class PrecautionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private String[] split;
    public PrecautionsAdapter(FindActivity findActivity, String[] split) {
        this.context = findActivity;
        this.split = split;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.precautions_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  MyHolder){
            ((MyHolder) holder).name.setText(split[position]);
        }
    }

    @Override
    public int getItemCount() {
        return split.length;
    }
    class MyHolder extends RecyclerView.ViewHolder {

        private final TextView name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.Precautions_name);
        }
    }
}
