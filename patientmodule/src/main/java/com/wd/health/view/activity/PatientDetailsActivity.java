package com.wd.health.view.activity;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wd.health.R;
import com.wd.health.bean.PatientDetailsBean;
import com.wd.health.contract.IContractDetails;
import com.wd.health.presenter.PatientDetailsPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;

import java.text.SimpleDateFormat;

public class PatientDetailsActivity extends BaseActivity<PatientDetailsPresenter> implements IContractDetails.iView {

    private ImageView patient_iv_user_head_pic;
    private TextView patient_activity_tv_title;
    private ImageView patient_iv_user_message;
    private RelativeLayout patient_relative_titlebar;
    private RelativeLayout patient_activity_relative_release_sickCircle;
    private TextView patient_activity_tv_adoptNickName;
    private TextView patient_activity_tv_disease;
    private TextView patient_activity_tv_department;
    private TextView patient_activity_tv_detail;
    private TextView patient_activity_tv_treatmentProcess;
    private ImageView patient_activity_iv_picture;
    private ImageView patient_activity_iv_intent_release_sickCircle;
    private TextView patient_activity_tv_commentNum;
    private TextView patient_activity_tv_collectionNum;
    private RecyclerView recycler_sick_circle_comment_list;
    private TextView patient_activity_tv_treatment_time;
    private ImageView patient_activity_iv_content;
    private RelativeLayout patient_activity_relative_content;
    private ImageView patient_activity_iv_cancel;
    //发表评论
    private ImageView patient_activity_iv_send_content;
    //输入评论内容
    private EditText patient_activity_et_content;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int sickCircleId = intent.getIntExtra("sickCircleId", 0);
        Logger.d("FFFFFFF", sickCircleId + "");
        mPresenter.getPatientDetailsPresenter(445, "1576310426648445", sickCircleId);
    }

    @Override
    protected PatientDetailsPresenter providePresenter() {
        return new PatientDetailsPresenter();
    }

    @Override
    protected void initView() {
        patient_iv_user_head_pic = (ImageView) findViewById(R.id.patient_iv_user_head_pic);
        patient_activity_tv_title = (TextView) findViewById(R.id.patient_activity_tv_title);
        patient_iv_user_message = (ImageView) findViewById(R.id.patient_iv_user_message);
        patient_activity_iv_intent_release_sickCircle = (ImageView) findViewById(R.id.patient_activity_iv_intent_release_sickCircle);
        patient_activity_iv_cancel = (ImageView) findViewById(R.id.patient_activity_iv_cancel);
        patient_relative_titlebar = (RelativeLayout) findViewById(R.id.patient_relative_titlebar);
        patient_activity_relative_release_sickCircle = (RelativeLayout) findViewById(R.id.patient_activity_relative_release_sickCircle);
        patient_activity_relative_content = (RelativeLayout) findViewById(R.id.patient_activity_relative_content);
        patient_activity_tv_adoptNickName = (TextView) findViewById(R.id.patient_activity_tv_adoptNickName);
        patient_activity_tv_disease = (TextView) findViewById(R.id.patient_activity_tv_disease);
        patient_activity_tv_department = (TextView) findViewById(R.id.patient_activity_tv_department);
        patient_activity_tv_detail = (TextView) findViewById(R.id.patient_activity_tv_detail);
        patient_activity_tv_treatmentProcess = (TextView) findViewById(R.id.patient_activity_tv_treatmentProcess);
        patient_activity_tv_treatment_time = (TextView) findViewById(R.id.patient_activity_tv_treatment_time);
        patient_activity_iv_picture = (ImageView) findViewById(R.id.patient_activity_iv_picture);
        patient_activity_iv_content = (ImageView) findViewById(R.id.patient_activity_iv_content);
        patient_activity_iv_send_content = (ImageView) findViewById(R.id.patient_activity_iv_send_content);
        patient_activity_tv_commentNum = (TextView) findViewById(R.id.patient_activity_tv_commentNum);
        patient_activity_tv_collectionNum = (TextView) findViewById(R.id.patient_activity_tv_collectionNum);
        recycler_sick_circle_comment_list = (RecyclerView) findViewById(R.id.recycler_sick_circle_comment_list);
        patient_activity_et_content = (EditText) findViewById(R.id.patient_activity_et_content);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_patient_details;
    }

    @Override
    public void PatientDetailssuccess(PatientDetailsBean patientDetailsBean) {
        Logger.d("CCCCCC", "" + patientDetailsBean.getMessage());
        PatientDetailsBean.ResultBean result = patientDetailsBean.getResult();
        patient_activity_tv_title.setText(result.getTitle() + "");
        patient_activity_tv_adoptNickName.setText(result.getAdoptNickName() + "");
        patient_activity_tv_disease.setText(result.getDisease() + "");
        patient_activity_tv_department.setText(result.getDepartment() + "");
        patient_activity_tv_detail.setText(result.getDetail() + "");
        patient_activity_tv_treatmentProcess.setText(result.getTreatmentProcess() + "");
        long treatmentStartTime = result.getTreatmentStartTime();
        long treatmentEndTime = result.getTreatmentEndTime();
        SimpleDateFormat format0 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String endTimes = format0.format(treatmentEndTime);
        String startTimes = format0.format(treatmentStartTime);
        patient_activity_tv_treatment_time.setText(startTimes + "----" + endTimes);
        Glide.with(this).load(result.getPicture())
                .placeholder(R.drawable.none_comment)
                .error(R.drawable.none_comment)
                .into(patient_activity_iv_picture);
        patient_activity_tv_commentNum.setText(result.getCommentNum() + "");
        patient_activity_tv_collectionNum.setText(result.getCollectionNum() + "");

        patient_activity_iv_intent_release_sickCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientDetailsActivity.this, ReleaseCirclesActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void PatientDetailsFailure(Throwable e) {

    }
}
