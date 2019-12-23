package com.wd.doctor.contract;

import com.wd.doctor.bean.DoctorWalletBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/21<p>
 * <p>更改时间：2019/12/21<p>
 */
public interface DoctorWalletContract {
    interface iView extends IBaseView {
        void onDoctorWalletSuccess(DoctorWalletBean doctorWalletBean);
        void onDoctorWalletFailure(Throwable e);

    }
    interface iModel{
        void getDoctorWalletData(int doctorId,String sessionId,iDoctorWalletCallBack callBack);
        interface iDoctorWalletCallBack{
            void onDoctorWalletSuccess(DoctorWalletBean doctorWalletBean);
            void onDoctorWalletFailure(Throwable failure);

        }

    }
    interface iPresenter{
        void getDoctorWalletPresenter(int doctorId,String sessionId);

    }
}
