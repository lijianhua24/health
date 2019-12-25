package com.wd.health.presenter;

import com.wd.health.bean.PerfectUserInfoBean;
import com.wd.health.contract.PerfectUserInfoContract;
import com.wd.health.model.PerfectUserInfoModel;
import com.wd.mylibrary.Base.BasePresenter;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/18 16:04
 */
public class PerfectUserInfoPresenter extends BasePresenter<PerfectUserInfoContract.IView> implements PerfectUserInfoContract.IPresenter {

    private PerfectUserInfoModel model;

    @Override
    protected void initModel() {
        model = new PerfectUserInfoModel();
    }

    @Override
    public void PerfectUserInfoPresenter(String userId, String sessionId, String age, String height, String weight) {
        model.getPerfectUserInfoData(userId, sessionId, age, height, weight, new PerfectUserInfoContract.IModel.PerfectUserInfoContractCallback() {
            @Override
            public void onPerfectUserInfoSuccess(PerfectUserInfoBean bean) {
                getView().onPerfectUserInfoSuccess(bean);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }
}
