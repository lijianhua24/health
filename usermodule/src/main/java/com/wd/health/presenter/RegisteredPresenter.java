package com.wd.health.presenter;

import com.wd.health.bean.RegisteredBean;
import com.wd.health.bean.SendEmilBean;
import com.wd.health.contract.RegisteredContract;
import com.wd.health.model.RegisteredModel;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.app.Constant;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/13<p>
 * <p>更改时间：2019/12/13<p>
 */
public class RegisteredPresenter extends BasePresenter<RegisteredContract.iView>implements RegisteredContract.iPresenter {

    private RegisteredModel registeredModel;

    @Override
    public void getRegisteredPresenter(String email, String code, String pwd1, String pwd2, String invitationCode) {
        registeredModel.getRegisteredData(email, code, pwd1, pwd2, invitationCode, new RegisteredContract.iModel.iRegisteredCallBack() {
            @Override
            public void onRegisteredSuccess(RegisteredBean RegisteredBean) {
                if (isViewAttached()) {
                    if (RegisteredBean != null && Constant.SUCCESS_CODE.equals(RegisteredBean.getStatus())) {
                        getView().onRegisteredSuccess(RegisteredBean);
                    }else {
                        getView().onRegisteredFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onRegisteredFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onRegisteredFailure(failure);
                }
            }

            @Override
            public void onSendEmilSuccess(SendEmilBean sendEmilBean) {

            }

            @Override
            public void onSendEmilFailure(Throwable failure) {

            }
        });
    }

    @Override
    public void getSendEmilPresenter(String email) {
        registeredModel.getSendEmilData(email, new RegisteredContract.iModel.iRegisteredCallBack() {
            @Override
            public void onRegisteredSuccess(RegisteredBean RegisteredBean) {

            }

            @Override
            public void onRegisteredFailure(Throwable failure) {

            }

            @Override
            public void onSendEmilSuccess(SendEmilBean sendEmilBean) {
                if (isViewAttached()) {
                    if (sendEmilBean != null && Constant.SUCCESS_CODE.equals(sendEmilBean.getStatus())) {
                        getView().onSendEmilSuccess(sendEmilBean);
                    }else {
                        getView().onSendEmilFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onSendEmilFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onSendEmilFailure(failure);
                }
            }
        });
    }

    @Override
    protected void initModel() {
        registeredModel = new RegisteredModel();
    }
}
