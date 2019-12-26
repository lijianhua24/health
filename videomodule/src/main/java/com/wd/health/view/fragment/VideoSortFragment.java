package com.wd.health.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.health.R;
import com.wd.health.bean.HealthBuyBean;
import com.wd.health.bean.HealthSortBean;
import com.wd.health.bean.QvideoListBean;
import com.wd.health.bean.VideoSortBean;
import com.wd.health.contract.IContract;
import com.wd.health.model.App;
import com.wd.health.presenter.HealthSortPresenter;
import com.wd.health.view.adapter.RecyclerVideoVoListAdapter;
import com.wd.mylibrary.Base.BaseFragment;
import com.wd.mylibrary.Test.ToastUtils;
import com.wd.mylibrary.app.Constant;

import java.util.List;

import butterknife.BindView;
import master.flame.danmaku.ui.widget.DanmakuView;

public class VideoSortFragment extends BaseFragment<HealthSortPresenter> implements IContract.iView {

    @BindView(R.id.xrecyclerview)
    XRecyclerView xrecyclerview;
    @BindView(R.id.danwen)
    EditText dan_wen;
    @BindView(R.id.dan_fa)
    TextView dan_fa;
    @BindView(R.id.dddd)
    RelativeLayout dddd;
    private int anInt;
    private int page = 1;
    private int count = 5;
    private int userId;
    private String sessionId;

    @Override
    protected HealthSortPresenter providePresenter() {
        return new HealthSortPresenter();
    }

    @Override
    protected void initData() {
        sessionId = App.sharedPreferences.getString("sessionId", null);
        userId = App.sharedPreferences.getInt("userId", 0);
        Log.d("MNJNV", "initData: " + sessionId);
        xrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                xrecyclerview.refreshComplete();
                mPresenter.getVideoSort(userId, sessionId, anInt, page, count);
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getVideoSort(userId, sessionId, anInt, page, count);
                xrecyclerview.loadMoreComplete();
            }
        });
        dan_fa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = dan_wen.getText().toString().trim();
                mPresenter.getVideoComment(userId, sessionId, anInt, trim);
            }
        });

        mPresenter.getVideoSort(userId,sessionId,anInt,page, count);

    }

    @Override
    protected void initView() {
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.videosortfragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle arguments = getArguments();
        anInt = arguments.getInt("anInt");
        Log.d("anIntanIntanInt", "onActivityCreated: " + anInt);
        mPresenter.getVideoSort(userId, sessionId, anInt, page, count);
    }

    @Override
    public void healthSortsuccess(HealthSortBean healthSortBean) {

    }

    @Override
    public void healthSortFailure(Throwable e) {
        ToastUtils.show("请检查网络");
    }

    @Override
    public void VideoSortsuccess(VideoSortBean videoSortBean) {
        List<VideoSortBean.ResultBean> resultBeans = videoSortBean.getResult();
        RecyclerVideoVoListAdapter recyclerVideoVoListAdapter = new RecyclerVideoVoListAdapter(resultBeans, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        xrecyclerview.setLayoutManager(linearLayoutManager);
        xrecyclerview.setAdapter(recyclerVideoVoListAdapter);
        recyclerVideoVoListAdapter.setsetOnTouch(new RecyclerVideoVoListAdapter.setOnTouch() {
            @Override
            public void onClick(int osid) {
                mPresenter.getQvideoList(userId, sessionId, osid);
            }
        });
        recyclerVideoVoListAdapter.setOnDian(new RecyclerVideoVoListAdapter.setOnDian() {
            @Override
            public void onPriceClick(int ci) {
                if (ci % 2 == 1) {
//                    video_danmu.setVisibility(View.GONE);
                } else if (ci % 2 == 0) {
//                    video_danmu.setVisibility(View.VISIBLE);
                }
            }
        });
        recyclerVideoVoListAdapter.setSetOnPingLun(new RecyclerVideoVoListAdapter.setOnPingLun() {
            @Override
            public void onPingLunClick(int vid, String pl) {
                mPresenter.getVideoComment(userId,sessionId,vid,pl);
            }
        });

        recyclerVideoVoListAdapter.setVideLun(new RecyclerVideoVoListAdapter.VideLun() {
            @Override
            public void getData(int contens) {

            }
        });
        recyclerVideoVoListAdapter.setSetOnPriceTouch(new RecyclerVideoVoListAdapter.setOnPriceTouch() {
            @Override
            public void onPriceClick(int mp, int ooid) {
                mPresenter.getHealthBuy(userId,sessionId,ooid,100);
            }
        });


    }

    @Override
    public void VideoSortFailure(Throwable e) {
        ToastUtils.show("请检查网络");
    }

    @Override
    public void HealthBuysuccess(HealthBuyBean healthBuyBean) {
        if (healthBuyBean.getStatus() == Constant.SUCCESS_CODE) {
            ToastUtils.show(healthBuyBean.getMessage());
        } else {
            ToastUtils.show(healthBuyBean.getMessage());
        }
    }

    @Override
    public void HealthBuyFailure(Throwable e) {
        ToastUtils.show("请检查网络");
    }

    @Override
    public void HealthCollectionsuccess(HealthBuyBean healthBuyBean) {
        if (healthBuyBean.getStatus() == Constant.SUCCESS_CODE) {
            ToastUtils.show(healthBuyBean.getMessage());
        } else {
            ToastUtils.show(healthBuyBean.getMessage());
        }
    }

    @Override
    public void HealthCollectionFailure(Throwable e) {
        ToastUtils.show("请检查网络");
    }

    @Override
    public void QvideoListsuccess(QvideoListBean qvideoListBean) {
    }

    @Override
    public void QvideoListFailure(Throwable e) {
        ToastUtils.show("请检查网络");
    }

    @Override
    public void VideoCommentsuccess(HealthBuyBean healthBuyBean) {
        if (healthBuyBean.getStatus() == Constant.SUCCESS_CODE) {
            ToastUtils.show(healthBuyBean.getMessage());
            dddd.setVisibility(View.GONE);
        } else {
            ToastUtils.show(healthBuyBean.getMessage());
        }
    }

    @Override
    public void VideoCommentFailure(Throwable e) {
        ToastUtils.show("请检查网络");
    }
}
