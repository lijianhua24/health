package com.wd.health.presenter;

import com.wd.health.bean.user.IdBean;
import com.wd.health.contract.IdContract;
import com.wd.health.model.IdModel;
import com.wd.mylibrary.Base.BasePresenter;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/23 16:21
 */
public class IdPresenter extends BasePresenter<IdContract.IView> implements IdContract.IPresenter {

    private IdModel model;

    @Override
    protected void initModel() {
        model = new IdModel();
    }

    @Override
    public void IdPresenter(String userId, String sessionId, String Id, String name, String sex, String nation, String birthday, String address, String idNumber, String issueOffice) {
        model.getIdData(userId, sessionId, Id, name, sex, nation, birthday, address, idNumber, issueOffice, new IdContract.IModel.IdContractCallback() {
            @Override
            public void onIdSuccess(IdBean bean) {
                getView().onIdSuccess(bean);
            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }
}
