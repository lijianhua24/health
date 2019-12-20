package com.wd.health.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.tabs.TabLayout;
import com.wd.health.R;
import com.wd.health.app.App;
import com.wd.health.bean.DepartmentBean;
import com.wd.health.contract.HomeContract;
import com.wd.health.fragment.DoctorListFragment;
import com.wd.health.presenter.FettlesPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import java.util.List;

import butterknife.BindView;

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
        mPresenter.getDepartmentPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_inquiry;
    }

    @Override
    public void onDepartmentSuccess(Object data) {
        DepartmentBean departmentBean = (DepartmentBean) data;
        List<DepartmentBean.ResultBean> result = departmentBean.getResult();
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                DoctorListFragment doctorListFragment = new DoctorListFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("departmentId",result.get(position).getId());
                doctorListFragment.setArguments(bundle);
                return doctorListFragment;
            }

            @Override
            public int getCount() {
                return result.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return result.get(position).getDepartmentName();
            }
        });
        int position1 = App.sharedPreferences.getInt("position", 0);
        pager.setCurrentItem(position1);
        tab.setupWithViewPager(pager);
       // pager.getCurrentItem();
        tab.getTabAt(position1).select();

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

}
