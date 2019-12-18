package com.wd.health.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.wd.health.view.adapter.KeywordSearchAdapter;
import com.wd.health.view.adapter.RecyclerSickCircleAdapter;
import com.wd.mylibrary.Base.BaseFragment;
import java.util.List;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/18<p>
 * <p>更改时间：2019/12/18<p>
 */
public class SickCircleFragment extends BaseFragment<DepartmentListPresenter> implements IContract.iView {
    private int page = 1;
    private int count1 = 10;
    private RecyclerView patient_recycler_sick_circle_list;
    private int anInt;

    @Override
    protected DepartmentListPresenter providePresenter() {
        return new DepartmentListPresenter();
    }

    @Override
    protected void initData() {
       /* //病友圈列表刷新
        patient_recycler_sick_circle_list.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                mPresenter.getCircleListShowPresenter(anInt, page, count1);
                patient_recycler_sick_circle_list.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                page++;
                mPresenter.getCircleListShowPresenter(anInt, page, count1);
                patient_recycler_sick_circle_list.loadMoreComplete();
            }
        });*/

        Bundle bundle = getArguments();
        anInt = bundle.getInt("anInt");
        Log.d("LLLLLLLKL", "initData: "+ anInt);
        mPresenter.getCircleListShowPresenter(anInt, page, count1);

    }

    @Override
    protected void initView() {
        patient_recycler_sick_circle_list = getActivity().findViewById(R.id.patient_recycler_sick_circle_list);

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.sickcirclefragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void DepartmentListsuccess(DepartmentListBean departmentListBean) {
    }

    @Override
    public void DepartmentListFailure(Throwable e) {

    }

    @Override
    public void CircleListShowsuccess(CircleListShowBean circleListShowBean) {
        List<CircleListShowBean.ResultBean> result = circleListShowBean.getResult();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        patient_recycler_sick_circle_list.setLayoutManager(linearLayoutManager);
        RecyclerSickCircleAdapter recyclerSickCircleAdapter = new RecyclerSickCircleAdapter(getContext());
        patient_recycler_sick_circle_list.setAdapter(recyclerSickCircleAdapter);
        recyclerSickCircleAdapter.addData(result);
        recyclerSickCircleAdapter.onItemClickListener(new RecyclerSickCircleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, int sickCircleId) {
                Intent intent = new Intent(getActivity(), PatientDetailsActivity.class);
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
