package com.wd.health.presenter;

import android.util.Log;

import com.wd.health.bean.CircleListShowBean;
import com.wd.health.bean.DepartmentListBean;
import com.wd.health.bean.DoTaskBean;
import com.wd.health.bean.KeywordSearchBean;
import com.wd.health.bean.ReleasePatientsBean;
import com.wd.health.bean.UnitDiseaseBean;
import com.wd.health.bean.UploadPatientBean;
import com.wd.health.contract.IContract;
import com.wd.health.model.DepartmentListModel;
import com.wd.mylibrary.Base.BasePresenter;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;

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

            @Override
            public void ReleasePatientssuccess(ReleasePatientsBean ReleasePatientsBean) {
            }

            @Override
            public void ReleasePatientsFailure(Throwable e) {

            }

            @Override
            public void UnitDiseasesuccess(UnitDiseaseBean unitDiseaseBean) {

            }

            @Override
            public void UnitDiseaseFailure(Throwable e) {

            }

            @Override
            public void uploadPatientsuccess(UploadPatientBean uploadPatientBean) {

            }

            @Override
            public void uploadPatientFailure(Throwable e) {

            }

            @Override
            public void DoTasksuccess(DoTaskBean doTaskBean) {

            }

            @Override
            public void DoTaskFailure(Throwable e) {

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

            @Override
            public void ReleasePatientssuccess(ReleasePatientsBean ReleasePatientsBean) {

            }

            @Override
            public void ReleasePatientsFailure(Throwable e) {

            }

            @Override
            public void UnitDiseasesuccess(UnitDiseaseBean unitDiseaseBean) {

            }

            @Override
            public void UnitDiseaseFailure(Throwable e) {

            }

            @Override
            public void uploadPatientsuccess(UploadPatientBean uploadPatientBean) {

            }

            @Override
            public void uploadPatientFailure(Throwable e) {

            }

            @Override
            public void DoTasksuccess(DoTaskBean doTaskBean) {

            }

            @Override
            public void DoTaskFailure(Throwable e) {

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

            @Override
            public void ReleasePatientssuccess(ReleasePatientsBean ReleasePatientsBean) {

            }

            @Override
            public void ReleasePatientsFailure(Throwable e) {

            }

            @Override
            public void UnitDiseasesuccess(UnitDiseaseBean unitDiseaseBean) {

            }

            @Override
            public void UnitDiseaseFailure(Throwable e) {

            }

            @Override
            public void uploadPatientsuccess(UploadPatientBean uploadPatientBean) {

            }

            @Override
            public void uploadPatientFailure(Throwable e) {

            }

            @Override
            public void DoTasksuccess(DoTaskBean doTaskBean) {

            }

            @Override
            public void DoTaskFailure(Throwable e) {

            }
        });
    }

    @Override
    public void getReleasePatientsPresenter(int userId, String sessionId, Map<String, Object> map) {
        departmentListModel.getReleasePatients(userId, sessionId, map, new IContract.iModel.iDepartmentListCallBack() {
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

            }

            @Override
            public void KeywordSearchFailure(Throwable e) {

            }

            @Override
            public void ReleasePatientssuccess(ReleasePatientsBean ReleasePatientsBean) {
                getView().ReleasePatientssuccess(ReleasePatientsBean);
            }

            @Override
            public void ReleasePatientsFailure(Throwable e) {
                getView().ReleasePatientsFailure(e);
            }

            @Override
            public void UnitDiseasesuccess(UnitDiseaseBean unitDiseaseBean) {

            }

            @Override
            public void UnitDiseaseFailure(Throwable e) {

            }

            @Override
            public void uploadPatientsuccess(UploadPatientBean uploadPatientBean) {

            }

            @Override
            public void uploadPatientFailure(Throwable e) {

            }

            @Override
            public void DoTasksuccess(DoTaskBean doTaskBean) {

            }

            @Override
            public void DoTaskFailure(Throwable e) {

            }
        });
    }

    @Override
    public void getUnitDiseasePresenter(int departmentId) {
        departmentListModel.getUnitDisease(departmentId, new IContract.iModel.iDepartmentListCallBack() {
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

            }

            @Override
            public void KeywordSearchFailure(Throwable e) {

            }

            @Override
            public void ReleasePatientssuccess(ReleasePatientsBean ReleasePatientsBean) {

            }

            @Override
            public void ReleasePatientsFailure(Throwable e) {

            }

            @Override
            public void UnitDiseasesuccess(UnitDiseaseBean unitDiseaseBean) {
                getView().UnitDiseasessuccess(unitDiseaseBean);
            }

            @Override
            public void UnitDiseaseFailure(Throwable e) {
                getView().UnitDiseaseFailure(e);
            }

            @Override
            public void uploadPatientsuccess(UploadPatientBean uploadPatientBean) {

            }

            @Override
            public void uploadPatientFailure(Throwable e) {

            }

            @Override
            public void DoTasksuccess(DoTaskBean doTaskBean) {

            }

            @Override
            public void DoTaskFailure(Throwable e) {

            }
        });
    }

    @Override
    public void getuploadPatient(int userId, String sessionId, int sickCircleId, List<MultipartBody.Part> parts) {
        if (isViewAttached()) {
            departmentListModel.getuploadPatient(userId, sessionId, sickCircleId, parts, new IContract.iModel.iDepartmentListCallBack() {
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

                }

                @Override
                public void KeywordSearchFailure(Throwable e) {

                }

                @Override
                public void ReleasePatientssuccess(ReleasePatientsBean ReleasePatientsBean) {

                }

                @Override
                public void ReleasePatientsFailure(Throwable e) {

                }

                @Override
                public void UnitDiseasesuccess(UnitDiseaseBean unitDiseaseBean) {

                }

                @Override
                public void UnitDiseaseFailure(Throwable e) {

                }

                @Override
                public void uploadPatientsuccess(UploadPatientBean uploadPatientBean) {
                    getView().uploadPatientsuccess(uploadPatientBean);
                }

                @Override
                public void uploadPatientFailure(Throwable e) {
                    getView().uploadPatientFailure(e);
                }

                @Override
                public void DoTasksuccess(DoTaskBean doTaskBean) {

                }

                @Override
                public void DoTaskFailure(Throwable e) {

                }
            });
        }
    }

    @Override
    public void getDoTask(int userId, String sessionId, int taskId) {
        departmentListModel.getDoTask(userId, sessionId, taskId, new IContract.iModel.iDepartmentListCallBack() {
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

            }

            @Override
            public void KeywordSearchFailure(Throwable e) {

            }

            @Override
            public void ReleasePatientssuccess(ReleasePatientsBean ReleasePatientsBean) {

            }

            @Override
            public void ReleasePatientsFailure(Throwable e) {

            }

            @Override
            public void UnitDiseasesuccess(UnitDiseaseBean unitDiseaseBean) {

            }

            @Override
            public void UnitDiseaseFailure(Throwable e) {

            }

            @Override
            public void uploadPatientsuccess(UploadPatientBean uploadPatientBean) {

            }

            @Override
            public void uploadPatientFailure(Throwable e) {

            }

            @Override
            public void DoTasksuccess(DoTaskBean doTaskBean) {
                getView().DoTasksuccess(doTaskBean);
            }

            @Override
            public void DoTaskFailure(Throwable e) {
                getView().DoTaskFailure(e);
            }
        });
    }

    @Override
    protected void initModel() {
        departmentListModel = new DepartmentListModel();
    }
}
