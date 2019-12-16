package com.wd.homemodel.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.homemodel.R;
import com.wd.homemodel.app.App;
import com.wd.homemodel.bean.UnitDiseaseBean;
import com.wd.homemodel.view.CmedicinesActivity;
import com.wd.homemodel.view.FindActivity;

import java.util.List;

public class UnitDiseaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<UnitDiseaseBean.ResultBean> result;
    public UnitDiseaseAdapter(FragmentActivity activity, List<UnitDiseaseBean.ResultBean> result) {
        this.context = activity;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.unitdiseas_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  MyHolder){
            ((MyHolder) holder).unitdiseas.setText(result.get(position).getName());
            ((MyHolder) holder).unitdiseas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = result.get(position).getId();
                    String name = result.get(position).getName();
                    SharedPreferences.Editor edit = App.sharedPreferences.edit();
                    edit.putInt("bingid",id);
                    edit.putString("name",name);
                    edit.commit();
                    context.startActivity(new Intent(context, CmedicinesActivity.class));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {

        private final TextView unitdiseas;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            unitdiseas = itemView.findViewById(R.id.unitdiseas);
        }
    }
}
