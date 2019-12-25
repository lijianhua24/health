package com.wd.health.model;

import com.wd.health.bean.UserTaskListBean;
import com.wd.health.bean.user.FindUserSignBean;
import com.wd.health.bean.user.ReceiveReWardBean;
import com.wd.health.contract.UserTaskContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/16 9:48
 */
public class UserTaskListModel implements UserTaskContract.IModel {
    @Override
    public void getUserTaskContractData(String userId, String sessionId, UserTaskContractCallback userTaskContractCallback) {
        RetrofitManager.getInstance().create().getUserTaskList(userId, sessionId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<UserTaskListBean>() {
                    @Override
                    public void onNext(UserTaskListBean bean) {
                       userTaskContractCallback.onUserTaskSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                       userTaskContractCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void getFindUserSignContractData(String userId, String sessionId, UserTaskContractCallback userTaskContractCallback) {
        RetrofitManager.getInstance().create().FindUserSign(userId, sessionId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<FindUserSignBean>() {
                    @Override
                    public void onNext(FindUserSignBean bean) {
                        userTaskContractCallback.onFindUserSignSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        userTaskContractCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void getReceiveReWardData(String userId, String sessionId, String taskId, UserTaskContractCallback userTaskContractCallback) {
        RetrofitManager.getInstance().create().ReceiveReward(userId, sessionId,taskId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<ReceiveReWardBean>() {
                    @Override
                    public void onNext(ReceiveReWardBean bean) {
                        userTaskContractCallback.onReceiveReWardSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        userTaskContractCallback.onFailure(e);
                    }
                });
    }
}
