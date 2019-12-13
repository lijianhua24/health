package com.wd.homemodel.contract;

import com.wd.homemodel.bean.BannerBean;
import com.wd.homemodel.bean.SectionBean;
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


}
