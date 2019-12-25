package com.wd.health.contract;

import com.wd.health.bean.FocusBean;
import com.wd.health.bean.user.BindUserBankCardBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:04
 */
public interface BindUserBankCardContract {
    interface IView extends IBaseView {
        void onBindUserBankCardSuccess(BindUserBankCardBean bean);
        void onFailure(Throwable e);
    }

    interface IModel{
        void getBindUserBankCardData(String userId, String sessionId, String bankCardNumber, String bankName, String bankCardType, BindUserBankCardContractCallback bindUserBankCardContractCallback);
        interface BindUserBankCardContractCallback{
            void onBindUserBankCardSuccess(BindUserBankCardBean bean);
            void onFailure(Throwable e);
        }
    }

    interface IPresenter{
        void BindUserBankCardPresenter(String userId, String sessionId, String bankCardNumber, String bankName, String bankCardType);
    }
}
