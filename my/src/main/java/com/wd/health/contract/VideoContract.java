package com.wd.health.contract;

import com.wd.health.bean.FocusBean;
import com.wd.health.bean.VideoBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:04
 */
public interface VideoContract {
    interface IView extends IBaseView {
        void onVideoSuccess(VideoBean bean);
        void onFailure(Throwable e);
    }

    interface IModel{
        void getVideoData(String userId, String sessionId, String page, String count, VideoContractCallback videoContractCallback);
        interface VideoContractCallback{
            void onVideoSuccess(VideoBean bean);
            void onFailure(Throwable e);
        }
    }

    interface IPresenter{
        void VideoPresenter(String userId, String sessionId, String page, String count);
    }
}
