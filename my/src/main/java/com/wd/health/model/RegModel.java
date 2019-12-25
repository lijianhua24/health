package com.wd.health.model;

import com.wd.health.bean.CheckCodeBean;
import com.wd.health.bean.RegBean;
import com.wd.health.bean.ResetUserPwdBean;
import com.wd.health.bean.user.EmailBean;
import com.wd.health.contract.RegisterContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/12 20:29
 */
public class RegModel implements RegisterContract.IModel {
    @Override
    public void getEmailData(String email, RegisterContractCallback registerContractCallback) {
        RetrofitManager.getInstance().create().getEmail(email)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<EmailBean>() {
                    @Override
                    public void onNext(EmailBean bean) {
                        registerContractCallback.onEmailSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        registerContractCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void getRegData(String email, String code, String pwd1, String pwd2, String invitationCode, RegisterContractCallback registerContractCallback) {
        RetrofitManager.getInstance().create().getReg(email,code,pwd1,pwd2,invitationCode)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<RegBean>() {
                    @Override
                    public void onNext(RegBean bean) {
                        registerContractCallback.onRegSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        registerContractCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void getResatUserPwd(String email, String pwd1, String pwd2, RegisterContractCallback registerContractCallback) {
        RetrofitManager.getInstance().create().resetUserPwd(email,pwd1,pwd2)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<ResetUserPwdBean>() {
                    @Override
                    public void onNext(ResetUserPwdBean bean) {
                        registerContractCallback.onResetUserPwd(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        registerContractCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void getCheckCode(String email, String code, RegisterContractCallback registerContractCallback) {
        RetrofitManager.getInstance().create().getCheckCode(email,code)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<CheckCodeBean>() {
                    @Override
                    public void onNext(CheckCodeBean bean) {
                        registerContractCallback.onCheckCodeSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        registerContractCallback.onFailure(e);
                    }
                });
    }
}
