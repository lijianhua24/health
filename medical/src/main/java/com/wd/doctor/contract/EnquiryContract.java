package com.wd.doctor.contract;

import com.wd.doctor.bean.EnquiryBean;
import com.wd.doctor.bean.SuffererDetailBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/17<p>
 * <p>更改时间：2019/12/17<p>
 */
public interface EnquiryContract {
    interface iView extends IBaseView {
        void onEnquirySuccess(EnquiryBean enquiryBean);
        void onEnquiryFailure(Throwable e);

        //病友圈列表展示
        void onSuffererDetailSuccess(SuffererDetailBean suffererDetailBean);
        void onSuffererDetailFailure(Throwable e);
    }
    interface iModel{
        void getEnquiryData(iEnquiryCallBack callBack);
        void getSuffererDetailData(int departmentId,int page,int count,iEnquiryCallBack callBack);
        interface iEnquiryCallBack{
            void onEnquirySuccess(EnquiryBean enquiryBean);
            void onEnquiryFailure(Throwable failure);

            void onSuffererDetailSuccess(SuffererDetailBean suffererDetailBean);
            void onSuffererDetailFailure(Throwable failure);
        }

    }
    interface iPresenter{
        void getEnquiryPresenter();
        void getSuffererDetailPresenter(int departmentId,int page,int count);

    }
}
