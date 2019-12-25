package com.wd.health.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.health.R;
import com.wd.health.bean.UpdateUserPwdBean;
import com.wd.health.contract.UpdateUserPwdContract;
import com.wd.health.presenter.UpdateUserPwdPresenter;
import com.wd.health.utils.RsaCoder;
import com.wd.mylibrary.Base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReplacePassWordActivity extends BaseActivity<UpdateUserPwdPresenter> implements UpdateUserPwdContract.IView {

    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.pwd_oldpwd)
    EditText pwdOldpwd;
    @BindView(R.id.pwd_newpwd)
    EditText pwdNewpwd;
    @BindView(R.id.pwd_newpwd2)
    EditText pwdNewpwd2;
    @BindView(R.id.pwd_buttton)
    Button pwdButtton;
    private String id;
    private String sessionId;
    private String oldPwd;
    private String newPwd;

    @Override
    protected UpdateUserPwdPresenter providePresenter() {
        return new UpdateUserPwdPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_replace_pass_word;
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        id = sp.getString("id", "");
        sessionId = sp.getString("sessionId", "");
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onUpdateUserPwdSuccess(UpdateUserPwdBean bean) {
        if (bean.getStatus().equals("0000")){
            Toast.makeText(this, ""+bean.getMessage(), Toast.LENGTH_SHORT).show();
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

    @OnClick({R.id.fanhui, R.id.pwd_buttton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.pwd_buttton:
                String oldpwd = pwdOldpwd.getText().toString().trim();
                String newpwd = pwdNewpwd.getText().toString().trim();
                String newpwd2 = pwdNewpwd2.getText().toString().trim();
                try {
                    oldPwd = RsaCoder.encryptByPublicKey(oldpwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (newpwd.equals(newpwd2)){
                    try {
                        newPwd = RsaCoder.encryptByPublicKey(newpwd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                mPresenter.UpdateUserPwdPresenter(id,sessionId,oldPwd,newPwd);
                break;
        }
    }
}
