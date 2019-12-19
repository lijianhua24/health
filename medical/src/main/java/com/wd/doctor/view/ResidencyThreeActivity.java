package com.wd.doctor.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wd.doctor.R;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResidencyThreeActivity extends BaseActivity {

    private static final String TAG = "ResidencyThreeActivity";


    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {
//        btRuzhu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_residency_three;
    }

}
