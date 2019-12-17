package com.wd.homemodel.presenter;

import com.wd.homemodel.contract.HomeContract;
import com.wd.homemodel.model.HomeModel;
import com.wd.mylibrary.Base.BasePresenter;

public class SearchPresenter extends BasePresenter<HomeContract.SearchContreact.IView> implements HomeContract.SearchContreact.IPresenter {

    private HomeModel homeModel;

    @Override
    public void getSearchPresenter(String keyWord) {
        homeModel.getSearchModel(keyWord, new HomeContract.SearchContreact.IModel.IModelSearchCallback() {
            @Override
            public void onSearchSuccess(Object data) {
                getView().onSearchSuccess(data);
            }

            @Override
            public void onSearchFailure(Throwable e) {
                getView().onSearchFailure(e);
            }
        });
    }

    @Override
    public void getPopularPresenter() {
        homeModel.getPopularModel(new HomeContract.SearchContreact.IModel.IModelPopularCallback() {
            @Override
            public void onPopularSuccess(Object data) {
                getView().onPopularSuccess(data);
            }

            @Override
            public void onPopularFailure(Throwable e) {
                getView().onPopularFailure(e);
            }
        });
    }

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }
}
