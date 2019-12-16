package com.wd.health.utils;

import com.wd.health.bean.CircleListShowBean;
import com.wd.health.bean.DepartmentListBean;
import com.wd.health.bean.KeywordSearchBean;
import com.wd.health.bean.PatientDetailsBean;
import com.wd.health.bean.ReleasePatientsBean;
import com.wd.health.bean.UnitDiseaseBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/13<p>
 * <p>更改时间：2019/12/13<p>
 */
public interface ApiServers {

    //查询科室列表
    @GET("share/knowledgeBase/v1/findDepartment")
    Observable<DepartmentListBean> departmentlistbean();


    //病友圈列表展示
    @GET("user/sickCircle/v1/findSickCircleList")
    Observable<CircleListShowBean> circlelistshowbean(
            @Query("departmentId") int departmentId,
            @Query("page") int page,
            @Query("count") int count
    );


    //根据关键词查询病友圈
    @GET("user/sickCircle/v1/searchSickCircle")
    Observable<KeywordSearchBean> keywordsearchbean(
            @Query("keyWord") String keyWord
    );


    //查询病友圈详情
    @GET("user/sickCircle/v1/findSickCircleInfo")
    Observable<PatientDetailsBean> atientdetailsbean(
            @Header("userId")       int userId,
            @Header("sessionId")    String sessionId,
            @Query("sickCircleId") int sickCircleId
    );

    //发布病友圈
    @POST("user/sickCircle/verify/v1/publishSickCircle")
    Observable<ReleasePatientsBean> releasepatientsbean(
            @Header("userId")       int userId,
            @Header("sessionId")    String sessionId,
            @Body Map<String ,Object> map
    );
    //根据科室查询对应病症
    @GET("share/knowledgeBase/v1/findDiseaseCategory")
    Observable<UnitDiseaseBean> unitdiseasebean(
            @Query("departmentId") int  departmentId
    );


}
