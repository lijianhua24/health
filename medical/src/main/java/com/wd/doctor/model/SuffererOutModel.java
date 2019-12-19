package com.wd.doctor.model;

import com.wd.doctor.bean.SuffererOutBean;
import com.wd.doctor.contract.SuffererOutContract;
import com.wd.doctor.utils.ApiServers;
import com.wd.doctor.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/18<p>
 * <p>更改时间：2019/12/18<p>
 */
public class SuffererOutModel implements SuffererOutContract.iModel {
    @Override
    public void getSuffererOutData(int doctorId, String sessionId, int sickCircleId, iSuffererOutCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getSuffererOut(doctorId,sessionId,sickCircleId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<SuffererOutBean>() {
                    @Override
                    public void onNext(SuffererOutBean suffererOutBean) {
                        callBack.onSuffererOutSuccess(suffererOutBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onSuffererOutFailure(e);
                    }
                });
    }
}
