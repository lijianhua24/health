package com.wd.doctor.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.doctor.R;
import com.wd.doctor.adapter.DoctorWalletAdapter;
import com.wd.doctor.bean.DoctorWalletBean;
import com.wd.doctor.bean.QueryRevenueBean;
import com.wd.doctor.bean.WithdrawBean;
import com.wd.doctor.contract.DoctorWalletContract;
import com.wd.doctor.presenter.DoctorWalletPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;
import com.wd.mylibrary.Test.ToastUtils;

import java.util.List;

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
    @BindView(R.id.dowallet_rcv_Revenue)
    RecyclerView dowalletRcvRevenue;
    @BindView(R.id.dowallet_but_withdraw)
    Button dowalletButWithdraw;
    private SharedPreferences sharedPreferences;
    private int doctorId;
    private String sessionId;
    private int whetherBindIdCard;
    private int whetherBindBankCard;
    private int balance;

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
        mPresenter.getDoctorWalletPresenter(doctorId, sessionId);
        mPresenter.getQueryRevenuePresenter(doctorId, sessionId, 1, 10);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_doctor_wallet;
    }

    @Override
    public void onDoctorWalletSuccess(DoctorWalletBean doctorWalletBean) {
        Logger.d("DoctorWalletActivity", "" + doctorWalletBean.getMessage());
        DoctorWalletBean.ResultBean result = doctorWalletBean.getResult();
        balance = result.getBalance();
        dowalletTvBalance.setText(balance+"");

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

    @Override
    public void onQueryRevenueSuccess(QueryRevenueBean queryRevenueBean) {
        Logger.d("onQueryRevenueSuccess", "" + queryRevenueBean.getMessage());
        List<QueryRevenueBean.ResultBean> result = queryRevenueBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dowalletRcvRevenue.setLayoutManager(linearLayoutManager);
        DoctorWalletAdapter doctorWalletAdapter = new DoctorWalletAdapter(this, result);
        dowalletRcvRevenue.setAdapter(doctorWalletAdapter);

    }

    @Override
    public void onQueryRevenueFailure(Throwable e) {

    }

    @Override
    public void onWithdrawSuccess(WithdrawBean withdrawBean) {
        Logger.d("onWithdrawSuccess",""+withdrawBean.getMessage());
        if (withdrawBean.getStatus().equals("0000")) {
            ToastUtils.show("点击");
        } else if (withdrawBean.getStatus().equals("8002")) {
            ToastUtils.show(withdrawBean.getMessage());
        }
    }

    @Override
    public void onWithdrawFailure(Throwable e) {
        ToastUtils.show(e.getMessage());
    }


    @OnClick({R.id.dowallet_iv_back, R.id.dowallet_tv_Bind,R.id.dowallet_but_withdraw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dowallet_iv_back:
                finish();
                break;
            case R.id.dowallet_tv_Bind:
                if (whetherBindIdCard == 1 && whetherBindBankCard == 1) {
                    Intent intent = new Intent();
                    intent.setClass(this, BindInfoActivity.class);
                    startActivity(intent);
                } else if (whetherBindIdCard == 2 || whetherBindBankCard == 2) {
                    Intent intent = new Intent();
                    intent.setClass(this, ToBindActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.dowallet_but_withdraw:
                mPresenter.getWithdrawPresenter(doctorId,sessionId,balance);
                break;
        }
    }

}
