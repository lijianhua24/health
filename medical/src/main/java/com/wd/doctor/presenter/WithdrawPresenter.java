package com.wd.doctor.presenter;

import com.wd.doctor.bean.WithdrawBean;
import com.wd.doctor.contract.WithdrawContract;
import com.wd.doctor.model.WithdrawModel;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.app.Constant;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/27<p>
 * <p>更改时间：2019/12/27<p>
 */
public class WithdrawPresenter extends BasePresenter<WithdrawContract.iView>implements WithdrawContract.iPresenter {

    private WithdrawModel withdrawModel;

    @Override
    public void getWithdrawPresenter(int doctorId, String sessionId, int money) {
        withdrawModel.getWithdrawData(doctorId, sessionId, money, new WithdrawContract.iModel.iDoctorWalletCallBack() {
            @Override
            public void onWithdrawSuccess(WithdrawBean withdrawBean) {
                if (isViewAttached()) {
                    if (withdrawBean != null && Constant.SUCCESS_CODE.equals(withdrawBean.getStatus())) {
                        getView().onWithdrawSuccess(withdrawBean);
                    }else {
                        getView().onWithdrawFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onWithdrawFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onWithdrawFailure(failure);
                }
            }
        });
    }

    @Override
    protected void initModel() {
        withdrawModel = new WithdrawModel();
    }
}
