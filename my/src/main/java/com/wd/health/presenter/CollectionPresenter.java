package com.wd.health.presenter;

import com.wd.health.bean.JKZXBean;
import com.wd.health.contract.ConlltionContract;
import com.wd.health.model.CollectionModel;
import com.wd.mylibrary.Base.BasePresenter;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:07
 */
public class CollectionPresenter extends BasePresenter<ConlltionContract.IView> implements ConlltionContract.IPresenter {


    private CollectionModel model;

    @Override
    protected void initModel() {
        model = new CollectionModel();
    }


    @Override
    public void JKZXPresenter(String userId, String sessionId, String page, String count) {
        model.getJKZXData(userId, sessionId, page, count, new ConlltionContract.IModel.ConlltionContractCallback() {
            @Override
            public void onJKZXSuccess(JKZXBean bean) {
                getView().onJKZXSuccess(bean);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }
}
