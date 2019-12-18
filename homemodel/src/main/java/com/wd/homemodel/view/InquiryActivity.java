package com.wd.homemodel.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.tabs.TabLayout;
import com.wd.homemodel.R;
import com.wd.homemodel.bean.DepartmentBean;
import com.wd.homemodel.contract.HomeContract;
import com.wd.homemodel.presenter.FettlesPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InquiryActivity extends BaseActivity<FettlesPresenter> implements HomeContract.CommonContreact.IView {


    @BindView(R.id.home_touxiang)
    SimpleDraweeView homeTouxiang;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.home_xiaoxi)
    ImageView homeXiaoxi;
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.pager)
    ViewPager pager;

    @Override
    protected FettlesPresenter providePresenter() {
        return new FettlesPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_inquiry;
    }

    @Override
    public void onDepartmentSuccess(Object data) {
        DepartmentBean departmentBean = (DepartmentBean) data;
        List<DepartmentBean.ResultBean> result = departmentBean.getResult();

    }

    @Override
    public void onDepartmentFailure(Throwable e) {

    }

    @Override
    public void onUnitDiseaseSuccess(Object data) {

    }

    @Override
    public void onUnitDiseaseFailure(Throwable e) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
