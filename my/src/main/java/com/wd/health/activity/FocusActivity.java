package com.wd.health.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.FocusAdapter;
import com.wd.health.bean.FocusBean;
import com.wd.health.contract.FocusContract;
import com.wd.health.presenter.FocusPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FocusActivity extends BaseActivity<FocusPresenter> implements FocusContract.IView {


    @BindView(R2.id.fanhui)
    ImageView fanhui;
    @BindView(R2.id.xrecy_view)
    XRecyclerView xrecyView;
    int page=1;
    @Override
    protected FocusPresenter providePresenter() {
        return new FocusPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R2.layout.activity_focus;
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String id = sp.getString("id", "");
        String sessionId = sp.getString("sessionId", "");
        mPresenter.FocusPresenter(id, sessionId, page + "", "10");
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onFocusSuccess(FocusBean bean) {
        if (bean.getStatus().equals("0000")) {
            Toast.makeText(this, ""+bean.getMessage(), Toast.LENGTH_SHORT).show();
            List<FocusBean.ResultBean> result = bean.getResult();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(FocusActivity.this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            xrecyView.setLayoutManager(linearLayoutManager);
            xrecyView.setAdapter(new FocusAdapter(FocusActivity.this,result));
            xrecyView.setLoadingMoreEnabled(true);
            xrecyView.setPullRefreshEnabled(true);
            xrecyView.setLoadingListener(new XRecyclerView.LoadingListener() {
                @Override
                public void onRefresh() {
                    page=1;
                    xrecyView.refreshComplete();
                }

                @Override
                public void onLoadMore() {
                    page++;
                    xrecyView.refreshComplete();
                }
            });
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
