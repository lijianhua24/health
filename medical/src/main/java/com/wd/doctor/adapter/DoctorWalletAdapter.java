package com.wd.doctor.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.doctor.R;
import com.wd.doctor.bean.QueryRevenueBean;
import com.wd.doctor.view.DoctorWalletActivity;
import com.wd.doctor.view.SuffererOutActivity;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/27<p>
 * <p>更改时间：2019/12/27<p>
 */
public class DoctorWalletAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int ONE=1;
    private final int TWO=2;
    private final int THREE=3;
    private final int FOUR=4;
    DoctorWalletActivity activity;
    List<QueryRevenueBean.ResultBean> result;

    public DoctorWalletAdapter(DoctorWalletActivity activity, List<QueryRevenueBean.ResultBean> result) {
        this.activity = activity;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_queryrevenue, parent, false);
        return new myViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DoctorWalletAdapter.myViewHolder) {
            ((myViewHolder) holder).money.setText(result.get(position).getMoney()+"H币");
            int incomeType = result.get(position).getIncomeType();
            if (incomeType == ONE) {
                ((myViewHolder) holder).Type.setText("问诊咨询");
            } else if (incomeType==TWO) {
                ((myViewHolder) holder).Type.setText("采纳建议");
            } else if (incomeType == THREE) {
                ((myViewHolder) holder).Type.setText("收礼物");
            } else if (incomeType == FOUR) {
                ((myViewHolder) holder).Type.setText("提现");
            }
            String s = String.valueOf(result.get(position).getRecordTime());
            String format = DateFormatUtildian.format(s);
            ((myViewHolder) holder).recordTime.setText(format);
            int direction = result.get(position).getDirection();
            if (direction == ONE) {
                ((myViewHolder) holder).direction.setText("+");
                ((myViewHolder) holder).direction.setTextColor(activity.getResources().getColor(R.color.hong));
                ((myViewHolder) holder).money.setTextColor(activity.getResources().getColor(R.color.hong));

            } else if (direction == TWO) {
                ((myViewHolder) holder).direction.setText("-");
                ((myViewHolder) holder).direction.setTextColor(activity.getResources().getColor(R.color.blue));
                ((myViewHolder) holder).money.setTextColor(activity.getResources().getColor(R.color.blue));
            }
        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    class myViewHolder extends RecyclerView.ViewHolder {

        private final TextView Type,direction,money,recordTime;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            Type = itemView.findViewById(R.id.revenue_tv_incomeType);
            direction = itemView.findViewById(R.id.revenue_tv_direction);
            money = itemView.findViewById(R.id.revenue_tv_money);
            recordTime = itemView.findViewById(R.id.revenue_tv_recordTime);
        }
    }
    //转换时间
    public static class DateFormatUtildian {
        public static String format(String date) {
            if (TextUtils.isEmpty(date))
                return null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Long time = new Long(date);
            return format.format(time);
        }
    }
}
