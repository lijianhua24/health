package com.wd.health.view.activity;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.bean.CommentCircleBean;
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
    private TextView nameNickName;
    private TextView timeadoptTime;
    private TextView textadoptComment;
    private RecyclerView recycler_sick_circle_comment_list;
    private TextView patient_activity_tv_treatment_time;
    private ImageView patient_activity_iv_content;
    private RelativeLayout patient_activity_relative_content;
    private ImageView patient_activity_iv_cancel;
    //发表评论
    private SimpleDraweeView imgHeadPic;
    private ImageView patient_activity_iv_send_content;
    //输入评论内容
    private EditText patient_activity_et_content;
    private RecyclerSickCircleCommentListAdapter recyclerSickCircleCommentListAdapter;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        int sickCircleId = intent.getIntExtra("sickCircleId", 0);
        Logger.d("FFFFFFF", sickCircleId + "");
        mPresenter.getPatientDetailsPresenter(445, "1576310426648445", sickCircleId);
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
                mPresenter.getCommentCircle(445, "1576550914923445", sickCircleId, trim);
            }
        });

    }

    @Override
    protected PatientDetailsPresenter providePresenter() {
        return new PatientDetailsPresenter();
    }

    @Override
    protected void initView() {
        patient_iv_user_head_pic = (ImageView) findViewById(R.id.patient_iv_user_head_pic);
        imgHeadPic = (SimpleDraweeView) findViewById(R.id.img_HeadPic);
        patient_activity_tv_title = (TextView) findViewById(R.id.patient_activity_tv_title);
        nameNickName = (TextView) findViewById(R.id.name_NickName);
        timeadoptTime = (TextView) findViewById(R.id.time_adoptTime);
        textadoptComment = (TextView) findViewById(R.id.text_adoptComment);
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
                mPresenter.getQueryCommentPresenter(445, "1541576408889268154", sickCircleId, 1, 10);
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
        if (queryCommentBean.getStatus().equals("0000")) {
            List<QueryCommentBean.ResultBean> result = queryCommentBean.getResult();
            SyLinearLayoutManager syLinearLayoutManager = new SyLinearLayoutManager(this, SyLinearLayoutManager.VERTICAL, false);
            recyclerSickCircleCommentListAdapter = new RecyclerSickCircleCommentListAdapter(this);
            recyclerSickCircleCommentListAdapter.addData(result);
            recycler_sick_circle_comment_list.setLayoutManager(syLinearLayoutManager);
            recycler_sick_circle_comment_list.setAdapter(recyclerSickCircleCommentListAdapter);
        }
    }

    @Override
    public void QueryCommentFailure(Throwable e) {

    }

    @Override
    public void CommentCirclesuccess(CommentCircleBean commentCircleBean) {
if (commentCircleBean.getStatus()== Constant.SUCCESS_CODE){
    ToastUtils.show(commentCircleBean.getMessage());
}else {
    ToastUtils.show(commentCircleBean.getMessage());
}
    }

    @Override
    public void CommentCircleFailure(Throwable e) {

    }
}
