package com.wd.health.presenter;

import com.wd.health.contract.HomeContract;
import com.wd.health.model.HomeModel;
import com.wd.mylibrary.Base.BasePresenter;

public class DepartmentPresenter extends BasePresenter<HomeContract.DepartmentContreact.IView> implements HomeContract.DepartmentContreact.IPresenter {

    private HomeModel homeModel;

    @Override
    public void getDrugPresenter(Integer drugsCategoryId, Integer page, Integer count) {
        homeModel.getDrugDataModel(drugsCategoryId, page, count, new HomeContract.DepartmentContreact.IModel.IModelDrugCallback() {
            @Override
            public void onDrugSuccess(Object data) {
                getView().onDrugSuccess(data);
            }

            @Override
            public void onDrugFailure(Throwable e) {
                getView().onDrugFailure(e);
            }
        });
    }

    @Override
    public void getSubjectPresenter() {
        homeModel.getSubjectModel(new HomeContract.DepartmentContreact.IModel.IModelSubjectCallback() {
            @Override
            public void onSubjectSuccess(Object data) {
                getView().onSubjectSuccess(data);
            }

            @Override
            public void onSubjectFailure(Throwable e) {
                getView().onSubjectFailure(e);
            }
        });
    }

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }
}
