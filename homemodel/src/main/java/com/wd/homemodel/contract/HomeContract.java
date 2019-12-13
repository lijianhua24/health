package com.wd.homemodel.contract;

import com.wd.homemodel.bean.BannerBean;
import com.wd.mylibrary.Base.IBaseView;

public interface HomeContract {
    interface BnnerContreact {
        interface IModel {
            void getTjyyDataModel(IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onBnnerSuccess(BannerBean data);

                void onBnnerFailure(Throwable e);
            }

        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onBnnerSuccess(BannerBean data);

            void onBnnerFailure(Throwable e);


        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getBnnerPresenter();

        }
    }
}
