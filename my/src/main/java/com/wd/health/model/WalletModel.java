package com.wd.health.model;

import com.wd.health.bean.UserConsumptionRecordBean;
import com.wd.health.bean.WalletYUERBean;
import com.wd.health.contract.WalletContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/16 11:45
 */
public class WalletModel implements WalletContract.IModel {
    @Override
    public void getWalletYUERData(String userId, String sessionId, WalletContractCallback walletContractCallback) {
        RetrofitManager.getInstance().create().getWallet(userId, sessionId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<WalletYUERBean>() {
                    @Override
                    public void onNext(WalletYUERBean bean) {
                        walletContractCallback.onWalletYUERSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        walletContractCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void getUserConsumptionRecordData(String userId, String sessionId, String page, String count, WalletContractCallback walletContractCallback) {
        RetrofitManager.getInstance().create().getUserConsumptionRecord(userId, sessionId,page,count)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<UserConsumptionRecordBean>() {
                    @Override
                    public void onNext(UserConsumptionRecordBean bean) {
                        walletContractCallback.onUserConsumptionRecordSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        walletContractCallback.onFailure(e);
                    }
                });
    }
}
