package com.wd.homemodel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.homemodel.R;
import com.wd.homemodel.bean.UnitDiseaseBean;

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
