package com.wd.homemodel.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.homemodel.bean.InfoSectionBean;

import java.util.List;

public class InfoSectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<InfoSectionBean.ResultBean> infoSectionList;
    public InfoSectionAdapter(Context context, List<InfoSectionBean.ResultBean> infoSectionList) {
        this.context = context;
        this.infoSectionList = infoSectionList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
