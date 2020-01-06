package com.wd.health.contract;

import com.wd.health.bean.CircleListShowBean;
import com.wd.health.bean.DepartmentListBean;
import com.wd.health.bean.DoTaskBean;
import com.wd.health.bean.KeywordSearchBean;
import com.wd.health.bean.ReleasePatientsBean;
import com.wd.health.bean.UnitDiseaseBean;
import com.wd.health.bean.UploadPatientBean;
import com.wd.mylibrary.Base.IBaseView;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;

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

        void ReleasePatientssuccess(ReleasePatientsBean ReleasePatientsBean);
        void ReleasePatientsFailure(Throwable e);

        void UnitDiseasessuccess(UnitDiseaseBean unitDiseaseBean);
        void UnitDiseaseFailure(Throwable e);

        void uploadPatientsuccess(UploadPatientBean uploadPatientBean);
        void uploadPatientFailure(Throwable e);

        void DoTasksuccess(DoTaskBean doTaskBean);
        void DoTaskFailure(Throwable e);
    }

    interface iModel{
        void getDepartmentList(iDepartmentListCallBack callBack);
        void getReleasePatients(int userId, String sessionId,
                                Map<String ,Object> map,
                                iDepartmentListCallBack callBack);
        void getKeywordSearch(String keyWord,iDepartmentListCallBack callBack);

        void getUnitDisease(int  departmentId,iDepartmentListCallBack callBack);
        void getuploadPatient(int  userId, String sessionId, int sickCircleId, List<MultipartBody.Part> parts, iDepartmentListCallBack callBack);
        void getDoTask(int  userId, String sessionId, int taskId,iDepartmentListCallBack callBack);
        void getCircleListShow(int departmentId,int page,int count,iDepartmentListCallBack callBack);



        interface iDepartmentListCallBack{
            void DepartmentListsuccess(DepartmentListBean departmentListBean);
            void DepartmentListFailure(Throwable e);

            void CircleListShowsuccess(CircleListShowBean circleListShowBean);
            void CircleListShowFailure(Throwable e);


            void KeywordSearchsuccess(KeywordSearchBean keywordSearchBean);
            void KeywordSearchFailure(Throwable e);

            void ReleasePatientssuccess(ReleasePatientsBean ReleasePatientsBean);
            void ReleasePatientsFailure(Throwable e);

            void UnitDiseasesuccess(UnitDiseaseBean unitDiseaseBean);
            void UnitDiseaseFailure(Throwable e);


            void uploadPatientsuccess(UploadPatientBean uploadPatientBean);
            void uploadPatientFailure(Throwable e);

            void DoTasksuccess(DoTaskBean doTaskBean);
            void DoTaskFailure(Throwable e);
        }
    }
    interface iPresenter{
        void getDepartmentListPresenter();
        void getCircleListShowPresenter(int departmentId,int page,int count);
        void getKeywordSearchPresenter(String keyWord);
        void getReleasePatientsPresenter(int userId, String sessionId,
                                         Map<String ,Object>map
        );
        void getUnitDiseasePresenter(int departmentId);

        void getuploadPatient(int  userId, String sessionId, int sickCircleId, List<MultipartBody.Part> parts);
        void getDoTask(int  userId, String sessionId, int taskId);
    }
}
