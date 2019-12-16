package com.wd.health.presenter;

import com.wd.health.bean.CircleListShowBean;
import com.wd.health.bean.DepartmentListBean;
import com.wd.health.bean.KeywordSearchBean;
import com.wd.health.contract.IContract;
import com.wd.health.model.DepartmentListModel;
import com.wd.mylibrary.Base.BasePresenter;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/13<p>
 * <p>更改时间：2019/12/13<p>
 */
public class DepartmentListPresenter extends BasePresenter<IContract.iView> implements IContract.iPresenter {

    private DepartmentListModel departmentListModel;

    @Override
    public void getDepartmentListPresenter() {
        departmentListModel.getDepartmentList(new IContract.iModel.iDepartmentListCallBack() {
            @Override
            public void DepartmentListsuccess(DepartmentListBean departmentListBean) {
                getView().DepartmentListsuccess(departmentListBean);
            }

            @Override
            public void DepartmentListFailure(Throwable e) {
                getView().DepartmentListFailure(e);
            }

            @Override
            public void CircleListShowsuccess(CircleListShowBean circleListShowBean) {

            }

            @Override
            public void CircleListShowFailure(Throwable e) {

            }

            @Override
            public void KeywordSearchsuccess(KeywordSearchBean keywordSearchBean) {

            }

            @Override
            public void KeywordSearchFailure(Throwable e) {

            }


        });
    }

    @Override
    public void getCircleListShowPresenter(int departmentId, int page, int count) {
        departmentListModel.getCircleListShow(departmentId, page, count, new IContract.iModel.iDepartmentListCallBack() {
            @Override
            public void DepartmentListsuccess(DepartmentListBean departmentListBean) {

            }

            @Override
            public void DepartmentListFailure(Throwable e) {

            }

            @Override
            public void CircleListShowsuccess(CircleListShowBean circleListShowBean) {
                getView().CircleListShowsuccess(circleListShowBean);

            }

            @Override
            public void CircleListShowFailure(Throwable e) {
                getView().CircleListShowFailure(e);

            }

            @Override
            public void KeywordSearchsuccess(KeywordSearchBean keywordSearchBean) {

            }

            @Override
            public void KeywordSearchFailure(Throwable e) {

            }
        });
    }

    @Override
    public void getKeywordSearchPresenter(String keyWord) {
        departmentListModel.getKeywordSearch(keyWord, new IContract.iModel.iDepartmentListCallBack() {
            @Override
            public void DepartmentListsuccess(DepartmentListBean departmentListBean) {

            }

            @Override
            public void DepartmentListFailure(Throwable e) {

            }

            @Override
            public void CircleListShowsuccess(CircleListShowBean circleListShowBean) {

            }

            @Override
            public void CircleListShowFailure(Throwable e) {

            }

            @Override
            public void KeywordSearchsuccess(KeywordSearchBean keywordSearchBean) {
                getView().KeywordSearchsuccess(keywordSearchBean);
            }

            @Override
            public void KeywordSearchFailure(Throwable e) {
                getView().KeywordSearchFailure(e);
            }
        });
    }


    @Override
    protected void initModel() {
        departmentListModel = new DepartmentListModel();
    }
}
