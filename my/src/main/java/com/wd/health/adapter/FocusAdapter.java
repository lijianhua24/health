package com.wd.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.bean.FocusBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:11
 */
public class FocusAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private List<FocusBean.ResultBean> list;
    private View inflate;

    public FocusAdapter(Context context, List<FocusBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.item_focus, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).attentionDoctorImagePic.setImageURI(list.get(position).getImagePic());
            ((MyViewHolder) holder).attentionDoctorName.setText(list.get(position).getName());
            ((MyViewHolder) holder).tvFocusOnJobTitle.setText(list.get(position).getJobTitle());
            ((MyViewHolder) holder).attentionDoctorJobtitle.setText(list.get(position).getInauguralHospital());
            ((MyViewHolder) holder).attentionDoctorPraise.setText(list.get(position).getPraise());
            //((MyViewHolder) holder).attentionDoctorPraiseNum.setText("" + list.get(position).getNumber());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.attention_doctor_imagePic)
        SimpleDraweeView attentionDoctorImagePic;
        @BindView(R.id.attention_doctor_name)
        TextView attentionDoctorName;
        @BindView(R.id.tv_focusOn_jobTitle)
        TextView tvFocusOnJobTitle;
        @BindView(R.id.attention_doctor_jobtitle)
        TextView attentionDoctorJobtitle;
        @BindView(R.id.attention_doctor_inauguralhospital)
        TextView attentionDoctorInauguralhospital;
        @BindView(R.id.attention_doctor_praise)
        TextView attentionDoctorPraise;
        @BindView(R.id.attention_doctor_praiseNum)
        TextView attentionDoctorPraiseNum;
        @BindView(R.id.quxiao)
        Button quxiao;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
