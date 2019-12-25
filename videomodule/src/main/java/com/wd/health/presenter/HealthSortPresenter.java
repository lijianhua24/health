package com.wd.health.presenter;

import com.wd.health.bean.HealthBuyBean;
import com.wd.health.bean.HealthSortBean;
import com.wd.health.bean.QvideoListBean;
import com.wd.health.bean.VideoSortBean;
import com.wd.health.contract.IContract;
import com.wd.health.model.HealthSortModel;
import com.wd.mylibrary.Base.BasePresenter;
public class HealthSortPresenter extends BasePresenter<IContract.iView> implements IContract.iPresenter {

    private HealthSortModel healthSortModel;

    @Override
    protected void initModel() {
        healthSortModel = new HealthSortModel();
    }

    @Override
    public void getHealthSort() {
        healthSortModel.getHealthSort(new IContract.iModel.iDepartmentListCallBack() {
            @Override
            public void healthSortsuccess(HealthSortBean healthSortBean) {
                getView().healthSortsuccess(healthSortBean);
            }

            @Override
            public void healthSortFailure(Throwable e) {
                getView().healthSortFailure(e);
            }

            @Override
            public void VideoSortsuccess(VideoSortBean videoSortBean) {

            }

            @Override
            public void VideoSortFailure(Throwable e) {

            }

            @Override
            public void HealthBuysuccess(HealthBuyBean healthBuyBean) {

            }

            @Override
            public void HealthBuyFailure(Throwable e) {

            }

            @Override
            public void HealthCollectionsuccess(HealthBuyBean healthBuyBean) {

            }

            @Override
            public void HealthCollectionFailure(Throwable e) {

            }

            @Override
            public void QvideoListsuccess(QvideoListBean qvideoListBean) {

            }

            @Override
            public void QvideoListFailure(Throwable e) {

            }

            @Override
            public void VideoCommentsuccess(HealthBuyBean healthBuyBean) {


            }

            @Override
            public void VideoCommentFailure(Throwable e) {

            }
        });
    }

    @Override
    public void getVideoSort(int userId, String sessionId, int categoryId, int page, int count) {
        healthSortModel.getVideoSort(userId, sessionId, categoryId, page, count, new IContract.iModel.iDepartmentListCallBack() {
            @Override
            public void healthSortsuccess(HealthSortBean healthSortBean) {

            }

            @Override
            public void healthSortFailure(Throwable e) {

            }

            @Override
            public void VideoSortsuccess(VideoSortBean videoSortBean) {
                getView().VideoSortsuccess(videoSortBean);
            }

            @Override
            public void VideoSortFailure(Throwable e) {
                getView().VideoSortFailure(e);
            }

            @Override
            public void HealthBuysuccess(HealthBuyBean healthBuyBean) {

            }

            @Override
            public void HealthBuyFailure(Throwable e) {

            }

            @Override
            public void HealthCollectionsuccess(HealthBuyBean healthBuyBean) {

            }

            @Override
            public void HealthCollectionFailure(Throwable e) {

            }

            @Override
            public void QvideoListsuccess(QvideoListBean qvideoListBean) {

            }

            @Override
            public void QvideoListFailure(Throwable e) {

            }

            @Override
            public void VideoCommentsuccess(HealthBuyBean healthBuyBean) {

            }

            @Override
            public void VideoCommentFailure(Throwable e) {

            }
        });
    }

    @Override
    public void getHealthBuy(int userId, String sessionId, int videoId, int price) {
        healthSortModel.getHealthBuy(userId, sessionId, videoId, price, new IContract.iModel.iDepartmentListCallBack() {
            @Override
            public void healthSortsuccess(HealthSortBean healthSortBean) {

            }

            @Override
            public void healthSortFailure(Throwable e) {

            }

            @Override
            public void VideoSortsuccess(VideoSortBean videoSortBean) {

            }

            @Override
            public void VideoSortFailure(Throwable e) {

            }

            @Override
            public void HealthBuysuccess(HealthBuyBean healthBuyBean) {
                getView().HealthBuysuccess(healthBuyBean);
            }

            @Override
            public void HealthBuyFailure(Throwable e) {
                getView().HealthBuyFailure(e);
            }

            @Override
            public void HealthCollectionsuccess(HealthBuyBean healthBuyBean) {

            }

            @Override
            public void HealthCollectionFailure(Throwable e) {

            }

            @Override
            public void QvideoListsuccess(QvideoListBean qvideoListBean) {

            }

            @Override
            public void QvideoListFailure(Throwable e) {

            }

            @Override
            public void VideoCommentsuccess(HealthBuyBean healthBuyBean) {

            }

            @Override
            public void VideoCommentFailure(Throwable e) {

            }
        });
    }

    @Override
    public void getHealthCollection(int userId, String sessionId, int videoId) {
        healthSortModel.getHealthCollection(userId, sessionId, videoId, new IContract.iModel.iDepartmentListCallBack() {
            @Override
            public void healthSortsuccess(HealthSortBean healthSortBean) {

            }

            @Override
            public void healthSortFailure(Throwable e) {

            }

            @Override
            public void VideoSortsuccess(VideoSortBean videoSortBean) {

            }

            @Override
            public void VideoSortFailure(Throwable e) {

            }

            @Override
            public void HealthBuysuccess(HealthBuyBean healthBuyBean) {

            }

            @Override
            public void HealthBuyFailure(Throwable e) {

            }

            @Override
            public void HealthCollectionsuccess(HealthBuyBean healthBuyBean) {
                getView().HealthCollectionsuccess(healthBuyBean);
            }

            @Override
            public void HealthCollectionFailure(Throwable e) {
                getView().HealthCollectionFailure(e);
            }

            @Override
            public void QvideoListsuccess(QvideoListBean qvideoListBean) {

            }

            @Override
            public void QvideoListFailure(Throwable e) {

            }

            @Override
            public void VideoCommentsuccess(HealthBuyBean healthBuyBean) {

            }

            @Override
            public void VideoCommentFailure(Throwable e) {

            }
        });
    }

    @Override
    public void getQvideoList(int userId, String sessionId, int videoId) {
        healthSortModel.getQvideoList(userId, sessionId, videoId, new IContract.iModel.iDepartmentListCallBack() {
            @Override
            public void healthSortsuccess(HealthSortBean healthSortBean) {

            }

            @Override
            public void healthSortFailure(Throwable e) {

            }

            @Override
            public void VideoSortsuccess(VideoSortBean videoSortBean) {

            }

            @Override
            public void VideoSortFailure(Throwable e) {

            }

            @Override
            public void HealthBuysuccess(HealthBuyBean healthBuyBean) {

            }

            @Override
            public void HealthBuyFailure(Throwable e) {

            }

            @Override
            public void HealthCollectionsuccess(HealthBuyBean healthBuyBean) {

            }

            @Override
            public void HealthCollectionFailure(Throwable e) {

            }

            @Override
            public void QvideoListsuccess(QvideoListBean qvideoListBean) {
                getView().QvideoListsuccess(qvideoListBean);
            }

            @Override
            public void QvideoListFailure(Throwable e) {
                getView().QvideoListFailure(e);
            }

            @Override
            public void VideoCommentsuccess(HealthBuyBean healthBuyBean) {

            }

            @Override
            public void VideoCommentFailure(Throwable e) {

            }
        });
    }

    @Override
    public void getVideoComment(int userId, String sessionId, int videoId, String content) {
        healthSortModel.getVideoComment(userId, sessionId, videoId, content, new IContract.iModel.iDepartmentListCallBack() {
            @Override
            public void healthSortsuccess(HealthSortBean healthSortBean) {

            }

            @Override
            public void healthSortFailure(Throwable e) {

            }

            @Override
            public void VideoSortsuccess(VideoSortBean videoSortBean) {

            }

            @Override
            public void VideoSortFailure(Throwable e) {

            }

            @Override
            public void HealthBuysuccess(HealthBuyBean healthBuyBean) {

            }

            @Override
            public void HealthBuyFailure(Throwable e) {

            }

            @Override
            public void HealthCollectionsuccess(HealthBuyBean healthBuyBean) {

            }

            @Override
            public void HealthCollectionFailure(Throwable e) {

            }

            @Override
            public void QvideoListsuccess(QvideoListBean qvideoListBean) {

            }

            @Override
            public void QvideoListFailure(Throwable e) {

            }

            @Override
            public void VideoCommentsuccess(HealthBuyBean healthBuyBean) {
                getView().VideoCommentsuccess(healthBuyBean);
            }

            @Override
            public void VideoCommentFailure(Throwable e) {
                getView().VideoCommentFailure(e);
            }
        });
    }
}
