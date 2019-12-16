package com.wd.health.contract;

import com.wd.health.bean.CircleListShowBean;
import com.wd.health.bean.PatientDetailsBean;
import com.wd.health.bean.KeywordSearchBean;
import com.wd.health.bean.PatientDetailsBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/13<p>
 * <p>更改时间：2019/12/13<p>
 */
public interface IContractDetails extends IBaseView {
    interface iView extends IBaseView {
        void PatientDetailssuccess(PatientDetailsBean patientDetailsBean);
        void PatientDetailsFailure(Throwable e);
    }

    interface iModel{
        void getPatientDetails(int userId, String sessionId, int sickCircleId,iPatientDetailsCallBack callBack);
        interface iPatientDetailsCallBack{
            void PatientDetailssuccess(PatientDetailsBean PatientDetailsBean);
            void PatientDetailsFailure(Throwable e);
        }
    }
    interface iPresenter{
        void getPatientDetailsPresenter(int userId, String sessionId, int sickCircleId);
    }
}
