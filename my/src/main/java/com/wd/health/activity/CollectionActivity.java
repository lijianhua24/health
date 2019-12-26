package com.wd.health.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.google.android.material.tabs.TabLayout;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.view.fragment.FragBYQ;
import com.wd.health.view.fragment.FragJKSP;
import com.wd.health.view.FragJKZX;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectionActivity extends BaseActivity {


    @BindView(R2.id.fanhui)
    ImageView fanhui;
    @BindView(R2.id.tablayout)
    TabLayout tablayout;
    @BindView(R2.id.vp)
    ViewPager vp;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected int provideLayoutId() {
        return R2.layout.activity_collection;
    }

    @Override
    protected void initData() {
        List<Fragment> list = new ArrayList<>();
        list.add(new FragJKZX());
        list.add(new FragJKSP());
        list.add(new FragBYQ());
        List<String> slist = new ArrayList<>();
        slist.add("健康咨询");
        slist.add("健康视频");
        slist.add("病友圈");
        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return slist.get(position);
            }
        });
        tablayout.setupWithViewPager(vp);
        vp.setOffscreenPageLimit(3);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fanhui)
    public void onClick() {
        finish();
    }
}
