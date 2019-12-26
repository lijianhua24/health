package com.wd.health.model;

import com.wd.health.bean.HealthBuyBean;
import com.wd.health.bean.HealthSortBean;
import com.wd.health.bean.QvideoListBean;
import com.wd.health.bean.VideoSortBean;
import com.wd.health.contract.IContract;
import com.wd.health.utils.ApiServers;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;
public class HealthSortModel implements IContract.iModel {

    @Override
    public void getHealthSort(iDepartmentListCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .healthsprtbean()
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<HealthSortBean>() {
                    @Override
                    public void onNext(HealthSortBean healthSortBean) {
                        callBack.healthSortsuccess(healthSortBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.healthSortFailure(e);
                    }
                });
    }

    @Override
    public void getVideoSort(int userId, String sessionId, int categoryId, int page, int count, iDepartmentListCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .videosortbean(userId, sessionId, categoryId, page, count)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<VideoSortBean>() {
                    @Override
                    public void onNext(VideoSortBean videoSortBean) {
                        callBack.VideoSortsuccess(videoSortBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.VideoSortFailure(e);
                    }
                });
    }

    @Override
    public void getHealthBuy(int userId, String sessionId, int videoId, int price, iDepartmentListCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .healthbuybean(userId, sessionId, videoId, price)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<HealthBuyBean>() {
                    @Override
                    public void onNext(HealthBuyBean healthBuyBean) {
                        callBack.HealthBuysuccess(healthBuyBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.HealthBuyFailure(e);
                    }
                });
    }

    @Override
    public void getHealthCollection(int userId, String sessionId, int videoId, iDepartmentListCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .HealthCollection(userId, sessionId,videoId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<HealthBuyBean>() {
                    @Override
                    public void onNext(HealthBuyBean healthBuyBean) {
                        callBack.HealthCollectionsuccess(healthBuyBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.HealthCollectionFailure(e);
                    }
                });
    }

    @Override
    public void getQvideoList(int userId, String sessionId, int videoId, iDepartmentListCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .QvideoList(userId, sessionId, videoId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<QvideoListBean>() {
                    @Override
                    public void onNext(QvideoListBean qvideoListBean) {
                        callBack.QvideoListsuccess(qvideoListBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.QvideoListFailure(e);
                    }
                });
    }

    @Override
    public void getVideoComment(int userId, String sessionId, int videoId, String content, iDepartmentListCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .VideoComment(userId, sessionId, videoId, content)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<HealthBuyBean>() {
                    @Override
                    public void onNext(HealthBuyBean healthBuyBean) {
                        callBack.VideoCommentsuccess(healthBuyBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.VideoCommentFailure(e);
                    }
                });
    }

}
