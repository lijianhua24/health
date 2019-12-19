package com.wd.doctor.model;

import com.wd.doctor.bean.SearchSuffererBean;
import com.wd.doctor.contract.SearchSuffererContract;
import com.wd.doctor.utils.ApiServers;
import com.wd.doctor.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/18<p>
 * <p>更改时间：2019/12/18<p>
 */
public class SearchSuffererModel implements SearchSuffererContract.iModel {
    @Override
    public void getSearchSuffererData(String keyWord, iSearchSuffererCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .getSearchSufferer(keyWord)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<SearchSuffererBean>() {
                    @Override
                    public void onNext(SearchSuffererBean searchSuffererBean) {
                        callBack.onSearchSuffererSuccess(searchSuffererBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onSearchSuffererFailure(e);
                    }
                });
    }
}
