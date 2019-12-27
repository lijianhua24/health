package com.wd.doctor.contract;

import com.wd.doctor.bean.DoctorWalletBean;
import com.wd.doctor.bean.WithdrawBean;
import com.wd.doctor.bean.QueryRevenueBean;
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

        void onQueryRevenueSuccess(QueryRevenueBean queryRevenueBean);
        void onQueryRevenueFailure(Throwable e);

    }
    interface iModel{
        void getDoctorWalletData(int doctorId,String sessionId,iDoctorWalletCallBack callBack);
        void getQueryRevenueData(int doctorId, String sessionId, int page,int count,iDoctorWalletCallBack callBack);
        interface iDoctorWalletCallBack{
            void onDoctorWalletSuccess(DoctorWalletBean doctorWalletBean);
            void onDoctorWalletFailure(Throwable failure);

            void onQueryRevenueSuccess(QueryRevenueBean queryRevenueBean);
            void onQueryRevenueFailure(Throwable failure);

        }

    }
    interface iPresenter{
        void getDoctorWalletPresenter(int doctorId,String sessionId);
        void getQueryRevenuePresenter(int doctorId, String sessionId,int page,int count);

    }
}
