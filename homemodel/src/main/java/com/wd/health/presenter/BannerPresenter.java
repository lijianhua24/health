package com.wd.health.presenter;

import com.wd.health.bean.BannerBean;
import com.wd.health.bean.SectionBean;
import com.wd.health.contract.HomeContract;
import com.wd.health.model.HomeModel;
import com.wd.mylibrary.Base.BasePresenter;

public class BannerPresenter extends BasePresenter<HomeContract.BnnerContreact.IView> implements HomeContract.BnnerContreact.IPresenter {

    private HomeModel homeModel;

    @Override
    public void getBnnerPresenter() {
        homeModel.getBannerDataModel(new HomeContract.BnnerContreact.IModel.IModelCallback() {
            @Override
            public void onBnnerSuccess(BannerBean data) {
                if (isViewAttached()) {
                    if (data != null && data.getStatus().equals("0000")) {
                        getView().onBnnerSuccess(data);
                    }
                }
            }

            @Override
            public void onBnnerFailure(Throwable e) {
                if (isViewAttached()) {
                    getView().onBnnerFailure(e);
                }
            }
        });
    }

    @Override
    public void getSectionPresenter() {
        homeModel.getSectionDataModel(new HomeContract.BnnerContreact.IModel.IModelSectionCallback() {
            @Override
            public void onSectionSuccess(SectionBean data) {
                getView().onSectionSuccess(data);
            }

            @Override
            public void onSectionFailure(Throwable e) {
                getView().onSectionFailure(e);
            }
        });
    }

    @Override
    public void getDepartmentPresenter() {
        homeModel.getDepartmentDataModel(new HomeContract.BnnerContreact.IModel.IModelDepartmentCallback() {
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
    public void getInfoSectionPresenter(String plateId, Integer page, Integer count) {
            homeModel.getInfoSectionDataModel(plateId, page, count, new HomeContract.BnnerContreact.IModel.IModelInfoSectionCallback() {
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
