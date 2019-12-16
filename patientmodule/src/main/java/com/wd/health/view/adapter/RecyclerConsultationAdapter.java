package com.wd.health.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.bean.DepartmentListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/13<p>
 * <p>更改时间：2019/12/13<p>
 */
public class RecyclerConsultationAdapter  extends RecyclerView.Adapter {

    private Context context;
    //科室列表集合
    private List<DepartmentListBean.ResultBean> datas = new ArrayList<>();

    private int Oneon;

    public RecyclerConsultationAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_sick_circle_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.consultation_name.setText(datas.get(i).getDepartmentName());
        if (Oneon == i) {
            myViewHolder.consultation_name.setTextColor(Color.BLUE);
        } else {
            myViewHolder.consultation_name.setTextColor(Color.GRAY);
        }
        //传点击的id
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Oneon = i;
                notifyDataSetChanged();
                mOnItemClickListener.onItemClick(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addData(List<DepartmentListBean.ResultBean> result) {
        if (result.size() > 0 && result != null) {
            datas.addAll(result);
        }
        notifyDataSetChanged();
    }

    //设置recycler条目的点击事件
    private OnItemClickListener mOnItemClickListener;

    public void onItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView consultation_name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            consultation_name = itemView.findViewById(R.id.consultation_name);
        }
    }
}