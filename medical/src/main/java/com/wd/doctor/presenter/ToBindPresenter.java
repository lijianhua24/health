package com.wd.doctor.presenter;

import android.util.Log;

import com.wd.doctor.bean.ToBindBean;
import com.wd.doctor.contract.ToBindContract;
import com.wd.doctor.model.ToBindModel;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.app.Constant;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/23<p>
 * <p>更改时间：2019/12/23<p>
 */
public class ToBindPresenter extends BasePresenter<ToBindContract.iView>implements ToBindContract.iPresenter {
    public static final String TAG="ToBindPresenter";
    private ToBindModel toBindModel;

    @Override
    public void getToBindPresenter(int doctorId, String sessionId, Map<String, Object> BodyMap) {
        toBindModel.getToBindData(doctorId, sessionId, BodyMap, new ToBindContract.iModel.iToBindCallBack() {
            @Override
            public void onToBindSuccess(ToBindBean toBindBean) {
                if (isViewAttached()) {
                    if (toBindBean != null && Constant.SUCCESS_CODE.equals(toBindBean.getStatus())) {
                        getView().onToBindSuccess(toBindBean);
                    }else {
                        getView().onToBindFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onToBindFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onToBindFailure(failure);
                }
            }

            @Override
            public void onBindBankSuccess(ToBindBean toBindBean) {

            }

            @Override
            public void onBindBankFailure(Throwable failure) {

            }
        });
    }

    @Override
    public void getBindBankPresenter(int doctorId, String sessionId, String bankCardNumber, String bankName, int bankCardType) {
        toBindModel.getBindBankData(doctorId, sessionId, bankCardNumber, bankName, bankCardType, new ToBindContract.iModel.iToBindCallBack() {
            @Override
            public void onToBindSuccess(ToBindBean toBindBean) {

            }

            @Override
            public void onToBindFailure(Throwable failure) {

            }

            @Override
            public void onBindBankSuccess(ToBindBean toBindBean) {
                if (isViewAttached()) {
                    if (toBindBean != null && Constant.SUCCESS_CODE.equals(toBindBean.getStatus())) {
                        getView().onBindBankSuccess(toBindBean);
                    }else {
                        getView().onBindBankFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onBindBankFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onBindBankFailure(failure);
                }
            }
        });


    }

    @Override
    protected void initModel() {
        toBindModel = new ToBindModel();
    }
}