package com.wd.doctor.presenter;

import com.wd.doctor.bean.EnquiryBean;
import com.wd.doctor.bean.SuffererDetailBean;
import com.wd.doctor.contract.EnquiryContract;
import com.wd.doctor.model.EnquiryModel;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.app.Constant;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/17<p>
 * <p>更改时间：2019/12/17<p>
 */
public class EnquiryPresenter extends BasePresenter<EnquiryContract.iView>implements EnquiryContract.iPresenter {

    private EnquiryModel enquiryModel;

    @Override
    public void getEnquiryPresenter() {
        enquiryModel.getEnquiryData(new EnquiryContract.iModel.iEnquiryCallBack() {
            @Override
            public void onEnquirySuccess(EnquiryBean enquiryBean) {
                if (isViewAttached()) {
                    if (enquiryBean != null && Constant.SUCCESS_CODE.equals(enquiryBean.getStatus())) {
                        getView().onEnquirySuccess(enquiryBean);
                    }else {
                        getView().onEnquiryFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onEnquiryFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onEnquiryFailure(failure);
                }
            }

            @Override
            public void onSuffererDetailSuccess(SuffererDetailBean suffererDetailBean) {

            }

            @Override
            public void onSuffererDetailFailure(Throwable failure) {

            }
        });
    }

    @Override
    public void getSuffererDetailPresenter(int departmentId, int page, int count) {
        enquiryModel.getSuffererDetailData(departmentId, page, count, new EnquiryContract.iModel.iEnquiryCallBack() {
            @Override
            public void onEnquirySuccess(EnquiryBean enquiryBean) {

            }

            @Override
            public void onEnquiryFailure(Throwable failure) {

            }

            @Override
            public void onSuffererDetailSuccess(SuffererDetailBean suffererDetailBean) {
                if (isViewAttached()) {
                    if (suffererDetailBean != null && Constant.SUCCESS_CODE.equals(suffererDetailBean.getStatus())) {
                        getView().onSuffererDetailSuccess(suffererDetailBean);
                    }else {
                        getView().onSuffererDetailFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onSuffererDetailFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onSuffererDetailFailure(failure);
                }
            }
        });
    }

    @Override
    protected void initModel() {
        enquiryModel = new EnquiryModel();
    }
}
