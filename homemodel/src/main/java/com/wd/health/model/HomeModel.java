package com.wd.health.model;

import com.wd.health.bean.AttentionBean;
import com.wd.health.bean.BannerBean;
import com.wd.health.bean.CheckDoctorsBean;
import com.wd.health.bean.CmedicinesBean;
import com.wd.health.bean.DepartmentBean;
import com.wd.health.bean.DoctorDetailsBean;
import com.wd.health.bean.DrugBean;
import com.wd.health.bean.FindBean;
import com.wd.health.bean.InfoSectionBean;
import com.wd.health.bean.PopularBean;
import com.wd.health.bean.SearchBean;
import com.wd.health.bean.SectionBean;
import com.wd.health.bean.SpyDetailsBean;
import com.wd.health.bean.SubjectBean;
import com.wd.health.bean.UnitDiseaseBean;
import com.wd.health.bean.UnsubscribeBean;
import com.wd.health.contract.HomeContract;
import com.wd.health.utils.RequestNet;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

public class HomeModel implements HomeContract.BnnerContreact.IModel,HomeContract.CommonContreact.IModel,HomeContract.DepartmentContreact.IModel,
HomeContract.FindContreact.IModel,HomeContract.CmedicinesContreact.IModel,HomeContract.InfoSectionContreact.IModel,HomeContract.SpyDetailsContreact.IModel,
HomeContract.SearchContreact.IModel,HomeContract.CheckDoctorsContreact.IModel{

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

    @Override
    public void getFindModel(Integer id, IModelFindCallback callback) {
        RequestNet.getInstance().create().getFind(id)
                .compose(CommonSchedulers.<FindBean>io2main())
                .subscribe(new CommonObserver<FindBean>() {
                    @Override
                    public void onNext(FindBean emailBean) {
                        callback.onFindSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFindFailure(e);
                    }
                });
    }

    @Override
    public void getCmedicinesModel(Integer id, IModelCmedicinesCallback callback) {
        RequestNet.getInstance().create().getCmedicines(id)
                .compose(CommonSchedulers.<CmedicinesBean>io2main())
                .subscribe(new CommonObserver<CmedicinesBean>() {
                    @Override
                    public void onNext(CmedicinesBean emailBean) {
                        callback.onCmedicinesSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onCmedicinesFailure(e);
                    }
                });
    }

    @Override
    public void getInfoSectionsModel(String plateId, Integer page, Integer count, IModelInfoSectionsCallback callback) {
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
    public void getSpyDetailsModel(String userId, String sessionId, Integer infoId, IModelSpyDetailsCallback callback) {
        RequestNet.getInstance().create().getSpyDetal(userId,sessionId,infoId)
                .compose(CommonSchedulers.<SpyDetailsBean>io2main())
                .subscribe(new CommonObserver<SpyDetailsBean>() {
                    @Override
                    public void onNext(SpyDetailsBean emailBean) {
                        callback.onSpyDetailsSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onSpyDetailsFailure(e);
                    }
                });
    }

    @Override
    public void getSearchModel(String keyWord, IModelSearchCallback callback) {
        RequestNet.getInstance().create().getSearBean(keyWord)
                .compose(CommonSchedulers.<SearchBean>io2main())
                .subscribe(new CommonObserver<SearchBean>() {
                    @Override
                    public void onNext(SearchBean emailBean) {
                        callback.onSearchSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onSearchFailure(e);
                    }
                });
    }

    @Override
    public void getPopularModel( IModelPopularCallback callback) {
        RequestNet.getInstance().create().getPopular()
                .compose(CommonSchedulers.<PopularBean>io2main())
                .subscribe(new CommonObserver<PopularBean>() {
                    @Override
                    public void onNext(PopularBean emailBean) {
                        callback.onPopularSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onPopularFailure(e);
                    }
                });
    }

    @Override
    public void getCheckDoctorsModel(Integer deptId, Integer condition, Integer sortBy, Integer page, Integer count, IModelCheckDoctorsCallback callback) {
        RequestNet.getInstance().create().getCheckDoctors(deptId,condition,sortBy,page,count)
                .compose(CommonSchedulers.<CheckDoctorsBean>io2main())
                .subscribe(new CommonObserver<CheckDoctorsBean>() {
                    @Override
                    public void onNext(CheckDoctorsBean emailBean) {
                        callback.onCheckDoctorsSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onCheckDoctorsFailure(e);
                    }
                });
    }

    @Override
    public void getDoctorDetailsModel(String userId, String sessionId, String doctorId, IModelDoctorDetailsCallback callback) {
        RequestNet.getInstance().create().getDoctorDetails(userId,sessionId,doctorId)
                .compose(CommonSchedulers.<DoctorDetailsBean>io2main())
                .subscribe(new CommonObserver<DoctorDetailsBean>() {
                    @Override
                    public void onNext(DoctorDetailsBean emailBean) {
                        callback.onDoctorDetailsSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDoctorDetailsFailure(e);
                    }
                });
    }

    @Override
    public void getAttentionModel(String userId, String sessionId, String doctorId, IModelAttentionCallback callback) {
        RequestNet.getInstance().create().getAttention(userId,sessionId,doctorId)
                .compose(CommonSchedulers.<AttentionBean>io2main())
                .subscribe(new CommonObserver<AttentionBean>() {
                    @Override
                    public void onNext(AttentionBean emailBean) {
                        callback.onDoctorDetailsSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDoctorDetailsFailure(e);
                    }
                });
    }

    @Override
    public void getUnsubscribeModel(String userId, String sessionId, String doctorId, IModelUnsubscribeCallback callback) {
        RequestNet.getInstance().create().getUnsubscribe(userId,sessionId,doctorId)
                .compose(CommonSchedulers.<UnsubscribeBean>io2main())
                .subscribe(new CommonObserver<UnsubscribeBean>() {
                    @Override
                    public void onNext(UnsubscribeBean emailBean) {
                        callback.onDoctorDetailsSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDoctorDetailsFailure(e);
                    }
                });
    }
}
