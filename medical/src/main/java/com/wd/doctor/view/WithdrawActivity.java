package com.wd.doctor.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.doctor.R;
import com.wd.doctor.bean.WithdrawBean;
import com.wd.doctor.contract.WithdrawContract;
import com.wd.doctor.presenter.WithdrawPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;
import com.wd.mylibrary.Test.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WithdrawActivity extends BaseActivity<WithdrawPresenter> implements WithdrawContract.iView {

    private static final String TAG = "WithdrawActivity";
    @BindView(R.id.withdraw_iv_back)
    ImageView withdrawIvBack;
    @BindView(R.id.withdraw_tv_withdraw)
    TextView withdrawTvWithdraw;
    @BindView(R.id.withdraw_tv_bankname)
    TextView withdrawTvBankname;
    @BindView(R.id.withdraw_iv_next)
    ImageView withdrawIvNext;
    @BindView(R.id.withdraw_et_money)
    EditText withdrawEtMoney;
    @BindView(R.id.withdraw_cb_qbmoney)
    CheckBox withdrawCbQbmoney;
    @BindView(R.id.withdraw_tv_qbmoney)
    TextView withdrawTvQbmoney;
    @BindView(R.id.withdraw_but_withdraw)
    Button withdrawButWithdraw;
    private SharedPreferences sharedPreferences;
    private int doctorId;
    private String sessionId;
    private String bankName;
    private int money;

    @Override
    protected WithdrawPresenter providePresenter() {
        return new WithdrawPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        doctorId = sharedPreferences.getInt("doctorId", 0);
        sessionId = sharedPreferences.getString("sessionId", "");
        bankName = sharedPreferences.getString("bankName", "");
        money = getIntent().getIntExtra("money", 0);
        withdrawTvBankname.setText(bankName);
        withdrawTvQbmoney.setText("全部提现。"+ money +"H币，可提现30元。");
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_withdraw;
    }

    @Override
    public void onWithdrawSuccess(WithdrawBean withdrawBean) {
        Logger.d("onWithdrawSuccess", "" + withdrawBean.getMessage());
        if (withdrawBean.getStatus().equals("0000")) {
            ToastUtils.show(withdrawBean.getMessage());
            finish();
        } else if (withdrawBean.getStatus().equals("8002")) {
            ToastUtils.show(withdrawBean.getMessage());
        }
    }

    @Override
    public void onWithdrawFailure(Throwable e) {
        ToastUtils.show("低于2000不能提现");
    }


    @OnClick({R.id.withdraw_iv_back, R.id.withdraw_iv_next, R.id.withdraw_cb_qbmoney, R.id.withdraw_but_withdraw})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.withdraw_iv_back:
                finish();
                break;
            case R.id.withdraw_iv_next:
                break;
            case R.id.withdraw_cb_qbmoney:
                withdrawCbQbmoney.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            withdrawEtMoney.setText(money+"");
                        }else {
                            withdrawEtMoney.setText("");
                        }
                    }
                });
                break;
            case R.id.withdraw_but_withdraw:
                String trim = withdrawEtMoney.getText().toString().trim();
                mPresenter.getWithdrawPresenter(doctorId,sessionId, Integer.parseInt(trim));
                break;
        }
    }

}
