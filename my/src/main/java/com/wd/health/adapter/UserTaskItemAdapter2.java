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
public class UserTaskItemAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<UserTaskListBean.ResultBean> list;
    private View inflate;

    public UserTaskItemAdapter2(Context context, List<UserTaskListBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = View.inflate(context, R.layout.item_mrrw2, null);
        return new MyViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            if (list.get(position).getTaskType() == 2) {
                ((MyViewHolder) holder).textMrrw2.setText(list.get(position).getTaskName());
                ((MyViewHolder) holder).textHb2.setText("+" + list.get(position).getReward());
                if (list.get(position).getWhetherFinish()==1){
                    ((MyViewHolder) holder).firstButtonFinish2.setVisibility(View.VISIBLE);


                    if (list.get(position).getWhetherReceive() == 1) {
                        ((MyViewHolder) holder).collarButtonGoFinish2.setVisibility(View.VISIBLE);
                        ((MyViewHolder) holder).firstButtonFinish2.setVisibility(View.GONE);
                        ((MyViewHolder) holder).firstButtonGoFinish2.setVisibility(View.GONE);
                    } else if (list.get(position).getWhetherReceive() == 2) {
                        ((MyViewHolder) holder).collarButtonGoFinish2.setVisibility(View.GONE);
                        ((MyViewHolder) holder).firstButtonFinish2.setVisibility(View.VISIBLE);
                        ((MyViewHolder) holder).firstButtonGoFinish2.setVisibility(View.GONE);
                    } else if (list.get(position).getWhetherReceive() == 3) {
                        ((MyViewHolder) holder).collarButtonGoFinish2.setVisibility(View.GONE);
                        ((MyViewHolder) holder).firstButtonFinish2.setVisibility(View.GONE);
                        ((MyViewHolder) holder).firstButtonGoFinish2.setVisibility(View.VISIBLE);
                    }

                }else if (list.get(position).getWhetherFinish()==2){
                    ((MyViewHolder) holder).firstButtonGoFinish2.setVisibility(View.VISIBLE);
                }

            }

        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_mrrw2)
        TextView textMrrw2;
        @BindView(R.id.text_hb2)
        TextView textHb2;
        @BindView(R.id.first_button_finish2)
        Button firstButtonFinish2;
        @BindView(R.id.first_button_go_finish2)
        Button firstButtonGoFinish2;
        @BindView(R.id.collar_button_go_finish2)
        Button collarButtonGoFinish2;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
