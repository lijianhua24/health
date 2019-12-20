package com.wd.health.adapter;

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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.app.App;
import com.wd.health.bean.DrugBean;
import com.wd.health.view.FindActivity;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<DrugBean.ResultBean> result;
    public SubjectAdapter(FragmentActivity activity, List<DrugBean.ResultBean> result) {
        this.context = activity;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.subject_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof  MyHolder){
                ((MyHolder) holder).name.setText(result.get(position).getName());
                ((MyHolder) holder).img.setImageURI(Uri.parse(result.get(position).getPicture()));
                ((MyHolder) holder).linear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id = result.get(position).getId();
                        SharedPreferences.Editor edit = App.sharedPreferences.edit();
                        edit.putInt("id",id);
                        edit.commit();
                        context.startActivity(new Intent(context, FindActivity.class));
                    }
                });
            }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    class MyHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView img;
        private final TextView name;
        private final LinearLayout linear;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.subject_img);
            name = itemView.findViewById(R.id.subject_name);
            linear = itemView.findViewById(R.id.subject_linear);
        }
    }
}
