package com.wd.doctor.view;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.tabs.TabLayout;
import com.stx.xhb.androidx.XBanner;
import com.stx.xhb.androidx.entity.SimpleBannerInfo;
import com.wd.doctor.R;
import com.wd.doctor.bean.ImageQueryBean;
import com.wd.doctor.bean.SimagePhotosBean;
import com.wd.doctor.contract.ImageQueryContract;
import com.wd.doctor.presenter.ImageQueryPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;

import java.util.AbstractList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageQueryActivity extends BaseActivity<ImageQueryPresenter> implements ImageQueryContract.iView {

    private static final String TAG = "ImageQueryActivity";

    @BindView(R.id.imqu_xb_xbannerone)
    XBanner imquXbXbannerone;
    @BindView(R.id.imqu_btn_butone)
    Button imquBtnButone;
    private SharedPreferences sharedPreferences;
    private String imagePic;

    @Override
    protected ImageQueryPresenter providePresenter() {
        return new ImageQueryPresenter();
    }

    @Override
    protected void initData() {
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        int doctorId = sharedPreferences.getInt("doctorId", 0);
        String sessionId = sharedPreferences.getString("sessionId", "");
        imquBtnButone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getSimagePhotosPresenter(doctorId,sessionId,imagePic);
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        mPresenter.getImageQueryPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_image_query;
    }

    @Override
    public void onImageQuerySuccess(ImageQueryBean imageQueryBean) {
        Logger.d("ImageQueryActivity", "" + imageQueryBean.getMessage());
        List<ImageQueryBean.ResultBean> result = imageQueryBean.getResult();

        if (!result.isEmpty()) {
            imquXbXbannerone.setBannerData(R.layout.fresco_query, new AbstractList<SimpleBannerInfo>() {
                @Override
                public SimpleBannerInfo get(int index) {
                    return null;
                }

                @Override
                public int size() {
                    return result.size();
                }
            });
            imquXbXbannerone.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    SimpleDraweeView fresco_sdv_freone = view.findViewById(R.id.fresco_sdv_freone);
                    imagePic = result.get(position).getImagePic();
                    Uri parse = Uri.parse(imagePic);
                    fresco_sdv_freone.setImageURI(parse);
                }
            });
        }else { }

    }

    @Override
    public void onImageQueryFailure(Throwable e) {

    }

    @Override
    public void onSimagePhotosSuccess(SimagePhotosBean simagePhotosBean) {
        Logger.d("onSimagePhotosSuccess:", "" + simagePhotosBean.getMessage());

    }

    @Override
    public void onSimagePhotosFailure(Throwable e) {

    }

}
