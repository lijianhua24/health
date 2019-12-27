package com.wd.doctor.model;

import com.wd.doctor.bean.QueryBankBean;
import com.wd.doctor.bean.QueryIdBean;
import com.wd.doctor.contract.BindInFoContract;
import com.wd.doctor.utils.ApiServers;
import com.wd.doctor.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/27<p>
 * <p>更改时间：2019/12/27<p>
 */
public class BindInFoModel implements BindInFoContract.iModel {
    @Override
    public void getQueryIdData(int doctorId, String sessionId, iQueryIdCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getQueryId(doctorId,sessionId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<QueryIdBean>() {
                    @Override
                    public void onNext(QueryIdBean queryIdBean) {
                        callBack.onQueryIdSuccess(queryIdBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onQueryIdFailure(e);
                    }
                });
    }

    @Override
    public void getQueryBankData(int doctorId, String sessionId, iQueryIdCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getQueryBank(doctorId,sessionId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<QueryBankBean>() {
                    @Override
                    public void onNext(QueryBankBean queryBankBean) {
                        callBack.onQueryBankSuccess(queryBankBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onQueryBankFailure(e);
                    }
                });
    }
}
