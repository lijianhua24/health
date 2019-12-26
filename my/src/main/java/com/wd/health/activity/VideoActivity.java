package com.wd.health.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.VideoAdapter;
import com.wd.health.bean.VideoBean;
import com.wd.health.contract.VideoContract;
import com.wd.health.presenter.VodelPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.utils.RsaCoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class VideoActivity extends BaseActivity<VodelPresenter> implements VideoContract.IView {


    @BindView(R2.id.fanhui)
    ImageView fanhui;
    @BindView(R2.id.xrecy_video)
    XRecyclerView xrecyVideo;
    @BindView(R2.id.message_include_img)
    SimpleDraweeView messageIncludeImg;
    @BindView(R2.id.message_include_text)
    TextView messageIncludeText;
    @BindView(R2.id.message_include_relat)
    RelativeLayout messageIncludeRelat;
    int page =1;
    public static final String TAG="video";
    @Override
    protected VodelPresenter providePresenter() {
        return new VodelPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_video;
    }

    @Override
    protected void initView() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String id = sp.getString("id", "");
        String sessionId = sp.getString("sessionId", "");
        mPresenter.VideoPresenter(id,sessionId,page+"","9");
    }

    @Override
    protected void initData() {
        try {
            String s = RsaCoder.decryptByPublicKey("R+0jdN3P4MXHPMFVe9cX5MbX5ulIXHJkfigPLKEeTBY5lUgxJWUNg0js1oGtbsKiLFL4ScqdmUbtHXIfrgQnWrwTNjf09OJLycbeJ+ka4+CV7I1eEqG8DtZPnQoCyxjoYMjO4soDl6EX9YgqaZp3DlUH4pXrYHYz58YyFkSeJEk=");
            Log.d(TAG, "onCreate: "+s);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onVideoSuccess(VideoBean bean) {
        if (bean.getStatus().equals("0000")) {
            if (bean.getResult().size() != 0) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(VideoActivity.this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                xrecyVideo.setLayoutManager(linearLayoutManager);
                xrecyVideo.setAdapter(new VideoAdapter(VideoActivity.this, bean.getResult()));
                xrecyVideo.setLoadingMoreEnabled(true);
                xrecyVideo.setPullRefreshEnabled(true);
                xrecyVideo.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        page = 1;
                        xrecyVideo.refreshComplete();
                    }

                    @Override
                    public void onLoadMore() {
                        page++;
                        xrecyVideo.refreshComplete();
                    }
                });
            } else {
                messageIncludeImg.setVisibility(View.VISIBLE);
                messageIncludeRelat.setVisibility(View.VISIBLE);
                messageIncludeText.setText("无视频消息");
            }

        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayerStandard.releaseAllVideos();
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
