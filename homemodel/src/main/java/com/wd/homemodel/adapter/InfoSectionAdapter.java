package com.wd.homemodel.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
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
import com.wd.homemodel.bean.InfoSectionBean;
import com.wd.homemodel.view.SpyDetailsActivity;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.List;

public class InfoSectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<InfoSectionBean.ResultBean> infoSectionList;
    public InfoSectionAdapter(Context context, List<InfoSectionBean.ResultBean> infoSectionList) {
        Log.d("InfoSectionBean",infoSectionList.get(0).getSource());
        this.context = context;
        this.infoSectionList = infoSectionList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == 0){
            View inflate = LayoutInflater.from(context).inflate(R.layout.three_infosection_layout, parent, false);
            return new MyThreeHodel(inflate);
        }else if (viewType ==1){
            View inflate = LayoutInflater.from(context).inflate(R.layout.zi_infosection_layout, parent, false);
            return new MyoneHodel(inflate);
        }else if (viewType == 2){
            View inflate = LayoutInflater.from(context).inflate(R.layout.tow_infosection_layout, parent, false);
            return new MyTwoHodel(inflate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType){
            case 0:
                    if (holder instanceof MyThreeHodel){
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                        ((MyThreeHodel) holder).time.setText(simpleDateFormat.format(infoSectionList.get(position).getReleaseTime()));
                        ((MyThreeHodel) holder).dorect.setText(infoSectionList.get(position).getSource());
                        ((MyThreeHodel) holder).name.setText(infoSectionList.get(position).getTitle());
                        ((MyThreeHodel) holder).linear.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int id = infoSectionList.get(position).getId();
                                SharedPreferences.Editor edit = App.sharedPreferences.edit();
                                edit.putInt("zixunid",id);
                                edit.commit();
                                context.startActivity(new Intent(context, SpyDetailsActivity.class));
                            }
                        });
                    }
                break;
            case 1:
                if (holder instanceof  MyoneHodel){

                    Log.d("InfoSectionAdapter",infoSectionList.get(position).getSource());
                    ((MyoneHodel) holder).name.setText(infoSectionList.get(position).getTitle());
                    ((MyoneHodel) holder).dorect.setText(infoSectionList.get(position).getSource());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                   // ((MyViewHolder) holder).workJkzx.setText(simpleDateFormat.format(date));
                    ((MyoneHodel) holder).time.setText(simpleDateFormat.format(infoSectionList.get(position).getReleaseTime()));
                    ((MyoneHodel) holder).img.setImageURI(Uri.parse(infoSectionList.get(position).getThumbnail()));
                    ((MyoneHodel) holder).linear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int id = infoSectionList.get(position).getId();
                            SharedPreferences.Editor edit = App.sharedPreferences.edit();
                            edit.putInt("zixunid",id);
                            edit.commit();
                            context.startActivity(new Intent(context, SpyDetailsActivity.class));
                        }
                    });
                }
                break;
            case 2:
                if (holder instanceof MyTwoHodel){
                    String thumbnail = infoSectionList.get(position).getThumbnail();
                    String[] split = thumbnail.split(";");
                    ((MyTwoHodel) holder).img.setImageURI(Uri.parse(split[0]));
                    ((MyTwoHodel) holder).twoimg.setImageURI(Uri.parse(split[1]));
                    ((MyTwoHodel) holder).threeimg.setImageURI(Uri.parse(split[2]));
                    ((MyTwoHodel) holder).name.setText(infoSectionList.get(position).getTitle());
                    ((MyTwoHodel) holder).dorect.setText(infoSectionList.get(position).getSource());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
                    ((MyTwoHodel) holder).time.setText(simpleDateFormat.format(infoSectionList.get(position).getReleaseTime()));
                    ((MyTwoHodel) holder).linear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int id = infoSectionList.get(position).getId();
                            SharedPreferences.Editor edit = App.sharedPreferences.edit();
                            edit.putInt("zixunid",id);
                            edit.commit();
                            context.startActivity(new Intent(context, SpyDetailsActivity.class));
                        }
                    });

                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return infoSectionList.size();
    }

    @Override
    public int getItemViewType(int position) {
        String thumbnail = infoSectionList.get(position).getThumbnail();
        String[] split = thumbnail.split(";");
        int length = split.length;
        if(length ==0){
            return 0;
        }else if (length==1){
            return 1;
        }else if (length == 3){
            return 2;
        }
        return 0;
    }

    class MyoneHodel extends RecyclerView.ViewHolder {

        private final TextView dorect;
        private final TextView name;
        private final LinearLayout linear;
        private final TextView time;
        private final SimpleDraweeView img;

        public MyoneHodel(@NonNull View itemView) {

            super(itemView);
            dorect = itemView.findViewById(R.id.zi_infosection_dorect);
            name = itemView.findViewById(R.id.zi_infosection_name);
            time = itemView.findViewById(R.id.zi_infosection_time);
            img = itemView.findViewById(R.id.zi_infosection_img);
            linear = itemView.findViewById(R.id.zi_infosection_linear);
        }
    }

    class MyTwoHodel extends RecyclerView.ViewHolder {

        private final TextView dorect;
        private final TextView name;
        private final TextView time;
        private final LinearLayout linear;
        private final SimpleDraweeView img,twoimg,threeimg;

        public MyTwoHodel(@NonNull View itemView) {

            super(itemView);
            dorect = itemView.findViewById(R.id.two_dector);
            name = itemView.findViewById(R.id.two_name);
            time = itemView.findViewById(R.id.two_time);
            img = itemView.findViewById(R.id.two_oneimg);
            twoimg = itemView.findViewById(R.id.two_twoimg);
            threeimg = itemView.findViewById(R.id.two_threeimg);
            linear = itemView.findViewById(R.id.two_twolinear);
        }
    }

    class MyThreeHodel extends RecyclerView.ViewHolder {

        private final TextView dorect;
        private final TextView name;
        private final TextView time;
        private final LinearLayout linear;

        public MyThreeHodel(@NonNull View itemView) {

            super(itemView);
            dorect = itemView.findViewById(R.id.three_dector);
            name = itemView.findViewById(R.id.three_name);
            time = itemView.findViewById(R.id.three_time);
            linear = itemView.findViewById(R.id.three_linear);
        }
    }
}
