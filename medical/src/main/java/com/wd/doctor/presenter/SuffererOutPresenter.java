package com.wd.doctor.presenter;

import com.wd.doctor.bean.SuffererOutBean;
import com.wd.doctor.contract.SuffererOutContract;
import com.wd.doctor.model.SuffererOutModel;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.app.Constant;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/18<p>
 * <p>更改时间：2019/12/18<p>
 */
public class SuffererOutPresenter extends BasePresenter<SuffererOutContract.iView>implements SuffererOutContract.iPresenter {

    private SuffererOutModel suffererOutModel;

    @Override
    public void getSuffererOutPresenter(int doctorId, String sessionId, int sickCircleId) {
        suffererOutModel.getSuffererOutData(doctorId, sessionId, sickCircleId, new SuffererOutContract.iModel.iSuffererOutCallBack() {
            @Override
            public void onSuffererOutSuccess(SuffererOutBean suffererOutBean) {
                if (isViewAttached()) {
                    if (suffererOutBean != null && Constant.SUCCESS_CODE.equals(suffererOutBean.getStatus())) {
                        getView().onSuffererOutSuccess(suffererOutBean);
                    }else {
                        getView().onSuffererOutFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onSuffererOutFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onSuffererOutFailure(failure);
                }
            }
        });
    }

    @Override
    protected void initModel() {
        suffererOutModel = new SuffererOutModel();
    }
}
