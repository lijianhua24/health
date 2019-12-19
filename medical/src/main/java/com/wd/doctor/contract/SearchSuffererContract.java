package com.wd.doctor.contract;

import com.wd.doctor.bean.SearchSuffererBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/18<p>
 * <p>更改时间：2019/12/18<p>
 */
public interface SearchSuffererContract {
    interface iView extends IBaseView {
        void onSearchSuffererSuccess(SearchSuffererBean searchSuffererBean);
        void onSearchSuffererFailure(Throwable e);
    }
    interface iModel{
        void getSearchSuffererData(String keyWord,iSearchSuffererCallBack callBack);
        interface iSearchSuffererCallBack{
            void onSearchSuffererSuccess(SearchSuffererBean searchSuffererBean);
            void onSearchSuffererFailure(Throwable failure);
        }

    }
    interface iPresenter{
        void getSearchSuffererPresenter(String keyWord);
    }
}
