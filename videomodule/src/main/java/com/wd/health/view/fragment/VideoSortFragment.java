package com.wd.health.view.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.dou361.ijkplayer.widget.IjkVideoView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.bean.HealthBuyBean;
import com.wd.health.bean.HealthSortBean;
import com.wd.health.bean.QvideoListBean;
import com.wd.health.bean.VideoSortBean;
import com.wd.health.contract.IContract;
import com.wd.health.model.App;
import com.wd.health.presenter.HealthSortPresenter;
import com.wd.health.view.adapter.RecyclerVideoVoListAdapter;
import com.wd.health.view.curtrom.AcFunDanmakuParser;
import com.wd.mylibrary.Base.BaseFragment;
import com.wd.mylibrary.Test.Logger;
import com.wd.mylibrary.Test.ToastUtils;
import com.wd.mylibrary.app.Constant;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.ui.widget.DanmakuView;

public class VideoSortFragment extends BaseFragment<HealthSortPresenter> implements IContract.iView {

    @BindView(R2.id.xrecyclerview)
    XRecyclerView xrecyclerview;
    @BindView(R2.id.danwen)
    EditText dan_wen;
    @BindView(R2.id.dan_fa)
    TextView dan_fa;
    @BindView(R2.id.dddd)
    RelativeLayout dddd;
    @BindView(R2.id.video_danmu)
    DanmakuView video_danmu;
    private int anInt;
    private int page = 1;
    private int count = 100;
    private int userId;
    private String sessionId;
    private String contents;
    private DanmakuContext mContext;
    private AcFunDanmakuParser mParser;
    private BaseDanmaku danmaku;
    private IjkVideoView mVideoView;
    private LinearLayoutManager linearLayoutManager;

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

        mPresenter.getVideoSort(userId, sessionId, anInt, page, count);
        mContext = DanmakuContext.create();
        //设置最大显示行数

        HashMap<Integer, Integer> maxLinesPair = new HashMap<>();
        maxLinesPair.put(BaseDanmaku.TYPE_SCROLL_RL, 5); // 滚动弹幕最大显示5行
        // 设置是否禁止重叠
        HashMap<Integer, Boolean> overlappingEnablePair = new HashMap<>();
        overlappingEnablePair.put(BaseDanmaku.TYPE_SCROLL_RL, true);
        overlappingEnablePair.put(BaseDanmaku.TYPE_FIX_TOP, true);
        mContext.setDanmakuStyle(IDisplayer.DANMAKU_STYLE_STROKEN, 10) //描边的厚度
                .setDuplicateMergingEnabled(false)
                .setScrollSpeedFactor(1.5f) //弹幕的速度。注意！此值越小，速度越快！值越大，速度越慢。// by phil
                .setScaleTextSize(1.5f)  //缩放的值
                .setMaximumLines(maxLinesPair)
                .preventOverlapping(overlappingEnablePair);

        mParser = new AcFunDanmakuParser();
        video_danmu.prepare(mParser, mContext);
        video_danmu.enableDanmakuDrawingCache(true);
        if (video_danmu != null) {
            video_danmu.setCallback(new master.flame.danmaku.controller.DrawHandler.Callback() {
                @Override
                public void updateTimer(DanmakuTimer timer) {
                }

                @Override
                public void drawingFinished() {

                }

                @Override
                public void danmakuShown(BaseDanmaku danmaku) {
                    Log.d("弹幕文本", "danmakuShown text=" + danmaku.text);
                }

                @Override
                public void prepared() {
                    video_danmu.start();
                }
            });
        }

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
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
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
                    video_danmu.setVisibility(View.GONE);
                } else if (ci % 2 == 0) {
                    video_danmu.setVisibility(View.VISIBLE);
                }
            }
        });
        recyclerVideoVoListAdapter.setSetOnPingLun(new RecyclerVideoVoListAdapter.setOnPingLun() {
            @Override
            public void onPingLunClick(int vid, String pl) {
                mPresenter.getVideoComment(userId, sessionId, vid, pl);
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
                mPresenter.getHealthBuy(userId, sessionId, ooid, 100);
            }
        });
        recyclerVideoVoListAdapter.setSetOnClick(new RecyclerVideoVoListAdapter.setOnClick() {
            @Override
            public void onClick(int oid) {
               mPresenter.getHealthCollection(userId,sessionId,oid);
            }
        });

recyclerVideoVoListAdapter.setOnDian(new RecyclerVideoVoListAdapter.setOnDian() {
    @Override
    public void onPriceClick(int ci) {
        if (ci%2==1){
            video_danmu.setVisibility(View.GONE);
        }else if (ci%2==0){
            video_danmu.setVisibility(View.VISIBLE);
        }
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
        Logger.d("LKHJ",qvideoListBean.getMessage());
        List<QvideoListBean.ResultBean> result = qvideoListBean.getResult();
        String message = qvideoListBean.getMessage();
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        for (int j = 0; j < result.size(); j++) {
           contents = result.get(j).getContent();
            Logger.d("TTR",contents);
            sendTextMessage();
    }
        sendTextMessage();
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
    private void sendTextMessage() {
        addDanmaku(true);
    }
    private void addDanmaku(boolean islive) {
        danmaku = mContext.mDanmakuFactory.createDanmaku(danmaku.TYPE_SCROLL_RL);
        if (danmaku == null || video_danmu == null) {
            return;
        }
        danmaku.text = contents;
        danmaku.padding = 5;
        danmaku.isLive = islive;
        danmaku.setTime(video_danmu.getCurrentTime() + 1000);
        danmaku.textSize = 20f * (mParser.getDisplayer().getDensity() - 0.6f);

        video_danmu.addDanmaku(danmaku);
    }
    @Override
    public void onPause() {
        if (video_danmu != null && video_danmu.isPrepared()) {
            video_danmu.pause();
        }
        super.onPause();

    }

    @Override
    public void onResume() {
        if (video_danmu != null && video_danmu.isPrepared() && video_danmu.isPaused()) {
            video_danmu.resume();
        }
        super.onResume();

    }


    @Override
    public void onStop() {
        if (video_danmu != null) {
            video_danmu.stop();
            video_danmu = null;
        }
        super.onStop();
    }
    @Override
    public void onDestroyView() {
        if (video_danmu != null) {
            video_danmu.release();
            video_danmu = null;
        }
        super.onDestroyView();
    }
    @Override
    public void onDestroy() {
        if (mVideoView != null) {
            mVideoView.pause();
        }
        if (video_danmu != null) {
            video_danmu.release();
            video_danmu = null;
        }
        super.onDestroy();

    }

}
