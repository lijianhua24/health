package com.wd.doctor.contract;

import com.wd.doctor.bean.QueryBankBean;
import com.wd.doctor.bean.QueryIdBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/26<p>
 * <p>更改时间：2019/12/26<p>
 */
public interface BindInFoContract {
    interface iView extends IBaseView {
        void onQueryIdSuccess(QueryIdBean queryIdBean);
        void onQueryIdFailure(Throwable e);

        //查询系统形象照
        void onQueryBankSuccess(QueryBankBean queryBankBean);
        void onQueryBankFailure(Throwable e);

    }
    interface iModel{
        void getQueryIdData(int doctorId,String sessionId,iQueryIdCallBack callBack);
        void getQueryBankData(int doctorId,String sessionId,iQueryIdCallBack callBack);
        interface iQueryIdCallBack{
            void onQueryIdSuccess(QueryIdBean queryIdBean);
            void onQueryIdFailure(Throwable failure);

            void onQueryBankSuccess(QueryBankBean queryBankBean);
            void onQueryBankFailure(Throwable failure);
        }

    }
    interface iPresenter{
        void getQueryIdPresenter(int doctorId,String sessionId);
        void getQueryBankPresenter(int doctorId,String sessionId);

    }
}
