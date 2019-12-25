package com.wd.health.model;

import com.wd.health.bean.AddSignBean;
import com.wd.health.bean.user.DoTaskBean;
import com.wd.health.contract.AddSignContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/15 21:10
 */
public class AddSignModel  implements AddSignContract.IModel {
    @Override
    public void getAddSignData(String userId, String sessionId, AddSignContractCallback addSignContractCallback) {
        RetrofitManager.getInstance().create().getaddSign(userId, sessionId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<AddSignBean>() {
                    @Override
                    public void onNext(AddSignBean bean) {
                        addSignContractCallback.onAddSignSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                       addSignContractCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void getDoTaskData(String userId, String sessionId, String taskId, AddSignContractCallback addSignContractCallback) {
        RetrofitManager.getInstance().create().doTask(userId, sessionId,taskId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<DoTaskBean>() {
                    @Override
                    public void onNext(DoTaskBean bean) {
                        addSignContractCallback.onDoTaskSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        addSignContractCallback.onFailure(e);
                    }
                });
    }
}
