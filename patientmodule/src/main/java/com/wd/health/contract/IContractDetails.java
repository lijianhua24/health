package com.wd.health.contract;

import com.wd.health.bean.CircleListShowBean;
import com.wd.health.bean.CommentCircleBean;
import com.wd.health.bean.OpinionBean;
import com.wd.health.bean.PatientDetailsBean;
import com.wd.health.bean.KeywordSearchBean;
import com.wd.health.bean.PatientDetailsBean;
import com.wd.health.bean.QueryCommentBean;
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

        void QueryCommentsuccess(QueryCommentBean queryCommentBean);
        void QueryCommentFailure(Throwable e);

        void CommentCirclesuccess(CommentCircleBean commentCircleBean);
        void CommentCircleFailure(Throwable e);

        void OpinionBeansuccess(OpinionBean opinionBean);
        void OpinionBeanFailure(Throwable e);
    }

    interface iModel{
        void getCommentCircle(int userId, String sessionId, int sickCircleId,String content,iPatientDetailsCallBack callBack);
        void getPatientDetails(int userId, String sessionId, int sickCircleId,iPatientDetailsCallBack callBack);
        void getOpinionBean(int userId, String sessionId, int commentId,int opinion,iPatientDetailsCallBack callBack);
        void getQueryComment(int userId, String sessionId, int sickCircleId,int page,int count,iPatientDetailsCallBack callBack);
        interface iPatientDetailsCallBack{
            void PatientDetailssuccess(PatientDetailsBean PatientDetailsBean);
            void PatientDetailsFailure(Throwable e);

            void QueryCommentsuccess(QueryCommentBean queryCommentBean);
            void QueryCommentFailure(Throwable e);

            void CommentCirclesuccess(CommentCircleBean commentCircleBean);
            void CommentCircleFailure(Throwable e);

            void OpinionBeansuccess(OpinionBean opinionBean);
            void OpinionBeanFailure(Throwable e);

        }
    }
    interface iPresenter{
        void getPatientDetailsPresenter(int userId, String sessionId, int sickCircleId);
        void getQueryCommentPresenter(int userId, String sessionId, int sickCircleId,int page,int count);
        void getCommentCircle(int userId, String sessionId, int sickCircleId,String content);
        void getOpinionBean(int userId, String sessionId, int commentId,int opinion);
    }
}
