package com.wd.health.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.bean.RecordingBean;
import com.wd.health.view.JgActivity;

import java.util.List;

public class RecordingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<RecordingBean.ResultBean> result;
    private  getposition getposition;
    public RecordingAdapter(JgActivity jgActivity, List<RecordingBean.ResultBean> result) {
        this.context = jgActivity;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0){
            View inflate = LayoutInflater.from(context).inflate(R.layout.one_recording_layout, parent, false);
            return new MyOneRecordHolder(inflate);
        }else if (viewType == 1){
            View inflate = LayoutInflater.from(context).inflate(R.layout.two_recording_layout, parent, false);
            return new MyTwoRecordHolder(inflate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType){
            case 0:
                if (holder instanceof MyOneRecordHolder){
                    ((MyOneRecordHolder) holder).one_recording_img.setImageURI(Uri.parse(result.get(position).getDoctorHeadPic()));
                    ((MyOneRecordHolder) holder).one_recording_tx.setText(result.get(position).getContent());
                    if (getposition!=null){
                        getposition.getposition(position);
                    }
                }
                break;
            case 1:
                    if (holder instanceof MyTwoRecordHolder){
                        ((MyTwoRecordHolder) holder).two_recording_img.setImageURI(Uri.parse(result.get(position).getUserHeadPic()));
                        ((MyTwoRecordHolder) holder).two_recording_tx.setText(result.get(position).getContent());
                        if (getposition!=null){
                            getposition.getposition(position);
                        }
                    }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    @Override
    public int getItemViewType(int position) {
        int direction = result.get(position).getDirection();
        if (direction == 1){
            return 1;
        }else if (direction == 2){
            return 0;
        }
        return 0;
    }
    class MyOneRecordHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView one_recording_img;
        private final TextView one_recording_tx;
        public MyOneRecordHolder(@NonNull View itemView) {
            super(itemView);
            one_recording_img = itemView.findViewById(R.id.one_recording_img);
            one_recording_tx = itemView.findViewById(R.id.one_recording_tx);
        }
    }

    class MyTwoRecordHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView two_recording_img;
        private final TextView two_recording_tx;

        public MyTwoRecordHolder(@NonNull View itemView) {
            super(itemView);
            two_recording_img = itemView.findViewById(R.id.two_recording_img);
            two_recording_tx = itemView.findViewById(R.id.two_recording_tx);
        }
    }
    public void setId(getposition getposition){
    this.getposition = getposition;
    }
    public interface getposition{
        void getposition(int position);
    }
}
