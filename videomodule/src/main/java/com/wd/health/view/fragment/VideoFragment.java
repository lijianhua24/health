package com.wd.health.view.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.bean.HealthBuyBean;
import com.wd.health.bean.HealthSortBean;
import com.wd.health.bean.QvideoListBean;
import com.wd.health.bean.VideoSortBean;
import com.wd.health.contract.IContract;
import com.wd.health.presenter.HealthSortPresenter;
import com.wd.mylibrary.Base.BaseFragment;
import java.util.List;
import butterknife.BindView;
public class VideoFragment extends BaseFragment<HealthSortPresenter> implements IContract.iView {
    @BindView(R2.id.videofragment_tablayout)
    TabLayout videofragment_tablayout;
    @BindView(R2.id.videofragment_viewpager)
    ViewPager videofragment_viewpager;
    @BindView(R2.id.video_pull)
    CheckBox videoPull;
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
        videoPull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.video_pull:
                        if (videoPull.isChecked()) {
                            float translationY = view.getTranslationY();
                            ObjectAnimator animator = ObjectAnimator.ofFloat(videofragment_tablayout, "translationY", translationY, -120f);
                            ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "translationY", translationY, -120f);
                            animator.setDuration(500);
                            animator1.setDuration(500);
                            animator.start();
                            animator1.start();
                        } else {
                            float translationY = view.getTranslationY();
                            ObjectAnimator animator = ObjectAnimator.ofFloat(videofragment_tablayout, "translationY", translationY, 0f);
                            ObjectAnimator animator1 = ObjectAnimator.ofFloat(view, "translationY", translationY, 0f);
                            animator.setDuration(500);
                            animator1.setDuration(500);
                            animator.start();
                            animator1.start();
                        }
                        break;
                }
            }
        });
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

    @Override
    public void QvideoListsuccess(QvideoListBean qvideoListBean) {

    }

    @Override
    public void QvideoListFailure(Throwable e) {

    }

    @Override
    public void VideoCommentsuccess(HealthBuyBean healthBuyBean) {

    }

    @Override
    public void VideoCommentFailure(Throwable e) {

    }
}
