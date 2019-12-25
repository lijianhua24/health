package com.wd.health.contract;

import com.wd.health.bean.AddSignBean;
import com.wd.health.bean.user.DoTaskBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:04
 */
public interface AddSignContract {
    interface IView extends IBaseView {
        void onAddSignSuccess(AddSignBean bean);

        void onDoTaskSuccess(DoTaskBean bean);

        void onFailure(Throwable e);
    }

    interface IModel {
        void getAddSignData(String userId, String sessionId, AddSignContractCallback addSignContractCallback);

        void getDoTaskData(String userId, String sessionId, String taskId, AddSignContractCallback addSignContractCallback);

        interface AddSignContractCallback {
            void onAddSignSuccess(AddSignBean bean);

            void onDoTaskSuccess(DoTaskBean bean);

            void onFailure(Throwable e);
        }
    }

    interface IPresenter {
        void AddSignresenter(String userId, String sessionId);

        //做任务
        void getDoTaskPresenter(String userId, String sessionId, String taskId);
    }
}
