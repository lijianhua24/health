package com.wd.health.presenter;

import com.wd.health.contract.HomeContract;
import com.wd.health.model.HomeModel;
import com.wd.mylibrary.Base.BasePresenter;

public class CmedicinesPreesenter extends BasePresenter<HomeContract.CmedicinesContreact.IView> implements HomeContract.CmedicinesContreact.IPresenter {

    private HomeModel homeModel;

    @Override
    public void getCmedicinesPresenter(Integer id) {
    homeModel.getCmedicinesModel(id, new HomeContract.CmedicinesContreact.IModel.IModelCmedicinesCallback() {
        @Override
        public void onCmedicinesSuccess(Object data) {
            getView().onCmedicinesSuccess(data);
        }

        @Override
        public void onCmedicinesFailure(Throwable e) {
            getView().onCmedicinesFailure(e);
        }
    });
    }

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }
}
