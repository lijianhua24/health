package com.wd.health.model;

import com.wd.health.bean.AddSignBean;
import com.wd.health.bean.user.BindUserBankCardBean;
import com.wd.health.contract.BindUserBankCardContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/24 11:16
 */
public class BindUserBankCardModel implements BindUserBankCardContract.IModel {
    @Override
    public void getBindUserBankCardData(String userId, String sessionId, String bankCardNumber, String bankName, String bankCardType, BindUserBankCardContractCallback bindUserBankCardContractCallback) {
        RetrofitManager.getInstance().create().bindUserBankCard(userId, sessionId,bankCardNumber,bankName,bankCardType)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<BindUserBankCardBean>() {
                    @Override
                    public void onNext(BindUserBankCardBean bean) {
                        bindUserBankCardContractCallback.onBindUserBankCardSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                       bindUserBankCardContractCallback.onFailure(e);
                    }
                });
    }
}
