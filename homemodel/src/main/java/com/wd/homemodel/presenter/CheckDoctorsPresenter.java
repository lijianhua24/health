package com.wd.homemodel.presenter;

import android.widget.Toast;

import com.wd.homemodel.app.App;
import com.wd.homemodel.contract.HomeContract;
import com.wd.homemodel.model.HomeModel;
import com.wd.mylibrary.Base.BasePresenter;

public class CheckDoctorsPresenter extends BasePresenter<HomeContract.CheckDoctorsContreact.IView> implements HomeContract.CheckDoctorsContreact.IPresenter{

    private HomeModel homeModel;

    @Override
    public void getCheckDoctorsPresenter(Integer deptId, Integer condition, Integer sortBy, Integer page, Integer count) {

        homeModel.getCheckDoctorsModel(deptId, condition, sortBy, page, count, new HomeContract.CheckDoctorsContreact.IModel.IModelCheckDoctorsCallback() {
                @Override
                public void onCheckDoctorsSuccess(Object data) {
                    getView().onCheckDoctorsSuccess(data);
                }

                @Override
                public void onCheckDoctorsFailure(Throwable e) {
                    getView().onCheckDoctorsFailure(e);
                }
            });
    }

    @Override
    public void getDoctorDetailsPresenter(String userId, String sessionId, String doctorId) {
            homeModel.getDoctorDetailsModel(userId, sessionId, doctorId, new HomeContract.CheckDoctorsContreact.IModel.IModelDoctorDetailsCallback() {
                @Override
                public void onDoctorDetailsSuccess(Object data) {
                    getView().onDoctorDetailsSuccess(data);
                }

                @Override
                public void onDoctorDetailsFailure(Throwable e) {
                    getView().onDoctorDetailsFailure(e);
                }
            });
    }

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }
}