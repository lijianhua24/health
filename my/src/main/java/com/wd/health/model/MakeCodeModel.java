package com.wd.health.model;

import com.wd.health.bean.InvitationCodeBean;
import com.wd.health.bean.UserInvitationCodeBean;
import com.wd.health.contract.MakeCodeContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/16 20:00
 */
public class MakeCodeModel implements MakeCodeContract.IModel {
    @Override
    public void getMakeCodeData(String userId, String sessionId, MakeCodeContractCallback makeCodeContractCallback) {
        RetrofitManager.getInstance().create().getMakeCode(userId, sessionId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<InvitationCodeBean>() {
                    @Override
                    public void onNext(InvitationCodeBean bean) {
                       makeCodeContractCallback.onMakeCodeSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        makeCodeContractCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void getUserCodeData(String userId, String sessionId, MakeCodeContractCallback makeCodeContractCallback) {
        RetrofitManager.getInstance().create().getUserCode(userId, sessionId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<UserInvitationCodeBean>() {
                    @Override
                    public void onNext(UserInvitationCodeBean bean) {
                        makeCodeContractCallback.onUserCodeSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        makeCodeContractCallback.onFailure(e);
                    }
                });
    }
}
