package com.wd.health.contract;

import com.wd.health.bean.BannerBean;
import com.wd.health.bean.SectionBean;
import com.wd.mylibrary.Base.IBaseView;

public interface IMContract {
    interface IModel {
        void getCurrentModel(String userId,String sessionId,IModelCurrentCallback callback);

        //model层中的接口回调
        interface IModelCurrentCallback {
            void onCurrentSuccess(Object data);

            void onCurrentFailure(Throwable e);
        }

        //查询健康资讯板块
        void getRecordingModel(String userId,String sessionId,int inquiryId,int page,int count,IModelRecordingCallback callback);

        //model层中的接口回调
        interface IModelRecordingCallback {
            void onRecordingSuccess(Object data);

            void onRecordingFailure(Throwable e);
        }

        //查询科室列表
        void getMessageDataModel(String userId, String sessionId,int inquiryId,String content,int type,int doctorId,IModelMessageCallback callback);

        //model层中的接口回调
        interface IModelMessageCallback {
            void onMessageSuccess(Object data);

            void onMessageFailure(Throwable e);
        }


    }

    //view层  命名必须是IView
    interface IView extends IBaseView {
        void onCurrentSuccess(Object data);

        void onCurrentFailure(Throwable e);

        void onRecordingSuccess(Object data);

        void onRecordingFailure(Throwable e);

        void onMessageSuccess(Object data);

        void onMessageFailure(Throwable e);



    }

    //presenter层   命名必须是IPresenter
    interface IPresenter {

        void getCurrentPresenter(String userId,String sessionId);

        void getRecordingPresenter(String userId,String sessionId,int inquiryId,int page,int count);

        void getMessagePresenter(String userId, String sessionId,int inquiryId,String content,int type,int doctorId);


    }
}
