package com.wd.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.bean.user.HistoryBean;

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
public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private List<HistoryBean.ResultBean> list;
    private View inflate;

    public HistoryAdapter(Context context, List<HistoryBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.item_history, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).historySimp.setImageURI(list.get(position).getImagePic());
            ((MyViewHolder) holder).historyName.setText(list.get(position).getDoctorName());
            ((MyViewHolder) holder).historyBumen.setText(list.get(position).getJobTitle());
            Date date = new Date(list.get(position).getInquiryTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd  hh:mm");
            ((MyViewHolder) holder).historyTime.setText(simpleDateFormat.format(date));

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.history_simp)
        SimpleDraweeView historySimp;
        @BindView(R.id.history_name)
        TextView historyName;
        @BindView(R.id.history_bumen)
        TextView historyBumen;
        @BindView(R.id.history_shijian)
        TextView historyShijian;
        @BindView(R.id.history_time)
        TextView historyTime;
        @BindView(R.id.history_btn_history)
        Button historyBtnHistory;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
