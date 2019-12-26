package com.wd.health.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.HealthyCurrencyAdapter;
import com.wd.health.bean.HealthyCurrencyBean;
import com.wd.health.bean.SystemMessageBean;
import com.wd.health.contract.SystemMessageContract;
import com.wd.health.presenter.SystemMessagePresenter;
import com.wd.mylibrary.Base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HealthyCurrencyActivity extends BaseActivity<SystemMessagePresenter> implements SystemMessageContract.IView {


    @BindView(R2.id.fanhui)
    ImageView fanhui;
    @BindView(R2.id.xrecy_healthy)
    XRecyclerView xrecyHealthy;
    int page = 1;
    @BindView(R2.id.no_imageview)
    ImageView noImageview;
    @BindView(R2.id.no_message)
    TextView noMessage;

    @Override
    protected SystemMessagePresenter providePresenter() {
        return new SystemMessagePresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R2.layout.activity_healthy_currency;
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String id = sp.getString("id", "");
        String sessionId = sp.getString("sessionId", "");
        mPresenter.HealthyCurrencyPresenter(id, sessionId, page + "", "10");
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onSystemMessageSuccess(SystemMessageBean bean) {

    }

    @Override
    public void onHealthyCurrencySuccess(HealthyCurrencyBean bean) {
        if (bean.getStatus().equals("0000")) {
            if (bean.getResult().size() == 0) {
                noImageview.setVisibility(View.VISIBLE);
                noMessage.setVisibility(View.VISIBLE);
            }else {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HealthyCurrencyActivity.this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                List<HealthyCurrencyBean.ResultBean> result = bean.getResult();
                xrecyHealthy.setLayoutManager(linearLayoutManager);
                xrecyHealthy.setAdapter(new HealthyCurrencyAdapter(HealthyCurrencyActivity.this, result));
                xrecyHealthy.setLoadingMoreEnabled(true);
                xrecyHealthy.setPullRefreshEnabled(true);
                xrecyHealthy.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        page = 1;
                        xrecyHealthy.refreshComplete();
                    }

                    @Override
                    public void onLoadMore() {
                        page++;
                        xrecyHealthy.refreshComplete();
                    }
                });
            }

        }
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
