package com.wd.doctor.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.doctor.R;
import com.wd.doctor.bean.DoctorWalletBean;
import com.wd.doctor.contract.DoctorWalletContract;
import com.wd.doctor.presenter.DoctorWalletPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DoctorWalletActivity extends BaseActivity<DoctorWalletPresenter> implements DoctorWalletContract.iView {

    private static final String TAG = "DoctorWalletActivity";
    @BindView(R.id.dowallet_iv_back)
    ImageView dowalletIvBack;
    @BindView(R.id.dowallet_tv_Bind)
    TextView dowalletTvBind;
    @BindView(R.id.dowallet_tv_balance)
    TextView dowalletTvBalance;
    private SharedPreferences sharedPreferences;
    private int doctorId;
    private String sessionId;
    private int whetherBindIdCard;
    private int whetherBindBankCard;

    @Override
    protected DoctorWalletPresenter providePresenter() {
        return new DoctorWalletPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        doctorId = sharedPreferences.getInt("doctorId", 0);
        sessionId = sharedPreferences.getString("sessionId", "");
        mPresenter.getDoctorWalletPresenter(doctorId,sessionId);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_doctor_wallet;
    }

    @Override
    public void onDoctorWalletSuccess(DoctorWalletBean doctorWalletBean) {
        Logger.d("DoctorWalletActivity",""+doctorWalletBean.getMessage());
        DoctorWalletBean.ResultBean result = doctorWalletBean.getResult();
        dowalletTvBalance.setText(result.getBalance()+"");
        whetherBindIdCard = result.getWhetherBindIdCard();
        whetherBindBankCard = result.getWhetherBindBankCard();

        if (whetherBindIdCard == 1 && whetherBindBankCard == 1) {
            dowalletTvBind.setText("查看绑定");
        } else if (whetherBindIdCard == 2 || whetherBindBankCard == 2) {
            dowalletTvBind.setText("去绑定");
        }
    }

    @Override
    public void onDoctorWalletFailure(Throwable e) {

    }


    @OnClick({R.id.dowallet_iv_back, R.id.dowallet_tv_Bind})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dowallet_iv_back:
                finish();
                break;
            case R.id.dowallet_tv_Bind:
                if (whetherBindIdCard == 1 && whetherBindBankCard == 1) {
                    Intent intent = new Intent();
                    intent.setClass(this,BindInfoActivity.class);
                    startActivity(intent);
                } else if (whetherBindIdCard == 2 || whetherBindBankCard == 2) {
                    Intent intent = new Intent();
                    intent.setClass(this,ToBindActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }
}
