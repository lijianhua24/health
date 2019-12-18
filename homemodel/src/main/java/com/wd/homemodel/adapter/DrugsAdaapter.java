package com.wd.homemodel.adapter;

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

import com.wd.homemodel.R;
import com.wd.homemodel.app.App;
import com.wd.homemodel.bean.SearchBean;
import com.wd.homemodel.view.FindActivity;
import com.wd.homemodel.view.SouActivity;

import java.util.List;

public class DrugsAdaapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<SearchBean.ResultBean.DrugsSearchVoListBean> name;

    public DrugsAdaapter(SouActivity souActivity, List<SearchBean.ResultBean.DrugsSearchVoListBean> drugsSearchVoList) {
        this.context = souActivity;
        this.name = drugsSearchVoList;
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
            ((MyHolder) holder).name.setText(name.get(position).getDrugsName());
            ((MyHolder) holder).cuowu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int drugsId = name.get(position).getDrugsId();
                    SharedPreferences.Editor edit = App.sharedPreferences.edit();
                    edit.putInt("id",drugsId);
                    edit.commit();
                    context.startActivity(new Intent(context, FindActivity.class));
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
        private final RelativeLayout cuowu;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.disease_name);
            cuowu = itemView.findViewById(R.id.disease_relat);
        }
    }
}
