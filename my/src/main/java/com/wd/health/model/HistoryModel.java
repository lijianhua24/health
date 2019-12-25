package com.wd.health.model;

import com.wd.health.bean.AddSignBean;
import com.wd.health.bean.EndInquiryBean;
import com.wd.health.bean.user.HistoryBean;
import com.wd.health.bean.user.InquiryRecordBean;
import com.wd.health.contract.HistoryContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/24 14:33
 */
public class HistoryModel implements HistoryContract.IModel {
    @Override
    public void getHistoryData(String userId, String sessionId, String page, String count, HistoryContractCallback historyContractCallback) {
        RetrofitManager.getInstance().create().history(userId, sessionId,page,count)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<HistoryBean>() {
                    @Override
                    public void onNext(HistoryBean bean) {
                        historyContractCallback.onHistorySuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        historyContractCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void getInquiryData(String userId, String sessionId, HistoryContractCallback historyContractCallback) {
        RetrofitManager.getInstance().create().InquiryRecord(userId, sessionId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<InquiryRecordBean>() {
                    @Override
                    public void onNext(InquiryRecordBean bean) {
                        historyContractCallback.onInquirySuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        historyContractCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void getendInquiryData(String userId, String sessionId, String recordId, HistoryContractCallback historyContractCallback) {
        RetrofitManager.getInstance().create().endInquiry(userId, sessionId,recordId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<EndInquiryBean>() {
                    @Override
                    public void onNext(EndInquiryBean bean) {
                        historyContractCallback.onendInquirySuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        historyContractCallback.onFailure(e);
                    }
                });
    }
}
