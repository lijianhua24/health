package com.wd.health.view.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.health.R;
import com.wd.health.bean.HealthBuyBean;
import com.wd.health.bean.HealthSortBean;
import com.wd.health.bean.VideoSortBean;
import com.wd.health.contract.IContract;
import com.wd.health.presenter.HealthSortPresenter;
import com.wd.health.view.adapter.RecyclerVideoVoListAdapter;
import com.wd.mylibrary.Base.BaseFragment;
import com.wd.mylibrary.Test.ToastUtils;
import com.wd.mylibrary.app.Constant;

import java.util.List;

import butterknife.BindView;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/19<p>
 * <p>更改时间：2019/12/19<p>
 */
public class VideoSortFragment extends BaseFragment<HealthSortPresenter> implements IContract.iView {

    @BindView(R.id.xrecyclerview)
    XRecyclerView xrecyclerview;
    private int anInt;
    private int page = 1;
    private int count = 5;
    private int userId = 456;
    private String sessionId = "1576890153311456";

    @Override
    protected HealthSortPresenter providePresenter() {
        return new HealthSortPresenter();
    }

    @Override
    protected void initData() {
        xrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getVideoSort(userId, sessionId, anInt, page, count);
                xrecyclerview.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getVideoSort(userId, sessionId, anInt, page, count);
                xrecyclerview.loadMoreComplete();
            }
        });
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

    }

    @Override
    public void VideoSortsuccess(VideoSortBean videoSortBean) {
        List<VideoSortBean.ResultBean> resultBeans = videoSortBean.getResult();
        RecyclerVideoVoListAdapter recyclerVideoVoListAdapter = new RecyclerVideoVoListAdapter(getContext());
        recyclerVideoVoListAdapter.addData(resultBeans);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        xrecyclerview.setLayoutManager(linearLayoutManager);
        xrecyclerview.setAdapter(recyclerVideoVoListAdapter);
        recyclerVideoVoListAdapter.setOnItemonclick(new RecyclerVideoVoListAdapter.OnItemonclick() {
            @Override
            public void getOnItemonclick(int i, int id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("购买视频");
                builder.setNegativeButton("取消购买", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtils.show("用户已取消");
                    }
                });
                builder.setPositiveButton("确认购买", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.getHealthBuy(userId, sessionId, id, 100);
                    }
                });
                builder.show();
            }
        });
        recyclerVideoVoListAdapter.setOnItemonclickShouCang(new RecyclerVideoVoListAdapter.OnItemonclickShouCang() {
            @Override
            public void getOnItemonclick(int i, int id) {
                mPresenter.getHealthCollection(userId, sessionId, id);
            }
        });
    }

    @Override
    public void VideoSortFailure(Throwable e) {

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

    }
}
