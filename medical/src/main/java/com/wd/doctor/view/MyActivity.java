package com.wd.doctor.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.wd.doctor.R;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyActivity extends BaseActivity {
    private static final String TAG = "MyActivity";
    @BindView(R.id.my_iv_touxiang)
    ImageView myIvTouxiang;
    @BindView(R.id.my_iv_back)
    ImageView myIvBack;
    @BindView(R.id.my_iv_xiaoxin)
    ImageView myIvXiaoxin;
    @BindView(R.id.my_tv_geren)
    TextView myTvGeren;
    @BindView(R.id.my_rbton_lishiwenzheng)
    RadioButton myRbtonLishiwenzheng;
    @BindView(R.id.my_rbton_qianbao)
    RadioButton myRbtonQianbao;
    @BindView(R.id.my_rbton_jianyi)
    RadioButton myRbtonJianyi;
    @BindView(R.id.my_rbton_huifu)
    RadioButton myRbtonHuifu;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {
        //返回
        myIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        myTvGeren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyActivity.this,DoctorInforActivity.class));
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_my;
    }

}
