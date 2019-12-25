package com.wd.health.contract;

import com.wd.health.bean.PerfectUserInfoBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:04
 */
public interface PerfectUserInfoContract {
    interface IView extends IBaseView {
        void onPerfectUserInfoSuccess(PerfectUserInfoBean bean);
        void onFailure(Throwable e);
    }

    interface IModel{
        void getPerfectUserInfoData(String userId, String sessionId,String age, String height, String weight ,PerfectUserInfoContractCallback perfectUserInfoContractCallback);
        interface PerfectUserInfoContractCallback{
            void onPerfectUserInfoSuccess(PerfectUserInfoBean bean);
            void onFailure(Throwable e);
        }
    }

    interface IPresenter{
        void PerfectUserInfoPresenter(String userId, String sessionId,String age, String height, String weight);
    }
}
