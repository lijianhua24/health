package com.wd.doctor.contract;

import com.wd.doctor.bean.ImageQueryBean;
import com.wd.doctor.bean.LoginBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/13<p>
 * <p>更改时间：2019/12/13<p>
 */
public interface LoginContract {
    interface iView extends IBaseView{
        void onLoginSuccess(LoginBean loginBean);
        void onLoginFailure(Throwable e);

        //查询系统形象照
        void onImageQuerySuccess(ImageQueryBean imageQueryBean);
        void onImageQueryFailure(Throwable e);

    }
    interface iModel{
        void getLoginData(String email, String pwd, iLoginCallBack callBack);
        void getImageQueryData(iLoginCallBack callBack);
        interface iLoginCallBack{
            void onLoginSuccess(LoginBean loginBean);
            void onLoginFailure(Throwable failure);

            void onImageQuerySuccess(ImageQueryBean imageQueryBean);
            void onImageQueryFailure(Throwable failure);
        }

    }
    interface iPresenter{
        void getLoginPresenter(String email, String pwd);
        void getImageQueryPresenter();

    }
}
