package com.wd.health.contract;

import com.wd.health.bean.CheckCodeBean;
import com.wd.health.bean.RegBean;
import com.wd.health.bean.ResetUserPwdBean;
import com.wd.health.bean.user.EmailBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 9:17
 */
public interface RegisterContract {
    interface IView extends IBaseView {
        void onEmailSuccess(EmailBean bean);

        void onRegSuccess(RegBean bean);

        void onResetUserPwd(ResetUserPwdBean bean);

        void onCheckCodeSuccess(CheckCodeBean bean);

        void onFailure(Throwable e);
    }

    interface IModel {
        //发送验证码
        void getEmailData(String email, RegisterContractCallback registerContractCallback);
        //注册
        void getRegData(String email, String code, String pwd1
                , String pwd2, String invitationCode, RegisterContractCallback registerContractCallback);
        //重置密码
        void getResatUserPwd(String email,String pwd1,String pwd2,RegisterContractCallback registerContractCallback);
        //校验验证码
        void getCheckCode(String email,String code,RegisterContractCallback registerContractCallback);

        interface RegisterContractCallback {
            void onEmailSuccess(EmailBean bean);

            void onRegSuccess(RegBean bean);

            void onResetUserPwd(ResetUserPwdBean bean);

            void onCheckCodeSuccess(CheckCodeBean bean);

            void onFailure(Throwable e);
        }
    }

    interface IPresenter {
        void EmailPresenter(String email);
        void RegPresenter(String email, String code, String pwd1, String pwd2, String invitationCode);

        void getResatUserPwdPresenter(String email,String pwd1,String pwd2);

        void getCheckCodePresenter(String email,String code);
    }
}
