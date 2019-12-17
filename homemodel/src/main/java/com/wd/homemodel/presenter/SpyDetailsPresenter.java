package com.wd.homemodel.presenter;

import com.wd.homemodel.contract.HomeContract;
import com.wd.homemodel.model.HomeModel;
import com.wd.mylibrary.Base.BasePresenter;

public class SpyDetailsPresenter extends BasePresenter<HomeContract.SpyDetailsContreact.IView> implements HomeContract.SpyDetailsContreact.IPresenter {

    private HomeModel homeModel;

    @Override
    public void getSpyDetailsPresenter(String userId, String sessionId, Integer infoId) {
    homeModel.getSpyDetailsModel(userId, sessionId, infoId, new HomeContract.SpyDetailsContreact.IModel.IModelSpyDetailsCallback() {
        @Override
        public void onSpyDetailsSuccess(Object data) {
            getView().onSpyDetailsSuccess(data);
        }

        @Override
        public void onSpyDetailsFailure(Throwable e) {
            getView().onSpyDetailsFailure(e);
        }
    });
    }

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }
}
