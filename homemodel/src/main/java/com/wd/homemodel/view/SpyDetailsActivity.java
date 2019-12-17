package com.wd.homemodel.view;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.homemodel.R;
import com.wd.homemodel.app.App;
import com.wd.homemodel.bean.SpyDetailsBean;
import com.wd.homemodel.contract.HomeContract;
import com.wd.homemodel.presenter.SpyDetailsPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SpyDetailsActivity extends BaseActivity<SpyDetailsPresenter> implements HomeContract.SpyDetailsContreact.IView {


    @BindView(R.id.syp_title)
    TextView sypTitle;
    @BindView(R.id.spy_name)
    TextView spyName;
    @BindView(R.id.titles_touxiang)
    ImageView titlesTouxiang;
    @BindView(R.id.titles_name)
    TextView titlesName;
    @BindView(R.id.home_xiaoxi)
    ImageView homeXiaoxi;
    @BindView(R.id.spy_wib)
    WebView spyWib;

    @Override
    public void onSpyDetailsSuccess(Object data) {
        SpyDetailsBean spyDetailsBean = (SpyDetailsBean) data;
        SpyDetailsBean.ResultBean result = ((SpyDetailsBean) data).getResult();
        sypTitle.setText(result.getTitle());
        spyName.setText(result.getSource());
        String content = result.getContent();
        spyWib.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
    }

    @Override
    public void onSpyDetailsFailure(Throwable e) {

    }

    @Override
    protected SpyDetailsPresenter providePresenter() {
        return new SpyDetailsPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        WebSettings settings = spyWib.getSettings();
        spyWib.getSettings().setJavaScriptEnabled(true);
        settings.setJavaScriptEnabled(true);
        //支持自动适配
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);  //支持放大缩小
        settings.setBuiltInZoomControls(true); //显示缩放按钮
        // settings.setBlockNetworkImage(true);// 把图片加载放在最后来加载渲染
        settings.setAllowFileAccess(true); // 允许访问文件
        settings.setSaveFormData(true);
        settings.setGeolocationEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);/// 支持通过JS打开新窗口
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        spyWib.setNestedScrollingEnabled(false);
        int id = App.sharedPreferences.getInt("zixunid", 0);
        mPresenter.getSpyDetailsPresenter("0", null, id);
        titlesName.setText("资讯详情");
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_spy_details;
    }


    @OnClick(R.id.titles_touxiang)
    public void onViewClicked() {
        finish();
    }
}
