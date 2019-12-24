package com.wd.health.utils;

import com.wd.health.bean.AttentionBean;
import com.wd.health.bean.BannerBean;
import com.wd.health.bean.CheckDoctorsBean;
import com.wd.health.bean.CmedicinesBean;
import com.wd.health.bean.ConsultBean;
import com.wd.health.bean.CurrentBean;
import com.wd.health.bean.DepartmentBean;
import com.wd.health.bean.DoctorDetailsBean;
import com.wd.health.bean.DrugBean;
import com.wd.health.bean.FindBean;
import com.wd.health.bean.InfoSectionBean;
import com.wd.health.bean.PopularBean;
import com.wd.health.bean.PushMessageBean;
import com.wd.health.bean.RecordingBean;
import com.wd.health.bean.SearchBean;
import com.wd.health.bean.SectionBean;
import com.wd.health.bean.SpyDetailsBean;
import com.wd.health.bean.SubjectBean;
import com.wd.health.bean.UnitDiseaseBean;
import com.wd.health.bean.UnsubscribeBean;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiServers {
    //banner
    @GET("share/v1/bannersShow")
    Observable<BannerBean> getBanner();

    //首页搜索
    @GET("share/v1/homePageSearch")
    Observable<SearchBean> getSearBean(@Query("keyWord") String keyWord);

    //查询健康资讯板块
    @GET("share/information/v1/findInformationPlateList")
    Observable<SectionBean> getSection();

    //查询科室列表
    @GET("share/knowledgeBase/v1/findDepartment")
    Observable<DepartmentBean> getDepartment();

    //根据资讯板块查询资讯列表
    @GET("share/information/v1/findInformationList")
    Observable<InfoSectionBean> getInFoSection(@Query("plateId") String plateId,@Query("page") Integer page,@Query("count") Integer count);

    //根据科室查询对应病症
    @GET("share/knowledgeBase/v1/findDiseaseCategory")
    Observable<UnitDiseaseBean> getUnitsease(@Query("departmentId") Integer departmentId);

    //药品科目分类列表查询
    @GET("share/knowledgeBase/v1/findDrugsCategoryList")
    Observable<SubjectBean> getSubject();

    //根据药品类目查询常见药品
    @GET("share/knowledgeBase/v1/findDrugsKnowledgeList")
    Observable<DrugBean> getDrug(@Query("drugsCategoryId") Integer drugsCategoryId,@Query("page") Integer page,@Query("count") Integer count);

    //查询常见药品详情
    @GET("share/knowledgeBase/v1/findDrugsKnowledge")
    Observable<FindBean> getFind(@Query("id") Integer id);

    //查询常见病症详情
    @GET("share/knowledgeBase/v1/findDiseaseKnowledge")
    Observable<CmedicinesBean> getCmedicines(@Query("id") Integer id);

    //资讯详情
    @GET("share/information/v1/findInformation")
    Observable<SpyDetailsBean> getSpyDetal(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("infoId")Integer infoId);

    //热门搜索
    @GET("share/v1/popularSearch")
    Observable<PopularBean> getPopular();

    //查询问诊医生列表
    @GET("user/inquiry/v1/findDoctorList")
    Observable<CheckDoctorsBean> getCheckDoctors(@Query("deptId" ) Integer deptId,@Query("condition") Integer condition,@Query("sortBy") Integer sortBy,@Query("page") Integer page,@Query("count") Integer count);

    //查询医生明细信息
    @GET("user/inquiry/v1/findDoctorInfo")
    Observable<DoctorDetailsBean> getDoctorDetails(@Header("userId") String userId,@Header("sessionId") String sessionId,@Query("doctorId") String doctorId);

    //关注医生
    @POST("user/inquiry/verify/v1/followDoctor")
    Observable<AttentionBean> getAttention(@Header("userId") String userId,@Header("sessionId") String sessionId,@Query("doctorId") String doctorId);

    //取消关注医生
    @DELETE("user/inquiry/verify/v1/cancelFollow")
    Observable<UnsubscribeBean> getUnsubscribe(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("doctorId") String doctorId);

    //咨询医生
    @PUT("user/inquiry/verify/v1/consultDoctor")
    Observable<ConsultBean> getConsult(@Header("userId") String userId,@Header("sessionId") String sessionId,@Query("doctorId") String doctorId);

    //用户查看当前问诊
    @GET("user/inquiry/verify/v1/findCurrentInquiryRecord")
    Observable<CurrentBean> getCurrent(@Header("userId") String userId,@Header("sessionId") String sessionId);

    //查询历史问诊聊天记录
    @GET("user/inquiry/verify/v1/findInquiryRecordList")
    Observable<RecordingBean> getRecording(@Header("userId") String userId,@Header("sessionId") String sessionId,@Query("inquiryId") int inquiryId,@Query("page") int page,@Query("count") int count);

    //问诊-发送消息（文本消息）
    @FormUrlEncoded
    @POST("user/inquiry/verify/v1/pushMessage")
    Observable<PushMessageBean> getMessage(@Header("userId") String userId, @Header("sessionId") String sessionId, @Field("inquiryId") int inquiryId,@Field("content") String content,@Field("type") int type,@Field("doctorId") int doctorId);

    //
}
