package com.wd.health.contract;

import com.wd.health.bean.UpdateUserPwdBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:04
 */
public interface UpdateUserPwdContract {
    interface IView extends IBaseView {
        void onUpdateUserPwdSuccess( UpdateUserPwdBean bean);
        void onFailure(Throwable e);
    }

    interface IModel{
        void getUpdateUserPwdData(String userId, String sessionId,String oldPwd,String newPwd, UpdateUserPwdContractCallback updateUserPwdContractCallback);
        interface UpdateUserPwdContractCallback{
            void onUpdateUserPwdSuccess( UpdateUserPwdBean bean);
            void onFailure(Throwable e);
        }
    }

    interface IPresenter{
        void  UpdateUserPwdPresenter(String userId, String sessionId,String oldPwd,String newPwd);
    }
}
