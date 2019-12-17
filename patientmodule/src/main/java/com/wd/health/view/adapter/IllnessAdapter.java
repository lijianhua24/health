package com.wd.health.view.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.bean.UnitDiseaseBean;

import java.util.List;

public class IllnessAdapter extends RecyclerView.Adapter {
    private List<UnitDiseaseBean.ResultBean> result1;
    private Context context;

    public IllnessAdapter(List<UnitDiseaseBean.ResultBean> result1, Context context) {
        this.result1 = result1;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_illness_item, null);
        Viewholder1 viewholder1 = new Viewholder1(inflate);
        return viewholder1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Viewholder1 viewholder1 = (Viewholder1) viewHolder;
        viewholder1.illness_name.setText(result1.get(i).getName());



        viewholder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result1.size();
    }

    private OnItemClickListener mOnItemClickListener;

    public void onItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class Viewholder1 extends RecyclerView.ViewHolder {


        private final TextView illness_name;

        public Viewholder1(@NonNull View itemView) {
            super(itemView);
            illness_name = itemView.findViewById(R.id.illness_name);
        }
    }
}
