package com.wd.doctor.presenter;

import com.wd.doctor.bean.DoctorWalletBean;
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
        });
    }

    @Override
    protected void initModel() {
        doctorWalletModel = new DoctorWalletModel();
    }
}
