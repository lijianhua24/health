package com.wd.health.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.wd.health.R;

import com.wd.health.R2;
import com.wd.health.bean.WxLogBean;
import com.wd.health.bean.evebus.SettingBus;
import com.wd.health.bean.user.LoginBean;
import com.wd.health.contract.LoginContract;
import com.wd.health.presenter.LoginPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;
import com.wd.mylibrary.utils.RsaCoder;

import org.greenrobot.eventbus.EventBus;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wd.health.app.App.api;


public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.IView {


    @BindView(R2.id.email)
    ImageView email;
    @BindView(R2.id.edit_email)
    EditText editEmail;
    @BindView(R2.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R2.id.lock)
    ImageView lock;
    @BindView(R2.id.edit_pwd)
    EditText editPwd;
    @BindView(R2.id.yanjing)
    ToggleButton yanjing;
    @BindView(R2.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    @BindView(R2.id.btn_login)
    Button btnLogin;
    @BindView(R2.id.weixin)
    SimpleDraweeView weixin;
    public static final String TAG = "LoginActivity";
    @BindView(R2.id.reg)
    TextView reg;
    @BindView(R2.id.forget_pwd)
    TextView forgetPwd;
    private SharedPreferences.Editor editor;
    private String md5;
    private String s;

    @Override
    protected LoginPresenter providePresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R2.layout.activity_login;
    }


    @Override
    protected void initView() {
        Intent intent = getIntent();
        String code = intent.getStringExtra("code");
        if (code != null) {
            Logger.i(TAG, "initData: " + code);
            mPresenter.getWxloginPresenter(code);
        }
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        editor = sp.edit();
        editPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());//默认为隐藏
        yanjing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //判断事件源的选中状态
                if (isChecked) {
                    //显示密码
                    editPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    yanjing.setBackgroundResource(R.mipmap.login_icon_show_password);//选中时显示的图标
                } else {
                    //设置转换方法
                    editPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    yanjing.setBackgroundResource(R.mipmap.login_icon_hide_password_n);//选中时隐藏的图标
                }
                //每次显示或者关闭时，密码显示编辑的线不统一在最后，下面是为了统一
                editPwd.setSelection(editPwd.length());//设置选择
            }
        });

        forgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgetActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLoginSuccess(LoginBean bean) {
        Logger.d(TAG, "" + bean.getMessage());
        if (bean.getStatus().equals("0000")) {
            editor.putString("id", bean.getResult().getId() + "");
            editor.putString("sessionId", bean.getResult().getSessionId() + "");
            editor.putString("headPic", bean.getResult().getHeadPic());
            editor.putString("nickName", bean.getResult().getNickName());
            editor.putString("sex", bean.getResult().getSex() + "");
            editor.putString("email", bean.getResult().getEmail());
            editor.commit();

            LoginBean.ResultBean result = bean.getResult();
            String jiGuangPwd = result.getJiGuangPwd();
            String userName = result.getUserName();
            try {
                s = RsaCoder.decryptByPublicKey(jiGuangPwd);

            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.d(TAG, "onLoginSuccess: "+s);
            String s1 = MD5(s);
            Log.d(TAG, "onLoginSuccess: "+s1);
            EventBus.getDefault().postSticky(new SettingBus(bean.getResult().getNickName(), bean.getResult().getHeadPic()));
            Toast.makeText(this, "" + bean.getMessage(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent();
            finish();
        } else if (bean.getStatus().equals("8001")) {
            Toast.makeText(this, "" + bean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onWxloginSuccess(WxLogBean bean) {
        Logger.i(TAG, "onWxloginSuccess: " + bean.getMessage());
        if (bean.getMessage().equals("登陆成功")) {

            editor.putString("id", bean.getResult().getId() + "");
            editor.putString("sessionId", bean.getResult().getSessionId() + "");
            editor.putString("headPic", bean.getResult().getHeadPic());
            editor.putString("nickName", bean.getResult().getNickName());
            editor.putString("md5", md5);
            Logger.i(TAG, "onWxloginSuccess: " + bean.getResult().getId() + "");
            Logger.i(TAG, "onWxloginSuccess: " + bean.getResult().getSessionId() + "");
            EventBus.getDefault().postSticky(new SettingBus(bean.getResult().getNickName(), bean.getResult().getHeadPic()));
            editor.commit();

            finish();
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

    @OnClick({R.id.btn_login, R.id.weixin})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String email = editEmail.getText().toString().trim();
                String s = editPwd.getText().toString().trim();
                md5 = MD5(s);

                try {
                    String pwd = RsaCoder.encryptByPublicKey(s);
                    mPresenter.LoginPresenter(email, pwd);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case R.id.weixin:
                if (!api.isWXAppInstalled()) {
                    Toast.makeText(LoginActivity.this, "您的设备未安装微信客户端", Toast.LENGTH_SHORT).show();
                } else {
                    final SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_demo_test";
                    api.sendReq(req);
                }

                finish();
                break;
        }
    }

    @OnClick(R.id.reg)
    public void onClick() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
}
