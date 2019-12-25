package com.wd.health.contract;

import com.wd.health.bean.UserConsumptionRecordBean;
import com.wd.health.bean.WalletYUERBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:04
 */
public interface WalletContract {
    interface IView extends IBaseView {
        void onWalletYUERSuccess(WalletYUERBean bean);

        void onUserConsumptionRecordSuccess(UserConsumptionRecordBean bean);

        void onFailure(Throwable e);
    }

    interface IModel {
        void getWalletYUERData(String userId, String sessionId, WalletContractCallback walletContractCallback);

        void getUserConsumptionRecordData(String userId, String sessionId, String page, String count, WalletContractCallback walletContractCallback);

        interface WalletContractCallback {
            void onWalletYUERSuccess(WalletYUERBean bean);

            void onUserConsumptionRecordSuccess(UserConsumptionRecordBean bean);

            void onFailure(Throwable e);
        }
    }

    interface IPresenter {
        void WalletYUERPresenter(String userId, String sessionId);

        void UserConsumptionRecordPresenter(String userId, String sessionId, String page, String count);
    }
}
