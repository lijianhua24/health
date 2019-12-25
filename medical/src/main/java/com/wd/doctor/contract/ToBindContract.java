package com.wd.doctor.contract;

import com.wd.doctor.bean.ToBindBean;
import com.wd.mylibrary.Base.IBaseView;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;

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

        void onBindBankSuccess(ToBindBean toBindBean);
        void onBindBankFailure(Throwable e);

    }
    interface iModel{
        void getToBindData(int doctorId, String sessionId, Map<String,Object> BodyMap, iToBindCallBack callBack);
        void getBindBankData(int doctorId, String sessionId,String bankCardNumber,String bankName,int bankCardType,iToBindCallBack callBack);
        interface iToBindCallBack{
            void onToBindSuccess(ToBindBean toBindBean);
            void onToBindFailure(Throwable failure);

            void onBindBankSuccess(ToBindBean toBindBean);
            void onBindBankFailure(Throwable failure);
        }

    }
    interface iPresenter{
        void getToBindPresenter(int doctorId, String sessionId, Map<String,Object> BodyMap);
        void getBindBankPresenter(int doctorId, String sessionId,String bankCardNumber,
                                  String bankName,int bankCardType);

    }
}
