package com.wd.doctor.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wd.doctor.R;
import com.wd.doctor.bean.ImageQueryBean;
import com.wd.doctor.bean.SimagePhotosBean;
import com.wd.doctor.contract.ImageQueryContract;
import com.wd.doctor.presenter.ImageQueryPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;

public class ImageQueryActivity extends BaseActivity<ImageQueryPresenter>implements ImageQueryContract.iView {

    private static final String TAG = "ImageQueryActivity";

    @Override
    protected ImageQueryPresenter providePresenter() {
        return new ImageQueryPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_image_query;
    }

    @Override
    public void onImageQuerySuccess(ImageQueryBean imageQueryBean) {
        Logger.d("ImageQueryActivity",""+imageQueryBean.getMessage());
    }

    @Override
    public void onImageQueryFailure(Throwable e) {

    }

    @Override
    public void onSimagePhotosSuccess(SimagePhotosBean simagePhotosBean) {

    }

    @Override
    public void onSimagePhotosFailure(Throwable e) {

    }
}
