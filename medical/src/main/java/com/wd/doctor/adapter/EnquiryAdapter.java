package com.wd.doctor.adapter;

import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.doctor.R;
import com.wd.doctor.bean.SuffererDetailBean;
import com.wd.doctor.view.SuffererOutActivity;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/17<p>
 * <p>更改时间：2019/12/17<p>
 */
public class EnquiryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    FragmentActivity activity;
    List<SuffererDetailBean.ResultBean> result;

    public EnquiryAdapter(FragmentActivity activity, List<SuffererDetailBean.ResultBean> result) {
        this.activity = activity;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(activity).inflate(R.layout.item_enquiry, parent, false);
        return new myViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof EnquiryAdapter.myViewHolder){
            ((myViewHolder) holder).enqu_item_tv_title.setText(result.get(position).getTitle());
            ((myViewHolder) holder).enqu_item_tv_nei.setText(result.get(position).getDetail());
            String s = String.valueOf(result.get(position).getReleaseTime());
            String format = DateFormatUtil.format(s);
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
    public static class DateFormatUtil {
        public static String format(String date) {
            if (TextUtils.isEmpty(date))
                return null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh-mm");
            Long time = new Long(date);
            return format.format(time);
        }
    }
}
