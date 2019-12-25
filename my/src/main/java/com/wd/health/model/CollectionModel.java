package com.wd.health.model;

import com.wd.health.bean.JKZXBean;
import com.wd.health.contract.ConlltionContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:06
 */
public class CollectionModel implements ConlltionContract.IModel {

    @Override
    public void getJKZXData(String userId, String sessionId, String page, String count, ConlltionContractCallback conlltionContractCallback) {
        RetrofitManager.getInstance().create().getJKZX(userId, sessionId, page, count)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<JKZXBean>() {
                    @Override
                    public void onNext(JKZXBean bean) {
                       conlltionContractCallback.onJKZXSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                       conlltionContractCallback.onFailure(e);
                    }
                });
    }
}
