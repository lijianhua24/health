package com.wd.health.model;

import com.wd.health.bean.PerfectUserInfoBean;
import com.wd.health.contract.PerfectUserInfoContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/18 16:03
 */
public class PerfectUserInfoModel implements PerfectUserInfoContract.IModel {
    @Override
    public void getPerfectUserInfoData(String userId, String sessionId, String age, String height, String weight, PerfectUserInfoContractCallback perfectUserInfoContractCallback) {
        RetrofitManager.getInstance().create().PerfectUserInfo(userId, sessionId,age,height,weight)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<PerfectUserInfoBean>() {
                    @Override
                    public void onNext(PerfectUserInfoBean bean) {
                       perfectUserInfoContractCallback.onPerfectUserInfoSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        perfectUserInfoContractCallback.onFailure(e);
                    }
                });
    }
}
