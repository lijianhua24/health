package com.wd.doctor.presenter;

import com.wd.doctor.bean.ResidencyBean;
import com.wd.doctor.contract.ResidencyContract;
import com.wd.doctor.model.ResidencyModel;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.app.Constant;

import okhttp3.RequestBody;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/17<p>
 * <p>更改时间：2019/12/17<p>
 */
public class ResidencyPresenter extends BasePresenter<ResidencyContract.iView>implements ResidencyContract.iPresenter {

    private ResidencyModel residencyModel;

    @Override
    public void getResidencyPresenter(RequestBody route) {
        residencyModel.getResidencyData(route, new ResidencyContract.iModel.iResidencyCallBack() {
            @Override
            public void onResidencySuccess(ResidencyBean residencyBean) {
                if (isViewAttached()) {
                    if (residencyBean != null && Constant.SUCCESS_CODE.equals(residencyBean.getStatus())) {
                        getView().onResidencySuccess(residencyBean);
                    }else {
                        getView().onResidencyFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onResidencyFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onResidencyFailure(failure);
                }
            }
        });
    }

    @Override
    protected void initModel() {
        residencyModel = new ResidencyModel();
    }
}
