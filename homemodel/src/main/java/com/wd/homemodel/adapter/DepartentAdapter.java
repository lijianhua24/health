package com.wd.homemodel.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.homemodel.R;
import com.wd.homemodel.app.App;
import com.wd.homemodel.bean.DepartmentBean;
import com.wd.homemodel.bean.SectionBean;
import com.wd.homemodel.view.InquiryActivity;

import java.net.URI;
import java.util.List;

public class DepartentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<DepartmentBean.ResultBean> departmentlist;
    public DepartentAdapter(Context context, List<DepartmentBean.ResultBean> departmentlist) {
        this.context = context;
        this.departmentlist = departmentlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.zi_section_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder){

            ((MyHolder) holder).image.setImageURI(Uri.parse(departmentlist.get(position).getPic()));
            ((MyHolder) holder).name.setText(departmentlist.get(position).getDepartmentName());
            ((MyHolder) holder).linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int id = departmentlist.get(position).getId();
                    SharedPreferences.Editor edit = App.sharedPreferences.edit();
                    edit.putInt("position",position);
                    edit.putInt("id",id);
                    edit.commit();
                    context.startActivity(new Intent(context, InquiryActivity.class));
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return departmentlist.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView image;
        private final TextView name;
        private final LinearLayout linear;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.zi_section_mv);
            name = itemView.findViewById(R.id.zi_section_tv);
            linear = itemView.findViewById(R.id.zi_linear_tv);
        }
    }
}
