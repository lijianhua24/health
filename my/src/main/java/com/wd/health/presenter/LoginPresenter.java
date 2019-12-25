package com.wd.health.presenter;

import com.wd.health.bean.WxLogBean;
import com.wd.health.bean.user.LoginBean;
import com.wd.health.contract.LoginContract;
import com.wd.health.model.LoginModel;
import com.wd.mylibrary.Base.BasePresenter;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/12 20:30
 */
public class LoginPresenter extends BasePresenter<LoginContract.IView> implements LoginContract.IPresenter  {

    private LoginModel model;

    @Override
    protected void initModel() {
        model = new LoginModel();
    }

    @Override
    public void LoginPresenter(String email, String pwd) {
        model.getLoginData(email, pwd, new LoginContract.IModel.LoginContractCallback() {
            @Override
            public void onLoginSuccess(LoginBean bean) {
                getView().onLoginSuccess(bean);
            }


            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void getWxloginPresenter(String wxCode) {
            model.getWxloginData(wxCode, new LoginContract.IModel.IWxloginModelCallback() {
                @Override
                public void onWxloginSuccess(WxLogBean bean) {
                    getView().onWxloginSuccess(bean);
                }

                @Override
                public void onWxloginFailure(Throwable e) {
                    getView().onFailure(e);
                }
            });
    }
}
