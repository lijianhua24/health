package com.wd.doctor.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.doctor.R;
import com.wd.doctor.bean.DoctorInforBean;
import com.wd.doctor.contract.DoctorInforContract;
import com.wd.doctor.presenter.DoctorInforPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DoctorInforActivity extends BaseActivity<DoctorInforPresenter> implements DoctorInforContract.iView {
    private static final String TAG = "DoctorInforActivity";
    @BindView(R.id.docorinfor_iv_back)
    ImageView docorinforIvBack;
    @BindView(R.id.docorinfor_tv_name)
    TextView docorinforTvName;
    @BindView(R.id.docorinfor_tv_yiyuan)
    TextView docorinforTvYiyuan;
    @BindView(R.id.docorinfor_tv_keshi)
    TextView docorinforTvKeshi;
    @BindView(R.id.docorinfor_tv_zhicheng)
    TextView docorinforTvZhicheng;
    @BindView(R.id.tv_jieshao)
    TextView tvJieshao;
    @BindView(R.id.docorinfor_tv_jieshao)
    TextView docorinforTvJieshao;
    @BindView(R.id.tv_lingyu)
    TextView tvLingyu;
    @BindView(R.id.docorinfor_tv_lingyu)
    TextView docorinforTvLingyu;
    private SharedPreferences sharedPreferences;

    @Override
    protected DoctorInforPresenter providePresenter() {
        return new DoctorInforPresenter();
    }

    @Override
    protected void initData() {
        docorinforIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void initView() {
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        int doctorId = sharedPreferences.getInt("doctorId", 0);
        String sessionId = sharedPreferences.getString("sessionId", "");
        Log.d(TAG, "initView: " + doctorId);
        Log.d(TAG, "initView: " + sessionId);
        mPresenter.getDoctorInforPresenter(doctorId,sessionId);

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_doctor_infor;
    }

    @Override
    public void onDoctorInforSuccess(DoctorInforBean doctorInforBean) {
        Logger.d("DoctorInforActivity", "" + doctorInforBean.getMessage());
        DoctorInforBean.ResultBean result = doctorInforBean.getResult();
        docorinforTvName.setText(result.getName());
        docorinforTvYiyuan.setText(result.getInauguralHospital());
        docorinforTvKeshi.setText(result.getDepartmentName());
        docorinforTvZhicheng.setText(result.getJobTitle());
        docorinforTvJieshao.setText(result.getPersonalProfile());
        docorinforTvLingyu.setText(result.getGoodField());

    }

    @Override
    public void onDoctorInforFailure(Throwable e) {

    }


}
