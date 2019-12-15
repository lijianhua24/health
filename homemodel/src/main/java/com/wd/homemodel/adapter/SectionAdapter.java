package com.wd.homemodel.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.homemodel.R;
import com.wd.homemodel.bean.InfoSectionBean;
import com.wd.homemodel.bean.SectionBean;

import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private  setChage setChage;
    private List<SectionBean.ResultBean> sectionlist;
    private boolean che = true;
    public SectionAdapter(Context context, List<SectionBean.ResultBean> sectionlist) {
        this.context = context;
        this.sectionlist =sectionlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.zi_department_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof  MyHolder){
                if (che){
                }
                ((MyHolder) holder).name.setText(sectionlist.get(position).getName());
                ((MyHolder) holder).name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (setChage!=null){
                            int id = sectionlist.get(position).getId();
                            setChage.getChange(id);
                        }
                    }
                });
                //        如果下标和传回来的下标相等 那么确定是点击的条目 把背景设置一下颜色
                if (position == getmPosition()) {
                    ((MyHolder) holder).name.setTextColor(context.getResources().getColor(R.color.colorAccent));
                }else{
//            否则的话就全白色初始化背景
                    ((MyHolder) holder).name.setTextColor(context.getResources().getColor(R.color.colorPrimary));
                }
            }
    }

    @Override
    public int getItemCount() {
        return sectionlist.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private final TextView name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.zi_department_tv);
        }
    }
    public void onListenter(setChage setChage){
        this.setChage = setChage;
    }
    public interface setChage{
        void getChange(int i);
    }
    private  int mPosition;

    public int getmPosition() {
        return mPosition;
    }
}
