package com.wd.health.presenter;

import com.wd.health.contract.HomeContract;
import com.wd.health.model.HomeModel;
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
    public void getgetCollectionPresenter(String userId, String sessionId, Integer infoId) {
        homeModel.getgetCollectionModel(userId, sessionId, infoId, new HomeContract.SpyDetailsContreact.IModel.IModelgetCollectionCallback() {
            @Override
            public void ongetCollectionSuccess(Object data) {
                getView().ongetCollectionSuccess(data);
            }

            @Override
            public void ongetCollectionFailure(Throwable e) {
                getView().ongetCollectionFailure(e);
            }
        });
    }

    @Override
    public void getgetCollectionsPresenter(String userId, String sessionId, Integer infoId) {
        homeModel.getgetCollectionsModel(userId, sessionId, infoId, new HomeContract.SpyDetailsContreact.IModel.IModelgetCollectionsCallback() {
            @Override
            public void ongetCollectionsSuccess(Object data) {
                getView().ongetCollectionsSuccess(data);
            }

            @Override
            public void ongetCollectionsFailure(Throwable e) {
                getView().ongetCollectionsFailure(e);
            }
        });
    }

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }
}
