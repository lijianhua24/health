package com.wd.doctor.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.doctor.R;
import com.wd.doctor.bean.PostReviewBean;
import com.wd.doctor.bean.SuffererOutBean;
import com.wd.doctor.contract.SuffererOutContract;
import com.wd.doctor.presenter.SuffererOutPresenter;
import com.wd.doctor.utils.GuideView;
import com.wd.doctor.utils.InputUtil.HideIMEUtil;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SuffererOutActivity extends BaseActivity<SuffererOutPresenter> implements SuffererOutContract.iView {

    private static final String TAG = "SuffererOutActivity";
    @BindView(R.id.sufferer_iv_back)
    ImageView suffererIvBack;
    @BindView(R.id.sufferer_tv_title)
    TextView suffererTvTitle;
    @BindView(R.id.sufferer_tv_authorName)
    TextView suffererTvAuthorName;
    @BindView(R.id.sufferer_tv_disease)
    TextView suffererTvDisease;
    @BindView(R.id.sufferer_tv_departmentName)
    TextView suffererTvDepartmentName;
    @BindView(R.id.sufferer_tv_detail)
    TextView suffererTvDetail;
    @BindView(R.id.sufferer_tv_treatmentHospital)
    TextView suffererTvTreatmentHospital;
    @BindView(R.id.sufferer_tv_Time)
    TextView suffererTvTime;
    @BindView(R.id.sufferer_tv_treatmentProcess)
    TextView suffererTvTreatmentProcess;
    @BindView(R.id.sufferer_im_picture)
    SimpleDraweeView suffererImPicture;
    @BindView(R.id.sufferer_tv_amount)
    TextView suffererTvAmount;
    @BindView(R.id.sufferer_iv_dian)
    TextView suffererIvDian;//我来解答
    @BindView(R.id.sufferer_tv_EndTime)
    TextView suffererTvEndTime;
    @BindView(R.id.tv_my_jd)
    TextView tvMyJd;//发表完成后的内容文本框
    @BindView(R.id.linear_cancel_jd)
    LinearLayout linearCancelJd;//内容文本框的整个布局
    @BindView(R.id.lin_ll_one)
    LinearLayout linLlOne;//我来解答的整个布局
    @BindView(R.id.et_text)
    EditText etText;
    @BindView(R.id.img_enjoy)
    ImageView imgEnjoy;//表情
    @BindView(R.id.img_send)
    ImageView imgSend;//发送
    @BindView(R.id.linear_et)
    LinearLayout linearEt;
    @BindView(R.id.relative_edit)
    RelativeLayout relativeEdit;//输入框整个布局
    @BindView(R.id.Relative_show)
    RelativeLayout RelativeShow;
    private SharedPreferences sharedPreferences;
    private GuideView guideView;
    private GuideView guideView2;
    private GuideView guideView3;
    private int doctorId;
    private String sessionId;
    private int sickCircleId;
    private int whetherContent;


    @Override
    protected SuffererOutPresenter providePresenter() {
        return new SuffererOutPresenter();
    }

    @Override
    protected void initData() {
        //返回
        suffererIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //点击事件我的解答
        suffererIvDian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linLlOne.setVisibility(View.GONE);
                relativeEdit.setVisibility(View.VISIBLE);
            }
        });
        //发送
        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ed_tive = etText.getText().toString().trim();
                mPresenter.getPostReviewPresenter(doctorId, sessionId, sickCircleId, ed_tive);
            }
        });

//        RelativeShow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (whetherContent == 1) {
//                    linearCancelJd.setVisibility(View.VISIBLE);
//                    linLlOne.setVisibility(View.GONE);
////                    lineView.setVisibility(View.GONE);
//                } else if (whetherContent == 2) {
//                    linearCancelJd.setVisibility(View.GONE);
//                    linLlOne.setVisibility(View.VISIBLE);
////                    lineView.setVisibility(View.VISIBLE);
//                }
//                relativeEdit.setVisibility(View.GONE);
//                HideIMEUtil.wrap(SuffererOutActivity.this);
//            }
//        });

    }

    @Override
    protected void initView() {
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        doctorId = sharedPreferences.getInt("doctorId", 0);
        sessionId = sharedPreferences.getString("sessionId", "");
        Intent intent = getIntent();
        sickCircleId = intent.getIntExtra("sickCircleId", 0);
        mPresenter.getSuffererOutPresenter(doctorId, sessionId, sickCircleId);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_sufferer_out;
    }

    @Override
    public void onSuffererOutSuccess(SuffererOutBean suffererOutBean) {
        Logger.d("SuffererOutActivity", "" + suffererOutBean.getMessage());
        SuffererOutBean.ResultBean result = suffererOutBean.getResult();
        if (result != null) {
            //判断是否评论
            whetherContent = suffererOutBean.getResult().getWhetherContent();
            if (whetherContent == 1) {
                linearCancelJd.setVisibility(View.VISIBLE);
                linLlOne.setVisibility(View.GONE);
//                lineView.setVisibility(View.GONE);
            } else if (whetherContent == 2) {
                linearCancelJd.setVisibility(View.GONE);
                linLlOne.setVisibility(View.VISIBLE);
//                lineView.setVisibility(View.VISIBLE);
            }

            //解答
            String content = result.getContent();
            tvMyJd.setText(content);

            suffererTvTitle.setText(result.getTitle());
            suffererTvAuthorName.setText(result.getAuthorName());
            suffererTvDepartmentName.setText(result.getDepartmentName());
            suffererTvDetail.setText(result.getDetail());
            suffererTvTreatmentHospital.setText(result.getTreatmentHospital());

            String s = String.valueOf(result.getTreatmentStartTime());
            String s2 = String.valueOf(result.getTreatmentEndTime());
            String format = DateFormatUtilTwo.format(s);
            String format1 = DateFormatUtilThree.format(s2);
            suffererTvTime.setText(format + "-");
            suffererTvEndTime.setText(format1);
            suffererTvTreatmentProcess.setText(result.getTreatmentProcess());
            Uri parse = Uri.parse(result.getPicture() + "");
            suffererImPicture.setImageURI(parse);
            suffererTvAmount.setText(result.getAmount() + "H币");
        } else {
        }


    }

    @Override
    public void onSuffererOutFailure(Throwable e) {

    }

    @Override
    public void onPostReviewSuccess(PostReviewBean postReviewBean) {
        Logger.d("", "" + postReviewBean.getMessage());
        if (postReviewBean.getMessage().equals("发表成功")) {
            relativeEdit.setVisibility(View.GONE);
            HideIMEUtil.wrap(this);
            mPresenter.getSuffererOutPresenter(doctorId, sessionId, sickCircleId);
        }
    }

    @Override
    public void onPostReviewFailure(Throwable e) {

    }

    //转换时间
    public static class DateFormatUtilTwo {
        public static String format(String date) {
            if (TextUtils.isEmpty(date))
                return null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Long time = new Long(date);
            return format.format(time);
        }
    }

    public static class DateFormatUtilThree {
        public static String format(String date) {
            if (TextUtils.isEmpty(date))
                return null;
            SimpleDateFormat format = new SimpleDateFormat("MM-dd");
            Long time = new Long(date);
            return format.format(time);
        }
    }

    //蒙层引导页
    private void setGuideView() {

        // 使用图片
        final ImageView iv = new ImageView(this);
        iv.setImageResource(R.mipmap.zhiying);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        iv.setLayoutParams(params);

        // 使用文字
        TextView tv = new TextView(this);
        tv.setText("帮助解答问题将有机会H币哟~");
        tv.setTextColor(getResources().getColor(R.color.white));
        tv.setTextSize(20);
        tv.setGravity(Gravity.CENTER);

//        // 使用文字
//        final TextView tv2 = new TextView(this);
//        tv2.setText("欢迎使用2");
//        tv2.setTextColor(getResources().getColor(R.color.white));
//        tv2.setTextSize(30);
//        tv2.setGravity(Gravity.CENTER);


        guideView = GuideView.Builder
                .newInstance(this)
                .setTargetView(suffererTvAmount)//设置目标
                .setCustomGuideView(tv)
                .setDirction(GuideView.Direction.TOP)
                .setShape(GuideView.MyShape.RECTANGULAR)   // 设置圆形显示区域，
                .setBgColor(getResources().getColor(R.color.shadow))
                .setOnclickListener(new GuideView.OnClickCallback() {
                    @Override
                    public void onClickedGuideView() {
                        guideView.hide();
//                        guideView2.show();
                    }
                })
                .build();


//        guideView2 = GuideView.Builder
//                .newInstance(this)
//                .setTargetView(suffererIvDian)
//                .setCustomGuideView(tv)
//                .setDirction(GuideView.Direction.TOP)
//                .setShape(GuideView.MyShape.RECTANGULAR)   // 设置椭圆形显示区域，
//                .setBgColor(getResources().getColor(R.color.shadow))
//                .setOnclickListener(new GuideView.OnClickCallback() {
//                    @Override
//                    public void onClickedGuideView() {
//                        guideView2.hide();
////                        guideView3.show();
//                    }
//                })
//                .build();

//        guideView3 = GuideView.Builder
//                .newInstance(this)
//                .setTargetView(suffererImPicture)
//                .setCustomGuideView(tv2)
//                .setDirction(GuideView.Direction.LEFT_BOTTOM)
//                .setShape(GuideView.MyShape.RECTANGULAR)   // 设置矩形显示区域，
//                .setRadius(80)          // 设置圆形或矩形透明区域半径，默认是targetView的显示矩形的半径，如果是矩形，这里是设置矩形圆角大小
//                .setBgColor(getResources().getColor(R.color.shadow))
//                .setOnclickListener(new GuideView.OnClickCallback() {
//                    @Override
//                    public void onClickedGuideView() {
//                        guideView3.hide();
//                        guideView.show();
//                    }
//                })
//                .build();

//        guideView.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setGuideView();
    }
}

