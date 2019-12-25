package com.wd.health.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.wd.health.LoginActivity;
import com.wd.health.bean.RegisteredBean;
import com.wd.health.bean.SendEmilBean;
import com.wd.health.contract.RegisteredContract;
import com.wd.health.presenter.RegisteredPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;
import com.wd.mylibrary.Test.ToastUtils;
import com.wd.mylibrary.utils.RsaCoder;
import com.wd.mymodule.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisteredActivity extends BaseActivity<RegisteredPresenter> implements RegisteredContract.iView {
    private static final String TAG = "RegisteredActivity";
    @BindView(R.id.registered_icon_logo)
    ImageView registeredIconLogo;
    @BindView(R.id.iv_mail_n)
    ImageView ivMailN;
    @BindView(R.id.registered_et_youxiang)
    EditText registeredEtYouxiang;
    @BindView(R.id.registered_cb_code)
    CheckBox registeredCbCode;
    @BindView(R.id.icon_verification)
    ImageView iconVerification;
    @BindView(R.id.registered_et_code)
    EditText registeredEtCode;
    @BindView(R.id.icon_lock_one)
    ImageView iconLockOne;
    @BindView(R.id.registered_et_pwd)
    EditText registeredEtPwd;
    @BindView(R.id.registered_icon_yanone)
    ImageView registeredIconYanone;
    @BindView(R.id.icon_lock_two)
    ImageView iconLockTwo;
    @BindView(R.id.registered_et_pwdtwo)
    EditText registeredEtPwdtwo;
    @BindView(R.id.registered_icon_yantwo)
    ImageView registeredIconYantwo;
    @BindView(R.id.icon_invitatiion)
    ImageView iconInvitatiion;
    @BindView(R.id.registered_et_yaoqing)
    EditText registeredEtYaoqing;
    @BindView(R.id.registered_bt_zhuce)
    Button registeredBtZhuce;
    private boolean lock;

    @Override
    protected RegisteredPresenter providePresenter() {
        return new RegisteredPresenter();
    }

    @Override
    protected void initData() {
        //发送验证码
        registeredCbCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = registeredEtYouxiang.getText().toString().trim();
                mPresenter.getSendEmilPresenter(trim);
            }
        });
        //注册
        registeredBtZhuce.setOnClickListener(new View.OnClickListener() {

            private String s1;

            @Override
            public void onClick(View v) {
                String emil = registeredEtYouxiang.getText().toString().trim();
                String code = registeredEtCode.getText().toString().trim();
                String pwd1 = registeredEtPwd.getText().toString().trim();
                String pwd2 = registeredEtPwdtwo.getText().toString().trim();
                String xuantian = registeredEtYaoqing.getText().toString().trim();
                if (!emil.isEmpty() && !code.isEmpty() && !pwd1.isEmpty() && !pwd2.isEmpty()) {
                    if (pwd2.equals(pwd1)) {
                        try {
                            s1 = RsaCoder.encryptByPublicKey(pwd1);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        mPresenter.getRegisteredPresenter(emil, code, s1, s1, xuantian);
                    } else {
                        ToastUtils.show("两次密码输入不一致");
                    }
                } else {
                    ToastUtils.show("输入不能为空");
                }

            }
        });

        registeredIconYanone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lock){
                    lock=false;
                    registeredEtPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    registeredIconYanone.setImageResource(R.mipmap.login_icon_hide_password_n);
                }else {
                    lock=true;
                    registeredEtPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    registeredIconYanone.setImageResource(R.mipmap.login_icon_show_password);
                }
            }
        });
        registeredIconYantwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lock){
                    lock=false;
                    registeredEtPwdtwo.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    registeredIconYantwo.setImageResource(R.mipmap.login_icon_hide_password_n);
                }else {
                    lock=true;
                    registeredEtPwdtwo.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    registeredIconYantwo.setImageResource(R.mipmap.login_icon_show_password);
                }
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_registered;
    }

    @Override
    public void onRegisteredSuccess(RegisteredBean registeredBean) {
        Logger.d("RegisteredActivity", "" + registeredBean.getMessage());
        String message = registeredBean.getMessage();
        ToastUtils.show(message);
        startActivity(new Intent(RegisteredActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void onRegisteredFailure(Throwable e) {

    }

    @Override
    public void onSendEmilSuccess(SendEmilBean sendEmilBean) {
        Logger.d("RegisteredActivity", "onSendEmilSuccess" + sendEmilBean.getMessage());
        ToastUtils.show("" + sendEmilBean.getMessage());
    }

    @Override
    public void onSendEmilFailure(Throwable e) {

    }

}
