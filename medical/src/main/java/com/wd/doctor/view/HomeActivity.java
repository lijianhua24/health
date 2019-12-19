package com.wd.doctor.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.doctor.R;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity {
    private static final String TAG = "HomeActivity";
    @BindView(R.id.home_sdv_touxiang)
    SimpleDraweeView homeSdvTouxiang;
    @BindView(R.id.home_iv_xiaoxi)
    ImageView homeIvXiaoxi;
    @BindView(R.id.home_iv_wenzheng)
    ImageView homeIvWenzheng;
    @BindView(R.id.home_iv_dayi)
    ImageView homeIvDayi;
    @BindView(R.id.home_tv_wd)
    TextView homeTvWd;
    @BindView(R.id.home_iv_wode)
    ImageView homeIvWode;
    @BindView(R.id.home_tv_name)
    TextView homeTvName;
    @BindView(R.id.home_tv_yiyuan)
    TextView homeTvYiyuan;
    @BindView(R.id.home_tv_zhicheng)
    TextView homeTvZhicheng;
    @BindView(R.id.home_tv_keshi)
    TextView homeTvKeshi;
    @BindView(R.id.home_rl_gerenwd)
    RelativeLayout homeRlGerenwd;
    @BindView(R.id.home_rl_wenzheng)
    RelativeLayout homeRlWenzheng;
    @BindView(R.id.home_rl_dayi)
    RelativeLayout homeRlDayi;
    private String imagePic;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        imagePic = intent.getStringExtra("imagePic");
        String inauguralHospital = intent.getStringExtra("inauguralHospital");
        String jobTitle = intent.getStringExtra("jobTitle");
        String departmentName = intent.getStringExtra("departmentName");
//        homeSdvTouxiang.setImageURI("res://mipmap/" + R.mipmap.e);
        homeSdvTouxiang.setImageURI(imagePic);
        homeTvName.setText(name);//姓名
        homeTvYiyuan.setText(inauguralHospital);//就职医院
        homeTvZhicheng.setText(jobTitle);//职称
        homeTvKeshi.setText(departmentName);//科室名称
        //我的
        homeRlGerenwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent();
                intent1.setClass(HomeActivity.this, MyActivity.class);
                intent1.putExtra("imagePic",imagePic);
                startActivity(intent1);
            }
        });
        //答疑
        homeRlDayi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,EnquiryActivity.class));
            }
        });

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_home;
    }

}
