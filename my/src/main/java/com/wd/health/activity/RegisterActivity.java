package com.wd.health.activity;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.bean.CheckCodeBean;
import com.wd.health.bean.RegBean;
import com.wd.health.bean.ResetUserPwdBean;
import com.wd.health.bean.user.EmailBean;
import com.wd.health.contract.RegisterContract;
import com.wd.health.presenter.RegPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;
import com.wd.mylibrary.utils.RsaCoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegPresenter> implements RegisterContract.IView {

    @BindView(R2.id.edit_email_rg)
    EditText editEmailRg;
    @BindView(R2.id.button_rg)
    CheckBox buttonRg;
    @BindView(R2.id.edit_code_rg)
    EditText editCodeRg;
    @BindView(R2.id.edit_pwd_rg)
    EditText editPwdRg;
    @BindView(R2.id.hide_ears)
    ToggleButton hideEars;
    @BindView(R2.id.edit_pwd_rg2)
    EditText editPwdRg2;
    @BindView(R2.id.hide_ears2)
    ToggleButton hideEars2;
    @BindView(R2.id.edit_yaoqing)
    EditText editYaoqing;
    @BindView(R2.id.reg_btn)
    Button regBtn;
    private String pwd1;
    public static final String TAG = "RegisterActivity";

    @Override
    protected RegPresenter providePresenter() {
        return new RegPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        editPwdRg.setTransformationMethod(PasswordTransformationMethod.getInstance());//默认为隐藏
        hideEars.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判断事件源的选中状态
                if (isChecked) {
                    //显示密码
                    editPwdRg.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    hideEars.setBackgroundResource(R.mipmap.login_icon_show_password);//选中时显示的图标
                } else {
                    //设置转换方法
                    editPwdRg.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    hideEars.setBackgroundResource(R.mipmap.login_icon_hide_password_n);//选中时隐藏的图标
                }
                //每次显示或者关闭时，密码显示编辑的线不统一在最后，下面是为了统一
                editPwdRg.setSelection(editPwdRg.length());//设置选择
            }
        });

        editPwdRg2.setTransformationMethod(PasswordTransformationMethod.getInstance());//默认为隐藏
        hideEars2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判断事件源的选中状态
                if (isChecked) {
                    //显示密码
                    editPwdRg2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    hideEars2.setBackgroundResource(R.mipmap.login_icon_show_password);//选中时显示的图标
                } else {
                    //设置转换方法
                    editPwdRg2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    hideEars2.setBackgroundResource(R.mipmap.login_icon_hide_password_n);//选中时隐藏的图标
                }
                //每次显示或者关闭时，密码显示编辑的线不统一在最后，下面是为了统一
                editPwdRg2.setSelection(editPwdRg2.length());//设置选择
            }
        });
    }

    @Override
    public void onEmailSuccess(EmailBean bean) {
        Logger.d(TAG, "" + bean.getMessage());
        if (bean.getStatus().equals("0000")) {
            Toast.makeText(this, "" + bean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRegSuccess(RegBean bean) {
        if (bean.getStatus().equals("0000")) {
            Toast.makeText(this, "" + bean.getMessage(), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "" + bean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResetUserPwd(ResetUserPwdBean bean) {

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


    @OnClick({R.id.button_rg, R.id.hide_ears, R.id.hide_ears2, R.id.reg_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_rg:
                String email = editEmailRg.getText().toString().trim();
                Logger.d("email", email);
                mPresenter.EmailPresenter(email);
                break;
            case R.id.hide_ears:
                break;
            case R.id.hide_ears2:
                break;
            case R.id.reg_btn:
                String emaill = editEmailRg.getText().toString().trim();
                String code = editCodeRg.getText().toString().trim();
                String trim = editPwdRg.getText().toString().trim();
                try {
                    pwd1 = RsaCoder.encryptByPublicKey(trim);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String yaoqing = editYaoqing.getText().toString().trim();
                String trim1 = editPwdRg2.getText().toString().trim();
                if (trim.equals(trim1)) {
                    mPresenter.RegPresenter(emaill, code, pwd1, pwd1, yaoqing);
                } else {
                    Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
