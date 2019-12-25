package com.wd.health.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.wd.health.R;
import com.wd.health.bean.CheckCodeBean;
import com.wd.health.bean.RegBean;
import com.wd.health.bean.ResetUserPwdBean;
import com.wd.health.bean.user.EmailBean;
import com.wd.health.contract.RegisterContract;
import com.wd.health.presenter.RegPresenter;
import com.wd.health.utils.RsaCoder;
import com.wd.mylibrary.Base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResatUserPwdActivity extends BaseActivity<RegPresenter> implements RegisterContract.IView {

    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.new_pwd1)
    EditText newPwd1;
    @BindView(R.id.yanjing11)
    ToggleButton yanjing11;
    @BindView(R.id.new_pwd2)
    EditText newPwd2;
    @BindView(R.id.yanjing22)
    ToggleButton yanjing22;
    @BindView(R.id.btn_finish)
    Button btnFinish;
    private String email;
    private String pwd1;
    private String pwd2;

    @Override
    protected RegPresenter providePresenter() {
        return new RegPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_resat_user_pwd;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        email = intent.getStringExtra("email");


        newPwd1.setTransformationMethod(PasswordTransformationMethod.getInstance());//默认为隐藏
        yanjing11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判断事件源的选中状态
                if (isChecked) {
                    //显示密码
                    newPwd1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    yanjing11.setBackgroundResource(R.mipmap.login_icon_show_password);//选中时显示的图标
                } else {
                    //设置转换方法
                    newPwd1.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    yanjing11.setBackgroundResource(R.mipmap.login_icon_hide_password_n);//选中时隐藏的图标
                }
                //每次显示或者关闭时，密码显示编辑的线不统一在最后，下面是为了统一
                newPwd1.setSelection(newPwd1.length());//设置选择
            }
        });

        newPwd2.setTransformationMethod(PasswordTransformationMethod.getInstance());//默认为隐藏
        yanjing22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判断事件源的选中状态
                if (isChecked) {
                    //显示密码
                    newPwd2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    yanjing22.setBackgroundResource(R.mipmap.login_icon_show_password);//选中时显示的图标
                } else {
                    //设置转换方法
                    newPwd2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    yanjing22.setBackgroundResource(R.mipmap.login_icon_hide_password_n);//选中时隐藏的图标
                }
                //每次显示或者关闭时，密码显示编辑的线不统一在最后，下面是为了统一
                newPwd2.setSelection(newPwd2.length());//设置选择
            }
        });

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onEmailSuccess(EmailBean bean) {

    }

    @Override
    public void onRegSuccess(RegBean bean) {

    }

    @Override
    public void onResetUserPwd(ResetUserPwdBean bean) {
        if (bean.getStatus().equals("0000")){
            Toast.makeText(this, ""+bean.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onCheckCodeSuccess(CheckCodeBean bean) {

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

    @OnClick({R.id.fanhui, R.id.btn_finish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.btn_finish:
                String trim = newPwd1.getText().toString().trim();
                try {
                    pwd1 = RsaCoder.encryptByPublicKey(trim);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String trim1 = newPwd2.getText().toString().trim();
                if (trim.equals(trim1)){
                    mPresenter.getResatUserPwdPresenter(email,pwd1,pwd1);
                }else {
                    Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }
}
