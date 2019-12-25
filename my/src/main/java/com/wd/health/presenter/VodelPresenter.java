package com.wd.health.presenter;

import com.wd.health.bean.VideoBean;
import com.wd.health.contract.VideoContract;
import com.wd.health.model.VideoModel;
import com.wd.mylibrary.Base.BasePresenter;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/24 20:09
 */
public class VodelPresenter extends BasePresenter<VideoContract.IView> implements VideoContract.IPresenter {

    private VideoModel model;

    @Override
    protected void initModel() {
        model = new VideoModel();
    }

    @Override
    public void VideoPresenter(String userId, String sessionId, String page, String count) {
        model.getVideoData(userId, sessionId, page, count, new VideoContract.IModel.VideoContractCallback() {
            @Override
            public void onVideoSuccess(VideoBean bean) {
                getView().onVideoSuccess(bean);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }
}
