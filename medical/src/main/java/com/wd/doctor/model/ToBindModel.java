package com.wd.doctor.model;

import android.util.Log;

import com.wd.doctor.bean.ToBindBean;
import com.wd.doctor.contract.ToBindContract;
import com.wd.doctor.utils.ApiServers;
import com.wd.doctor.utils.RetrofitManager;
import com.wd.mylibrary.Test.ToastUtils;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/23<p>
 * <p>更改时间：2019/12/23<p>
 */
public class ToBindModel implements ToBindContract.iModel {
        public static final String TAG="ToBindModel";
    @Override
    public void getToBindData(int doctorId, String sessionId, Map<String,Object> BodyMap, iToBindCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getToBind(doctorId,sessionId,BodyMap)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<ToBindBean>() {
                    @Override
                    public void onNext(ToBindBean toBindBean) {
                        callBack.onToBindSuccess(toBindBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onToBindFailure(e);
                    }
                });
    }

    @Override
    public void getBindBankData(int doctorId, String sessionId, String bankCardNumber, String bankName, int bankCardType, iToBindCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getbindBank(doctorId,sessionId,bankCardNumber,bankName,bankCardType)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<ToBindBean>() {
                    @Override
                    public void onNext(ToBindBean toBindBean) {
                        Log.d(TAG, "onNext1: "+toBindBean.getMessage());
                        callBack.onBindBankSuccess(toBindBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onBindBankFailure(e);
                    }
                });
    }
}
