package com.wd.health.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.bean.CheckCodeBean;
import com.wd.health.bean.RegBean;
import com.wd.health.bean.ResetUserPwdBean;
import com.wd.health.bean.user.EmailBean;
import com.wd.health.contract.RegisterContract;
import com.wd.health.presenter.RegPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetActivity extends BaseActivity<RegPresenter> implements RegisterContract.IView {

    @BindView(R2.id.fanhui)
    ImageView fanhui;
    @BindView(R2.id.new_email)
    EditText newEmail;
    @BindView(R2.id.btn_email)
    CheckBox btnEmail;
    @BindView(R2.id.edit_code)
    EditText editCode;
    @BindView(R2.id.next)
    Button next;
    private String email;

    @Override
    protected RegPresenter providePresenter() {
        return new RegPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int provideLayoutId() {
        return R2.layout.activity_forget;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }


    @Override
    public void onEmailSuccess(EmailBean bean) {

    }

    @Override
    public void onRegSuccess(RegBean bean) {

    }

    @Override
    public void onResetUserPwd(ResetUserPwdBean bean) {

    }

    @Override
    public void onCheckCodeSuccess(CheckCodeBean bean) {
        if (bean.getStatus().equals("0000")) {
            Toast.makeText(this, "" + bean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @OnClick({R.id.fanhui, R.id.btn_email, R.id.next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.btn_email:
                email = newEmail.getText().toString().trim();
                mPresenter.EmailPresenter(email);
                break;
            case R.id.next:
                String code = editCode.getText().toString().trim();
                String trim = newEmail.getText().toString().trim();
                if (code.equals("") && trim.equals("")){
                    Toast.makeText(this, "邮箱或验证码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    mPresenter.getCheckCodePresenter(email, code);
                    Intent intent = new Intent(ForgetActivity.this, ResatUserPwdActivity.class);
                    intent.putExtra("email", newEmail.getText().toString().trim() + "");
                    startActivity(intent);
                }

                break;
        }
    }
}
