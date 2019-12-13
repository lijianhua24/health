package com.wd.homemodel.model;

import com.wd.homemodel.contract.HomeContract;
import com.wd.mylibrary.utils.CommonSchedulers;
import com.wd.mylibrary.utils.RequestNet;

public class HomeModel implements HomeContract.BnnerContreact.IModel {
    @Override
    public void getTjyyDataModel(IModelCallback callback) {
        /*RequestNet.getInstance().create().getYingYuanXingqing(userId,sessionId, movieId)
                .compose(CommonSchedulers.<YingYuanXQBean>io2main())
                .subscribe(new CommonObserver<YingYuanXQBean>() {
                    @Override
                    public void onNext(YingYuanXQBean emailBean) {
                        callback.onYingYuanXqSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onYingYuanXqFailure(e);
                    }
                });*/
    }
}
