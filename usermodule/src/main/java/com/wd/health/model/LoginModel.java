package com.wd.health.model;

import com.wd.health.bean.LoginBean;
import com.wd.health.contract.LoginContract;
import com.wd.health.utils.ApiServers;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/13<p>
 * <p>更改时间：2019/12/13<p>
 */
public class LoginModel implements LoginContract.iModel {
    @Override
    public void getLoginData(String email, String pwd, iLoginCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getLogin(email,pwd)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        callBack.onLoginSuccess(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onLoginFailure(e);
                    }
                });
    }
}
