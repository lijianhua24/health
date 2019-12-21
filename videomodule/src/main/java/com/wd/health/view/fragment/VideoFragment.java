package com.wd.health.view.fragment;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wd.health.R;
import com.wd.health.bean.HealthBuyBean;
import com.wd.health.bean.HealthSortBean;
import com.wd.health.bean.VideoSortBean;
import com.wd.health.contract.IContract;
import com.wd.health.presenter.HealthSortPresenter;
import com.wd.mylibrary.Base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/19<p>
 * <p>更改时间：2019/12/19<p>
 */
public class VideoFragment extends BaseFragment<HealthSortPresenter> implements IContract.iView {
    @BindView(R.id.viewfragment_iv_user_head_pic)
    ImageView viewfragment_iv_user_head_pic;
    @BindView(R.id.viewfragment_iv_user_message)
    ImageView viewfragment_iv_user_message;
    @BindView(R.id.videofragment_tablayout)
    TabLayout videofragment_tablayout;
    @BindView(R.id.videofragment_viewpager)
    ViewPager videofragment_viewpager;
    @Override
    protected HealthSortPresenter providePresenter() {
        return new HealthSortPresenter();
    }

    @Override
    protected void initData() {
        mPresenter.getHealthSort();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.videofragment;
    }

    @Override
    public void healthSortsuccess(HealthSortBean healthSortBean) {
        List<HealthSortBean.ResultBean> result = healthSortBean.getResult();
        videofragment_viewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                VideoSortFragment videoSortFragment = new VideoSortFragment();
                Bundle bundle = new Bundle();
                int id = result.get(position).getId();
                bundle.putInt("anInt", id);
                videoSortFragment.setArguments(bundle);
                return videoSortFragment;
            }

            @Override
            public int getCount() {
                return result.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return result.get(position).getName();
            }
        });
        videofragment_tablayout.setupWithViewPager(videofragment_viewpager);
    }

    @Override
    public void healthSortFailure(Throwable e) {

    }

    @Override
    public void VideoSortsuccess(VideoSortBean videoSortBean) {

    }

    @Override
    public void VideoSortFailure(Throwable e) {

    }

    @Override
    public void HealthBuysuccess(HealthBuyBean healthBuyBean) {

    }

    @Override
    public void HealthBuyFailure(Throwable e) {

    }

    @Override
    public void HealthCollectionsuccess(HealthBuyBean healthBuyBean) {

    }

    @Override
    public void HealthCollectionFailure(Throwable e) {

    }
}
