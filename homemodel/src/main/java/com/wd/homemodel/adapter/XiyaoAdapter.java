package com.wd.homemodel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.homemodel.R;
import com.wd.homemodel.view.CmedicinesActivity;

public class XiyaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private  String[] split;
    private Context context;
    public XiyaoAdapter(CmedicinesActivity cmedicinesActivity, String[] split1) {
        this.context = cmedicinesActivity;
        this.split = split1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.xiyao_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder){
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
            name = itemView.findViewById(R.id.xiyao_name);
        }
    }
}