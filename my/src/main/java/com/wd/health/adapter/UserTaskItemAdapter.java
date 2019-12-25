package com.wd.health.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.bean.UserTaskListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:11
 */
public class UserTaskItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<UserTaskListBean.ResultBean> list;
    private View inflate;

    public UserTaskItemAdapter(Context context, List<UserTaskListBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = View.inflate(context, R.layout.item_mrrw, null);
        return new MyViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            if (list.get(position).getTaskType() == 1) {

                ((MyViewHolder) holder).textMrrw.setText(list.get(position).getTaskName());
                ((MyViewHolder) holder).textHb.setText("+" + list.get(position).getReward() + "H币");


                if (list.get(position).getWhetherFinish()==1){
                    ((MyViewHolder) holder).firstButtonFinish.setVisibility(View.VISIBLE);


                    if (list.get(position).getWhetherReceive() == 1) {
                        ((MyViewHolder) holder).collarButtonGoFinish.setVisibility(View.VISIBLE);
                        ((MyViewHolder) holder).firstButtonFinish.setVisibility(View.GONE);
                        ((MyViewHolder) holder).firstButtonGoFinish.setVisibility(View.GONE);
                    } else if (list.get(position).getWhetherReceive() == 2) {
                        ((MyViewHolder) holder).collarButtonGoFinish.setVisibility(View.GONE);
                        ((MyViewHolder) holder).firstButtonFinish.setVisibility(View.VISIBLE);
                        ((MyViewHolder) holder).firstButtonGoFinish.setVisibility(View.GONE);
                    } else if (list.get(position).getWhetherReceive() == 3) {
                        ((MyViewHolder) holder).collarButtonGoFinish.setVisibility(View.GONE);
                        ((MyViewHolder) holder).firstButtonFinish.setVisibility(View.GONE);
                        ((MyViewHolder) holder).firstButtonGoFinish.setVisibility(View.VISIBLE);
                    }

                }else if (list.get(position).getWhetherFinish()==2){
                    ((MyViewHolder) holder).firstButtonGoFinish.setVisibility(View.VISIBLE);
                }

                ((MyViewHolder) holder).firstButtonFinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doTask.onclick(position);
                    }
                });
                ((MyViewHolder) holder).firstButtonGoFinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doTask.onclick(position);
                    }
                });
                ((MyViewHolder) holder).collarButtonGoFinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doTask.onclick(position);
                    }
                });

            }
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_mrrw)
        TextView textMrrw;
        @BindView(R.id.text_hb)
        TextView textHb;
        @BindView(R.id.first_button_finish)
        Button firstButtonFinish;
        @BindView(R.id.first_button_go_finish)
        Button firstButtonGoFinish;
        @BindView(R.id.collar_button_go_finish)
        Button collarButtonGoFinish;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    DoTask doTask;

    public void setDoTask(DoTask doTask) {
        this.doTask = doTask;
    }

    public interface DoTask{
        int onclick(int position);
    }
}
