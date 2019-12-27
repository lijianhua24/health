package com.wd.doctor.model;

import com.wd.doctor.bean.DoctorWalletBean;
import com.wd.doctor.bean.QueryRevenueBean;
import com.wd.doctor.bean.WithdrawBean;
import com.wd.doctor.contract.DoctorWalletContract;
import com.wd.doctor.utils.ApiServers;
import com.wd.doctor.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/21<p>
 * <p>更改时间：2019/12/21<p>
 */
public class DoctorWalletModel implements DoctorWalletContract.iModel {
    @Override
    public void getDoctorWalletData(int doctorId, String sessionId, iDoctorWalletCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getDoctorWallet(doctorId,sessionId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<DoctorWalletBean>() {
                    @Override
                    public void onNext(DoctorWalletBean doctorWalletBean) {
                        callBack.onDoctorWalletSuccess(doctorWalletBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onDoctorWalletFailure(e);
                    }
                });
    }

    @Override
    public void getQueryRevenueData(int doctorId, String sessionId, int page, int count, iDoctorWalletCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getQueryRevenue(doctorId,sessionId,page,count)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<QueryRevenueBean>() {
                    @Override
                    public void onNext(QueryRevenueBean queryRevenueBean) {
                        callBack.onQueryRevenueSuccess(queryRevenueBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onQueryRevenueFailure(e);
                    }
                });
    }

    @Override
    public void getWithdrawData(int doctorId, String sessionId, int money, iDoctorWalletCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getWithdraw(doctorId,sessionId,money)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<WithdrawBean>() {
                    @Override
                    public void onNext(WithdrawBean withdrawBean) {
                        callBack.onWithdrawSuccess(withdrawBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onWithdrawFailure(e);
                    }
                });
    }
}