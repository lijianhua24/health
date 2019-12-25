package com.wd.health.model;

import com.wd.health.bean.JKZXBean;
import com.wd.health.bean.user.MySickCircleCommentListBean;
import com.wd.health.bean.user.MySickCircleListBean;
import com.wd.health.contract.MySinkCircleContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/22 19:08
 */
public class MySinkCircleModel implements MySinkCircleContract.IModel {
    @Override
    public void getMySinkCircleData(String userId, String sessionId, String page, String count, MySinkCircleContractCallback mySinkCircleContractCallback) {
        RetrofitManager.getInstance().create().getMySickCircleList(userId, sessionId, page, count)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<MySickCircleListBean>() {
                    @Override
                    public void onNext(MySickCircleListBean bean) {
                        mySinkCircleContractCallback.onMySinkCircleSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                      mySinkCircleContractCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void getMySickCircleCommentData(String userId, String sessionId, String sickCircleId, String page, String count, MySinkCircleContractCallback mySinkCircleContractCallback) {
        RetrofitManager.getInstance().create().getMySickCircleCommentList(userId, sessionId,sickCircleId, page, count)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<MySickCircleCommentListBean>() {
                    @Override
                    public void onNext(MySickCircleCommentListBean bean) {
                        mySinkCircleContractCallback.onMySickCircleCommentSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mySinkCircleContractCallback.onFailure(e);
                    }
                });
    }
}
