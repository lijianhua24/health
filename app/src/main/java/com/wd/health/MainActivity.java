package com.wd.health;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

//@Route(path = "/app/MainActivity")
public class MainActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.lin_movei)
    ImageView linMovei;
    @BindView(R.id.lay_one)
    LinearLayout layOne;
    @BindView(R.id.lay_two)
    LinearLayout layTwo;
    @BindView(R.id.image_myy_dj)
    ImageView imageMyyDj;
    @BindView(R.id.image_cinem_dj)
    ImageView imageCinemDj;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        linMovei.setOnClickListener(this);
        imageCinemDj.setOnClickListener(this);
        imageMyyDj.setOnClickListener(this);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_movei:
                linMovei.setBackgroundResource(R.mipmap.common_tab_home_s);
                imageCinemDj.setBackgroundResource(R.mipmap.common_tab_circle_n);
                imageMyyDj.setBackgroundResource(R.mipmap.common_tab_video_n);
                break;
            case R.id.image_cinem_dj:
                linMovei.setBackgroundResource(R.mipmap.common_tab_home_n);
                imageCinemDj.setBackgroundResource(R.mipmap.common_tab_circle_s);
                imageMyyDj.setBackgroundResource(R.mipmap.common_tab_video_n);
                break;
            case R.id.image_myy_dj:
                linMovei.setBackgroundResource(R.mipmap.common_tab_home_n);
                imageCinemDj.setBackgroundResource(R.mipmap.common_tab_circle_n);
                imageMyyDj.setBackgroundResource(R.mipmap.common_tab_video_s);
                break;
        }
    }


}
