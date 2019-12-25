package com.wd.health.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.bean.InvitationCodeBean;
import com.wd.health.bean.UserInvitationCodeBean;
import com.wd.health.bean.evebus.SexbeanBus;
import com.wd.health.bean.evebus.imageBus;
import com.wd.health.contract.MakeCodeContract;
import com.wd.health.presenter.MakeCodePresenter;
import com.wd.health.utils.DataCleanUtil;
import com.wd.mylibrary.Base.BaseActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
/**
 *@describe(描述)：SettingActivity   设置界面
 *@data（日期）: 2019/12/19
 *@time（时间）: 8:21
 *@author（作者）: 张恩
 **/
public class SettingActivity extends BaseActivity<MakeCodePresenter> implements MakeCodeContract.IView {


    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.setting_inquir)
    SimpleDraweeView settingInquir;
    @BindView(R.id.setting_name)
    TextView settingName;
    @BindView(R.id.setting_user)
    RelativeLayout settingUser;
    @BindView(R.id.setting_changepwd)
    RelativeLayout settingChangepwd;
    @BindView(R.id.setting_Cache_test)
    TextView settingCacheTest;
    @BindView(R.id.setting_C)
    ImageView settingC;
    @BindView(R.id.setting_Cache)
    RelativeLayout settingCache;
    @BindView(R.id.setting_brightness)
    RelativeLayout settingBrightness;
    @BindView(R.id.setting_version)
    RelativeLayout settingVersion;
    @BindView(R.id.setting_help)
    RelativeLayout settingHelp;
    @BindView(R.id.setting_attention)
    RelativeLayout settingAttention;
    @BindView(R.id.setting_invite)
    RelativeLayout settingInvite;
    @BindView(R.id.setting_out)
    TextView settingOut;
    private String totalCacheSize1;
    private String totalCacheSize;
    private String id;
    private String sessionId;
    private String name;


    @Override
    protected int provideLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected MakeCodePresenter providePresenter() {
        return new MakeCodePresenter();
    }


    @Override
    protected void initView() {
        EventBus.getDefault().register(SettingActivity.this);
        //获取当前缓存
        try {
            totalCacheSize = DataCleanUtil.getTotalCacheSize(SettingActivity.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //设置文字
        settingCacheTest.setText(totalCacheSize);

    }

    @Override
    protected void initData() {

        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String headPic = sp.getString("headPic", "");
        String nickName = sp.getString("nickName", "");
        id = sp.getString("id", "");
        sessionId = sp.getString("sessionId", "");
        settingInquir.setImageURI(headPic);
        settingName.setText(nickName);




        settingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sp.edit().clear().commit();

                finish();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessage(SexbeanBus busBean) {
        name = busBean.getSex();
        settingName.setText(name);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getImage(imageBus bus) {
        String image = bus.getImage();
        settingInquir.setImageURI(image);
    }
    @OnClick({R.id.fanhui, R.id.setting_inquir, R.id.setting_user, R.id.setting_changepwd, R.id.setting_C, R.id.setting_Cache, R.id.setting_brightness, R.id.setting_version, R.id.setting_help, R.id.setting_attention, R.id.setting_invite})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.setting_inquir:
                break;
            case R.id.setting_user:
                startActivity(new Intent(SettingActivity.this, MyInformatioActivity.class));
                break;
            case R.id.setting_changepwd:
                startActivity(new Intent(SettingActivity.this, ReplacePassWordActivity.class));
                break;
            case R.id.setting_C:

                break;
            case R.id.setting_Cache:
                if (!TextUtils.isEmpty(totalCacheSize)) {
                    //清空缓存
                    DataCleanUtil.clearAllCache(SettingActivity.this);
                    //获取当前缓存
                    try {
                        totalCacheSize1 = DataCleanUtil.getTotalCacheSize(SettingActivity.this);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                settingCacheTest.setText(totalCacheSize1 + "");
                Toast.makeText(SettingActivity.this, "已清空缓存", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting_brightness:
                startActivity(new Intent(SettingActivity.this, ScreenBrightnessActivity.class));
                break;
            case R.id.setting_version:
                startActivity(new Intent(SettingActivity.this,VersionActivity.class));
                break;
            case R.id.setting_help:
                break;
            case R.id.setting_attention:
                break;
            case R.id.setting_invite:
                mPresenter.MakeCodePresenter(id, sessionId);
                startActivity(new Intent(SettingActivity.this, InviteActivity.class));
                break;
        }
    }

    @Override
    public void onMakeCodeSuccess(InvitationCodeBean bean) {
        if (bean.getStatus().equals("0000")) {

        }
    }

    @Override
    public void onUserCodeSuccess(UserInvitationCodeBean bean) {

    }

    @Override
    public void onFailure(Throwable e) {

    }
}

