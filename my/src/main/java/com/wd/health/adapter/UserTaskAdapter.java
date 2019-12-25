package com.wd.health.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
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
public class UserTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<UserTaskListBean.ResultBean> list;
    private View inflate;

    public UserTaskAdapter(Context context, List<UserTaskListBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getTaskType() == 1) {
            return 1;
        } else if (list.get(position).getTaskType() == 2) {
            return 2;
        }

        return 0;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View inflate = View.inflate(context, R.layout.item_user_task1, null);
            return new MyViewHolder(inflate);
        } else if (viewType == 2) {
            View inflate = View.inflate(context, R.layout.item_user_task2, null);
            return new MyViewHolder2(inflate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case 1:
                if (holder instanceof MyViewHolder) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                    ((MyViewHolder) holder).recyMrrw.setLayoutManager(linearLayoutManager);
                    ((MyViewHolder) holder).recyMrrw.setAdapter(new UserTaskItemAdapter(context,list));
                }
                break;
            case 2:

                if (holder instanceof MyViewHolder2) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                    ((MyViewHolder2) holder).recyYcxrw.setLayoutManager(linearLayoutManager);
                    ((MyViewHolder2) holder).recyYcxrw.setAdapter(new UserTaskItemAdapter(context,list));
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recy_mrrw)
        RecyclerView recyMrrw;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class MyViewHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.recy_ycxrw)
        RecyclerView recyYcxrw;
        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
