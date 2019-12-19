package com.wd.doctor.model;

import com.wd.doctor.bean.EnquiryBean;
import com.wd.doctor.bean.SuffererDetailBean;
import com.wd.doctor.contract.EnquiryContract;
import com.wd.doctor.utils.ApiServers;
import com.wd.doctor.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/17<p>
 * <p>更改时间：2019/12/17<p>
 */
public class EnquiryModel implements EnquiryContract.iModel {
    @Override
    public void getEnquiryData(iEnquiryCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getEnquiry()
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<EnquiryBean>() {
                    @Override
                    public void onNext(EnquiryBean enquiryBean) {
                        callBack.onEnquirySuccess(enquiryBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onEnquiryFailure(e);
                    }
                });
    }

    @Override
    public void getSuffererDetailData(int departmentId, int page, int count, iEnquiryCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getSuffererDetail(departmentId,page,count)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<SuffererDetailBean>() {
                    @Override
                    public void onNext(SuffererDetailBean suffererDetailBean) {
                        callBack.onSuffererDetailSuccess(suffererDetailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onSuffererDetailFailure(e);
                    }
                });
    }
}
