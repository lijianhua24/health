package com.wd.doctor.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wd.doctor.R;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToBindActivity extends BaseActivity {
    private static final String TAG = "ToBindActivity";
    @BindView(R.id.tobind_iv_back)
    ImageView tobindIvBack;
    @BindView(R.id.tobind_rl_sfz)
    RelativeLayout tobindRlSfz;
    @BindView(R.id.tobind_rl_bank)
    RelativeLayout tobindRlBank;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_to_bind;
    }


    @OnClick({R.id.tobind_iv_back, R.id.tobind_rl_sfz, R.id.tobind_rl_bank})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tobind_iv_back:
                finish();
                break;
            case R.id.tobind_rl_sfz:
                startActivity(new Intent(this,BindIdActivity.class));
                break;
            case R.id.tobind_rl_bank:

                break;
        }
    }
}
