package com.wd.homemodel.view;

import android.os.Bundle;
import android.webkit.WebView;

import com.wd.homemodel.R;
import com.wd.homemodel.app.App;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BannerXQActivity extends BaseActivity {

    @BindView(R.id.banner_xq_wb)
    WebView bannerXqWb;
    private String jumpUrl;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        jumpUrl = App.sharedPreferences.getString("jumpUrl", null);
        bannerXqWb.loadUrl(jumpUrl);
        //finish();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_banner_xq;
    }


}
