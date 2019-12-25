package com.wd.health.presenter;

import com.wd.health.bean.UpdateUserPwdBean;
import com.wd.health.contract.UpdateUserPwdContract;
import com.wd.health.model.UpdateUserPwdModel;
import com.wd.mylibrary.Base.BasePresenter;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/17 20:28
 */
public class UpdateUserPwdPresenter extends BasePresenter<UpdateUserPwdContract.IView> implements UpdateUserPwdContract.IPresenter {

    private UpdateUserPwdModel model;

    @Override
    protected void initModel() {
        model = new UpdateUserPwdModel();
    }

    @Override
    public void UpdateUserPwdPresenter(String userId, String sessionId, String oldPwd, String newPwd) {
        model.getUpdateUserPwdData(userId, sessionId, oldPwd, newPwd, new UpdateUserPwdContract.IModel.UpdateUserPwdContractCallback() {
            @Override
            public void onUpdateUserPwdSuccess(UpdateUserPwdBean bean) {
                getView().onUpdateUserPwdSuccess(bean);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }
}
