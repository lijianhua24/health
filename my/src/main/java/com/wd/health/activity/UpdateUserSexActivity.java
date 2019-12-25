package com.wd.health.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import com.wd.health.R;
import com.wd.health.bean.GetUserInfoByIdBean;
import com.wd.health.bean.ModifyHeadPicBean;
import com.wd.health.bean.ModifyNickNameBean;
import com.wd.health.bean.UpdateUserSexBean;
import com.wd.health.bean.evebus.EventBusBean;
import com.wd.health.contract.ModifyHeadPicContract;
import com.wd.health.presenter.ModifyHeadPicPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateUserSexActivity extends BaseActivity<ModifyHeadPicPresenter> implements ModifyHeadPicContract.IView {
    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.btn_finish_sex)
    Button btnFinishSex;
    @BindView(R.id.boy01)
    CheckBox boy01;
    @BindView(R.id.gilr01)
    CheckBox gilr01;
    int sex;
    @BindView(R.id.boy_check01)
    ImageView boyCheck01;
    @BindView(R.id.boy_check02)
    ImageView boyCheck02;
    private String id;
    private String sessionId;

    @Override
    protected ModifyHeadPicPresenter providePresenter() {
        return new ModifyHeadPicPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_update_user_sex;
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        id = sp.getString("id", "");
        sessionId = sp.getString("sessionId", "");
        boy01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    sex=1;
                    EventBus.getDefault().postSticky(new EventBusBean(null,sex+""));
                    boyCheck01.setVisibility(View.VISIBLE);
                    boyCheck02.setVisibility(View.GONE);
                }else {
                    boyCheck01.setVisibility(View.GONE);
                }
            }
        });
        gilr01.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked==true){
                    sex=2;
                    EventBus.getDefault().postSticky(new EventBusBean(null,sex+""));
                    boyCheck02.setVisibility(View.VISIBLE);
                    boyCheck01.setVisibility(View.GONE);
                }else {
                    boyCheck02.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    protected void initView() {

    }

    @Override
    public void onModifyHeadPicSuccess(ModifyHeadPicBean bean) {

    }

    @Override
    public void onUpdateSexSuccess(UpdateUserSexBean bean) {

    }

    @Override
    public void onModifyNickNameSuccess(ModifyNickNameBean bean) {

    }

    @Override
    public void onGetUserInfoSuccess(GetUserInfoByIdBean bean) {

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

    @OnClick({R.id.fanhui, R.id.btn_finish_sex, R.id.boy01, R.id.gilr01})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.btn_finish_sex:
                mPresenter.geUpdateUserSexPresenter(id, sessionId, sex + "");
                finish();
                break;
        }
    }
}
