package com.wd.doctor.presenter;

import com.wd.doctor.bean.DoctorWalletBean;
import com.wd.doctor.bean.QueryRevenueBean;
import com.wd.doctor.bean.WithdrawBean;
import com.wd.doctor.contract.DoctorWalletContract;
import com.wd.doctor.model.DoctorWalletModel;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.app.Constant;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/21<p>
 * <p>更改时间：2019/12/21<p>
 */
public class DoctorWalletPresenter extends BasePresenter<DoctorWalletContract.iView>implements DoctorWalletContract.iPresenter {

    private DoctorWalletModel doctorWalletModel;

    @Override
    public void getDoctorWalletPresenter(int doctorId, String sessionId) {
        doctorWalletModel.getDoctorWalletData(doctorId, sessionId, new DoctorWalletContract.iModel.iDoctorWalletCallBack() {
            @Override
            public void onDoctorWalletSuccess(DoctorWalletBean doctorWalletBean) {
                if (isViewAttached()) {
                    if (doctorWalletBean != null && Constant.SUCCESS_CODE.equals(doctorWalletBean.getStatus())) {
                        getView().onDoctorWalletSuccess(doctorWalletBean);
                    }else {
                        getView().onDoctorWalletFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onDoctorWalletFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onDoctorWalletFailure(failure);
                }
            }

            @Override
            public void onQueryRevenueSuccess(QueryRevenueBean queryRevenueBean) {

            }

            @Override
            public void onQueryRevenueFailure(Throwable failure) {

            }

            @Override
            public void onWithdrawSuccess(WithdrawBean withdrawBean) {

            }

            @Override
            public void onWithdrawFailure(Throwable failure) {

            }
        });
    }

    @Override
    public void getQueryRevenuePresenter(int doctorId, String sessionId, int page, int count) {
        doctorWalletModel.getQueryRevenueData(doctorId, sessionId, page, count, new DoctorWalletContract.iModel.iDoctorWalletCallBack() {
            @Override
            public void onDoctorWalletSuccess(DoctorWalletBean doctorWalletBean) {

            }

            @Override
            public void onDoctorWalletFailure(Throwable failure) {

            }

            @Override
            public void onQueryRevenueSuccess(QueryRevenueBean queryRevenueBean) {
                if (isViewAttached()) {
                    if (queryRevenueBean != null && Constant.SUCCESS_CODE.equals(queryRevenueBean.getStatus())) {
                        getView().onQueryRevenueSuccess(queryRevenueBean);
                    }else {
                        getView().onQueryRevenueFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onQueryRevenueFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onQueryRevenueFailure(failure);
                }
            }

            @Override
            public void onWithdrawSuccess(WithdrawBean withdrawBean) {

            }

            @Override
            public void onWithdrawFailure(Throwable failure) {

            }
        });
    }

    @Override
    public void getWithdrawPresenter(int doctorId, String sessionId, int money) {
        doctorWalletModel.getWithdrawData(doctorId, sessionId, money, new DoctorWalletContract.iModel.iDoctorWalletCallBack() {
            @Override
            public void onDoctorWalletSuccess(DoctorWalletBean doctorWalletBean) {

            }

            @Override
            public void onDoctorWalletFailure(Throwable failure) {

            }

            @Override
            public void onQueryRevenueSuccess(QueryRevenueBean queryRevenueBean) {

            }

            @Override
            public void onQueryRevenueFailure(Throwable failure) {

            }

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
        doctorWalletModel = new DoctorWalletModel();
    }
}
