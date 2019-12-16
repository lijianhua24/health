package com.wd.homemodel.presenter;

import com.wd.homemodel.contract.HomeContract;
import com.wd.homemodel.model.HomeModel;
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
