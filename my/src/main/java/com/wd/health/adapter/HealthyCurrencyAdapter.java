package com.wd.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.bean.HealthyCurrencyBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:11
 */
public class HealthyCurrencyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private Context context;
    private List<HealthyCurrencyBean.ResultBean> list;
    private View inflate;

    public HealthyCurrencyAdapter(Context context, List<HealthyCurrencyBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.item_healthycurrency, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).healthyMessageItem.setText(list.get(position).getContent());
            Date date = new Date(list.get(position).getCreateTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日  hh:mm");
            ((MyViewHolder) holder).healthyMessageTime.setText(simpleDateFormat.format(date));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.healthy_message_item)
        TextView healthyMessageItem;
        @BindView(R.id.healthy_message_time)
        TextView healthyMessageTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
