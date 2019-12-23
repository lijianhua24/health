package com.wd.doctor.contract;

import com.wd.doctor.bean.ToBindBean;
import com.wd.mylibrary.Base.IBaseView;

import okhttp3.RequestBody;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/23<p>
 * <p>更改时间：2019/12/23<p>
 */
public interface ToBindContract {
    interface iView extends IBaseView {
        void onToBindSuccess(ToBindBean toBindBean);
        void onToBindFailure(Throwable e);

        void onBindBankSuccess(ToBindBean data);
        void onBindBankFailure(Throwable e);

    }
    interface iModel{
        void getToBindData(int doctorId, String sessionId, RequestBody route,iToBindCallBack callBack);
        void getBindBankData(int doctorId, String sessionId,String bankCardNumber,String bankName,int bankCardType,iToBindCallBack callBack);
        interface iToBindCallBack{
            void onToBindSuccess(ToBindBean toBindBean);
            void onToBindFailure(Throwable failure);

            void onBindBankSuccess(ToBindBean data);
            void onBindBankFailure(Throwable failure);
        }

    }
    interface iPresenter{
        void getToBindPresenter(int doctorId, String sessionId, RequestBody route);
        void getBindBankPresenter(int doctorId, String sessionId,String bankCardNumber,
                                  String bankName,int bankCardType);

    }
}
