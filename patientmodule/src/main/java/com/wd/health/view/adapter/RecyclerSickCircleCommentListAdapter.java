package com.wd.health.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.bean.QueryCommentBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RecyclerSickCircleCommentListAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<QueryCommentBean.ResultBean> datas = new ArrayList<>();

    public RecyclerSickCircleCommentListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_sick_circle_comment_list_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        myViewHolder.adapter_comment_list_tv_nickName.setText(datas.get(i).getNickName() + "");
        myViewHolder.adapter_comment_list_tv_content.setText(datas.get(i).getContent() + "");
        long commentTime = datas.get(i).getCommentTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String times = formatter.format(commentTime);
        myViewHolder.adapter_comment_list_tv_commentTime.setText(times);
        String headPic = datas.get(i).getHeadPic();
        Uri parse = Uri.parse(headPic);
        myViewHolder.adapter_comment_list_iv_headPic.setImageURI(parse);
        myViewHolder.adapter_comment_list_tv_supportNum.setText(datas.get(i).getSupportNum() + "");
        myViewHolder.adapter_comment_list_tv_opposeNum.setText(datas.get(i).getOpposeNum() + "");
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addData(List<QueryCommentBean.ResultBean> sickCircleCommentListBeanResult) {
        if (sickCircleCommentListBeanResult != null && sickCircleCommentListBeanResult.size() > 0) {
            datas.addAll(sickCircleCommentListBeanResult);
        }
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView adapter_comment_list_iv_headPic;
        private final TextView adapter_comment_list_tv_nickName;
        private final TextView adapter_comment_list_tv_content;
        private final TextView adapter_comment_list_tv_commentTime;
        private final TextView adapter_comment_list_tv_supportNum;
        private final TextView adapter_comment_list_tv_opposeNum;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            adapter_comment_list_iv_headPic = itemView.findViewById(R.id.adapter_comment_list_iv_headPic);
            adapter_comment_list_tv_nickName = itemView.findViewById(R.id.adapter_comment_list_tv_nickName);
            adapter_comment_list_tv_commentTime = itemView.findViewById(R.id.adapter_comment_list_tv_commentTime);
            adapter_comment_list_tv_content = itemView.findViewById(R.id.adapter_comment_list_tv_content);
            adapter_comment_list_tv_supportNum = itemView.findViewById(R.id.adapter_comment_list_tv_supportNum);
            adapter_comment_list_tv_opposeNum = itemView.findViewById(R.id.adapter_comment_list_tv_opposeNum);
        }
    }
}
