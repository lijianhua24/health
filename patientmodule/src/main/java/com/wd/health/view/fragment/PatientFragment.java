package com.wd.health.view.fragment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.health.R;
import com.wd.health.bean.CircleListShowBean;
import com.wd.health.bean.DepartmentListBean;
import com.wd.health.bean.KeywordSearchBean;
import com.wd.health.contract.IContract;
import com.wd.health.presenter.DepartmentListPresenter;
import com.wd.health.view.PatientDetailsActivity;
import com.wd.health.view.SearchActivity;
import com.wd.health.view.adapter.KeywordSearchAdapter;
import com.wd.health.view.adapter.RecyclerConsultationAdapter;
import com.wd.health.view.adapter.RecyclerSickCircleAdapter;
import com.wd.health.view.custom.ObservableScrollView;
import com.wd.mylibrary.Base.BaseFragment;
import com.wd.mylibrary.Test.Logger;
import com.wd.mylibrary.Test.ToastUtils;

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
    private RecyclerView patient_recycler_department;
    private XRecyclerView patient_recycler_sick_circle_list;
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
    private int page = 1;
    private int count = 5;
    private RecyclerSickCircleAdapter recyclerSickCircleAdapter;
    private EditText patient_tv_department_keyword;
    private KeywordSearchAdapter keywordSearchAdapter;

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
                String trim = patient_tv_department_keyword.getText().toString().trim();
                mPresenter.getKeywordSearchPresenter(trim);
                mPresenter.getCircleListShowPresenter(id, page, count);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //病友圈列表刷新
        patient_recycler_sick_circle_list.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getCircleListShowPresenter(id, page, count);
                patient_recycler_sick_circle_list.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getCircleListShowPresenter(id, page, count);
                patient_recycler_sick_circle_list.loadMoreComplete();
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

    @SuppressLint("WrongViewCast")
    @Override
    protected void initView() {
        patient_iv_user_head_pic = getActivity().findViewById(R.id.patient_iv_user_head_pic);
        patient_iv_user_message = getActivity().findViewById(R.id.patient_iv_user_message);
        patient_iv_search = getActivity().findViewById(R.id.patient_iv_search);
        patient_recycler_department = getActivity().findViewById(R.id.patient_recycler_department);
        patient_recycler_sick_circle_list = getActivity().findViewById(R.id.patient_recycler_sick_circle_list);
        patient_relative_titlebar = getActivity().findViewById(R.id.patient_relative_titlebar);
        patient_tv_department_name = getActivity().findViewById(R.id.patient_tv_department_name);
        patient_fragment_tv_select = getActivity().findViewById(R.id.patient_fragment_tv_select);
        patient_iv_user_news = getActivity().findViewById(R.id.patient_iv_user_news);
        patient_relative_serach = getActivity().findViewById(R.id.patient_relative_serach);
        patient_scorll_view = getActivity().findViewById(R.id.patient_scorll_view);
        patient_tv_department_keyword = getActivity().findViewById(R.id.patient_tv_department_keyword);
        patient_linear_layout = getActivity().findViewById(R.id.patient_linear_layout);
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
                //我们做的第一件事情就是移除监听,卸磨杀驴,减少内存的消耗
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        patient_recycler_department.setLayoutManager(linearLayoutManager);
        recyclerConsultationAdapter = new RecyclerConsultationAdapter(getContext());
        recyclerConsultationAdapter.addData(result);
        patient_recycler_department.setAdapter(recyclerConsultationAdapter);
        recyclerConsultationAdapter.onItemClickListener(new RecyclerConsultationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                positions = position;
                patient_fragment_tv_select.setVisibility(View.GONE);
                patient_recycler_sick_circle_list.setVisibility(View.VISIBLE);
                id = result.get(position).getId();
                mPresenter.getCircleListShowPresenter(id, page, count);
            }
        });


    }

    @Override
    public void DepartmentListFailure(Throwable e) {

    }

    @Override
    public void CircleListShowsuccess(CircleListShowBean circleListShowBean) {
        showBeanResult = circleListShowBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerSickCircleAdapter = new RecyclerSickCircleAdapter(getContext());
        recyclerSickCircleAdapter.addData(showBeanResult);
        patient_recycler_sick_circle_list.setLayoutManager(linearLayoutManager);
        patient_recycler_sick_circle_list.setAdapter(recyclerSickCircleAdapter);
        ;
        recyclerSickCircleAdapter.onItemClickListener(new RecyclerSickCircleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int sickCircleId) {
                Intent intent = new Intent(getContext(), PatientDetailsActivity.class);
                intent.putExtra("sickCircleId", sickCircleId);
                startActivity(intent);
            }
        });
    }

    @Override
    public void CircleListShowFailure(Throwable e) {

    }

    @Override
    public void KeywordSearchsuccess(KeywordSearchBean keywordSearchBean) {
        List<KeywordSearchBean.ResultBean> result = keywordSearchBean.getResult();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            patient_recycler_sick_circle_list.setLayoutManager(linearLayoutManager);
            keywordSearchAdapter = new KeywordSearchAdapter(getContext());
            keywordSearchAdapter.addData(result);
            patient_recycler_sick_circle_list.setAdapter(keywordSearchAdapter);
    }

    @Override
    public void KeywordSearchFailure(Throwable e) {

    }
}
