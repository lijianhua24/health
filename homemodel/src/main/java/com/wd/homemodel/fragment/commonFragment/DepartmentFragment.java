package com.wd.homemodel.fragment.commonFragment;

import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.homemodel.R;
import com.wd.homemodel.adapter.DrugAdapter;
import com.wd.homemodel.adapter.KeShiAdapter;
import com.wd.homemodel.adapter.SubjectAdapter;
import com.wd.homemodel.bean.DrugBean;
import com.wd.homemodel.bean.SubjectBean;
import com.wd.homemodel.contract.HomeContract;
import com.wd.homemodel.presenter.DepartmentPresenter;
import com.wd.mylibrary.Base.BaseFragment;

import java.util.List;

import butterknife.BindView;


public class DepartmentFragment extends BaseFragment<DepartmentPresenter> implements HomeContract.DepartmentContreact.IView {

    @BindView(R.id.departent_keshi)
    RecyclerView departentKeshi;
    @BindView(R.id.departent_bing)
    RecyclerView departentBing;

    @Override
    public void onDrugSuccess(Object data) {
       DrugBean drugBean = (DrugBean) data;
        List<DrugBean.ResultBean> result = drugBean.getResult();
        if (result!=null){
            SubjectAdapter subjectAdapter = new SubjectAdapter(getActivity(), result);
            departentBing.setAdapter(subjectAdapter);
        }
    }

    @Override
    public void onDrugFailure(Throwable e) {

    }

    @Override
    public void onSubjectSuccess(Object data) {
        SubjectBean drugBean = (SubjectBean) data;
        List<SubjectBean.ResultBean> result = drugBean.getResult();
        DrugAdapter drugAdapter = new DrugAdapter(getActivity(), result);
        departentKeshi.setAdapter(drugAdapter);

        drugAdapter.setListenter(new DrugAdapter.getChange() {
            @Override
            public void setChange(int id, int position) {
                mPresenter.getDrugPresenter(id,1,100);
                drugAdapter.setmPosition(position);
                drugAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSubjectFailure(Throwable e) {

    }

    @Override
    protected DepartmentPresenter providePresenter() {
        return new DepartmentPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mPresenter.getDrugPresenter(1,1,100);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        departentBing.setLayoutManager(gridLayoutManager);
        departentKeshi.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPresenter.getSubjectPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_department;
    }
}