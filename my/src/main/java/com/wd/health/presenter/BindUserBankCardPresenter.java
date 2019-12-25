package com.wd.health.presenter;

import com.wd.health.bean.user.BindUserBankCardBean;
import com.wd.health.contract.BindUserBankCardContract;
import com.wd.health.model.BindUserBankCardModel;
import com.wd.mylibrary.Base.BasePresenter;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/24 11:21
 */
public class BindUserBankCardPresenter extends BasePresenter<BindUserBankCardContract.IView> implements BindUserBankCardContract.IPresenter {

    private BindUserBankCardModel model;

    @Override
    protected void initModel() {
        model = new BindUserBankCardModel();
    }

    @Override
    public void BindUserBankCardPresenter(String userId, String sessionId, String bankCardNumber, String bankName, String bankCardType) {
        model.getBindUserBankCardData(userId, sessionId, bankCardNumber, bankName, bankCardType, new BindUserBankCardContract.IModel.BindUserBankCardContractCallback() {
            @Override
            public void onBindUserBankCardSuccess(BindUserBankCardBean bean) {
                getView().onBindUserBankCardSuccess(bean);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }
}
