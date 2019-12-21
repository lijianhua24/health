package com.wd.health.presenter;

import com.wd.health.bean.CommentCircleBean;
import com.wd.health.bean.OpinionBean;
import com.wd.health.bean.PatientDetailsBean;
import com.wd.health.bean.QueryCommentBean;
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

            @Override
            public void QueryCommentsuccess(QueryCommentBean queryCommentBean) {

            }

            @Override
            public void QueryCommentFailure(Throwable e) {

            }

            @Override
            public void CommentCirclesuccess(CommentCircleBean commentCircleBean) {

            }

            @Override
            public void CommentCircleFailure(Throwable e) {

            }

            @Override
            public void OpinionBeansuccess(OpinionBean opinionBean) {

            }

            @Override
            public void OpinionBeanFailure(Throwable e) {

            }
        });
    }

    @Override
    public void getQueryCommentPresenter(int userId, String sessionId, int sickCircleId, int page, int count) {
        model.getQueryComment(userId, sessionId, sickCircleId, page, count, new IContractDetails.iModel.iPatientDetailsCallBack() {
            @Override
            public void PatientDetailssuccess(PatientDetailsBean PatientDetailsBean) {

            }

            @Override
            public void PatientDetailsFailure(Throwable e) {

            }

            @Override
            public void QueryCommentsuccess(QueryCommentBean queryCommentBean) {
                getView().QueryCommentsuccess(queryCommentBean);
            }

            @Override
            public void QueryCommentFailure(Throwable e) {
                getView().QueryCommentFailure(e);
            }

            @Override
            public void CommentCirclesuccess(CommentCircleBean commentCircleBean) {

            }

            @Override
            public void CommentCircleFailure(Throwable e) {

            }

            @Override
            public void OpinionBeansuccess(OpinionBean opinionBean) {

            }

            @Override
            public void OpinionBeanFailure(Throwable e) {

            }
        });
    }

    @Override
    public void getCommentCircle(int userId, String sessionId, int sickCircleId, String content) {
        model.getCommentCircle(userId, sessionId, sickCircleId, content, new IContractDetails.iModel.iPatientDetailsCallBack() {
            @Override
            public void PatientDetailssuccess(PatientDetailsBean PatientDetailsBean) {

            }

            @Override
            public void PatientDetailsFailure(Throwable e) {

            }

            @Override
            public void QueryCommentsuccess(QueryCommentBean queryCommentBean) {

            }

            @Override
            public void QueryCommentFailure(Throwable e) {

            }

            @Override
            public void CommentCirclesuccess(CommentCircleBean commentCircleBean) {
                getView().CommentCirclesuccess(commentCircleBean);
            }

            @Override
            public void CommentCircleFailure(Throwable e) {
                getView().CommentCircleFailure(e);
            }

            @Override
            public void OpinionBeansuccess(OpinionBean opinionBean) {

            }

            @Override
            public void OpinionBeanFailure(Throwable e) {

            }
        });
    }

    @Override
    public void getOpinionBean(int userId, String sessionId, int commentId, int opinion) {
        model.getOpinionBean(userId, sessionId, commentId, opinion, new IContractDetails.iModel.iPatientDetailsCallBack() {
            @Override
            public void PatientDetailssuccess(PatientDetailsBean PatientDetailsBean) {

            }

            @Override
            public void PatientDetailsFailure(Throwable e) {

            }

            @Override
            public void QueryCommentsuccess(QueryCommentBean queryCommentBean) {

            }

            @Override
            public void QueryCommentFailure(Throwable e) {

            }

            @Override
            public void CommentCirclesuccess(CommentCircleBean commentCircleBean) {

            }

            @Override
            public void CommentCircleFailure(Throwable e) {

            }

            @Override
            public void OpinionBeansuccess(OpinionBean opinionBean) {
                getView().OpinionBeansuccess(opinionBean);
            }

            @Override
            public void OpinionBeanFailure(Throwable e) {
                getView().OpinionBeanFailure(e);
            }
        });
    }
}
