package com.wd.health.presenter;

import com.wd.health.bean.EndInquiryBean;
import com.wd.health.bean.user.HistoryBean;
import com.wd.health.bean.user.InquiryRecordBean;
import com.wd.health.contract.HistoryContract;
import com.wd.health.model.HistoryModel;
import com.wd.mylibrary.Base.BasePresenter;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/24 14:35
 */
public class HistoryPresenter extends BasePresenter<HistoryContract.IView> implements HistoryContract.IPresenter {

    private HistoryModel model;

    @Override
    protected void initModel() {
        model = new HistoryModel();
    }

    @Override
    public void HistoryPresenter(String userId, String sessionId, String page, String count) {
        model.getHistoryData(userId, sessionId, page, count, new HistoryContract.IModel.HistoryContractCallback() {
            @Override
            public void onHistorySuccess(HistoryBean bean) {
                getView().onHistorySuccess(bean);
            }

            @Override
            public void onInquirySuccess(InquiryRecordBean bean) {

            }

            @Override
            public void onendInquirySuccess(EndInquiryBean bean) {

            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void InquiryPresenter(String userId, String sessionId) {
            model.getInquiryData(userId, sessionId, new HistoryContract.IModel.HistoryContractCallback() {
                @Override
                public void onHistorySuccess(HistoryBean bean) {

                }

                @Override
                public void onInquirySuccess(InquiryRecordBean bean) {
                    getView().onInquirySuccess(bean);
                }

                @Override
                public void onendInquirySuccess(EndInquiryBean bean) {

                }

                @Override
                public void onFailure(Throwable e) {
                    getView().onFailure(e);
                }
            });
    }

    @Override
    public void endInquiryPresenter(String userId, String sessionId, String recordId) {
            model.getendInquiryData(userId, sessionId, recordId, new HistoryContract.IModel.HistoryContractCallback() {
                @Override
                public void onHistorySuccess(HistoryBean bean) {

                }

                @Override
                public void onInquirySuccess(InquiryRecordBean bean) {

                }

                @Override
                public void onendInquirySuccess(EndInquiryBean bean) {
                    getView().onendInquirySuccess(bean);
                }

                @Override
                public void onFailure(Throwable e) {
                    getView().onFailure(e);
                }
            });
    }
}
