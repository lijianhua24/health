package com.wd.health.model;

import com.wd.health.bean.CircleListShowBean;
import com.wd.health.bean.DepartmentListBean;
import com.wd.health.bean.KeywordSearchBean;
import com.wd.health.bean.ReleasePatientsBean;
import com.wd.health.bean.UnitDiseaseBean;
import com.wd.health.contract.IContract;
import com.wd.health.utils.ApiServers;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

import java.util.Map;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/13<p>
 * <p>更改时间：2019/12/13<p>
 */
public class DepartmentListModel implements IContract.iModel {

    @Override
    public void getDepartmentList(iDepartmentListCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .departmentlistbean()
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<DepartmentListBean>() {
                    @Override
                    public void onNext(DepartmentListBean departmentListBean) {
                        callBack.DepartmentListsuccess(departmentListBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.DepartmentListFailure(e);
                    }
                });
    }

    @Override
    public void getReleasePatients(int userId, String sessionId, Map<String, Object> map, iDepartmentListCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .releasepatientsbean(userId, sessionId, map)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<ReleasePatientsBean>() {
                    @Override
                    public void onNext(ReleasePatientsBean releasePatientsBean) {
                        callBack.ReleasePatientssuccess(releasePatientsBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.ReleasePatientsFailure(e);
                    }
                });
    }

    @Override
    public void getKeywordSearch(String keyWord, iDepartmentListCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .keywordsearchbean(keyWord)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<KeywordSearchBean>() {
                    @Override
                    public void onNext(KeywordSearchBean keywordSearchBean) {
                        callBack.KeywordSearchsuccess(keywordSearchBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.KeywordSearchFailure(e);
                    }
                });
    }

    @Override
    public void getUnitDisease(int departmentId, iDepartmentListCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .unitdiseasebean(departmentId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<UnitDiseaseBean>() {
                    @Override
                    public void onNext(UnitDiseaseBean unitDiseaseBean) {
                        callBack.UnitDiseasesuccess(unitDiseaseBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.UnitDiseaseFailure(e);
                    }
                });
    }

    @Override
    public void getCircleListShow(int departmentId, int page, int count, iDepartmentListCallBack callBack) {
        RetrofitManager.getInstance().create(ApiServers.class)
                .circlelistshowbean(departmentId, page, count)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<CircleListShowBean>() {
                    @Override
                    public void onNext(CircleListShowBean circleListShowBean) {
                        callBack.CircleListShowsuccess(circleListShowBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.CircleListShowFailure(e);
                    }
                });
    }
}
