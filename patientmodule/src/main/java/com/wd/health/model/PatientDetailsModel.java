package com.wd.health.model;
import com.wd.health.bean.CommentCircleBean;
import com.wd.health.bean.OpinionBean;
import com.wd.health.bean.PatientDetailsBean;
import com.wd.health.bean.QueryCommentBean;
import com.wd.health.contract.IContractDetails;
import com.wd.health.utils.ApiServers;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.Test.Logger;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;
import io.reactivex.schedulers.Schedulers;
public class PatientDetailsModel implements IContractDetails.iModel {
    @Override
    public void getCommentCircle(int userId, String sessionId, int sickCircleId, String content, iPatientDetailsCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .commentcirclebean(userId, sessionId, sickCircleId, content)
                .subscribeOn(Schedulers.io())
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<CommentCircleBean>() {
                    @Override
                    public void onNext(CommentCircleBean commentCircleBean) {
                        callBack.CommentCirclesuccess(commentCircleBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.CommentCircleFailure(e);
                    }
                });
    }

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
    public void getOpinionBean(int userId, String sessionId, int commentId, int opinion, iPatientDetailsCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .opinionbean(userId, sessionId, commentId, opinion)
                .subscribeOn(Schedulers.io())
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<OpinionBean>() {
                    @Override
                    public void onNext(OpinionBean opinionBean) {
                        callBack.OpinionBeansuccess(opinionBean);
                    }
                    @Override
                    public void onError(Throwable e) {
                        callBack.OpinionBeanFailure(e);
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
