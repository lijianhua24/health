package com.wd.homemodel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.homemodel.R;
import com.wd.homemodel.bean.InfoSectionBean;
import com.wd.homemodel.bean.SectionBean;

import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<SectionBean.ResultBean> sectionlist;
    public SectionAdapter(Context context, List<SectionBean.ResultBean> sectionlist) {
        this.context = context;
        this.sectionlist =sectionlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.zi_department_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof  MyHolder){
                ((MyHolder) holder).name.setText(sectionlist.get(position).getName());
            }
    }

    @Override
    public int getItemCount() {
        return sectionlist.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private final TextView name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.zi_department_tv);
        }
    }
}
