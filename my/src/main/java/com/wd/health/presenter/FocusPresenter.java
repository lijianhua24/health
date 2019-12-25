package com.wd.health.presenter;

import com.wd.health.bean.FocusBean;
import com.wd.health.contract.FocusContract;
import com.wd.health.model.FocusModel;
import com.wd.mylibrary.Base.BasePresenter;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:07
 */
public class FocusPresenter extends BasePresenter<FocusContract.IView> implements FocusContract.IPresenter {

    private FocusModel model;

    @Override
    protected void initModel() {
        model = new FocusModel();
    }

    @Override
    public void FocusPresenter(String userId, String sessionId, String page, String count) {
        model.getFocusData(userId, sessionId, page, count, new FocusContract.IModel.FocusContractCallback() {
            @Override
            public void onFocusSuccess(FocusBean bean) {
                getView().onFocusSuccess(bean);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }
}
