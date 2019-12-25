package com.wd.health.presenter;

import com.wd.health.bean.InvitationCodeBean;
import com.wd.health.bean.UserInvitationCodeBean;
import com.wd.health.contract.MakeCodeContract;
import com.wd.health.model.MakeCodeModel;
import com.wd.mylibrary.Base.BasePresenter;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/16 20:02
 */
public class MakeCodePresenter extends BasePresenter<MakeCodeContract.IView> implements MakeCodeContract.IPresenter {

    private MakeCodeModel model;

    @Override
    protected void initModel() {
        model = new MakeCodeModel();
    }

    @Override
    public void MakeCodePresenter(String userId, String sessionId) {
        model.getMakeCodeData(userId, sessionId, new MakeCodeContract.IModel.MakeCodeContractCallback() {
            @Override
            public void onMakeCodeSuccess(InvitationCodeBean bean) {
                getView().onMakeCodeSuccess(bean);
            }

            @Override
            public void onUserCodeSuccess(UserInvitationCodeBean bean) {

            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void UserCodePresenter(String userId, String sessionId) {
        model.getUserCodeData(userId, sessionId, new MakeCodeContract.IModel.MakeCodeContractCallback() {
            @Override
            public void onMakeCodeSuccess(InvitationCodeBean bean) {

            }

            @Override
            public void onUserCodeSuccess(UserInvitationCodeBean bean) {
                getView().onUserCodeSuccess(bean);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }
}
