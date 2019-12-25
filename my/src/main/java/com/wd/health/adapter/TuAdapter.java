package com.wd.health.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wd.health.R;


import java.io.File;
import java.util.List;

/**
 * date:
 * author:
 * <p>
 * "multipart/form-data;charset=utf-8"
 * <p>
 * function:
 */
//图片适配器
public class TuAdapter extends RecyclerView.Adapter {

    private List<File> fileList;
    private Context context;
    private RecyclerView.ViewHolder holder;

    public TuAdapter(List<File> fileList, Context context) {
        this.fileList = fileList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.myarchives_images_item, viewGroup, false);
        holder =new TuHolder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof TuHolder){
            TuHolder tuHolder = (TuHolder) viewHolder;

            File file = fileList.get(i);
            Glide.with(context).load(file).into(tuHolder.tu_image);
        }
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    private class TuHolder extends RecyclerView.ViewHolder {

        private final ImageView tu_image;

        public TuHolder(View in) {
            super(in);

            tu_image = in.findViewById(R.id.tu_image);
        }
    }
}