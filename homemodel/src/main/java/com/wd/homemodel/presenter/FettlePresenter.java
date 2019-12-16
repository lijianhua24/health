package com.wd.homemodel.presenter;

import com.wd.homemodel.contract.HomeContract;
import com.wd.homemodel.model.HomeModel;
import com.wd.mylibrary.Base.BasePresenter;

public class FettlePresenter extends BasePresenter<HomeContract.CommonContreact.IView> implements HomeContract.CommonContreact.IPresenter {

    private HomeModel homeModel;

    @Override
    public void getUnitDiseasePresenter(Integer id) {
        homeModel.getUnitDiseaseDataModel(id, new HomeContract.CommonContreact.IModel.IModelUnitDiseaseCallback() {
            @Override
            public void onUnitDiseaseSuccess(Object data) {
                getView().onUnitDiseaseSuccess(data);
            }

            @Override
            public void onUnitDiseaseFailure(Throwable e) {
                getView().onUnitDiseaseFailure(e);
            }
        });
    }

    @Override
    public void getDepartmentPresenter() {
        homeModel.getDepartmentsDataModel(new HomeContract.CommonContreact.IModel.IModelDepartmentsCallback() {
            @Override
            public void onDepartmentSuccess(Object data) {
                getView().onDepartmentSuccess(data);
            }

            @Override
            public void onDepartmentFailure(Throwable e) {
                getView().onDepartmentFailure(e);
            }
        });
    }

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }
}
