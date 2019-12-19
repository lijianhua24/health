package com.wd.doctor.model;

import com.wd.doctor.bean.ImageQueryBean;
import com.wd.doctor.bean.SimagePhotosBean;
import com.wd.doctor.contract.ImageQueryContract;
import com.wd.doctor.utils.ApiServers;
import com.wd.doctor.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/19<p>
 * <p>更改时间：2019/12/19<p>
 */
public class ImageQueryModel implements ImageQueryContract.iModel {

    @Override
    public void getImageQueryData(iImageQueryCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getImageQuery()
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<ImageQueryBean>() {
                    @Override
                    public void onNext(ImageQueryBean imageQueryBean) {
                        callBack.onImageQuerySuccess(imageQueryBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onImageQueryFailure(e);
                    }
                });
    }

    @Override
    public void getSimagePhotosData(int doctorId, String sessionId, String imagePic, iImageQueryCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getSimagePhotos(doctorId,sessionId,imagePic)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<SimagePhotosBean>() {
                    @Override
                    public void onNext(SimagePhotosBean simagePhotosBean) {
                        callBack.onSimagePhotosSuccess(simagePhotosBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onSimagePhotosFailure(e);
                    }
                });
    }
}
