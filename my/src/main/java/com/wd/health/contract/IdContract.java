package com.wd.health.contract;

import com.wd.health.bean.JKZXBean;
import com.wd.health.bean.user.IdBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:04
 */
public interface IdContract {
    interface IView extends IBaseView {
        void onIdSuccess(IdBean bean);
        void onFailure(Throwable e);
    }

    interface IModel{
        void getIdData(String userId,  String sessionId, String Id,  String name, String sex,  String nation
                , String birthday, String address, String idNumber,  String issueOffice, IdContractCallback idContractCallback);
        interface IdContractCallback{
            void onIdSuccess(IdBean bean);
            void onFailure(Throwable e);
        }
    }

    interface IPresenter{
        void IdPresenter(String userId,  String sessionId, String Id,  String name, String sex,  String nation
                , String birthday, String address, String idNumber,  String issueOffice);
    }
}
