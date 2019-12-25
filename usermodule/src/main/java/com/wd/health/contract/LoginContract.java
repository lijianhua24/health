package com.wd.health.contract;

import com.wd.health.bean.LoginBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/13<p>
 * <p>更改时间：2019/12/13<p>
 */
public interface LoginContract {
    interface iView extends IBaseView{
        void onLoginSuccess(LoginBean loginBean) throws Exception;
        void onLoginFailure(Throwable e);
    }
    interface iModel{
        void getLoginData(String email,String pwd,iLoginCallBack callBack);
        interface iLoginCallBack{
            void onLoginSuccess(LoginBean loginBean) throws Exception;
            void onLoginFailure(Throwable failure);
        }

    }
    interface iPresenter{
        void getLoginPresenter(String email,String pwd);
    }
}
