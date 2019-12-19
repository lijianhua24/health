package com.wd.doctor.contract;

import com.wd.doctor.bean.ResidencyBean;
import com.wd.mylibrary.Base.IBaseView;

import okhttp3.RequestBody;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/17<p>
 * <p>更改时间：2019/12/17<p>
 */
public interface ResidencyContract {
    interface iView extends IBaseView {
        void onResidencySuccess(ResidencyBean residencyBean);
        void onResidencyFailure(Throwable e);
    }
    interface iModel{
        void getResidencyData(RequestBody route, iResidencyCallBack callBack);
        interface iResidencyCallBack{
            void onResidencySuccess(ResidencyBean residencyBean);
            void onResidencyFailure(Throwable failure);
        }

    }
    interface iPresenter{
        void getResidencyPresenter(RequestBody route);
    }
}
