package com.wd.health.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.bean.DepartmentListBean;

import java.util.List;

public class ConsultationTwoAdapter extends RecyclerView.Adapter {
    private List<DepartmentListBean.ResultBean> result;
    private Context context;
    private int  Oneon;
    public ConsultationTwoAdapter(List<DepartmentListBean.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.consultationtwo_item, null);
        Viewholder1 viewholder1 = new Viewholder1(inflate);
        return viewholder1;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        Viewholder1 viewholder1 = (Viewholder1) viewHolder;
        viewholder1.consultationtwo_name.setText(result.get(i).getDepartmentName());

        if (Oneon==i){
            viewholder1.consultationtwo_name.setTextColor(Color.BLUE);
        }else {
            viewholder1.consultationtwo_name.setTextColor(Color.GRAY);
        }

        viewholder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Oneon=i;
                notifyDataSetChanged();
                mOnItemClickListener.onItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    private OnItemClickListener mOnItemClickListener;

    public void onItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public class Viewholder1 extends RecyclerView.ViewHolder {

        private final TextView consultationtwo_name;


        public Viewholder1(@NonNull View itemView) {
            super(itemView);
            consultationtwo_name = itemView.findViewById(R.id.consultationtwo_name);
        }
    }
}
