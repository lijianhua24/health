package com.wd.homemodel.presenter;

import com.wd.homemodel.contract.HomeContract;
import com.wd.mylibrary.Base.BasePresenter;

public class SpyDetailsPresenter extends BasePresenter<HomeContract.SpyDetailsContreact.IView> implements HomeContract.SpyDetailsContreact.IPresenter {
    @Override
    public void getSpyDetailsPresenter(String userId, String sessionId, Integer infoId) {

    }

    @Override
    protected void initModel() {

    }
}
