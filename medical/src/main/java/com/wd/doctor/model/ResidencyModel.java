package com.wd.doctor.model;

import com.wd.doctor.bean.ResidencyBean;
import com.wd.doctor.contract.ResidencyContract;
import com.wd.doctor.utils.ApiServers;
import com.wd.doctor.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

import okhttp3.RequestBody;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/17<p>
 * <p>更改时间：2019/12/17<p>
 */
public class ResidencyModel implements ResidencyContract.iModel {
    @Override
    public void getResidencyData(RequestBody route, iResidencyCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getResidency(route)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<ResidencyBean>() {
                    @Override
                    public void onNext(ResidencyBean residencyBean) {
                        callBack.onResidencySuccess(residencyBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onResidencyFailure(e);
                    }
                });
    }
}
