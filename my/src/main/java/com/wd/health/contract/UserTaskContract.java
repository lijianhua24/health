package com.wd.health.contract;

import com.wd.health.bean.UserTaskListBean;
import com.wd.health.bean.user.FindUserSignBean;
import com.wd.health.bean.user.ReceiveReWardBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * @author: 张恩
 * @description: 我的任务
 * @date :2019/12/13 17:04
 */
public interface UserTaskContract {
    interface IView extends IBaseView {
        void onUserTaskSuccess(UserTaskListBean bean);

        void onFindUserSignSuccess(FindUserSignBean bean);

        void onReceiveReWardSuccess(ReceiveReWardBean bean);

        void onFailure(Throwable e);
    }

    interface IModel {
        void getUserTaskContractData(String userId, String sessionId, UserTaskContractCallback userTaskContractCallback);

        void getFindUserSignContractData(String userId, String sessionId, UserTaskContractCallback userTaskContractCallback);

        void getReceiveReWardData(String userId, String sessionId,String taskId, UserTaskContractCallback userTaskContractCallback);

        interface UserTaskContractCallback {
            void onUserTaskSuccess(UserTaskListBean bean);

            void onFindUserSignSuccess(FindUserSignBean bean);

            void onReceiveReWardSuccess(ReceiveReWardBean bean);

            void onFailure(Throwable e);
        }
    }

    interface IPresenter {
        void UserTaskPresenter(String userId, String sessionId);

        void FindUserSignPresenter(String userId, String sessionId);

        void ReceiveReWardPresenter(String userId, String sessionId,String taskId);
    }
}
