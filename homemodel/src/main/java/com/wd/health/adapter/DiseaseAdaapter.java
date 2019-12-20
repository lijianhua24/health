package com.wd.health.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.app.App;
import com.wd.health.bean.SearchBean;
import com.wd.health.view.CmedicinesActivity;
import com.wd.health.view.SouActivity;

import java.util.List;

public class DiseaseAdaapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<SearchBean.ResultBean.DiseaseSearchVoListBean> name;

    public DiseaseAdaapter(SouActivity souActivity, List<SearchBean.ResultBean.DiseaseSearchVoListBean> diseaseSearchVoList) {
        this.context = souActivity;
        this.name = diseaseSearchVoList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.disease_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder){
            ((MyHolder) holder).name.setText(name.get(position).getDiseaseName());
            ((MyHolder) holder).relat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int diseaseId = name.get(position).getDiseaseId();
                    String diseaseName = name.get(position).getDiseaseName();
                    SharedPreferences.Editor edit = App.sharedPreferences.edit();
                    edit.putInt("bingid",diseaseId);
                    edit.putString("name",diseaseName);
                    edit.commit();
                    context.startActivity(new Intent(context, CmedicinesActivity.class));

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return name.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final RelativeLayout relat;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.disease_name);
            relat = itemView.findViewById(R.id.disease_relat);
        }
    }
}
