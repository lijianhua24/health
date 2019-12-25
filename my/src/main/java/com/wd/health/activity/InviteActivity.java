package com.wd.health.activity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.wd.health.R;
import com.wd.health.bean.InvitationCodeBean;
import com.wd.health.bean.UserInvitationCodeBean;
import com.wd.health.contract.MakeCodeContract;
import com.wd.health.presenter.MakeCodePresenter;
import com.wd.mylibrary.Base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InviteActivity extends BaseActivity<MakeCodePresenter> implements MakeCodeContract.IView {


    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.invate_code)
    TextView invateCode;
    @BindView(R.id.erweima_iamge)
    SimpleDraweeView erweimaIamge;
    @BindView(R.id.btn_invate)
    Button btnInvate;
    private String id;
    private String sessionId;
    private String result;

    @Override
    protected MakeCodePresenter providePresenter() {
        return new MakeCodePresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_invite;
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        id = sp.getString("id", "");
        sessionId = sp.getString("sessionId", "");
        mPresenter.UserCodePresenter(id,sessionId);



    }

    @Override
    protected void initView() {

    }

    @Override
    public void onMakeCodeSuccess(InvitationCodeBean bean) {

    }

    @Override
    public void onUserCodeSuccess(UserInvitationCodeBean bean) {
        if (bean.getStatus().equals("0000")){
            result = bean.getResult();
            Bitmap image = CodeUtils.createImage(result, 120, 120, null);
            erweimaIamge.setImageBitmap(image);
            invateCode.setText(bean.getResult());
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

    @OnClick({R.id.fanhui, R.id.btn_invate})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.btn_invate:
                break;
        }
    }
}
