package com.wd.homemodel;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.homemodel.bean.BannerBean;
import com.wd.homemodel.bean.DepartmentBean;
import com.wd.homemodel.bean.InfoSectionBean;
import com.wd.homemodel.bean.SectionBean;
import com.wd.homemodel.contract.HomeContract;
import com.wd.homemodel.presenter.BannerPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


//@Route(path = "/app/sMainActivity")
public class MainActivity extends BaseActivity<BannerPresenter> implements HomeContract.BnnerContreact.IView {


    @BindView(R.id.home_touxiang)
    SimpleDraweeView homeTouxiang;
    @BindView(R.id.home_sou)
    EditText homeSou;
    @BindView(R.id.home_xiaoxi)
    ImageView homeXiaoxi;
    @BindView(R.id.home_recy)
    RecyclerView homeRecy;

    @Override
    protected BannerPresenter providePresenter() {
        return new BannerPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mPresenter.getBnnerPresenter();
        homeSou.getBackground().setAlpha(30);
        mPresenter.getDepartmentPresenter();
        mPresenter.getSectionPresenter();

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBnnerSuccess(BannerBean data) {
        String message = data.getMessage();
        Log.d("banner", message);
    }

    @Override
    public void onBnnerFailure(Throwable e) {

    }

    @Override
    public void onSectionSuccess(Object data) {
        SectionBean sectionBean = (SectionBean) data;
        Log.d("sectionBean",sectionBean.getMessage());

        List<SectionBean.ResultBean> result = sectionBean.getResult();
    }

    @Override
    public void onSectionFailure(Throwable e) {

    }

    @Override
    public void onDepartmentSuccess(Object data) {
        DepartmentBean departmentBean = (DepartmentBean) data;
        Log.d("departmentBean",departmentBean.getMessage());
    }

    @Override
    public void onDepartmentFailure(Throwable e) {

    }

    @Override
    public void onInfoSectionSuccess(Object data) {
        InfoSectionBean infoSectionBean = (InfoSectionBean) data;
        Log.d("infoSectionBean",infoSectionBean.getMessage());
    }

    @Override
    public void onInfoSectionFailure(Throwable e) {

    }


    @OnClick({R.id.home_touxiang, R.id.home_sou, R.id.home_xiaoxi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_touxiang:
                break;
            case R.id.home_sou:
                break;
            case R.id.home_xiaoxi:
                break;
        }
    }
}
