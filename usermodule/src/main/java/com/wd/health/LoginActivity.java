package com.wd.health;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.health.bean.LoginBean;
import com.wd.health.contract.LoginContract;
import com.wd.health.presenter.LoginPresenter;
import com.wd.health.utils.MD5Utils;
import com.wd.health.view.RegisteredActivity;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.Test.Logger;
import com.wd.mylibrary.Test.ToastUtils;
import com.wd.mylibrary.utils.EncryptUtil;
import com.wd.mylibrary.utils.RsaCoder;
import com.wd.mymodule.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.iView {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.iv_mail_n)
    ImageView ivMailN;
    @BindView(R.id.login_et_youxiang)
    EditText loginEtYouxiang;
    @BindView(R.id.icon_lock_n)
    ImageView iconLockN;
    @BindView(R.id.login_et_pwd)
    EditText loginEtPwd;
    @BindView(R.id.login_bt_denglu)
    Button loginBtDenglu;
    @BindView(R.id.login_icon_yanjing)
    ImageView loginIconYanjing;
    @BindView(R.id.login_tv_wjpwd)
    TextView loginTvWjpwd;
    @BindView(R.id.login_tv_zhuce)
    TextView loginTvZhuce;
    @BindView(R.id.login_activity_btn_wxlogin)
    ImageView loginActivityBtnWxlogin;
    private boolean lock;

    @Override
    protected LoginPresenter providePresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initData() {
        loginIconYanjing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lock = false;
                if (lock) {
                    lock = false;
                    //设置密码不可见
                    loginEtPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    //设置对应的输入框可见图片
                    loginIconYanjing.setImageResource(R.mipmap.login_icon_hide_password_n);
                }else{
                    lock = true;
                    loginEtPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    loginIconYanjing.setImageResource(R.mipmap.login_icon_show_password);
                }
            }
        });
        //登录
        loginBtDenglu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = loginEtYouxiang.getText().toString();
                String pwd = loginEtPwd.getText().toString();
                if (!name.isEmpty() && !pwd.isEmpty()) {
                    try {
                        String s = RsaCoder.encryptByPublicKey(pwd);
                        mPresenter.getLoginPresenter(name,s);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) throws Exception {
        Logger.d("LoginActivity",""+loginBean.getMessage());
        if (loginBean.getMessage().equals("登录成功")) {
            String jiGuangPwd = loginBean.getResult().getJiGuangPwd();
            String userName = loginBean.getResult().getUserName();
            String s = RsaCoder.decryptByPublicKey(jiGuangPwd);
            Log.d(TAG, "onLoginSuccess: "+s);
            Log.d(TAG, "userName: "+userName);
            ToastUtils.show("登录成功");
            String md5 = MD5Utils.MD5(s);
            Log.d(TAG, "md5: "+md5);
        }else {
            ToastUtils.show("输入的邮箱或密码不正确！");
        }
    }

    @Override
    public void onLoginFailure(Throwable e) {

    }
    @OnClick({R.id.login_bt_denglu, R.id.login_tv_wjpwd, R.id.login_tv_zhuce, R.id.login_activity_btn_wxlogin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_bt_denglu:
                break;
            case R.id.login_tv_wjpwd:
                break;
            case R.id.login_tv_zhuce:
                startActivity(new Intent(this, RegisteredActivity.class));
                break;
            case R.id.login_activity_btn_wxlogin:
                break;
        }
    }
}
