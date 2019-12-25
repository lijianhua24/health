package com.wd.health.presenter;

import com.wd.health.bean.HealthyCurrencyBean;
import com.wd.health.bean.SystemMessageBean;
import com.wd.health.contract.SystemMessageContract;
import com.wd.health.model.SystemMessageModel;
import com.wd.mylibrary.Base.BasePresenter;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/14 10:37
 */
public class SystemMessagePresenter extends BasePresenter<SystemMessageContract.IView> implements SystemMessageContract.IPresenter {

    private SystemMessageModel model;

    @Override
    protected void initModel() {
        model = new SystemMessageModel();
    }

    @Override
    public void SystemMessagePresenter(String userId, String sessionId, String page, String count) {
        model.getSystemMessageData(userId, sessionId, page, count, new SystemMessageContract.IModel.SystemMessageContractCallback() {
            @Override
            public void onSystemMessageSuccess(SystemMessageBean bean) {
                getView().onSystemMessageSuccess(bean);
            }

            @Override
            public void onHealthyCurrencySuccess(HealthyCurrencyBean bean) {

            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void HealthyCurrencyPresenter(String userId, String sessionId, String page, String count) {
        model.getHealthyCurrencyData(userId, sessionId, page, count, new SystemMessageContract.IModel.SystemMessageContractCallback() {
            @Override
            public void onSystemMessageSuccess(SystemMessageBean bean) {

            }

            @Override
            public void onHealthyCurrencySuccess(HealthyCurrencyBean bean) {
                getView().onHealthyCurrencySuccess(bean);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }
}
