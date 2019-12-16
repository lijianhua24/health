package com.wd.health.presenter;

import com.wd.health.bean.PatientDetailsBean;
import com.wd.health.contract.IContractDetails;
import com.wd.health.model.PatientDetailsModel;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.Test.Logger;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/14<p>
 * <p>更改时间：2019/12/14<p>
 */
public class PatientDetailsPresenter extends BasePresenter<IContractDetails.iView> implements IContractDetails.iPresenter {


    private PatientDetailsModel model;

    @Override
    protected void initModel() {
        model = new PatientDetailsModel();
    }


    @Override
    public void getPatientDetailsPresenter(int userId, String sessionId, int sickCircleId) {
        model.getPatientDetails(userId, sessionId, sickCircleId, new IContractDetails.iModel.iPatientDetailsCallBack() {
            @Override
            public void PatientDetailssuccess(PatientDetailsBean patientDetailsBean) {

                getView().PatientDetailssuccess(patientDetailsBean);
            }

            @Override
            public void PatientDetailsFailure(Throwable e) {
                getView().PatientDetailsFailure(e);
            }
        });
    }
}
