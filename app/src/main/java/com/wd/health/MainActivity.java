package com.wd.health;

import android.os.Bundle;
import android.widget.Button;

import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.Test.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//@Route(path = "/app/MainActivity")
public class MainActivity extends BaseActivity {


    @BindView(R.id.tiao_btn)
    Button tiaoBtn;

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
        return R.layout.activity_main;
    }



    @OnClick(R.id.tiao_btn)
    public void onViewClicked() {
        ToastUtils.show("李建华");
       // ARouter.getInstance().build("/app/sMainActivity").navigation();
    }
}
