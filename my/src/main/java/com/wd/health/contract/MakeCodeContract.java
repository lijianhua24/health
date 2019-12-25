package com.wd.health.contract;

import com.wd.health.bean.InvitationCodeBean;
import com.wd.health.bean.UserInvitationCodeBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:04
 */
public interface MakeCodeContract {
    interface IView extends IBaseView {
        void onMakeCodeSuccess(InvitationCodeBean bean);
        void onUserCodeSuccess(UserInvitationCodeBean bean);
        void onFailure(Throwable e);
    }

    interface IModel{
        void getMakeCodeData(String userId, String sessionId, MakeCodeContractCallback makeCodeContractCallback);
        void getUserCodeData(String userId, String sessionId, MakeCodeContractCallback makeCodeContractCallback);
        interface MakeCodeContractCallback{
            void onMakeCodeSuccess(InvitationCodeBean bean);
            void onUserCodeSuccess(UserInvitationCodeBean bean);
            void onFailure(Throwable e);
        }
    }

    interface IPresenter{
        void MakeCodePresenter(String userId, String sessionId);
        void UserCodePresenter(String userId, String sessionId);
    }
}
