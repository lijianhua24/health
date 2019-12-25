package com.wd.health.presenter;

import com.wd.health.bean.CheckCodeBean;
import com.wd.health.bean.RegBean;
import com.wd.health.bean.ResetUserPwdBean;
import com.wd.health.bean.user.EmailBean;
import com.wd.health.contract.RegisterContract;
import com.wd.health.model.RegModel;
import com.wd.mylibrary.Base.BasePresenter;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 9:45
 */
public class RegPresenter extends BasePresenter<RegisterContract.IView> implements RegisterContract.IPresenter {

    private RegModel model;

    @Override
    protected void initModel() {
        model = new RegModel();
    }

    @Override
    public void EmailPresenter(String email) {
        model.getEmailData(email, new RegisterContract.IModel.RegisterContractCallback() {
            @Override
            public void onEmailSuccess(EmailBean bean) {
                getView().onEmailSuccess(bean);
            }

            @Override
            public void onRegSuccess(RegBean bean) {

            }

            @Override
            public void onResetUserPwd(ResetUserPwdBean bean) {

            }

            @Override
            public void onCheckCodeSuccess(CheckCodeBean bean) {

            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void RegPresenter(String email, String code, String pwd1, String pwd2, String invitationCode) {
        model.getRegData(email, code, pwd1, pwd2, invitationCode, new RegisterContract.IModel.RegisterContractCallback() {
            @Override
            public void onEmailSuccess(EmailBean bean) {

            }

            @Override
            public void onRegSuccess(RegBean bean) {
                getView().onRegSuccess(bean);
            }

            @Override
            public void onResetUserPwd(ResetUserPwdBean bean) {

            }

            @Override
            public void onCheckCodeSuccess(CheckCodeBean bean) {

            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void getResatUserPwdPresenter(String email, String pwd1, String pwd2) {
        model.getResatUserPwd(email, pwd1, pwd2, new RegisterContract.IModel.RegisterContractCallback() {
            @Override
            public void onEmailSuccess(EmailBean bean) {

            }

            @Override
            public void onRegSuccess(RegBean bean) {

            }

            @Override
            public void onResetUserPwd(ResetUserPwdBean bean) {
                getView().onResetUserPwd(bean);
            }

            @Override
            public void onCheckCodeSuccess(CheckCodeBean bean) {

            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void getCheckCodePresenter(String email, String code) {
        model.getCheckCode(email, code, new RegisterContract.IModel.RegisterContractCallback() {
            @Override
            public void onEmailSuccess(EmailBean bean) {

            }

            @Override
            public void onRegSuccess(RegBean bean) {

            }

            @Override
            public void onResetUserPwd(ResetUserPwdBean bean) {

            }

            @Override
            public void onCheckCodeSuccess(CheckCodeBean bean) {
                getView().onCheckCodeSuccess(bean);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }
}
