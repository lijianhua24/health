package com.wd.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.bean.SearchBean;
import com.wd.health.view.SouActivity;

import java.util.List;

public class DoctorAdaapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<SearchBean.ResultBean.DoctorSearchVoListBean> name;

    public DoctorAdaapter(SouActivity souActivity, List<SearchBean.ResultBean.DoctorSearchVoListBean> doctorSearchVoList) {
        this.context = souActivity;
        this.name = doctorSearchVoList;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.doctor_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder){
            ((MyHolder) holder).name.setText(name.get(position).getDoctorName());
            ((MyHolder) holder).cuowu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

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
            name = itemView.findViewById(R.id.doctor_name);
            cuowu = itemView.findViewById(R.id.doctor_relat);
        }
    }
}
