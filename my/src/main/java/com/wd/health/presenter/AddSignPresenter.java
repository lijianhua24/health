package com.wd.health.presenter;

import com.wd.health.bean.AddSignBean;
import com.wd.health.bean.user.DoTaskBean;
import com.wd.health.contract.AddSignContract;
import com.wd.health.model.AddSignModel;
import com.wd.mylibrary.Base.BasePresenter;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/15 21:12
 */
public class AddSignPresenter extends BasePresenter<AddSignContract.IView> implements AddSignContract.IPresenter {

    private AddSignModel model;

    @Override
    protected void initModel() {
        model = new AddSignModel();
    }

    @Override
    public void AddSignresenter(String userId, String sessionId) {
        model.getAddSignData(userId, sessionId, new AddSignContract.IModel.AddSignContractCallback() {
            @Override
            public void onAddSignSuccess(AddSignBean bean) {
                getView().onAddSignSuccess(bean);
            }

            @Override
            public void onDoTaskSuccess(DoTaskBean bean) {

            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void getDoTaskPresenter(String userId, String sessionId, String taskId) {
        model.getDoTaskData(userId, sessionId, taskId, new AddSignContract.IModel.AddSignContractCallback() {
            @Override
            public void onAddSignSuccess(AddSignBean bean) {

            }

            @Override
            public void onDoTaskSuccess(DoTaskBean bean) {
                getView().onDoTaskSuccess(bean);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }
}
