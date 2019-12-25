package com.wd.health.contract;

import com.wd.health.bean.FocusBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:04
 */
public interface FocusContract {
    interface IView extends IBaseView {
        void onFocusSuccess(FocusBean bean);
        void onFailure(Throwable e);
    }

    interface IModel{
        void getFocusData(String userId , String sessionId, String page ,String count,FocusContractCallback focusContractCallback);
        interface FocusContractCallback{
            void onFocusSuccess(FocusBean bean);
            void onFailure(Throwable e);
        }
    }

    interface IPresenter{
        void FocusPresenter(String userId , String sessionId, String page ,String count);
    }
}
