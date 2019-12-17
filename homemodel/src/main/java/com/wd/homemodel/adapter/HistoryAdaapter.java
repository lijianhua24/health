package com.wd.homemodel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.homemodel.R;
import com.wd.homemodel.bean.HistoryBean;
import com.wd.homemodel.view.SouActivity;

import java.util.ArrayList;

public class HistoryAdaapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<HistoryBean> name;
    public HistoryAdaapter(SouActivity souActivity, ArrayList<HistoryBean> name) {
        this.context = souActivity;
        this.name = name;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.history_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder){
            ((MyHolder) holder).name.setText(name.get(position).getName()+position);
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
        private final ImageView cuowu;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.history_name);
            cuowu = itemView.findViewById(R.id.history_cuowu);
        }
    }
}
