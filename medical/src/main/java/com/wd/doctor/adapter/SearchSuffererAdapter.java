package com.wd.doctor.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.doctor.R;
import com.wd.doctor.bean.SearchSuffererBean;
import com.wd.doctor.view.SearchSuffererActivity;
import com.wd.doctor.view.SuffererOutActivity;

import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/18<p>
 * <p>更改时间：2019/12/18<p>
 */
public class SearchSuffererAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<SearchSuffererBean.ResultBean> result;
    SearchSuffererActivity activity;

    public SearchSuffererAdapter(List<SearchSuffererBean.ResultBean> result, SearchSuffererActivity activity) {
        this.result = result;
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_enquiry, parent, false);
        return new myViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SearchSuffererAdapter.myViewHolder) {
            ((myViewHolder) holder).enqu_item_tv_title.setText(result.get(position).getTitle());
            ((myViewHolder) holder).enqu_item_tv_nei.setText(result.get(position).getDetail());
            String s = String.valueOf(result.get(position).getReleaseTime());
            String format = EnquiryAdapter.DateFormatUtil.format(s);
            ((myViewHolder) holder).enqu_item_tv_data.setText(format);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int sickCircleId = result.get(position).getSickCircleId();
                    Intent intent = new Intent();
                    intent.putExtra("sickCircleId",sickCircleId);
                    intent.setClass(activity, SuffererOutActivity.class);
                    activity.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    class myViewHolder extends RecyclerView.ViewHolder {

        private final TextView enqu_item_tv_title,enqu_item_tv_nei,enqu_item_tv_data;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            enqu_item_tv_title = itemView.findViewById(R.id.enqu_item_tv_title);
            enqu_item_tv_nei = itemView.findViewById(R.id.enqu_item_tv_nei);
            enqu_item_tv_data = itemView.findViewById(R.id.enqu_item_tv_data);
        }
    }
}
