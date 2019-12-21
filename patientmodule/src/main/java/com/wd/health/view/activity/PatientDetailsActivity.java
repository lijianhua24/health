package com.wd.health.view.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.bean.CommentCircleBean;
import com.wd.health.bean.OpinionBean;
import com.wd.health.bean.PatientDetailsBean;
import com.wd.health.bean.QueryCommentBean;
import com.wd.health.contract.IContractDetails;
import com.wd.health.presenter.PatientDetailsPresenter;
import com.wd.health.view.adapter.RecyclerSickCircleCommentListAdapter;
import com.wd.health.view.custom.SyLinearLayoutManager;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;
import com.wd.mylibrary.Test.ToastUtils;
import com.wd.mylibrary.app.Constant;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PatientDetailsActivity extends BaseActivity<PatientDetailsPresenter> implements IContractDetails.iView {


    @BindView(R.id.patient_iv_user_head_pic)
    ImageView patient_iv_user_head_pic;
    @BindView(R.id.patient_activity_tv_title)
    TextView patient_activity_tv_title;
    @BindView(R.id.patient_iv_user_message)
    ImageView patient_iv_user_message;
    @BindView(R.id.patient_relative_titlebar)
    RelativeLayout patient_relative_titlebar;
    @BindView(R.id.patient_activity_tv_adoptNickName)
    TextView patient_activity_tv_adoptNickName;
    @BindView(R.id.patient_activity_tv_disease)
    TextView patient_activity_tv_disease;
    @BindView(R.id.patient_activity_tv_department)
    TextView patient_activity_tv_department;
    @BindView(R.id.patient_activity_tv_detail)
    TextView patient_activity_tv_detail;
    @BindView(R.id.patient_activity_tv_treatment_time)
    TextView patient_activity_tv_treatment_time;
    @BindView(R.id.patient_activity_tv_treatmentProcess)
    TextView patient_activity_tv_treatmentProcess;
    @BindView(R.id.patient_activity_iv_picture)
    ImageView patient_activity_iv_picture;
    @BindView(R.id.patient_activity_tv_commentNum)
    TextView patient_activity_tv_commentNum;
    @BindView(R.id.patient_activity_iv_content)
    ImageView patient_activity_iv_content;
    @BindView(R.id.patient_activity_tv_collectionNum)
    TextView patient_activity_tv_collectionNum;
    @BindView(R.id.img_HeadPic)
    SimpleDraweeView imgHeadPic;
    @BindView(R.id.name_NickName)
    TextView nameNickName;
    @BindView(R.id.time_adoptTime)
    TextView time_adoptTime;
    @BindView(R.id.text_adoptComment)
    TextView textadoptComment;
    @BindView(R.id.adoptFlag)
    LinearLayout adoptFlag;
    @BindView(R.id.recycler_sick_circle_comment_list)
    RecyclerView recycler_sick_circle_comment_list;
    @BindView(R.id.patient_activity_iv_cancel)
    ImageView patient_activity_iv_cancel;
    @BindView(R.id.patient_activity_et_content)
    EditText patient_activity_et_content;
    @BindView(R.id.patient_activity_iv_send_content)
    ImageView patient_activity_iv_send_content;
    @BindView(R.id.patient_activity_relative_content)
    RelativeLayout patient_activity_relative_content;
    @BindView(R.id.patient_activity_iv_intent_release_sickCircle)
    ImageView patient_activity_iv_intent_release_sickCircle;
    @BindView(R.id.patient_activity_relative_release_sickCircle)
    RelativeLayout patient_activity_relative_release_sickCircle;
    private RecyclerSickCircleCommentListAdapter recyclerSickCircleCommentListAdapter;
    private RelativeLayout activiy_patient_zong;
    private LinearLayout activiy_patient_deng;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int sickCircleId = intent.getIntExtra("sickCircleId", 0);
        Logger.d("FFFFFFF", sickCircleId + "");
        mPresenter.getPatientDetailsPresenter(445, "1576889997444445", sickCircleId);
        //关闭帖子
        patient_activity_iv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                patient_activity_relative_content.setVisibility(View.GONE);
                patient_activity_relative_release_sickCircle.setVisibility(View.VISIBLE);
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(patient_activity_et_content.getWindowToken(), 0);
            }
        });

        patient_activity_iv_send_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = patient_activity_et_content.getText().toString().trim();
                mPresenter.getCommentCircle(445, "1576831157143445", sickCircleId, trim);
            }
        });

    }

    @Override
    protected PatientDetailsPresenter providePresenter() {
        return new PatientDetailsPresenter();
    }

    @Override
    protected void initView() {
        activiy_patient_zong = findViewById(R.id.activiy_patient_zong);
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
        int sickCircleId = result.getSickCircleId();

        Uri parse = Uri.parse(result.getAdoptHeadPic());
        imgHeadPic.setImageURI(parse);
        nameNickName.setText(result.getAdoptNickName());
        textadoptComment.setText(result.getAdoptComment());
        Date date1 = new Date(result.getTreatmentEndTime());
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        textadoptComment.setText(simpleDateFormat1.format(date1));
        patient_activity_iv_intent_release_sickCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientDetailsActivity.this, ReleaseCirclesActivity.class);
                startActivity(intent);
            }
        });
        patient_activity_iv_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getQueryCommentPresenter(445, "1576821330500445", sickCircleId, 1, 10);
                patient_activity_relative_content.setVisibility(View.VISIBLE);
                patient_activity_relative_release_sickCircle.setVisibility(View.GONE);
            }
        });


    }

    @Override
    public void PatientDetailsFailure(Throwable e) {

    }

    @Override
    public void QueryCommentsuccess(QueryCommentBean queryCommentBean) {
        List<QueryCommentBean.ResultBean> result = queryCommentBean.getResult();
        SyLinearLayoutManager syLinearLayoutManager = new SyLinearLayoutManager(this, SyLinearLayoutManager.VERTICAL, false);
        recyclerSickCircleCommentListAdapter = new RecyclerSickCircleCommentListAdapter(this);
        recyclerSickCircleCommentListAdapter.addData(result);
        recycler_sick_circle_comment_list.setLayoutManager(syLinearLayoutManager);
        recycler_sick_circle_comment_list.setAdapter(recyclerSickCircleCommentListAdapter);
    }

    @Override
    public void QueryCommentFailure(Throwable e) {

    }

    @Override
    public void CommentCirclesuccess(CommentCircleBean commentCircleBean) {
        if (commentCircleBean.getStatus() == Constant.SUCCESS_CODE) {
            ToastUtils.show(commentCircleBean.getMessage());
        } else {
            ToastUtils.show(commentCircleBean.getMessage());
        }
    }

    @Override
    public void CommentCircleFailure(Throwable e) {

    }

    @Override
    public void OpinionBeansuccess(OpinionBean opinionBean) {

    }

    @Override
    public void OpinionBeanFailure(Throwable e) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
