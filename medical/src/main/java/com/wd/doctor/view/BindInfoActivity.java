package com.wd.doctor.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.doctor.R;
import com.wd.doctor.bean.QueryBankBean;
import com.wd.doctor.bean.QueryIdBean;
import com.wd.doctor.contract.BindInFoContract;
import com.wd.doctor.presenter.BindInFoPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;
import com.wd.mylibrary.utils.RsaCoder;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BindInfoActivity extends BaseActivity<BindInFoPresenter> implements BindInFoContract.iView {

    private static final String TAG = "BindInfoActivity";
    @BindView(R.id.bindinfo_iv_back)
    ImageView bindinfoIvBack;
    @BindView(R.id.bindinfo_tv_name)
    TextView bindinfoTvName;
    @BindView(R.id.bindinfo_tv_sex)
    TextView bindinfoTvSex;
    @BindView(R.id.bindinfo_tv_nation)
    TextView bindinfoTvNation;
    @BindView(R.id.bindinfo_tv_idNumber)
    TextView bindinfoTvIdNumber;
    @BindView(R.id.bindinfo_tv_bankName)
    TextView bindinfoTvBankName;
    @BindView(R.id.bindinfo_tv_bankCardType)
    TextView bindinfoTvBankCardType;
    @BindView(R.id.bindinfo_tv_bankCardNumber)
    TextView bindinfoTvBankCardNumber;
    private SharedPreferences sharedPreferences;
    private int doctorId;
    private String sessionId;
    private final int ONE=1;
    private final int TWO=2;
    private SharedPreferences.Editor edit;

    @Override
    protected BindInFoPresenter providePresenter() {
        return new BindInFoPresenter();
    }

    @Override
    protected void initData() {
        //返回
        bindinfoIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initView() {
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        edit = sharedPreferences.edit();
        doctorId = sharedPreferences.getInt("doctorId", 0);
        sessionId = sharedPreferences.getString("sessionId", "");
        mPresenter.getQueryIdPresenter(doctorId,sessionId);
        mPresenter.getQueryBankPresenter(doctorId,sessionId);

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_bind_info;
    }

    @Override
    public void onQueryIdSuccess(QueryIdBean queryIdBean) {
        Logger.d("BindInfoActivity", "" + queryIdBean.getMessage());
        QueryIdBean.ResultBean result = queryIdBean.getResult();
        String name = result.getName();
        String sex = result.getSex();
        String nation = result.getNation();
        String idNumber = result.getIdNumber();
        try {
            String s = RsaCoder.decryptByPublicKey(name);
            String s1 = RsaCoder.decryptByPublicKey(sex);
            String s2 = RsaCoder.decryptByPublicKey(nation);
            String s3 = RsaCoder.decryptByPublicKey(idNumber);
            String s4 = idCardHide(s3);
            bindinfoTvName.setText(s);
            bindinfoTvSex.setText(s1);
            bindinfoTvNation.setText(s2);
            bindinfoTvIdNumber.setText(s4);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onQueryIdFailure(Throwable e) {

    }

    @Override
    public void onQueryBankSuccess(QueryBankBean queryBankBean) {
        Logger.d("onQueryBankSuccess", "" + queryBankBean.getMessage());
        QueryBankBean.ResultBean result = queryBankBean.getResult();
        String bankName = result.getBankName();
        bindinfoTvBankName.setText(bankName);
        edit.putString("bankName",bankName);
        edit.commit();
        String bankCardNumber = result.getBankCardNumber();
        String s = bankCardHide(bankCardNumber);
        bindinfoTvBankCardNumber.setText(s);

        int bankCardType = result.getBankCardType();
        if (bankCardType==ONE) {
            bindinfoTvBankCardType.setText("储蓄卡");
        } else if (bankCardType == TWO) {
            bindinfoTvBankCardType.setText("信用卡");
        }

    }

    @Override
    public void onQueryBankFailure(Throwable e) {

    }
    /**
     * @param idCard 身份证号
     */
    public static String idCardHide(String idCard) {
        String idCardHide = idCard.replaceAll("(\\d{6})\\d{8}(\\w{4})","$1  ****  ****  ****");
        return idCardHide;
    }
    /**
     * @param bankCard 银行卡号
     */
    public static String bankCardHide(String bankCard) {
        String bankCardHide = bankCard.replaceAll("\\d{4}(\\d{4})\\d{7}(\\d{4})", "****  $1  *******  $2");
        return bankCardHide;
    }
}
