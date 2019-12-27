package com.wd.health.view.fragment;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.tabs.TabLayout;

import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.bean.CircleListShowBean;
import com.wd.health.bean.DepartmentListBean;
import com.wd.health.bean.DoTaskBean;
import com.wd.health.bean.KeywordSearchBean;
import com.wd.health.bean.ReleasePatientsBean;
import com.wd.health.bean.UnitDiseaseBean;
import com.wd.health.bean.UploadPatientBean;
import com.wd.health.contract.IContract;
import com.wd.health.presenter.DepartmentListPresenter;
import com.wd.health.view.activity.PatientDetailsActivity;
import com.wd.health.view.activity.SearchActivity;
import com.wd.health.view.adapter.KeywordSearchAdapter;
import com.wd.health.view.custom.ObservableScrollView;
import com.wd.mylibrary.Base.BaseFragment;

import java.util.List;

import butterknife.BindView;


public class PatientFragment extends BaseFragment<DepartmentListPresenter> implements ObservableScrollView.ScrollViewListener, IContract.iView {
    @BindView(R2.id.circle_touxiang)
    SimpleDraweeView circle_touxiang;
    @BindView(R2.id.circle_message)
    ImageView circle_message;
    @BindView(R2.id.patient_relative_titlebar)
    RelativeLayout patient_relative_titlebar;
    @BindView(R2.id.patient_tv_department_name)
    TextView patient_tv_department_name;
    @BindView(R2.id.patient_tv_department_keyword)
    EditText patient_tv_department_keyword;
    @BindView(R2.id.patient_iv_user_news)
    ImageView patient_iv_user_news;
    @BindView(R2.id.patient_relative_serach)
    RelativeLayout patient_relative_serach;
    @BindView(R2.id.patient_tablayout)
    TabLayout patient_tablayout;
    @BindView(R2.id.patient_iv_search)
    ImageView patient_iv_search;
    @BindView(R2.id.bbb)
    RelativeLayout bbb;
    @BindView(R2.id.patient_viewpager)
    ViewPager patient_viewpager;
    @BindView(R2.id.xiangxi_rlv)
    RecyclerView xiangxi_rlv;
    @BindView(R2.id.patient_linear_layout)
    RelativeLayout patient_linear_layout;
    @BindView(R2.id.patient_scorll_view)
    ObservableScrollView patient_scorll_view;
    private int mImageHeight;
    private KeywordSearchAdapter keywordSearchAdapter;
    private int anInt;
    private String departmentName;

    @Override
    protected DepartmentListPresenter providePresenter() {
        return new DepartmentListPresenter();
    }

    @Override
    protected void initData() {
        initListener();
        patient_scorll_view.setScrollViewListener(this);
        mPresenter.getDepartmentListPresenter();
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        xiangxi_rlv.setLayoutManager(linearLayoutManager1);

        patient_tv_department_keyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String trim = patient_tv_department_keyword.getText().toString().trim();
                if (trim != null) {
                    mPresenter.getKeywordSearchPresenter(trim);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        patient_iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initView() {
    }
    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_patient;
    }


    private void initListener() {
        //获取控件的视图观察者,一遍通过视图观察者得到控件的宽高属性
        ViewTreeObserver viewTreeObserver = patient_linear_layout.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                //此时就可以得到控件的高度
                mImageHeight = patient_linear_layout.getHeight();
                //                //我们做的第一件事情就是移除监听,卸磨杀驴,减少内存的消耗
                patient_linear_layout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

    }

    @Override
    public void onScrollChanged(ObservableScrollView scrollView, int l, int t, int oldl, int oldt) {
        if (t <= 10) {
            patient_relative_titlebar.setVisibility(View.VISIBLE);
            patient_relative_serach.setVisibility(View.GONE);
        } else if (t > 10 && t < mImageHeight) {
            patient_relative_serach.setVisibility(View.VISIBLE);
            patient_relative_titlebar.setVisibility(View.GONE);
        }
    }

    @Override
    public void DepartmentListsuccess(DepartmentListBean departmentListBean) {
        List<DepartmentListBean.ResultBean> result = departmentListBean.getResult();
        patient_tablayout.setupWithViewPager(patient_viewpager);
            patient_viewpager.setAdapter(new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
                @NonNull
                @Override
                public Fragment getItem(int position) {
                    departmentName = result.get(position).getDepartmentName();
                    SickCircleFragment sickCircleFragment = new SickCircleFragment();
                    Bundle bundle = new Bundle();
                    anInt = result.get(position).getId();
                    bundle.putInt("anInt", anInt);
                    sickCircleFragment.setArguments(bundle);
                    patient_tv_department_name.setText(departmentName);
                    return sickCircleFragment;
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
            patient_tablayout.setupWithViewPager(patient_viewpager);
            patient_viewpager.setOffscreenPageLimit(3);
        }


    @Override
    public void DepartmentListFailure(Throwable e) {

    }

    @Override
    public void CircleListShowsuccess(CircleListShowBean circleListShowBean) {

    }

    @Override
    public void CircleListShowFailure(Throwable e) {

    }

    @Override
    public void KeywordSearchsuccess(KeywordSearchBean keywordSearchBean) {
        xiangxi_rlv.setVisibility(View.VISIBLE);
        List<KeywordSearchBean.ResultBean> result = keywordSearchBean.getResult();
        keywordSearchAdapter = new KeywordSearchAdapter(getContext());
        xiangxi_rlv.setAdapter(keywordSearchAdapter);
        keywordSearchAdapter.addData(result);
        keywordSearchAdapter.onItemClickListener(new KeywordSearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int id) {
                int sickCircleId = result.get(position).getSickCircleId();
                Intent intent = new Intent(getContext(), PatientDetailsActivity.class);
                intent.putExtra("sickCircleId", sickCircleId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void KeywordSearchFailure(Throwable e) {

    }

    @Override
    public void ReleasePatientssuccess(ReleasePatientsBean ReleasePatientsBean) {

    }

    @Override
    public void ReleasePatientsFailure(Throwable e) {

    }

    @Override
    public void UnitDiseasessuccess(UnitDiseaseBean unitDiseaseBean) {

    }

    @Override
    public void UnitDiseaseFailure(Throwable e) {

    }

    @Override
    public void uploadPatientsuccess(UploadPatientBean uploadPatientBean) {

    }

    @Override
    public void uploadPatientFailure(Throwable e) {

    }

    @Override
    public void DoTasksuccess(DoTaskBean doTaskBean) {

    }

    @Override
    public void DoTaskFailure(Throwable e) {

    }
}
