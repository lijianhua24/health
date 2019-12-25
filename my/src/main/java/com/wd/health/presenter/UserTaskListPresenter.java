package com.wd.health.presenter;

import com.wd.health.bean.UserTaskListBean;
import com.wd.health.bean.user.FindUserSignBean;
import com.wd.health.bean.user.ReceiveReWardBean;
import com.wd.health.contract.UserTaskContract;
import com.wd.health.model.UserTaskListModel;
import com.wd.mylibrary.Base.BasePresenter;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/16 9:49
 */
public class UserTaskListPresenter extends BasePresenter<UserTaskContract.IView> implements UserTaskContract.IPresenter {

    private UserTaskListModel model;

    @Override
    protected void initModel() {
        model = new UserTaskListModel();
    }

    @Override
    public void UserTaskPresenter(String userId, String sessionId) {
        model.getUserTaskContractData(userId, sessionId, new UserTaskContract.IModel.UserTaskContractCallback() {
            @Override
            public void onUserTaskSuccess(UserTaskListBean bean) {
                getView().onUserTaskSuccess(bean);
            }

            @Override
            public void onFindUserSignSuccess(FindUserSignBean bean) {

            }

            @Override
            public void onReceiveReWardSuccess(ReceiveReWardBean bean) {

            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void FindUserSignPresenter(String userId, String sessionId) {
        model.getFindUserSignContractData(userId, sessionId, new UserTaskContract.IModel.UserTaskContractCallback() {
            @Override
            public void onUserTaskSuccess(UserTaskListBean bean) {

            }

            @Override
            public void onFindUserSignSuccess(FindUserSignBean bean) {
                getView().onFindUserSignSuccess(bean);
            }

            @Override
            public void onReceiveReWardSuccess(ReceiveReWardBean bean) {

            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void ReceiveReWardPresenter(String userId, String sessionId, String taskId) {
            model.getReceiveReWardData(userId, sessionId, taskId, new UserTaskContract.IModel.UserTaskContractCallback() {
                @Override
                public void onUserTaskSuccess(UserTaskListBean bean) {

                }

                @Override
                public void onFindUserSignSuccess(FindUserSignBean bean) {

                }

                @Override
                public void onReceiveReWardSuccess(ReceiveReWardBean bean) {
                    getView().onReceiveReWardSuccess(bean);
                }

                @Override
                public void onFailure(Throwable e) {
                    getView().onFailure(e);
                }
            });
    }
}
