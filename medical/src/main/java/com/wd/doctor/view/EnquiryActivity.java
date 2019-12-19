package com.wd.doctor.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.wd.doctor.R;
import com.wd.doctor.bean.EnquiryBean;
import com.wd.doctor.bean.SuffererDetailBean;
import com.wd.doctor.contract.EnquiryContract;
import com.wd.doctor.fragment.EnquiryFragment;
import com.wd.doctor.presenter.EnquiryPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnquiryActivity extends BaseActivity<EnquiryPresenter> implements EnquiryContract.iView {

    private static final String TAG = "EnquiryActivity";
    @BindView(R.id.enquiry_iv_back)
    ImageView enquiryIvBack;
    @BindView(R.id.enquiry_iv_xiaoxin)
    ImageView enquiryIvXiaoxin;
    @BindView(R.id.enquiry_tl_huodong)
    TabLayout enquiryTlHuodong;
    @BindView(R.id.enquiry_iv_sousuo)
    ImageView enquiryIvSousuo;
    @BindView(R.id.enquiry_ll_one)
    LinearLayout enquiryLlOne;
    @BindView(R.id.enquiry_v_one)
    View enquiryVOne;
    @BindView(R.id.enquiry_vp_vper)
    ViewPager enquiryVpVper;
    private List<EnquiryBean.ResultBean> result;

    @Override
    protected EnquiryPresenter providePresenter() {
        return new EnquiryPresenter();
    }

    @Override
    protected void initData() {
        //搜索
        enquiryIvSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EnquiryActivity.this,SearchSuffererActivity.class));
            }
        });

        enquiryIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //系统信息
        enquiryIvXiaoxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initView() {
        mPresenter.getEnquiryPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_enquiry;
    }

    @Override
    public void onEnquirySuccess(EnquiryBean enquiryBean) {
        Logger.d("EnquiryActivity", "" + enquiryBean.getMessage());
        result = enquiryBean.getResult();

        enquiryVpVper.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                int id = result.get(position).getId();
                Bundle bundle = new Bundle();
                bundle.putInt("id",id);
                EnquiryFragment enquiryFragment = new EnquiryFragment();
                enquiryFragment.setArguments(bundle);
                return enquiryFragment;
            }

            @Override
            public int getCount() {
                return result.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                    String departmentName = result.get(position).getDepartmentName();
                    return departmentName;
            }
        });
        enquiryTlHuodong.setupWithViewPager(enquiryVpVper);
    }

    @Override
    public void onEnquiryFailure(Throwable e) {

    }

    @Override
    public void onSuffererDetailSuccess(SuffererDetailBean suffererDetailBean) {

    }

    @Override
    public void onSuffererDetailFailure(Throwable e) {

    }

}
