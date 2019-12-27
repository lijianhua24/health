package com.wd.doctor.presenter;

import com.wd.doctor.bean.QueryBankBean;
import com.wd.doctor.bean.QueryIdBean;
import com.wd.doctor.contract.BindInFoContract;
import com.wd.doctor.model.BindInFoModel;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.app.Constant;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/27<p>
 * <p>更改时间：2019/12/27<p>
 */
public class BindInFoPresenter extends BasePresenter<BindInFoContract.iView>implements BindInFoContract.iPresenter {

    private BindInFoModel bindInFoModel;

    @Override
    public void getQueryIdPresenter(int doctorId, String sessionId) {
        bindInFoModel.getQueryIdData(doctorId, sessionId, new BindInFoContract.iModel.iQueryIdCallBack() {
            @Override
            public void onQueryIdSuccess(QueryIdBean queryIdBean) {
                if (isViewAttached()) {
                    if (queryIdBean != null && Constant.SUCCESS_CODE.equals(queryIdBean.getStatus())) {
                        getView().onQueryIdSuccess(queryIdBean);
                    }else {
                        getView().onQueryIdFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onQueryIdFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onQueryIdFailure(failure);
                }
            }

            @Override
            public void onQueryBankSuccess(QueryBankBean queryBankBean) {

            }

            @Override
            public void onQueryBankFailure(Throwable failure) {

            }
        });
    }

    @Override
    public void getQueryBankPresenter(int doctorId, String sessionId) {
        bindInFoModel.getQueryBankData(doctorId, sessionId, new BindInFoContract.iModel.iQueryIdCallBack() {
            @Override
            public void onQueryIdSuccess(QueryIdBean queryIdBean) {

            }

            @Override
            public void onQueryIdFailure(Throwable failure) {

            }

            @Override
            public void onQueryBankSuccess(QueryBankBean queryBankBean) {
                if (isViewAttached()) {
                    if (queryBankBean != null && Constant.SUCCESS_CODE.equals(queryBankBean.getStatus())) {
                        getView().onQueryBankSuccess(queryBankBean);
                    }else {
                        getView().onQueryBankFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onQueryBankFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onQueryBankFailure(failure);
                }
            }
        });
    }

    @Override
    protected void initModel() {
        bindInFoModel = new BindInFoModel();
    }
}
