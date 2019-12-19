package com.wd.doctor.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wd.doctor.R;
import com.wd.doctor.bean.ResidencyBean;
import com.wd.doctor.contract.ResidencyContract;
import com.wd.doctor.presenter.ResidencyPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.Test.Logger;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResidencyActivity extends BaseActivity<ResidencyPresenter>implements ResidencyContract.iView {
    private static final String TAG = "ResidencyActivity";
    @BindView(R.id.bt_two_next)
    Button btTwoNext;

    @Override
    protected ResidencyPresenter providePresenter() {
        return new ResidencyPresenter();
    }

    @Override
    protected void initData() {
        btTwoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResidencyActivity.this, ResidencyTwoActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_residency;
    }

    @Override
    public void onResidencySuccess(ResidencyBean residencyBean) {
        Logger.d("ResidencyActivity",""+residencyBean.getMessage());

    }

    @Override
    public void onResidencyFailure(Throwable e) {

    }
}
