package com.wd.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wd.health.R;
import com.wd.health.bean.VideoBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:11
 */
public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private List<VideoBean.ResultBean> list;
    private View inflate;

    public VideoAdapter(Context context, List<VideoBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.item_video, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).videoTitle.setText(list.get(position).getTitle());
            Date date = new Date(list.get(position).getCreateTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd  hh:mm");
            ((MyViewHolder) holder).videoTime.setText(simpleDateFormat.format(date));
            ((MyViewHolder) holder).jcVideoPlayer.setUp(list.get(position).getOriginalUrl(), JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
            Glide.with(context).load(list.get(position).getOriginalUrl()).into(((MyViewHolder) holder).jcVideoPlayer.thumbImageView);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.video_title)
        TextView videoTitle;
        @BindView(R.id.ijk_player)
        JCVideoPlayerStandard jcVideoPlayer;
        @BindView(R.id.video_time)
        TextView videoTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        JCVideoPlayerStandard.releaseAllVideos();
    }
}
