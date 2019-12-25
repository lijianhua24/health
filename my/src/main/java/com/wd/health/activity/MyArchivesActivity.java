package com.wd.health.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.bean.user.AddArchivesBean;
import com.wd.health.bean.user.DeleteArchivesBean;
import com.wd.health.bean.user.UpdateArchivesBean;
import com.wd.health.bean.user.UserArchivesBean;
import com.wd.health.bean.user.UserArchivesPictureBean;
import com.wd.health.contract.MyFileContract;
import com.wd.health.presenter.MyFilePresenter;
import com.wd.mylibrary.Base.BaseActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MyArchivesActivity extends BaseActivity<MyFilePresenter> implements MyFileContract.Iview {

    @BindView(R.id.message_fanhui)
    ImageView messageFanhui;
    @BindView(R.id.text_bingzheng)
    TextView textBingzheng;
    @BindView(R.id.text_neike)
    TextView textNeike;
    @BindView(R.id.text_xiangqing)
    TextView textXiangqing;
    @BindView(R.id.text_tiantan)
    TextView textTiantan;
    @BindView(R.id.text_riqi)
    TextView textRiqi;
    @BindView(R.id.text_jingli)
    TextView textJingli;
    @BindView(R.id.info_img)
    ImageView infoImg;
    @BindView(R.id.button_delete)
    Button buttonDelete;
    @BindView(R.id.button_bianji)
    Button buttonBianji;
    @BindView(R.id.myfile_button)
    Button myfileButton;
    @BindView(R.id.message_include_relat)
    RelativeLayout messageIncludeRelat;
    @BindView(R.id.my_archives_relat)
    RelativeLayout myArchivesLinear;
    private Unbinder bind;
    private SharedPreferences sp;
    private String userId;
    private String sessionId;
    private int id;
    private UserArchivesBean.ResultBean result;
    private String format;
    private String format1;
    public static final String TAG="MyArchivesActivity";
    @Override
    protected MyFilePresenter providePresenter() {
        return new MyFilePresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_my_archives;
    }

    @Override
    protected void initView() {
        // TODO: add setContentView(...) invocation
        bind = ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        //取userId和sessionId
        sp = getSharedPreferences("user", Context.MODE_PRIVATE);
        userId = sp.getString("id","");
        sessionId = sp.getString("sessionId", null);

        mPresenter.getPresenterarchives(userId, sessionId);

    }

    //查询
    @Override
    public void onMyFilesuccess(UserArchivesBean userArchivesBean) {
        Log.d(TAG, "onMyFilesuccess: "+userArchivesBean.getMessage());
        if (userArchivesBean.getStatus().equals("0000")) {
            result = userArchivesBean.getResult();
            if (result != null) {
                id = result.getId();
                myArchivesLinear.setVisibility(View.VISIBLE);
                messageIncludeRelat.setVisibility(View.GONE);

                textBingzheng.setText(result.getDiseaseMain());
                textNeike.setText(result.getDiseaseNow());
                textXiangqing.setText(result.getDiseaseBefore());
                textTiantan.setText(result.getTreatmentHospitalRecent());
                textJingli.setText(result.getTreatmentProcess());
                Date date = new Date(result.getTreatmentStartTime());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = new Date(result.getTreatmentEndTime());
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                textRiqi.setText(simpleDateFormat.format(date)+"-"+simpleDateFormat1.format(date1));
                format = simpleDateFormat.format(date);
                format1 = simpleDateFormat1.format(date1);
                Glide.with(this)
                        .load(result.getPicture())
                        .into(infoImg);

            } else {
                myArchivesLinear.setVisibility(View.GONE);
                messageIncludeRelat.setVisibility(View.VISIBLE);
            }
        } else {
            Toast.makeText(this, userArchivesBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    }

    //shanchu 删除
    @Override
    public void onMyFilesuccess(DeleteArchivesBean deleteArchivesBean) {
               if ( deleteArchivesBean.getStatus().equals("0000")){
                   Toast.makeText(this,  deleteArchivesBean.getMessage(), Toast.LENGTH_SHORT).show();
                   mPresenter.getPresenterarchives(userId,sessionId);
               }else{
                   Toast.makeText(this,  deleteArchivesBean.getMessage(), Toast.LENGTH_SHORT).show();
               }
    }

    //添加
    @Override
    public void onMyFilesuccess(AddArchivesBean addArchivesBean) {

    }

    //修改
    @Override
    public void onMyFilesuccess(UpdateArchivesBean updateArchivesBean) {

    }

    //图片
    @Override
    public void onMyFilesuccess(UserArchivesPictureBean userArchivesPictureBean) {

    }

    @Override
    public void onMyFileFaiure(Throwable e) {

    }

    @OnClick({R.id.message_fanhui, R.id.button_delete, R.id.button_bianji, R.id.myfile_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.message_fanhui:
                finish();
                break;
            //shanchu 删除
            case R.id.button_delete:
                AlertDialog.Builder builder=new AlertDialog.Builder(this,R.style.CustomDialog);
                final AlertDialog dialog=builder.create();
                //此处设置位置窗体大小
                View inflate = View.inflate(this, R.layout.layout_dialog, null);
                dialog.setView(inflate);
                dialog.show();//显示对话框
                Button quxiaoButton = inflate.findViewById(R.id.dialog_quxiao_button);
                Button querenButton = inflate.findViewById(R.id.dialog_queding_button);
                //取消
                quxiaoButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                //确定
                querenButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.getPresenterdeletearchives(userId,sessionId,id+"");
                        dialog.dismiss();
                    }
                });

                break;
            //编辑
            case R.id.button_bianji:
                Intent intent1 = new Intent(MyArchivesActivity.this, UpdateArchivesActivity.class);
                    intent1.putExtra("id",result.getId()+"");
                    intent1.putExtra("diseaseMain",result.getDiseaseMain());
                    intent1.putExtra("diseaseNow",result.getDiseaseNow());
                    intent1.putExtra("diseaseBefore",result.getDiseaseBefore());
                    intent1.putExtra("treatmentHospitalRecent",result.getTreatmentHospitalRecent());
                    intent1.putExtra("treatmentProcess",result.getTreatmentProcess());
                    intent1.putExtra("treatmentStartTime",format);
                    intent1.putExtra("treatmentEndTime",format1);
                startActivityForResult(intent1,1000);
                break;
            //点击添加
            case R.id.myfile_button:
                Intent intent = new Intent(MyArchivesActivity.this,AddArchivesActivity.class);
                startActivity(intent);
                break;
        }
    }
//回传
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000&& resultCode == 2000){
            //接受回传的值
            textBingzheng.setText(data.getStringExtra("main"));
            textNeike.setText(data.getStringExtra("now"));
            textXiangqing.setText(data.getStringExtra("before"));
            textTiantan.setText(data.getStringExtra("recent"));
            textJingli.setText(data.getStringExtra("process"));
            String starttimes = data.getStringExtra("starttimes");
            String endtimes = data.getStringExtra("endtimes");
            textRiqi.setText(starttimes+"-"+endtimes);

        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

}
