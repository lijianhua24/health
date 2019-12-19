package com.wd.doctor.presenter;

import com.wd.doctor.bean.ImageQueryBean;
import com.wd.doctor.bean.SimagePhotosBean;
import com.wd.doctor.contract.ImageQueryContract;
import com.wd.doctor.model.ImageQueryModel;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.app.Constant;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/19<p>
 * <p>更改时间：2019/12/19<p>
 */
public class ImageQueryPresenter extends BasePresenter<ImageQueryContract.iView>implements ImageQueryContract.iPresenter {

    private ImageQueryModel imageQueryModel;

    @Override
    public void getImageQueryPresenter() {
        imageQueryModel.getImageQueryData(new ImageQueryContract.iModel.iImageQueryCallBack() {
            @Override
            public void onImageQuerySuccess(ImageQueryBean imageQueryBean) {
                if (isViewAttached()) {
                    if (imageQueryBean != null && Constant.SUCCESS_CODE.equals(imageQueryBean.getStatus())) {
                        getView().onImageQuerySuccess(imageQueryBean);
                    }else {
                        getView().onImageQueryFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onImageQueryFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onImageQueryFailure(failure);
                }
            }

            @Override
            public void onSimagePhotosSuccess(SimagePhotosBean simagePhotosBean) {

            }

            @Override
            public void onSimagePhotosFailure(Throwable failure) {

            }
        });
    }

    @Override
    public void getSimagePhotosPresenter(int doctorId, String sessionId, String imagePic) {
        imageQueryModel.getSimagePhotosData(doctorId, sessionId, imagePic, new ImageQueryContract.iModel.iImageQueryCallBack() {
            @Override
            public void onImageQuerySuccess(ImageQueryBean imageQueryBean) {

            }

            @Override
            public void onImageQueryFailure(Throwable failure) {

            }

            @Override
            public void onSimagePhotosSuccess(SimagePhotosBean simagePhotosBean) {
                if (isViewAttached()) {
                    if (simagePhotosBean != null && Constant.SUCCESS_CODE.equals(simagePhotosBean.getStatus())) {
                        getView().onSimagePhotosSuccess(simagePhotosBean);
                    }else {
                        getView().onSimagePhotosFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onSimagePhotosFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onSimagePhotosFailure(failure);
                }
            }
        });
    }

    @Override
    protected void initModel() {
        imageQueryModel = new ImageQueryModel();
    }
}
