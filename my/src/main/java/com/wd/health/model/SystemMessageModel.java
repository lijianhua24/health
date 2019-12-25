package com.wd.health.model;

import com.wd.health.bean.HealthyCurrencyBean;
import com.wd.health.bean.SystemMessageBean;
import com.wd.health.contract.SystemMessageContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/14 10:36
 */
public class SystemMessageModel implements SystemMessageContract.IModel {
    @Override
    public void getSystemMessageData(String userId, String sessionId, String page, String count, SystemMessageContractCallback systemMessageContractCallback) {
        RetrofitManager.getInstance().create().getSystemMessage(userId, sessionId, page, count)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<SystemMessageBean>() {
                    @Override
                    public void onNext(SystemMessageBean bean) {
                       systemMessageContractCallback.onSystemMessageSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                      systemMessageContractCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void getHealthyCurrencyData(String userId, String sessionId, String page, String count, SystemMessageContractCallback systemMessageContractCallback) {
        RetrofitManager.getInstance().create().getHealthyCurrency(userId, sessionId, page, count)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<HealthyCurrencyBean>() {
                    @Override
                    public void onNext(HealthyCurrencyBean bean) {
                        systemMessageContractCallback.onHealthyCurrencySuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        systemMessageContractCallback.onFailure(e);
                    }
                });
    }
}
