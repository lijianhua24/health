package com.wd.doctor.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wd.doctor.R;
import com.wd.doctor.bean.ToBindBean;
import com.wd.doctor.contract.ToBindContract;
import com.wd.doctor.presenter.ToBindPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BindIdActivity extends BaseActivity<ToBindPresenter> implements ToBindContract.iView {
    private static final String TAG = "BindIdActivity";
    @BindView(R.id.bindid_iv_back)
    ImageView bindidIvBack;
    @BindView(R.id.bindid_rl_Portrait)
    RelativeLayout bindidRlPortrait;
    @BindView(R.id.bindid_rl_emblem)
    RelativeLayout bindidRlEmblem;

    @Override
    protected ToBindPresenter providePresenter() {
        return new ToBindPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_bind_id;
    }

    @Override
    public void onToBindSuccess(ToBindBean toBindBean) {
        Logger.d("BindIdActivity",""+toBindBean.getMessage());

    }

    @Override
    public void onToBindFailure(Throwable e) {

    }

    @Override
    public void onBindBankSuccess(ToBindBean data) {

    }

    @Override
    public void onBindBankFailure(Throwable e) {

    }


    @OnClick({R.id.bindid_iv_back, R.id.bindid_rl_Portrait, R.id.bindid_rl_emblem})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bindid_iv_back:
                finish();
                break;
            case R.id.bindid_rl_Portrait:

                break;
            case R.id.bindid_rl_emblem:

                break;
        }
    }
}
