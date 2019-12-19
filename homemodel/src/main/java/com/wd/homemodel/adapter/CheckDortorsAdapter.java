package com.wd.homemodel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wd.homemodel.R;
import com.wd.homemodel.bean.CheckDoctorsBean;

/*
 *@auther:王可欣
 *@Date: 2019/12/16
 *@Time:14:13
 *@Description:适配器
 **/
public class CheckDortorsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CheckDoctorsBean.ResultBean> list;
    private Context context;

    public CheckDortorsAdapter(List<CheckDoctorsBean.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_doctor, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder){
            Glide.with(context).load(list.get(position).getImagePic()).into(((MyHolder)holder).img);
            ((MyHolder)holder).name.setText(list.get(position).getDoctorName());
            ((MyHolder)holder).rela.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onCLickListener.onclick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (list.size()<3){
            return list.size();
        }
        return 3;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView name;
        private RelativeLayout rela;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            name=itemView.findViewById(R.id.name_item);
            rela=itemView.findViewById(R.id.rela);
        }
    }

    OnCLickListener onCLickListener;

    public void setOnCLickListener(OnCLickListener onCLickListener) {
        this.onCLickListener = onCLickListener;
    }

    public interface OnCLickListener{
        void onclick(int position);
    }

}
