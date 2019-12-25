package com.wd.health.model;

import com.wd.health.bean.UpdateUserPwdBean;
import com.wd.health.contract.UpdateUserPwdContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/17 20:26
 */
public class UpdateUserPwdModel implements UpdateUserPwdContract.IModel {
    @Override
    public void getUpdateUserPwdData(String userId, String sessionId, String oldPwd, String newPwd, UpdateUserPwdContractCallback updateUserPwdContractCallback) {
        RetrofitManager.getInstance().create().UpdateUserPwd(userId, sessionId,oldPwd,newPwd)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<UpdateUserPwdBean>() {
                    @Override
                    public void onNext(UpdateUserPwdBean bean) {
                      updateUserPwdContractCallback.onUpdateUserPwdSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                       updateUserPwdContractCallback.onFailure(e);
                    }
                });
    }
}
