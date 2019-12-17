package com.wd.homemodel.view;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

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

        //支持javascript
        bannerXqWb.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
        bannerXqWb.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        bannerXqWb.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        bannerXqWb.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        bannerXqWb.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        bannerXqWb.getSettings().setLoadWithOverviewMode(true);


        //如果不设置WebViewClient，请求会跳转系统浏览器
        bannerXqWb.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242

                if (url.toString().contains("sina.cn")){
                    view.loadUrl(jumpUrl);
                    return true;
                }

                return false;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    if (request.getUrl().toString().contains("sina.cn")){
                        view.loadUrl(jumpUrl);
                        return true;
                    }
                }

                return false;
            }

        });
        bannerXqWb.loadUrl(jumpUrl);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_banner_xq;
    }


}
