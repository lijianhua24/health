package com.wd.health.fragment.commonFragment;

import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.adapter.KeShiAdapter;
import com.wd.health.adapter.UnitDiseaseAdapter;
import com.wd.health.bean.DepartmentBean;
import com.wd.health.bean.UnitDiseaseBean;
import com.wd.health.contract.HomeContract;
import com.wd.health.presenter.FettlePresenter;
import com.wd.mylibrary.Base.BaseFragment;

import java.util.List;

import butterknife.BindView;


public class FettleFragment extends BaseFragment<FettlePresenter> implements HomeContract.CommonContreact.IView {


    @BindView(R.id.fettle_keshi)
    RecyclerView fettleKeshi;
    @BindView(R.id.fettle_bing)
    RecyclerView fettleBing;

    @Override
    protected FettlePresenter providePresenter() {
        return new FettlePresenter();
    }

    @Override
    protected void initData() {
        fettleKeshi.setLayoutManager(new LinearLayoutManager(getActivity()));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        fettleBing.setLayoutManager(gridLayoutManager);
        mPresenter.getDepartmentPresenter();
        mPresenter.getUnitDiseasePresenter(7);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_fettle;
    }

    @Override
    public void onDepartmentSuccess(Object data) {
        DepartmentBean departmentBean = (DepartmentBean) data;
        List<DepartmentBean.ResultBean> result = departmentBean.getResult();
        if (result!=null){
            KeShiAdapter keShiAdapter = new KeShiAdapter(getActivity(), result);
            fettleKeshi.setAdapter(keShiAdapter);
            keShiAdapter.setListenter(new KeShiAdapter.getChange() {
                @Override
                public void setChange(int id,int p) {
                    mPresenter.getUnitDiseasePresenter(id);
                    keShiAdapter.setmPosition(p);
                    keShiAdapter.notifyDataSetChanged();
                }
            });

        }
    }

    @Override
    public void onDepartmentFailure(Throwable e) {

    }

    @Override
    public void onUnitDiseaseSuccess(Object data) {
        UnitDiseaseBean unitDiseaseBean = (UnitDiseaseBean) data;
        String message = unitDiseaseBean.getMessage();
        Log.d("unitDiseaseBean",message);
        List<UnitDiseaseBean.ResultBean> result = unitDiseaseBean.getResult();
        if (result!=null){
            UnitDiseaseAdapter unitDiseaseAdapter = new UnitDiseaseAdapter(getActivity(), result);
            fettleBing.setAdapter(unitDiseaseAdapter);
        }
    }

    @Override
    public void onUnitDiseaseFailure(Throwable e) {

    }
}
