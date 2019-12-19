package com.wd.doctor.model;

import com.wd.doctor.bean.DoctorInforBean;
import com.wd.doctor.contract.DoctorInforContract;
import com.wd.doctor.utils.ApiServers;
import com.wd.doctor.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/16<p>
 * <p>更改时间：2019/12/16<p>
 */
public class DoctorInforModel implements DoctorInforContract.iModel {
    @Override
    public void getDoctorInforData(int doctorId, String sessionId, iDoctorInforCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getDoctorInfor(doctorId,sessionId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<DoctorInforBean>() {
                    @Override
                    public void onNext(DoctorInforBean doctorInforBean) {
                        callBack.onDoctorInforSuccess(doctorInforBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onDoctorInforFailure(e);
                    }
                });
    }
}
