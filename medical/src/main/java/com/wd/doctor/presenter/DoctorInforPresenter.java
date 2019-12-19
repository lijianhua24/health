package com.wd.doctor.presenter;

import com.wd.doctor.bean.DoctorInforBean;
import com.wd.doctor.contract.DoctorInforContract;
import com.wd.doctor.model.DoctorInforModel;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.app.Constant;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/16<p>
 * <p>更改时间：2019/12/16<p>
 */
public class DoctorInforPresenter extends BasePresenter<DoctorInforContract.iView>implements DoctorInforContract.iPresenter {

    private DoctorInforModel doctorInforModel;

    @Override
    public void getDoctorInforPresenter(int doctorId, String sessionId) {
        doctorInforModel.getDoctorInforData(doctorId, sessionId, new DoctorInforContract.iModel.iDoctorInforCallBack() {
            @Override
            public void onDoctorInforSuccess(DoctorInforBean doctorInforBean) {
                if (isViewAttached()) {
                    if (doctorInforBean != null && Constant.SUCCESS_CODE.equals(doctorInforBean.getStatus())) {
                        getView().onDoctorInforSuccess(doctorInforBean);
                    }else {
                        getView().onDoctorInforFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onDoctorInforFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onDoctorInforFailure(failure);
                }
            }
        });
    }

    @Override
    protected void initModel() {
        doctorInforModel = new DoctorInforModel();
    }
}
