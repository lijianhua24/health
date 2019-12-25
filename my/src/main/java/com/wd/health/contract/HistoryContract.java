package com.wd.health.contract;

import com.wd.health.bean.EndInquiryBean;
import com.wd.health.bean.FocusBean;
import com.wd.health.bean.user.HistoryBean;
import com.wd.health.bean.user.InquiryRecordBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:04
 */
public interface HistoryContract {
    interface IView extends IBaseView {
        void onHistorySuccess(HistoryBean bean);

        void onInquirySuccess(InquiryRecordBean bean);

        void onendInquirySuccess(EndInquiryBean bean);

        void onFailure(Throwable e);
    }

    interface IModel {
        void getHistoryData(String userId, String sessionId, String page, String count, HistoryContractCallback historyContractCallback);

        void getInquiryData(String userId, String sessionId, HistoryContractCallback historyContractCallback);

        void getendInquiryData(String userId, String sessionId,String recordId, HistoryContractCallback historyContractCallback);

        interface HistoryContractCallback {
            void onHistorySuccess(HistoryBean bean);

            void onInquirySuccess(InquiryRecordBean bean);
            void onendInquirySuccess(EndInquiryBean bean);
            void onFailure(Throwable e);
        }
    }

    interface IPresenter {
        void HistoryPresenter(String userId, String sessionId, String page, String count);

        void InquiryPresenter(String userId, String sessionId);

        void endInquiryPresenter(String userId, String sessionId,String recordId);
    }
}
