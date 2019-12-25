package com.wd.health.contract;

import com.wd.health.bean.JKZXBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:04
 */
public interface ConlltionContract {
    interface IView extends IBaseView {
        void onJKZXSuccess(JKZXBean bean);
        void onFailure(Throwable e);
    }

    interface IModel{
        void getJKZXData(String userId, String sessionId, String page, String count, ConlltionContractCallback conlltionContractCallback);
        interface ConlltionContractCallback{
            void onJKZXSuccess(JKZXBean bean);
            void onFailure(Throwable e);
        }
    }

    interface IPresenter{
        void JKZXPresenter(String userId, String sessionId, String page, String count);
    }
}
