package com.wd.health.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dou361.ijkplayer.widget.IjkVideoView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.health.R;
import com.wd.health.bean.VideoSortBean;

import java.util.ArrayList;
import java.util.List;

public class RecyclerVideoVoListAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<VideoSortBean.ResultBean> datas = new ArrayList<>();
    private int id;

    public RecyclerVideoVoListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.adapter_video_volist_item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        final MyViewHolder myViewHolder = (MyViewHolder) viewHolder;

        myViewHolder.adapter_video_volist_title.setText(datas.get(i).getTitle() + "");
        myViewHolder.adapter_video_volist_abstracts.setText(datas.get(i).getAbstracts() + "");
        myViewHolder.adapter_video_volist_buyNum.setText(datas.get(i).getBuyNum() + "人已购买");
        int whetherBuy = datas.get(i).getWhetherBuy();
        int whetherCollection = datas.get(i).getWhetherCollection();



        if (whetherBuy == 1) {
            myViewHolder.adapter_video_volist_pinglun.setVisibility(View.VISIBLE);
            myViewHolder.adapter_video_volist_goumai.setVisibility(View.GONE);
            myViewHolder.adapter_video_volist_ijkplayer.setVideoPath(datas.get(i).getOriginalUrl());
            myViewHolder.adapter_video_volist_ijkplayer.start();
            //设置播放监听
            myViewHolder.adapter_video_volist_ijkplayer.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    //如果正在播放(点击暂停、播放)
                    if (myViewHolder.adapter_video_volist_ijkplayer.isPlaying()) {
                        myViewHolder.adapter_video_volist_ijkplayer.pause();
                        myViewHolder.adapter_video_start_stop.setVisibility(View.VISIBLE);
                    } else {
                        myViewHolder.adapter_video_volist_ijkplayer.start();
                        myViewHolder.adapter_video_start_stop.setVisibility(View.GONE);
                    }
                    return false;
                }
            });
        } else if (whetherBuy == 2) {
            myViewHolder.adapter_video_volist_pinglun.setVisibility(View.GONE);
            myViewHolder.adapter_video_volist_goumai.setVisibility(View.VISIBLE);
            myViewHolder.adapter_video_volist_ijkplayer.setVideoPath(datas.get(i).getShearUrl());
            myViewHolder.adapter_video_volist_ijkplayer.start();
            //设置播放监听
            myViewHolder.adapter_video_volist_ijkplayer.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    //如果正在播放(点击暂停、播放)
                    if (myViewHolder.adapter_video_volist_ijkplayer.isPlaying()) {
                        myViewHolder.adapter_video_volist_ijkplayer.pause();
                        myViewHolder.adapter_video_start_stop.setVisibility(View.VISIBLE);
                    } else {
                        myViewHolder.adapter_video_volist_ijkplayer.start();
                        myViewHolder.adapter_video_start_stop.setVisibility(View.GONE);
                    }
                    return false;
                }
            });
            id = datas.get(i).getId();
        }

        if (whetherCollection == 1) {
            myViewHolder.adapter_video_volist_shoucang.setImageResource(R.mipmap.shoucang);

        } else {
            myViewHolder.adapter_video_volist_shoucang.setImageResource(R.mipmap.common_button_collection_large_n);
        }

        myViewHolder.adapter_video_volist_goumai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemonclick.getOnItemonclick(i, id);
            }
        });
        myViewHolder.adapter_video_volist_shoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnItemonclickShouCang.getOnItemonclick(i, id);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void addData(List<VideoSortBean.ResultBean> voListResult) {
        if (voListResult != null && voListResult.size() > 0) {
            datas.addAll(voListResult);
        }
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView adapter_video_volist_buyNum;
        private final TextView adapter_video_volist_title;
        private final TextView adapter_video_volist_abstracts;
        private final IjkVideoView adapter_video_volist_ijkplayer;
        private final ImageView adapter_video_start_stop;
        private final ImageView adapter_video_volist_goumai;
        private final ImageView adapter_video_volist_pinglun;
        private final ImageView adapter_video_volist_shoucang;
        private final ImageView danmu;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            adapter_video_volist_buyNum = itemView.findViewById(R.id.adapter_video_volist_buyNum);
            adapter_video_volist_title = itemView.findViewById(R.id.adapter_video_volist_title);
            adapter_video_volist_goumai = itemView.findViewById(R.id.adapter_video_volist_goumai);
            adapter_video_start_stop = itemView.findViewById(R.id.adapter_video_start_stop);
            adapter_video_volist_shoucang = itemView.findViewById(R.id.adapter_video_volist_shoucang);
            danmu = itemView.findViewById(R.id.danmu);
            adapter_video_volist_pinglun = itemView.findViewById(R.id.adapter_video_volist_pinglun);
            adapter_video_volist_abstracts = itemView.findViewById(R.id.adapter_video_volist_abstracts);
            adapter_video_volist_ijkplayer = itemView.findViewById(R.id.adapter_video_volist_ijkplayer);
        }
    }

    OnItemonclick onItemonclick;

    public void setOnItemonclick(OnItemonclick onItemonclick) {
        this.onItemonclick = onItemonclick;
    }

    public interface OnItemonclick {
        void getOnItemonclick(int i, int id);
    }


    OnItemonclickShouCang OnItemonclickShouCang;

    public void setOnItemonclickShouCang(OnItemonclickShouCang OnItemonclickShouCang) {
        this.OnItemonclickShouCang = OnItemonclickShouCang;
    }

    public interface OnItemonclickShouCang {
        void getOnItemonclick(int i, int id);
    }
}
