package com.wd.health.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.bean.EndInquiryBean;
import com.wd.health.bean.user.HistoryBean;
import com.wd.health.bean.user.InquiryRecordBean;
import com.wd.health.contract.HistoryContract;
import com.wd.health.presenter.HistoryPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindCurrentInquiryRecordActivity extends BaseActivity<HistoryPresenter> implements HistoryContract.IView {


    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.reaaa)
    RelativeLayout reaaa;
    @BindView(R.id.text_lines)
    TextView textLines;
    @BindView(R.id.xrecy_inquiry)
    SimpleDraweeView xrecyInquiry;
    @BindView(R.id.inquiry_name)
    TextView inquiryName;
    @BindView(R.id.inquiry_title)
    TextView inquiryTitle;
    @BindView(R.id.inquiry_department)
    TextView inquiryDepartment;
    @BindView(R.id.inquiry_time)
    TextView inquiryTime;
    @BindView(R.id.linay)
    LinearLayout linay;
    @BindView(R.id.btn_go)
    Button btnGo;
    @BindView(R.id.btn_end)
    Button btnEnd;
    @BindView(R.id.message_include_img)
    SimpleDraweeView messageIncludeImg;
    @BindView(R.id.message_include_text)
    TextView messageIncludeText;
    @BindView(R.id.message_include_relat)
    RelativeLayout messageIncludeRelat;
    private int recordId;
    private String id;
    private String sessionId;

    @Override
    protected HistoryPresenter providePresenter() {
        return new HistoryPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_find_current_inquiry_record;
    }

    @Override
    protected void initView() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        id = sp.getString("id", "");
        sessionId = sp.getString("sessionId", "");
        mPresenter.InquiryPresenter(id, sessionId);

    }

    @Override
    public void onHistorySuccess(HistoryBean bean) {

    }

    @Override
    public void onInquirySuccess(InquiryRecordBean bean) {
        if (bean.getStatus().equals("0000")) {
            if (bean.getMessage().equals("当前无问诊")){
                messageIncludeImg.setVisibility(View.VISIBLE);
                messageIncludeRelat.setVisibility(View.VISIBLE);
                messageIncludeText.setText("当前暂无问诊");
                btnGo.setVisibility(View.GONE);
                btnEnd.setVisibility(View.GONE);
                linay.setVisibility(View.GONE);
            }else {
                xrecyInquiry.setImageURI(bean.getResult().getImagePic());
                inquiryName.setText(bean.getResult().getDoctorName());
                inquiryTitle.setText(bean.getResult().getJobTitle());
                inquiryDepartment.setText(bean.getResult().getDepartment());
                Date date = new Date(bean.getResult().getInquiryTime());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd  hh:mm");
                inquiryTime.setText(simpleDateFormat.format(date));
                recordId = bean.getResult().getRecordId();
            }


        }
    }

    @Override
    public void onendInquirySuccess(EndInquiryBean bean) {
        if (bean.getStatus().equals("0000")){
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
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

    @OnClick({R.id.fanhui, R.id.btn_go, R.id.btn_end})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.btn_go:

                break;
            case R.id.btn_end:
                mPresenter.endInquiryPresenter(id,sessionId,recordId+"");
                finish();
                break;
        }
    }
}
