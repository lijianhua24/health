package com.wd.health.presenter;

import com.wd.health.bean.user.MySickCircleCommentListBean;
import com.wd.health.bean.user.MySickCircleListBean;
import com.wd.health.contract.MySinkCircleContract;
import com.wd.health.model.MySinkCircleModel;
import com.wd.mylibrary.Base.BasePresenter;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/22 19:10
 */
public class MySickCirclePresenter extends BasePresenter<MySinkCircleContract.IView> implements MySinkCircleContract.IPresenter {

    private MySinkCircleModel model;

    @Override
    protected void initModel() {
        model = new MySinkCircleModel();
    }

    @Override
    public void MySinkCirclePresenter(String userId, String sessionId, String page, String count) {
        model.getMySinkCircleData(userId, sessionId, page, count, new MySinkCircleContract.IModel.MySinkCircleContractCallback() {
            @Override
            public void onMySinkCircleSuccess(MySickCircleListBean bean) {
                getView().onMySinkCircleSuccess(bean);
            }

            @Override
            public void onMySickCircleCommentSuccess(MySickCircleCommentListBean bean) {

            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void MySickCircleCommentPresenter(String userId, String sessionId, String sickCircleId, String page, String count) {
        model.getMySickCircleCommentData(userId, sessionId, sickCircleId, page, count, new MySinkCircleContract.IModel.MySinkCircleContractCallback() {
            @Override
            public void onMySinkCircleSuccess(MySickCircleListBean bean) {

            }

            @Override
            public void onMySickCircleCommentSuccess(MySickCircleCommentListBean bean) {
                getView().onMySickCircleCommentSuccess(bean);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }
}
