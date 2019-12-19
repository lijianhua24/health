package com.wd.doctor.presenter;

import com.wd.doctor.bean.SearchSuffererBean;
import com.wd.doctor.contract.SearchSuffererContract;
import com.wd.doctor.model.SearchSuffererModel;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.app.Constant;

/**
 * <p>文件描述：<p>
 * <p>作者：徐晨曦<p>
 * <p>创建时间：2019/12/18<p>
 * <p>更改时间：2019/12/18<p>
 */
public class SearchSuffererPresenter extends BasePresenter<SearchSuffererContract.iView>implements SearchSuffererContract.iPresenter {

    private SearchSuffererModel searchSuffererModel;

    @Override
    public void getSearchSuffererPresenter(String keyWord) {
        searchSuffererModel.getSearchSuffererData(keyWord, new SearchSuffererContract.iModel.iSearchSuffererCallBack() {
            @Override
            public void onSearchSuffererSuccess(SearchSuffererBean searchSuffererBean) {
                if (isViewAttached()) {
                    if (searchSuffererBean != null && Constant.SUCCESS_CODE.equals(searchSuffererBean.getStatus())) {
                        getView().onSearchSuffererSuccess(searchSuffererBean);
                    }else {
                        getView().onSearchSuffererFailure(new Exception("服务器异常"));
                    }
                }
            }

            @Override
            public void onSearchSuffererFailure(Throwable failure) {
                if (isViewAttached()) {
                    getView().onSearchSuffererFailure(failure);
                }
            }
        });
    }

    @Override
    protected void initModel() {
        searchSuffererModel = new SearchSuffererModel();
    }
}
