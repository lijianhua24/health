package com.wd.doctor.contract;

import com.wd.doctor.bean.DoctorInforBean;
import com.wd.doctor.bean.QueryRevenueBean;
import com.wd.mylibrary.Base.IBaseView;

import retrofit2.http.Query;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/16<p>
 * <p>更改时间：2019/12/16<p>
 *     根据医生id查询医生信息
 */
public interface DoctorInforContract {
    interface iView extends IBaseView {
        void onDoctorInforSuccess(DoctorInforBean doctorInforBean);
        void onDoctorInforFailure(Throwable e);


    }
    interface iModel{
        void getDoctorInforData(int doctorId, String sessionId, iDoctorInforCallBack callBack);
        interface iDoctorInforCallBack{
            void onDoctorInforSuccess(DoctorInforBean doctorInforBean);
            void onDoctorInforFailure(Throwable failure);


        }

    }
    interface iPresenter{
        void getDoctorInforPresenter(int doctorId, String sessionId);

    }
}
