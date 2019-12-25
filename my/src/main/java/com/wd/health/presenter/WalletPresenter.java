package com.wd.health.presenter;

import com.wd.health.bean.UserConsumptionRecordBean;
import com.wd.health.bean.WalletYUERBean;
import com.wd.health.contract.WalletContract;
import com.wd.health.model.WalletModel;
import com.wd.mylibrary.Base.BasePresenter;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/16 11:48
 */
public class WalletPresenter extends BasePresenter<WalletContract.IView> implements WalletContract.IPresenter {

    private WalletModel model;

    @Override
    protected void initModel() {
        model = new WalletModel();
    }

    @Override
    public void WalletYUERPresenter(String userId, String sessionId) {
        model.getWalletYUERData(userId, sessionId, new WalletContract.IModel.WalletContractCallback() {
            @Override
            public void onWalletYUERSuccess(WalletYUERBean bean) {
                getView().onWalletYUERSuccess(bean);
            }

            @Override
            public void onUserConsumptionRecordSuccess(UserConsumptionRecordBean bean) {

            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void UserConsumptionRecordPresenter(String userId, String sessionId, String page, String count) {
        model.getUserConsumptionRecordData(userId, sessionId, page, count, new WalletContract.IModel.WalletContractCallback() {
            @Override
            public void onWalletYUERSuccess(WalletYUERBean bean) {

            }

            @Override
            public void onUserConsumptionRecordSuccess(UserConsumptionRecordBean bean) {
                getView().onUserConsumptionRecordSuccess(bean);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }
}
