package com.wd.health.model;

import com.wd.health.bean.RegisteredBean;
import com.wd.health.bean.SendEmilBean;
import com.wd.health.contract.RegisteredContract;
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
public class RegisteredModel implements RegisteredContract.iModel {
    @Override
    public void getRegisteredData(String email, String code, String pwd1, String pwd2, String invitationCode, iRegisteredCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getRegister(email,code,pwd1,pwd2,invitationCode)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<RegisteredBean>() {
                    @Override
                    public void onNext(RegisteredBean registeredBean) {
                        callBack.onRegisteredSuccess(registeredBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onRegisteredFailure(e);
                    }
                });
    }

    @Override
    public void getSendEmilData(String email, iRegisteredCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getSendEmil(email)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<SendEmilBean>() {
                    @Override
                    public void onNext(SendEmilBean sendEmilBean) {
                        callBack.onSendEmilSuccess(sendEmilBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onSendEmilFailure(e);
                    }
                });
    }
}
