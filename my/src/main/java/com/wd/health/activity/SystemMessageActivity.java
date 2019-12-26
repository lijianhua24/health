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
import com.wd.health.adapter.SystemMessageAdapter;
import com.wd.health.bean.HealthyCurrencyBean;
import com.wd.health.bean.SystemMessageBean;
import com.wd.health.contract.SystemMessageContract;
import com.wd.health.presenter.SystemMessagePresenter;
import com.wd.mylibrary.Base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SystemMessageActivity extends BaseActivity<SystemMessagePresenter> implements SystemMessageContract.IView {


    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.recy_system)
    XRecyclerView recySystem;
    int page = 1;
    @BindView(R.id.no_imageview)
    ImageView noImageview;
    @BindView(R.id.no_message)
    TextView noMessage;

    @Override
    protected SystemMessagePresenter providePresenter() {
        return new SystemMessagePresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_system_message;
    }


    @Override
    protected void initData() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String id = sp.getString("id", "");
        String sessionId = sp.getString("sessionId", "");
        mPresenter.SystemMessagePresenter(id, sessionId, page + "", "10");
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onSystemMessageSuccess(SystemMessageBean bean) {
        if (bean.getStatus().equals("0000")) {
            if (bean.getResult().size()==0){
                noImageview.setVisibility(View.VISIBLE);
                noMessage.setVisibility(View.VISIBLE);
            }else {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SystemMessageActivity.this);
                linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                List<SystemMessageBean.ResultBean> result = bean.getResult();
                recySystem.setLayoutManager(linearLayoutManager);
                recySystem.setAdapter(new SystemMessageAdapter(SystemMessageActivity.this, result));
                recySystem.setLoadingMoreEnabled(true);
                recySystem.setPullRefreshEnabled(true);
                recySystem.setLoadingListener(new XRecyclerView.LoadingListener() {
                    @Override
                    public void onRefresh() {
                        page = 1;
                        recySystem.refreshComplete();
                    }

                    @Override
                    public void onLoadMore() {
                        page++;
                        recySystem.refreshComplete();
                    }
                });
            }

        }
    }

    @Override
    public void onHealthyCurrencySuccess(HealthyCurrencyBean bean) {

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