package com.wd.homemodel.model;

import com.wd.homemodel.bean.BannerBean;
import com.wd.homemodel.bean.DepartmentBean;
import com.wd.homemodel.bean.InfoSectionBean;
import com.wd.homemodel.bean.SectionBean;
import com.wd.homemodel.contract.HomeContract;
import com.wd.homemodel.utils.RequestNet;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

public class HomeModel implements HomeContract.BnnerContreact.IModel {

    @Override
    public void getBannerDataModel(IModelCallback callback) {
        RequestNet.getInstance().create().getBanner()
                .compose(CommonSchedulers.<BannerBean>io2main())
                .subscribe(new CommonObserver<BannerBean>() {
                    @Override
                    public void onNext(BannerBean emailBean) {
                        callback.onBnnerSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onBnnerFailure(e);
                    }
                });
    }

    @Override
    public void getSectionDataModel(IModelSectionCallback callback) {
        RequestNet.getInstance().create().getSection()
                .compose(CommonSchedulers.<SectionBean>io2main())
                .subscribe(new CommonObserver<SectionBean>() {
                    @Override
                    public void onNext(SectionBean emailBean) {
                        callback.onSectionSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onSectionFailure(e);
                    }
                });
    }

    @Override
    public void getDepartmentDataModel(IModelDepartmentCallback callback) {
        RequestNet.getInstance().create().getDepartment()
                .compose(CommonSchedulers.<DepartmentBean>io2main())
                .subscribe(new CommonObserver<DepartmentBean>() {
                    @Override
                    public void onNext(DepartmentBean emailBean) {
                        callback.onDepartmentSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDepartmentFailure(e);
                    }
                });
    }

    @Override
    public void getInfoSectionDataModel(String plateId, Integer page, Integer count, IModelInfoSectionCallback callback) {
        RequestNet.getInstance().create().getInFoSection(plateId,page,count)
                .compose(CommonSchedulers.<InfoSectionBean>io2main())
                .subscribe(new CommonObserver<InfoSectionBean>() {
                    @Override
                    public void onNext(InfoSectionBean emailBean) {
                        callback.onInfoSectionSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onInfoSectionFailure(e);
                    }
                });
    }
}
