package com.wd.health.presenter;

import com.wd.health.contract.HomeContract;
import com.wd.health.model.HomeModel;
import com.wd.mylibrary.Base.BasePresenter;

public class FindPresenter extends BasePresenter<HomeContract.FindContreact.IView> implements HomeContract.FindContreact.IPresenter {

    private HomeModel homeModel;

    @Override
    public void getFindPresenter(Integer id) {
        homeModel.getFindModel(id, new HomeContract.FindContreact.IModel.IModelFindCallback() {
            @Override
            public void onFindSuccess(Object data) {
                getView().onFindSuccess(data);
            }

            @Override
            public void onFindFailure(Throwable e) {
                getView().onFindFailure(e);
            }
        });
    }

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }
}
