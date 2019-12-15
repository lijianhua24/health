package com.wd.homemodel.model;

import com.wd.homemodel.bean.BannerBean;
import com.wd.homemodel.bean.DepartmentBean;
import com.wd.homemodel.bean.DrugBean;
import com.wd.homemodel.bean.InfoSectionBean;
import com.wd.homemodel.bean.SectionBean;
import com.wd.homemodel.bean.SubjectBean;
import com.wd.homemodel.bean.UnitDiseaseBean;
import com.wd.homemodel.contract.HomeContract;
import com.wd.homemodel.utils.RequestNet;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

public class HomeModel implements HomeContract.BnnerContreact.IModel,HomeContract.CommonContreact.IModel,HomeContract.DepartmentContreact.IModel {

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

    @Override
    public void getUnitDiseaseDataModel(Integer id,IModelUnitDiseaseCallback callback) {
        RequestNet.getInstance().create().getUnitsease(id)
                .compose(CommonSchedulers.<UnitDiseaseBean>io2main())
                .subscribe(new CommonObserver<UnitDiseaseBean>() {
                    @Override
                    public void onNext(UnitDiseaseBean emailBean) {
                        callback.onUnitDiseaseSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onUnitDiseaseFailure(e);
                    }
                });
    }

    @Override
    public void getDepartmentsDataModel(IModelDepartmentsCallback callback) {
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
    public void getDrugDataModel(Integer drugsCategoryId, Integer page, Integer count, IModelDrugCallback callback) {
        RequestNet.getInstance().create().getDrug(drugsCategoryId,page,count)
                .compose(CommonSchedulers.<DrugBean>io2main())
                .subscribe(new CommonObserver<DrugBean>() {
                    @Override
                    public void onNext(DrugBean emailBean) {
                        callback.onDrugSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDrugFailure(e);
                    }
                });
    }

    @Override
    public void getSubjectModel(IModelSubjectCallback callback) {
        RequestNet.getInstance().create().getSubject()
                .compose(CommonSchedulers.<SubjectBean>io2main())
                .subscribe(new CommonObserver<SubjectBean>() {
                    @Override
                    public void onNext(SubjectBean emailBean) {
                        callback.onSubjectSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onSubjectFailure(e);
                    }
                });
    }
}
