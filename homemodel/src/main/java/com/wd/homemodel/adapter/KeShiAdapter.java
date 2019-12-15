package com.wd.homemodel.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.homemodel.R;
import com.wd.homemodel.bean.DepartmentBean;

import java.util.List;

public class KeShiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<DepartmentBean.ResultBean> result;
    private  getChange getChange;
    private int thisPosition;
    public KeShiAdapter(FragmentActivity activity, List<DepartmentBean.ResultBean> result) {
        this.context = activity;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.keshi_layout, parent, false);
        return new MyHoler(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHoler){
            ((MyHoler) holder).keshi_name.setText(result.get(position).getDepartmentName());
            ((MyHoler) holder).keshi_name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = result.get(position).getId();
                    if (getChange!=null){
                        getChange.setChange(id,position);
                        notifyDataSetChanged();
                    }

                }
            });
            if (position == getmPosition()) {
                ((MyHoler) holder).keshi_name.setBackgroundColor(Color.WHITE);
            }else{
//            否则的话就全白色初始化背景
                ((MyHoler) holder).keshi_name.setBackgroundColor(Color.parseColor("#D5D5D8"));
            }
        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    class MyHoler extends RecyclerView.ViewHolder {

        private final TextView keshi_name;

        public MyHoler(@NonNull View itemView) {
            super(itemView);
            keshi_name = itemView.findViewById(R.id.keshi_name);
        }
    }
    public void setListenter(getChange getChange){
        this.getChange = getChange;
    }
    public interface getChange{
        void setChange(int id,int position);
    }
    private  int mPosition;

    public int getmPosition() {
        return mPosition;
    }

    public void setmPosition(int mPosition) {
        this.mPosition = mPosition;
    }
}
