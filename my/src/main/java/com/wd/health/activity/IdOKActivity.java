package com.wd.health.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wd.health.R;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IdOKActivity extends BaseActivity {


    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.text_name)
    TextView textName;
    @BindView(R.id.text_sex)
    TextView textSex;
    @BindView(R.id.text_nation)
    TextView textNation;
    @BindView(R.id.text_number)
    TextView textNumber;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_id_ok;
    }

    @Override
    protected void initData() {
     SharedPreferences sp = getSharedPreferences("front", MODE_PRIVATE);
        String name = sp.getString("name", "");
        String sex = sp.getString("sex", "");
        String num = sp.getString("num", "");
        String nation = sp.getString("nation", "");
        textName.setText(name);
        textSex.setText(sex);
        textNation.setText(nation);
        textNumber.setText(num.replaceAll("(\\d{4})\\d{10}(\\d{4})","$1****$2"));

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick(R.id.fanhui)
    public void onClick() {
        finish();
    }
}
