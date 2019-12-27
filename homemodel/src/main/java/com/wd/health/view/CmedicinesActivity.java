package com.wd.health.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.XiyaoAdapter;
import com.wd.health.adapter.YiJiAdapter;
import com.wd.health.adapter.ZhongyaoAdapter;
import com.wd.health.app.App;
import com.wd.health.bean.CmedicinesBean;
import com.wd.health.contract.HomeContract;
import com.wd.health.presenter.CmedicinesPreesenter;
import com.wd.mylibrary.Base.BaseActivity;

import butterknife.BindView;

public class CmedicinesActivity extends BaseActivity<CmedicinesPreesenter> implements HomeContract.CmedicinesContreact.IView {


    @BindView(R2.id.home_touxiang)
    SimpleDraweeView homeTouxiang;
    @BindView(R2.id.home_xiaoxi)
    ImageView homeXiaoxi;
    @BindView(R2.id.cmedicines_name)
    TextView cmedicinesName;
    @BindView(R2.id.cmedicines_bingli)
    TextView cmedicinesBingli;
    @BindView(R2.id.cmedicines_zhengzhuang)
    TextView cmedicinesZhengzhuang;
    @BindView(R2.id.cmedicines_yiji)
    RecyclerView cmedicinesYiji;
    @BindView(R2.id.cmedicines_xiyao)
    RecyclerView cmedicinesXiyao;
    @BindView(R2.id.cmedicines_zhongyao)
    RecyclerView cmedicinesZhongyao;
    @BindView(R2.id.yiji_wu)
    TextView yijiWu;
    @BindView(R2.id.xiyao_wu)
    TextView xiyaoWu;
    @BindView(R2.id.zhongyao_wu)
    TextView zhongyaoWu;

    @Override
    public void onCmedicinesSuccess(Object data) {
        CmedicinesBean cmedicinesBean = (CmedicinesBean) data;
        CmedicinesBean.ResultBean result = ((CmedicinesBean) data).getResult();
        if (result != null) {
            String name = App.sharedPreferences.getString("name", null);
            cmedicinesName.setText(name);
            cmedicinesZhengzhuang.setText(result.getSymptom());
            cmedicinesBingli.setText(result.getPathology());
            String benefitTaboo = result.getBenefitTaboo();

            if (benefitTaboo != null) {
                String[] split = benefitTaboo.split("。");
                cmedicinesYiji.setAdapter(new YiJiAdapter(this, split));
            }else {
                yijiWu.setVisibility(View.VISIBLE);
            }
            String westernMedicineTreatment = result.getWesternMedicineTreatment();

            if (westernMedicineTreatment != null) {
                String[] split1 = westernMedicineTreatment.split("。");
                cmedicinesXiyao.setAdapter(new XiyaoAdapter(this, split1));
            }else {
                xiyaoWu.setVisibility(View.VISIBLE);
            }
            String chineseMedicineTreatment = result.getChineseMedicineTreatment();

            if (chineseMedicineTreatment != null) {
                String[] split2 = chineseMedicineTreatment.split("。");
                cmedicinesZhongyao.setAdapter(new ZhongyaoAdapter(this, split2));
            }else {
                zhongyaoWu.setVisibility(View.VISIBLE);
            }
        }

    }

    @Override
    public void onCmedicinesFailure(Throwable e) {

    }

    @Override
    protected CmedicinesPreesenter providePresenter() {
        return new CmedicinesPreesenter();
    }

    @Override
    protected void initData() {
        cmedicinesYiji.setLayoutManager(new LinearLayoutManager(this));
        cmedicinesXiyao.setLayoutManager(new LinearLayoutManager(this));
        cmedicinesZhongyao.setLayoutManager(new LinearLayoutManager(this));
        cmedicinesZhongyao.setNestedScrollingEnabled(false);
        cmedicinesYiji.setNestedScrollingEnabled(false);
        cmedicinesXiyao.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initView() {
        int id = App.sharedPreferences.getInt("bingid", 0);
        mPresenter.getCmedicinesPresenter(id);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_cmedicines;
    }



}
