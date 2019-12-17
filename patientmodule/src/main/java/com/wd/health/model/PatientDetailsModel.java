package com.wd.health.model;

import com.wd.health.bean.PatientDetailsBean;
import com.wd.health.bean.QueryCommentBean;
import com.wd.health.contract.IContractDetails;
import com.wd.health.utils.ApiServers;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.Test.Logger;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/14<p>
 * <p>更改时间：2019/12/14<p>
 */
public class PatientDetailsModel implements IContractDetails.iModel {
    @Override
    public void getPatientDetails(int userId, String sessionId, int sickCircleId, iPatientDetailsCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .atientdetailsbean(userId, sessionId, sickCircleId)
                .subscribeOn(Schedulers.io())
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<PatientDetailsBean>() {
                    @Override
                    public void onNext(PatientDetailsBean patientDetailsBean) {
                        Logger.d("CCCCCC", "" + patientDetailsBean);
                        callBack.PatientDetailssuccess(patientDetailsBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.PatientDetailsFailure(e);
                    }
                });
    }

    @Override
    public void getQueryComment(int userId, String sessionId, int sickCircleId, int page, int count, iPatientDetailsCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .querycommentbean(userId, sessionId, sickCircleId, page, count)
                .subscribeOn(Schedulers.io())
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<QueryCommentBean>() {
                    @Override
                    public void onNext(QueryCommentBean queryCommentBean) {
                        callBack.QueryCommentsuccess(queryCommentBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.QueryCommentFailure(e);
                    }
                });
    }
}
