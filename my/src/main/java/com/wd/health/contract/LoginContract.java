package com.wd.health.contract;


import com.wd.health.bean.WxLogBean;
import com.wd.health.bean.user.LoginBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/12 20:26
 */
public interface LoginContract {
    interface IView extends IBaseView {
        void onLoginSuccess(LoginBean bean);


        void onWxloginSuccess(WxLogBean bean);
        void onFailure(Throwable e);
    }

    interface IModel{
        void getLoginData(String email,String pwd,LoginContractCallback loginContractCallback);
        interface LoginContractCallback{
            void onLoginSuccess(LoginBean bean);
            void onFailure(Throwable e);
        }

        void getWxloginData(String wxCode, IWxloginModelCallback iWxloginModelCallback) ;
        interface IWxloginModelCallback{
            void onWxloginSuccess(WxLogBean bean);
            void onWxloginFailure(Throwable e);
        }
    }

    interface IPresenter{
        void LoginPresenter(String email,String pwd);

      void getWxloginPresenter(String wxCode);
    }
}
