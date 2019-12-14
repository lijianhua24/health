package com.wd.health.contract;

import com.wd.health.bean.SendEmilBean;
import com.wd.health.bean.RegisteredBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/13<p>
 * <p>更改时间：2019/12/13<p>
 */
public interface RegisteredContract {
    interface iView extends IBaseView {
        //注册 成功/失败
        void onRegisteredSuccess(RegisteredBean registeredBean);
        void onRegisteredFailure(Throwable e);
        //发送邮箱验证码 成功/失败
        void onSendEmilSuccess(SendEmilBean sendEmilBean);
        void onSendEmilFailure(Throwable e);
    }
    interface iModel{
        void getRegisteredData(String email,String code,String pwd1,String pwd2,String invitationCode,
                               iRegisteredCallBack callBack);
        void getSendEmilData(String email,iRegisteredCallBack callBack);
        interface iRegisteredCallBack{
            void onRegisteredSuccess(RegisteredBean RegisteredBean);
            void onRegisteredFailure(Throwable failure);

            void onSendEmilSuccess(SendEmilBean sendEmilBean);
            void onSendEmilFailure(Throwable failure);
            
        }

    }
    interface iPresenter{
        void getRegisteredPresenter(String email,String code,String pwd1,String pwd2,String invitationCode);
        void getSendEmilPresenter(String email);

    }
}
