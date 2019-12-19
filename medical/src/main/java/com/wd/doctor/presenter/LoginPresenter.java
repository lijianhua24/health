package com.wd.doctor.presenter;

import com.wd.doctor.bean.ImageQueryBean;
import com.wd.doctor.bean.LoginBean;
import com.wd.doctor.contract.LoginContract;
import com.wd.doctor.model.LoginModel;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.app.Constant;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/13<p>
 * <p>更改时间：2019/12/13<p>
 */
public class LoginPresenter extends BasePresenter<LoginContract.iView>implements LoginContract.iPresenter {

    private LoginModel loginModel;

    @Override
    public void getLoginPresenter(String email, String pwd) {
        loginModel.getLoginData(email, pwd, new LoginContract.iModel.iLoginCallBack() {
            @Override
            public void onLoginSuccess(LoginBean loginBean) {
                if (isViewAttached()) {
                    if (loginBean != null && Constant.SUCCESS_CODE.equals(loginBean.getStatus())) {
                        getView().onLoginSuccess(loginBean);
                    }else {
                        getView().onLoginFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onLoginFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onLoginFailure(failure);
                }
            }

            @Override
            public void onImageQuerySuccess(ImageQueryBean imageQueryBean) {

            }

            @Override
            public void onImageQueryFailure(Throwable failure) {

            }
        });
    }

    @Override
    public void getImageQueryPresenter() {
        loginModel.getImageQueryData(new LoginContract.iModel.iLoginCallBack() {
            @Override
            public void onLoginSuccess(LoginBean loginBean) {

            }

            @Override
            public void onLoginFailure(Throwable failure) {

            }

            @Override
            public void onImageQuerySuccess(ImageQueryBean imageQueryBean) {
                if (isViewAttached()) {
                    if (imageQueryBean != null && Constant.SUCCESS_CODE.equals(imageQueryBean.getStatus())) {
                        getView().onImageQuerySuccess(imageQueryBean);
                    }else {
                        getView().onImageQueryFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onImageQueryFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onImageQueryFailure(failure);
                }
            }
        });
    }

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }
}
