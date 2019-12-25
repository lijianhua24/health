package com.wd.health.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.bean.JKZXBean;

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
public class JKZXAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<JKZXBean.ResultBean> list;
    private View inflate;

    public JKZXAdapter(Context context, List<JKZXBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        String[] split = list.get(position).getThumbnail().split(";");
        if (split.length == 0) {
            return 1;
        } else if (split.length == 3) {
            return 2;
        } else if (split.length == 1) {
            return 3;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 1) {
            View inflate = View.inflate(context, R.layout.item_jkzx, null);
            return new MyViewHolder(inflate);
        } else if (viewType == 2) {
            View inflate = View.inflate(context, R.layout.item_jkzx2, null);
            return new MyViewHolder2(inflate);
        } else if (viewType == 3) {
            View inflate = View.inflate(context, R.layout.item_jkzx3, null);
            return new MyViewHolder3(inflate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
      switch (itemViewType){
          case 1:
              if (holder instanceof  MyViewHolder){
                  ((MyViewHolder) holder).imageLkzx.setImageURI(list.get(position).getThumbnail());
                  ((MyViewHolder) holder).textMessage.setText(list.get(position).getTitle());
                  Date date = new Date(list.get(position).getCreateTime());
                  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日mm分ss秒");
                  ((MyViewHolder) holder).workJkzx.setText(simpleDateFormat.format(date));
              }
              break;
          case 2:
              String[] split = list.get(position).getThumbnail().split(";");
              if (holder instanceof  MyViewHolder2){
                  ((MyViewHolder2) holder).messageJkzx2.setText(list.get(position).getTitle());
                  ((MyViewHolder2) holder).image1Jkzx2.setImageURI(split[0]);
                  ((MyViewHolder2) holder).image2Jkzx2.setImageURI(split[1]);
                  ((MyViewHolder2) holder).image3Jkzx2.setImageURI(split[2]);
                  Date date = new Date(list.get(position).getCreateTime());
                  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日  hh:mm");
                ((MyViewHolder2) holder).timeJkzx2.setText(simpleDateFormat.format(date));
              }
              break;
          case 3:
              if (holder instanceof  MyViewHolder3){
                  ((MyViewHolder3) holder).messageJkzx3.setText(list.get(position).getTitle());
                  Date date = new Date(list.get(position).getCreateTime());
                  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日  hh:mm");
                  ((MyViewHolder3) holder).timeJkzx3.setText(simpleDateFormat.format(date));
              }

              break;
      }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_lkzx)
        SimpleDraweeView imageLkzx;
        @BindView(R.id.text_message)
        TextView textMessage;
        @BindView(R.id.work_jkzx)
        TextView workJkzx;
        @BindView(R.id.time_jkzx)
        TextView timeJkzx;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class MyViewHolder2 extends RecyclerView.ViewHolder {
        @BindView(R.id.message_jkzx2)
        TextView messageJkzx2;
        @BindView(R.id.image1_jkzx2)
        SimpleDraweeView image1Jkzx2;
        @BindView(R.id.image2_jkzx2)
        SimpleDraweeView image2Jkzx2;
        @BindView(R.id.image3_jkzx2)
        SimpleDraweeView image3Jkzx2;
        @BindView(R.id.writer_jkzx2)
        TextView writerJkzx2;
        @BindView(R.id.time_jkzx2)
        TextView timeJkzx2;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    static class MyViewHolder3 extends RecyclerView.ViewHolder {
        @BindView(R.id.message_jkzx3)
        TextView messageJkzx3;
        @BindView(R.id.writer_jkzx3)
        TextView writerJkzx3;
        @BindView(R.id.time_jkzx3)
        TextView timeJkzx3;
        public MyViewHolder3(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
