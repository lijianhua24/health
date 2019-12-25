package com.wd.health.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.bean.UserConsumptionRecordBean;

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
public class UserConsumptionRecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private Context context;
    private List<UserConsumptionRecordBean.ResultBean> list;
    private View inflate;

    public UserConsumptionRecordAdapter(Context context, List<UserConsumptionRecordBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.item_user_consumption, null);
        return new MyViewHolder(inflate);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).ExpensesrecordTextView.setText(list.get(position).getRemark());
            if (list.get(position).getDirection() == 2) {
                ((MyViewHolder) holder).ExpensesrecordConsumption.setText(list.get(position).getChangeNum() + "H币");
            } else if (list.get(position).getDirection() == 1) {
                ((MyViewHolder) holder).ExpensesrecordConsumption.setVisibility(View.GONE);
                ((MyViewHolder) holder).ExpensesrecordConsumption2.setVisibility(View.VISIBLE);
                ((MyViewHolder) holder).ExpensesrecordConsumption2.setText("+"+list.get(position).getChangeNum() + "H币");
            }

            Date date = new Date(list.get(position).getCreateTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
            ((MyViewHolder) holder).ExpensesrecordTime.setText(simpleDateFormat.format(date));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.Expensesrecord_TextView)
        TextView ExpensesrecordTextView;
        @BindView(R.id.Expensesrecord_time)
        TextView ExpensesrecordTime;
        @BindView(R.id.Expensesrecord_consumption)
        TextView ExpensesrecordConsumption;
        @BindView(R.id.Expensesrecord_consumption2)
        TextView ExpensesrecordConsumption2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
