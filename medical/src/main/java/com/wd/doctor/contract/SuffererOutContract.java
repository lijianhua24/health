package com.wd.doctor.contract;

import com.wd.doctor.bean.PostReviewBean;
import com.wd.doctor.bean.SuffererOutBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/18<p>
 * <p>更改时间：2019/12/18<p>
 */
public interface SuffererOutContract {
    interface iView extends IBaseView {
        void onSuffererOutSuccess(SuffererOutBean suffererOutBean);
        void onSuffererOutFailure(Throwable e);

        //发表评论
        void onPostReviewSuccess(PostReviewBean postReviewBean);
        void onPostReviewFailure(Throwable e);
    }
    interface iModel{
        void getSuffererOutData(int doctorId,String sessionId,int sickCircleId,iSuffererOutCallBack callBack);
        void getPostReviewData(int doctorId,String sessionId,int sickCircleId,String content,iSuffererOutCallBack callBack);
        interface iSuffererOutCallBack{
            void onSuffererOutSuccess(SuffererOutBean suffererOutBean);
            void onSuffererOutFailure(Throwable failure);

            void onPostReviewSuccess(PostReviewBean postReviewBean);
            void onPostReviewFailure(Throwable failure);
        }

    }
    interface iPresenter{
        void getSuffererOutPresenter(int doctorId,String sessionId,int sickCircleId);
        void getPostReviewPresenter(int doctorId,String sessionId,int sickCircleId,String content);
    }
}
