package com.wd.health.view.fragment;

import android.annotation.SuppressLint;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.health.R;
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
import com.wd.health.view.adapter.RecyclerConsultationAdapter;
import com.wd.health.view.adapter.RecyclerSickCircleAdapter;
import com.wd.health.view.custom.ObservableScrollView;
import com.wd.mylibrary.Base.BaseFragment;
import com.wd.mylibrary.Test.Logger;

import java.util.List;


public class PatientFragment extends BaseFragment<DepartmentListPresenter> implements ObservableScrollView.ScrollViewListener, IContract.iView {

    private RecyclerConsultationAdapter recyclerConsultationAdapter;
    private int mImageHeight;
    private List<DepartmentListBean.ResultBean> result;
    private int positions;
    private int id;
    private ImageView patient_iv_user_message;
    //搜索框
    private ImageView patient_iv_user_head_pic;
    private ImageView patient_iv_search;

    private RelativeLayout patient_relative_titlebar;
    private TextView patient_tv_department_name;
    private TextView patient_fragment_tv_select;
    //用户收到的消息
    private ImageView patient_iv_user_news;
    private RelativeLayout patient_relative_serach;
    private ObservableScrollView patient_scorll_view;
    private LinearLayout patient_linear_layout;
    private List<CircleListShowBean.ResultBean> showBeanResult;

    //病友圈列表展示

    private RecyclerSickCircleAdapter recyclerSickCircleAdapter;
    private EditText patient_tv_department_keyword;
    private KeywordSearchAdapter keywordSearchAdapter;
    private TabLayout patient_tablayout;
    private ViewPager patient_viewpager;

    @Override
    protected DepartmentListPresenter providePresenter() {
        return new DepartmentListPresenter();
    }

    @Override
    protected void initData() {
        mPresenter.getDepartmentListPresenter();
        initListener();
        patient_scorll_view.setScrollViewListener(this);
        patient_tv_department_keyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String keyWord = patient_tv_department_keyword.getText().toString().trim();
                mPresenter.getKeywordSearchPresenter(keyWord);
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
        patient_iv_user_head_pic = getActivity().findViewById(R.id.patient_iv_user_head_pic);
        patient_iv_user_message = getActivity().findViewById(R.id.patient_iv_user_message);
        patient_iv_search = getActivity().findViewById(R.id.patient_iv_search);
        patient_relative_titlebar = getActivity().findViewById(R.id.patient_relative_titlebar);
        patient_tv_department_name = getActivity().findViewById(R.id.patient_tv_department_name);
        patient_iv_user_news = getActivity().findViewById(R.id.patient_iv_user_news);
        patient_relative_serach = getActivity().findViewById(R.id.patient_relative_serach);
        patient_scorll_view = getActivity().findViewById(R.id.patient_scorll_view);
        patient_tv_department_keyword = getActivity().findViewById(R.id.patient_tv_department_keyword);
        patient_linear_layout = getActivity().findViewById(R.id.patient_linear_layout);
        patient_tablayout = getActivity().findViewById(R.id.patient_tablayout);
        patient_viewpager = getActivity().findViewById(R.id.patient_viewpager);

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
            //设置标题隐藏
            patient_relative_titlebar.setVisibility(View.VISIBLE);
            //设置搜索框显示
            patient_relative_serach.setVisibility(View.GONE);
        } else if (t > 10 && t < mImageHeight) {
            //设置搜索框隐藏
            patient_relative_serach.setVisibility(View.VISIBLE);
            patient_tv_department_name.setText(result.get(positions).getDepartmentName());
            //标题显示
            patient_relative_titlebar.setVisibility(View.GONE);
        }
    }

    @Override
    public void DepartmentListsuccess(DepartmentListBean departmentListBean) {
        Logger.e("FFFF", "" + departmentListBean);
        result = departmentListBean.getResult();
        patient_viewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                int id = result.get(position).getId();
                Bundle bundle = new Bundle();
                bundle.putInt("id", id);
                SickCircleFragment sickCircleFragment = new SickCircleFragment();
                sickCircleFragment.setArguments(bundle);
                return sickCircleFragment;
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
        patient_tablayout.setupWithViewPager(patient_viewpager);
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
    /*    Logger.e("FFFF", "" + keywordSearchBean);
        patient_viewpager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                SickCircleFragment sickCircleFragment = new SickCircleFragment();
                return sickCircleFragment;
            }

            @Override
            public int getCount() {
                return PatientFragment.this.result.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                String departmentName = PatientFragment.this.result.get(position).getDepartmentName();
                return departmentName;
            }
        });
        patient_tablayout.setupWithViewPager(patient_viewpager);*/
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
