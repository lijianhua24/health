package com.wd.doctor.model;

import com.wd.doctor.bean.WithdrawBean;
import com.wd.doctor.contract.WithdrawContract;
import com.wd.doctor.utils.ApiServers;
import com.wd.doctor.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/27<p>
 * <p>更改时间：2019/12/27<p>
 */
public class WithdrawModel implements WithdrawContract.iModel {
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
