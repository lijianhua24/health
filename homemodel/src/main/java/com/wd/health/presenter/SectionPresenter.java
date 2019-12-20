package com.wd.health.presenter;

import com.wd.health.contract.HomeContract;
import com.wd.health.model.HomeModel;
import com.wd.mylibrary.Base.BasePresenter;

public class SectionPresenter extends BasePresenter<HomeContract.InfoSectionContreact.IView> implements HomeContract.InfoSectionContreact.IPresenter {

    private HomeModel homeModel;

    @Override
    public void getInfoSectionPresenter(String plateId, Integer page, Integer count) {
        homeModel.getInfoSectionsModel(plateId, page, count, new HomeContract.InfoSectionContreact.IModel.IModelInfoSectionsCallback() {
            @Override
            public void onInfoSectionSuccess(Object data) {
                getView().onInfoSectionSuccess(data);
            }

            @Override
            public void onInfoSectionFailure(Throwable e) {
                getView().onInfoSectionFailure(e);
            }
        });
    }

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }
}
