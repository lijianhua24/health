package com.wd.health.model;

import com.wd.health.bean.FocusBean;
import com.wd.health.bean.user.IdBean;
import com.wd.health.contract.IdContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/23 16:17
 */
public class IdModel implements IdContract.IModel{
    @Override
    public void getIdData(String userId, String sessionId, String Id, String name, String sex, String nation, String birthday, String address, String idNumber, String issueOffice, IdContractCallback idContractCallback) {
        RetrofitManager.getInstance().create().IdCard(userId, sessionId, Id, name,sex,nation,birthday,address,idNumber,issueOffice)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<IdBean>() {
                    @Override
                    public void onNext(IdBean bean) {
                       idContractCallback.onIdSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                       idContractCallback.onFailure(e);
                    }
                });
    }
}
