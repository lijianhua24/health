package com.wd.health.model;

import com.wd.health.bean.WxLogBean;
import com.wd.health.bean.user.LoginBean;
import com.wd.health.contract.LoginContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/12 20:29
 */
public class LoginModel implements LoginContract.IModel {
    @Override
    public void getLoginData(String email, String pwd, LoginContractCallback loginContractCallback) {
        RetrofitManager.getInstance().create().getLogin(email,pwd)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean bean) {
                       loginContractCallback.onLoginSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginContractCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void getWxloginData(String wxCode, IWxloginModelCallback iWxloginModelCallback) {
        RetrofitManager.getInstance().create().getWxlog(wxCode)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<WxLogBean>() {
                    @Override
                    public void onNext(WxLogBean bean) {
                        iWxloginModelCallback.onWxloginSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iWxloginModelCallback.onWxloginFailure(e);
                    }
                });
    }
}
