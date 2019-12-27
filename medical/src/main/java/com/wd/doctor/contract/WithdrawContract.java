package com.wd.doctor.contract;

import com.wd.doctor.bean.WithdrawBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/27<p>
 * <p>更改时间：2019/12/27<p>
 */
public interface WithdrawContract {
    interface iView extends IBaseView {
        void onWithdrawSuccess(WithdrawBean withdrawBean);
        void onWithdrawFailure(Throwable e);
    }
    interface iModel{
        void getWithdrawData(int doctorId, String sessionId,int money,iDoctorWalletCallBack callBack);
        interface iDoctorWalletCallBack{
            void onWithdrawSuccess(WithdrawBean withdrawBean);
            void onWithdrawFailure(Throwable failure);
        }

    }
    interface iPresenter{
        void getWithdrawPresenter(int doctorId, String sessionId,int money);
    }
}
