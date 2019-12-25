package com.wd.health.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.adapter.UserConsumptionRecordAdapter;
import com.wd.health.bean.UserConsumptionRecordBean;
import com.wd.health.bean.WalletYUERBean;
import com.wd.health.contract.WalletContract;
import com.wd.health.presenter.WalletPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WalletActivity extends BaseActivity<WalletPresenter> implements WalletContract.IView {

    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.Wallet_Amount)
    TextView WalletAmount;
    @BindView(R.id.aa)
    TextView aa;
    @BindView(R.id.Wallet_withdraw)
    TextView WalletWithdraw;
    @BindView(R.id.Wallet_Recharge)
    TextView WalletRecharge;
    @BindView(R.id.Wallet_RecyclerView)
    RecyclerView WalletRecyclerView;

    @Override
    protected WalletPresenter providePresenter() {
        return new WalletPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_wallet;
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String id = sp.getString("id", "");
        String sessionId = sp.getString("sessionId", "");
        mPresenter.WalletYUERPresenter(id,sessionId);
        mPresenter.UserConsumptionRecordPresenter(id,sessionId,"1","9");
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onWalletYUERSuccess(WalletYUERBean bean) {
        if (bean.getStatus().equals("0000")) {
            int result = bean.getResult();
            WalletAmount.setText(result+"");
        }
    }

    @Override
    public void onUserConsumptionRecordSuccess(UserConsumptionRecordBean bean) {
        if (bean.getStatus().equals("0000")){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WalletActivity.this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            WalletRecyclerView.setLayoutManager(linearLayoutManager);
            WalletRecyclerView.setAdapter(new UserConsumptionRecordAdapter(WalletActivity.this,bean.getResult()));
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.fanhui, R.id.Wallet_withdraw, R.id.Wallet_Recharge})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.Wallet_withdraw:
                startActivity(new Intent(WalletActivity.this,DrawCashActivity.class));
                break;
            case R.id.Wallet_Recharge:
                startActivity(new Intent(WalletActivity.this,RechargeActivity.class));
                break;
        }
    }
}
