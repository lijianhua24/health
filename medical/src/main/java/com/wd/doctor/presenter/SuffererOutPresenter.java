package com.wd.doctor.presenter;

import com.wd.doctor.bean.PostReviewBean;
import com.wd.doctor.bean.SuffererOutBean;
import com.wd.doctor.contract.SuffererOutContract;
import com.wd.doctor.model.SuffererOutModel;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.app.Constant;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/18<p>
 * <p>更改时间：2019/12/18<p>
 */
public class SuffererOutPresenter extends BasePresenter<SuffererOutContract.iView>implements SuffererOutContract.iPresenter {

    private SuffererOutModel suffererOutModel;

    @Override
    public void getSuffererOutPresenter(int doctorId, String sessionId, int sickCircleId) {
        suffererOutModel.getSuffererOutData(doctorId, sessionId, sickCircleId, new SuffererOutContract.iModel.iSuffererOutCallBack() {
            @Override
            public void onSuffererOutSuccess(SuffererOutBean suffererOutBean) {
                if (isViewAttached()) {
                    if (suffererOutBean != null && Constant.SUCCESS_CODE.equals(suffererOutBean.getStatus())) {
                        getView().onSuffererOutSuccess(suffererOutBean);
                    }else {
                        getView().onSuffererOutFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onSuffererOutFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onSuffererOutFailure(failure);
                }
            }

            @Override
            public void onPostReviewSuccess(PostReviewBean postReviewBean) {

            }

            @Override
            public void onPostReviewFailure(Throwable failure) {

            }
        });
    }

    @Override
    public void getPostReviewPresenter(int doctorId, String sessionId, int sickCircleId, String content) {
        suffererOutModel.getPostReviewData(doctorId, sessionId, sickCircleId, content, new SuffererOutContract.iModel.iSuffererOutCallBack() {
            @Override
            public void onSuffererOutSuccess(SuffererOutBean suffererOutBean) {

            }

            @Override
            public void onSuffererOutFailure(Throwable failure) {

            }

            @Override
            public void onPostReviewSuccess(PostReviewBean postReviewBean) {
                if (isViewAttached()) {
                    if (postReviewBean != null && Constant.SUCCESS_CODE.equals(postReviewBean.getStatus())) {
                        getView().onPostReviewSuccess(postReviewBean);
                    }else {
                        getView().onPostReviewFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onPostReviewFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onPostReviewFailure(failure);
                }
            }
        });
    }

    @Override
    protected void initModel() {
        suffererOutModel = new SuffererOutModel();
    }
}
