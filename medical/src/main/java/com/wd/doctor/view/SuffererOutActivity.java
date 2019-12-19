package com.wd.doctor.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.doctor.R;
import com.wd.doctor.bean.SuffererOutBean;
import com.wd.doctor.contract.SuffererOutContract;
import com.wd.doctor.presenter.SuffererOutPresenter;
import com.wd.doctor.utils.GuideView;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;
import com.wd.mylibrary.Test.ToastUtils;

import java.text.SimpleDateFormat;

import butterknife.BindView;

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
    TextView suffererIvDian;
    @BindView(R.id.sufferer_tv_EndTime)
    TextView suffererTvEndTime;
    private SharedPreferences sharedPreferences;
    private GuideView guideView;
    private GuideView guideView2;
    private GuideView guideView3;


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

    }

    @Override
    protected void initView() {
        boolean b = hasNetwork();
        if (b) {
            sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
            int doctorId = sharedPreferences.getInt("doctorId", 0);
            String sessionId = sharedPreferences.getString("sessionId", "");
            Intent intent = getIntent();
            int sickCircleId = intent.getIntExtra("sickCircleId", 0);
            mPresenter.getSuffererOutPresenter(doctorId, sessionId, sickCircleId);
        }else {
            ToastUtils.show("请检查一下网络");
        }

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

        guideView.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setGuideView();
    }
}

