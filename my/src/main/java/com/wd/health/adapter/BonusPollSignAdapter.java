package com.wd.health.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.bean.user.FindUserSignBean;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/16 8:47
 */
public class BonusPollSignAdapter extends RecyclerView.Adapter<BonusPollSignAdapter.ViewHolder> {

    private Context mContext;
    private FindUserSignBean bean;
    private int checkinDays = 0;//签到天数
    private int fullCheckinDays = 7;//总共天数

    public BonusPollSignAdapter(Context mContext, FindUserSignBean bean) {
        this.mContext = mContext;
        this.bean = bean;
    }

    public void setSignData(int checkinDays, int fullCheckinDays){
        this.checkinDays = checkinDays;
        this.fullCheckinDays = fullCheckinDays;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(mContext, R.layout.item_bonus_sign,null);
        return new ViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

       //viewHolder.bonus_sign_item_day.setText((position+1)+"");
        viewHolder.bonus_sign_item_time.setText((position+1)+"天");
        if (position < bean.getResult()) {//如果是已签到，则更换图标，颜色
            viewHolder.bonus_sign_item_left.setBackgroundColor(ContextCompat.getColor(mContext, R.color.blue));
            viewHolder.bonus_sign_item_right.setBackgroundColor(ContextCompat.getColor(mContext, R.color.blue));
           viewHolder.bonus_sign_item_day.setBackgroundResource(R.mipmap.my_task_select);

        }
       /* if (position < (checkinDays - 1) || checkinDays == fullCheckinDays){
            viewHolder.bonus_sign_item_right.setBackgroundColor(ContextCompat.getColor(mContext,R.color.colorPrimary));
        }*/
    }

    @Override
    public int getItemCount() {
        return fullCheckinDays;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View bonus_sign_item_left,bonus_sign_item_right;
        private TextView bonus_sign_item_day,bonus_sign_item_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bonus_sign_item_left = itemView.findViewById(R.id.bonus_sign_item_left);
            bonus_sign_item_right= itemView.findViewById(R.id.bonus_sign_item_right);
            bonus_sign_item_day = itemView.findViewById(R.id.bonus_sign_item_day);
            bonus_sign_item_time = itemView.findViewById(R.id.bonus_sign_item_time);
        }
    }

     /*  viewHolder.bonus_sign_item_left.setBackgroundColor(ContextCompat.getColor(mContext, R.color.blue));
            viewHolder.bonus_sign_item_right.setBackgroundColor(ContextCompat.getColor(mContext, R.color.blue));


            viewHolder.bonus_sign_item_day.setBackgroundResource(R.drawable.border_sign_day_checkined);
            viewHolder.bonus_sign_item_day.setTextColor(R.color.white);
            viewHolder.bonus_sign_item_day.setText(bean.getResult() + "");
    //  viewHolder.bonus_sign_item_day.setText("");*/
}
