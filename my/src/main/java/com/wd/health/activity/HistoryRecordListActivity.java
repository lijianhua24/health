package com.wd.health.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.health.R;
import com.wd.health.adapter.HistoryAdapter;
import com.wd.health.bean.EndInquiryBean;
import com.wd.health.bean.user.HistoryBean;
import com.wd.health.bean.user.InquiryRecordBean;
import com.wd.health.contract.HistoryContract;
import com.wd.health.presenter.HistoryPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistoryRecordListActivity extends BaseActivity<HistoryPresenter> implements HistoryContract.IView {

    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.xrecy_history)
    XRecyclerView xrecyHistory;
    int page = 1;
    @BindView(R.id.message_include_img)
    SimpleDraweeView messageIncludeImg;
    @BindView(R.id.message_include_text)
    TextView messageIncludeText;
    @BindView(R.id.message_include_relat)
    RelativeLayout messageIncludeRelat;

    @Override
    protected HistoryPresenter providePresenter() {
        return new HistoryPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_history_record_list;
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String id = sp.getString("id", "");
        String sessionId = sp.getString("sessionId", "");
        mPresenter.HistoryPresenter(id, sessionId, page + "", "9");
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onHistorySuccess(HistoryBean bean) {
        if (bean.getStatus().equals("0000")) {
            if (bean.getResult().size() != 0) {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HistoryRecordListActivity.this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                xrecyHistory.setLayoutManager(linearLayoutManager);
                xrecyHistory.setAdapter(new HistoryAdapter(HistoryRecordListActivity.this, bean.getResult()));
                xrecyHistory.setLoadingMoreEnabled(true);
                xrecyHistory.setPullRefreshEnabled(true);
                xrecyHistory.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        page = 1;
                        xrecyHistory.refreshComplete();
                    }

                    @Override
                    public void onLoadMore() {
                        page++;
                        xrecyHistory.refreshComplete();
                    }
                });
            } else {
                messageIncludeImg.setVisibility(View.VISIBLE);
                messageIncludeRelat.setVisibility(View.VISIBLE);
                messageIncludeText.setText("无问诊历史");
            }

        }
    }

    @Override
    public void onInquirySuccess(InquiryRecordBean bean) {

    }

    @Override
    public void onendInquirySuccess(EndInquiryBean bean) {

    }

    @Override
    public void onFailure(Throwable e) {

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
