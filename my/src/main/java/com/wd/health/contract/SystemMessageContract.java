package com.wd.health.contract;

import com.wd.health.bean.HealthyCurrencyBean;
import com.wd.health.bean.SystemMessageBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:04
 */
public interface SystemMessageContract {
    interface IView extends IBaseView {
        void onSystemMessageSuccess(SystemMessageBean bean);
        void onHealthyCurrencySuccess(HealthyCurrencyBean bean);
        void onFailure(Throwable e);
    }

    interface IModel{
        void getSystemMessageData(String userId, String sessionId, String page, String count, SystemMessageContractCallback systemMessageContractCallback);
        void getHealthyCurrencyData(String userId, String sessionId, String page, String count, SystemMessageContractCallback systemMessageContractCallback);

        interface SystemMessageContractCallback{
            void onSystemMessageSuccess(SystemMessageBean bean);
            void onHealthyCurrencySuccess(HealthyCurrencyBean bean);
            void onFailure(Throwable e);
        }
    }

    interface IPresenter{
        void SystemMessagePresenter(String userId, String sessionId, String page, String count);
        void HealthyCurrencyPresenter(String userId, String sessionId, String page, String count);
    }
}
