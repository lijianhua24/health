package com.wd.doctor.contract;

import com.wd.doctor.bean.ImageQueryBean;
import com.wd.doctor.bean.SimagePhotosBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/19<p>
 * <p>更改时间：2019/12/19<p>
 *     查询系统形象照
 *     选择系统提供形象照 SimagePhotos
 *     上传形象照
 */
public interface ImageQueryContract {
    interface iView extends IBaseView {
        void onImageQuerySuccess(ImageQueryBean imageQueryBean);
        void onImageQueryFailure(Throwable e);

        void onSimagePhotosSuccess(SimagePhotosBean simagePhotosBean);
        void onSimagePhotosFailure(Throwable e);


    }
    interface iModel{
        void getImageQueryData(iImageQueryCallBack callBack);
        void getSimagePhotosData(int doctorId,String sessionId,String imagePic,iImageQueryCallBack callBack);

        interface iImageQueryCallBack{
            void onImageQuerySuccess(ImageQueryBean imageQueryBean);
            void onImageQueryFailure(Throwable failure);

            void onSimagePhotosSuccess(SimagePhotosBean simagePhotosBean);
            void onSimagePhotosFailure(Throwable failure);


        }

    }
    interface iPresenter{
        void getImageQueryPresenter();
        void getSimagePhotosPresenter(int doctorId,String sessionId,String imagePic);


    }
}
