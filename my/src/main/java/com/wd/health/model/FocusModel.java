package com.wd.health.model;

import com.wd.health.bean.FocusBean;
import com.wd.health.contract.FocusContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:06
 */
public class FocusModel implements FocusContract.IModel {
    @Override
    public void getFocusData(String userId, String sessionId, String page, String count, FocusContractCallback focusContractCallback) {
        RetrofitManager.getInstance().create().getFocus(userId, sessionId, page, count)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<FocusBean>() {
                    @Override
                    public void onNext(FocusBean bean) {
                       focusContractCallback.onFocusSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                       focusContractCallback.onFailure(e);
                    }
                });
    }
}
