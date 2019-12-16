package com.wd.health.contract;

import com.wd.health.bean.CircleListShowBean;
import com.wd.health.bean.DepartmentListBean;
import com.wd.health.bean.KeywordSearchBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/13<p>
 * <p>更改时间：2019/12/13<p>
 */
public interface IContract  extends IBaseView {
    interface iView extends IBaseView {
        void DepartmentListsuccess(DepartmentListBean departmentListBean);
        void DepartmentListFailure(Throwable e);

        void CircleListShowsuccess(CircleListShowBean circleListShowBean);
        void CircleListShowFailure(Throwable e);

        void KeywordSearchsuccess(KeywordSearchBean keywordSearchBean);
        void KeywordSearchFailure(Throwable e);
    }

    interface iModel{
        void getDepartmentList(iDepartmentListCallBack callBack);
        void getKeywordSearch(String keyWord,iDepartmentListCallBack callBack);
        void getCircleListShow(int departmentId,int page,int count,iDepartmentListCallBack callBack);
        interface iDepartmentListCallBack{
            void DepartmentListsuccess(DepartmentListBean departmentListBean);
            void DepartmentListFailure(Throwable e);

            void CircleListShowsuccess(CircleListShowBean circleListShowBean);
            void CircleListShowFailure(Throwable e);


            void KeywordSearchsuccess(KeywordSearchBean keywordSearchBean);
            void KeywordSearchFailure(Throwable e);
        }
    }
    interface iPresenter{
        void getDepartmentListPresenter();
        void getCircleListShowPresenter(int departmentId,int page,int count);
        void getKeywordSearchPresenter(String keyWord);
    }
}
