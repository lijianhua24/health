package com.wd.doctor.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.doctor.R;
import com.wd.doctor.bean.ImageQueryBean;
import com.wd.doctor.bean.LoginBean;
import com.wd.doctor.contract.LoginContract;
import com.wd.doctor.presenter.LoginPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;
import com.wd.mylibrary.Test.ToastUtils;
import com.wd.mylibrary.utils.RsaCoder;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.iView {
    private static final String TAG = "LoginActivity";
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.pwd_bi)
    ImageView pwdBi;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.tv_settled_in)
    TextView tvSettledIn;
    @BindView(R.id.bt_login)
    Button btLogin;
    private boolean lock;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;
    private Intent intent;
    private List<ImageQueryBean.ResultBean> result1;

    @Override
    protected LoginPresenter providePresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initData() {
        //忘记密码
        tvForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgetActivity.class));
            }
        });

            //登录
            btLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = etEmail.getText().toString().trim();
                    String pwd = etPwd.getText().toString().trim();
                        //判断非空
                        if (!email.isEmpty() && !pwd.isEmpty()) {
                            try {
                                String s = RsaCoder.encryptByPublicKey(pwd);
                                //判断网络
                                boolean b = hasNetwork();
                                if (b) {
                                    mPresenter.getLoginPresenter(email,s);
                                }else {
                                    ToastUtils.show("请检查一下网络");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }else {
                            ToastUtils.show("输入不能为空");
                        }

                }
            });


        //申请入住
        tvSettledIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ResidencyActivity.class));
            }
        });
        //密码显示隐藏
        pwdBi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lock) {
                    lock=false;
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    pwdBi.setImageResource(R.mipmap.login_pwd_bi);
                }else {
                    lock=true;
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    pwdBi.setImageResource(R.mipmap.login_pwd_kai);
                }
            }
        });
    }

    @Override
    protected void initView() {
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        edit = sharedPreferences.edit();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {
        Logger.d("LoginActivity",""+loginBean.getMessage());
        ToastUtils.show(""+loginBean.getMessage());
        LoginBean.ResultBean result = loginBean.getResult();
            String name = result.getName();//姓名
            String inauguralHospital = result.getInauguralHospital();//就职医院
            String jobTitle = result.getJobTitle();//职称
            String departmentName = result.getDepartmentName();//科室名称
            int doctorId = result.getId();
            String sessionId = result.getSessionId();
            edit.putInt("doctorId",doctorId);
            edit.putString("sessionId",sessionId);
            edit.commit();

        if (loginBean.getMessage().equals("登录成功")) {
            intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.putExtra("name",name);
            intent.putExtra("inauguralHospital",inauguralHospital);
            intent.putExtra("jobTitle",jobTitle);
            intent.putExtra("departmentName",departmentName);
            int whetherHaveImagePic = result.getWhetherHaveImagePic();
            if (whetherHaveImagePic == 1) {
                intent.putExtra("imagePic",result.getImagePic());
            } else if (whetherHaveImagePic == 2) {
                intent.putExtra("imagePic",result1.get(1).getImagePic());
            }
            startActivity(intent);
        }else{ }

    }

    @Override
    public void onLoginFailure(Throwable e) {
        ToastUtils.show("登录失败，账号或密码错误");

    }

    @Override
    public void onImageQuerySuccess(ImageQueryBean imageQueryBean) {
        result1 = imageQueryBean.getResult();
    }

    @Override
    public void onImageQueryFailure(Throwable e) {

    }
    /**
     * EditText 禁止换行
     * 设置相关监听器
     */
    private void setListener(){
        etEmail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return (event.getKeyCode()==KeyEvent.KEYCODE_ENTER);

            }
        });
        etPwd.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return (event.getKeyCode()==KeyEvent.KEYCODE_ENTER);

            }
        });
    }

//    public static boolean isEmail(String email) {
//        String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
//        Pattern p = Pattern.compile(str);
//        Matcher m = p.matcher(email);
//        Logger.d("aaa",""+m.matches() + "---");
//        return m.matches();
//    }
    }
