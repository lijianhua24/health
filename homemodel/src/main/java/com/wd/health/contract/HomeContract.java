package com.wd.health.contract;

import com.wd.health.bean.BannerBean;
import com.wd.health.bean.SectionBean;
import com.wd.mylibrary.Base.IBaseView;

public interface HomeContract {
    interface BnnerContreact {
        interface IModel {
            void getBannerDataModel(IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onBnnerSuccess(BannerBean data);

                void onBnnerFailure(Throwable e);
            }

            //查询健康资讯板块
            void getSectionDataModel(IModelSectionCallback callback);

            //model层中的接口回调
            interface IModelSectionCallback {
                void onSectionSuccess(SectionBean data);

                void onSectionFailure(Throwable e);
            }

            //查询科室列表
            void getDepartmentDataModel(IModelDepartmentCallback callback);

            //model层中的接口回调
            interface IModelDepartmentCallback {
                void onDepartmentSuccess(Object data);

                void onDepartmentFailure(Throwable e);
            }

            //根据资讯板块查询资讯列表

            void getInfoSectionDataModel(String plateId, Integer page,Integer count,IModelInfoSectionCallback callback);

            //model层中的接口回调
            interface IModelInfoSectionCallback {
                void onInfoSectionSuccess(Object data);

                void onInfoSectionFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onBnnerSuccess(BannerBean data);

            void onBnnerFailure(Throwable e);

            void onSectionSuccess(Object data);

            void onSectionFailure(Throwable e);

            void onDepartmentSuccess(Object data);

            void onDepartmentFailure(Throwable e);

            void onInfoSectionSuccess(Object data);

            void onInfoSectionFailure(Throwable e);

        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getBnnerPresenter();

            void getSectionPresenter();

            void getDepartmentPresenter();

            void getInfoSectionPresenter(String plateId, Integer page,Integer count);

        }
    }

    interface CommonContreact {
        interface IModel {
            void getUnitDiseaseDataModel(Integer id,IModelUnitDiseaseCallback callback);

            //model层中的接口回调
            interface IModelUnitDiseaseCallback {
                void onUnitDiseaseSuccess(Object data);

                void onUnitDiseaseFailure(Throwable e);
            }

            //查询科室列表
            void getDepartmentsDataModel(IModelDepartmentsCallback callback);

            //model层中的接口回调
            interface IModelDepartmentsCallback {
                void onDepartmentSuccess(Object data);

                void onDepartmentFailure(Throwable e);
            }

        }

        //view层  命名必须是IView
        interface IView extends IBaseView {


            void onDepartmentSuccess(Object data);

            void onDepartmentFailure(Throwable e);

            void onUnitDiseaseSuccess(Object data);

            void onUnitDiseaseFailure(Throwable e);

        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getUnitDiseasePresenter(Integer id);

            void getDepartmentPresenter();

        }
    }

    interface DepartmentContreact {
        interface IModel {
            void getDrugDataModel(Integer drugsCategoryId,Integer page,Integer count,IModelDrugCallback callback);

            //model层中的接口回调
            interface IModelDrugCallback {
                void onDrugSuccess(Object data);

                void onDrugFailure(Throwable e);
            }

            //查询科室列表
            void getSubjectModel(IModelSubjectCallback callback);

            //model层中的接口回调
            interface IModelSubjectCallback {
                void onSubjectSuccess(Object data);

                void onSubjectFailure(Throwable e);
            }

        }

        //view层  命名必须是IView
        interface IView extends IBaseView {

            void onDrugSuccess(Object data);

            void onDrugFailure(Throwable e);

            void onSubjectSuccess(Object data);

            void onSubjectFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getDrugPresenter(Integer drugsCategoryId,Integer page,Integer count);

            void getSubjectPresenter();

        }
    }


    interface FindContreact {
        interface IModel {
            void getFindModel(Integer id,IModelFindCallback callback);

            //model层中的接口回调
            interface IModelFindCallback {
                void onFindSuccess(Object data);

                void onFindFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {


            void onFindSuccess(Object data);

            void onFindFailure(Throwable e);

        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getFindPresenter(Integer id);

        }
    }


    interface CmedicinesContreact {
        interface IModel {
            void getCmedicinesModel(Integer id,IModelCmedicinesCallback callback);

            //model层中的接口回调
            interface IModelCmedicinesCallback {
                void onCmedicinesSuccess(Object data);

                void onCmedicinesFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {


            void onCmedicinesSuccess(Object data);

            void onCmedicinesFailure(Throwable e);

        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getCmedicinesPresenter(Integer id);

        }
    }

    interface InfoSectionContreact {
        interface IModel {
            void getInfoSectionsModel(String plateId, Integer page,Integer count,IModelInfoSectionsCallback callback);

            //model层中的接口回调
            interface IModelInfoSectionsCallback {
                void onInfoSectionSuccess(Object data);

                void onInfoSectionFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {


            void onInfoSectionSuccess(Object data);

            void onInfoSectionFailure(Throwable e);

        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getInfoSectionPresenter(String plateId, Integer page,Integer count);

        }
    }

    interface SpyDetailsContreact {
        interface IModel {
            void getSpyDetailsModel(String userId,String sessionId,Integer infoId,IModelSpyDetailsCallback callback);

            //model层中的接口回调
            interface IModelSpyDetailsCallback {
                void onSpyDetailsSuccess(Object data);

                void onSpyDetailsFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {


            void onSpyDetailsSuccess(Object data);

            void onSpyDetailsFailure(Throwable e);

        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getSpyDetailsPresenter(String userId,String sessionId,Integer infoId);

        }
    }


    interface SearchContreact {
        interface IModel {
            void getSearchModel(String keyWord,IModelSearchCallback callback);

            //model层中的接口回调
            interface IModelSearchCallback {
                void onSearchSuccess(Object data);

                void onSearchFailure(Throwable e);
            }

            void getPopularModel(IModelPopularCallback callback);

            //model层中的接口回调
            interface IModelPopularCallback {
                void onPopularSuccess(Object data);

                void onPopularFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {


            void onSearchSuccess(Object data);

            void onSearchFailure(Throwable e);

            void onPopularSuccess(Object data);

            void onPopularFailure(Throwable e);

        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getSearchPresenter(String keyWord);

            void getPopularPresenter();

        }
    }


    interface CheckDoctorsContreact {
        interface IModel {
            void getCheckDoctorsModel(Integer deptId,Integer condition,Integer sortBy,Integer page,Integer count,IModelCheckDoctorsCallback callback);

            //model层中的接口回调
            interface IModelCheckDoctorsCallback {
                void onCheckDoctorsSuccess(Object data);

                void onCheckDoctorsFailure(Throwable e);
            }

            void getDoctorDetailsModel(String userId,String sessionId,String doctorId,IModelDoctorDetailsCallback callback);

            //model层中的接口回调
            interface IModelDoctorDetailsCallback {
                void onDoctorDetailsSuccess(Object data);

                void onDoctorDetailsFailure(Throwable e);
            }
            void getAttentionModel(String userId,String sessionId,String doctorId,IModelAttentionCallback callback);

            //model层中的接口回调
            interface IModelAttentionCallback {
                void onDoctorDetailsSuccess(Object data);

                void onDoctorDetailsFailure(Throwable e);
            }
            void getUnsubscribeModel(String userId,String sessionId,String doctorId,IModelUnsubscribeCallback callback);

            //model层中的接口回调
            interface IModelUnsubscribeCallback {
                void onUnsubscribeSuccess(Object data);

                void onUnsubscribeFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {


            void onCheckDoctorsSuccess(Object data);

            void onCheckDoctorsFailure(Throwable e);

            void onDoctorDetailsSuccess(Object data);

            void onDoctorDetailsFailure(Throwable e);

            void onAttentionSuccess(Object data);

            void onAttentionFailure(Throwable e);

            void onUnsubscribeSuccess(Object data);

            void onUnsubscribeFailure(Throwable e);

        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getCheckDoctorsPresenter(Integer deptId,Integer condition,Integer sortBy,Integer page,Integer count);

            void getDoctorDetailsPresenter(String userId,String sessionId,String doctorId);

            void getAttentionPresenter(String userId,String sessionId,String doctorId);

            void getUnsubscribePresenter(String userId,String sessionId,String doctorId);

        }
    }
}
