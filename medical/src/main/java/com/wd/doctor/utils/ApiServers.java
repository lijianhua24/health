package com.wd.doctor.utils;


import com.wd.doctor.bean.DoctorInforBean;
import com.wd.doctor.bean.EnquiryBean;
import com.wd.doctor.bean.LoginBean;
import com.wd.doctor.bean.ResidencyBean;
import com.wd.doctor.bean.SearchSuffererBean;
import com.wd.doctor.bean.SuffererDetailBean;
import com.wd.doctor.bean.SuffererOutBean;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServers {
    //登录
    //http://172.17.8.100/health/doctor/v1/login
    @FormUrlEncoded
    @POST("doctor/v1/login")
    Observable<LoginBean> getLogin(@Field("email") String email, @Field("pwd") String pwd);
    //根据医生id查询医生信息
    //http://172.17.8.100/health/doctor/verify/v1/findDoctorById
    @GET("doctor/verify/v1/findDoctorById")
    Observable<DoctorInforBean> getDoctorInfor(@Header("doctorId")int doctorId,
                                               @Header("sessionId")String sessionId);
    //申请入驻
    //http://172.17.8.100/health/doctor/v1/applyJoin
    @Headers({"Content-type:application/json;charset=UTF-8"})
    @POST("doctor/v1/applyJoin")
    Observable<ResidencyBean> getResidency(@Body RequestBody route);

    //查询科室列表
    //http://172.17.8.100/health/share/knowledgeBase/v1/findDepartment
    @GET("share/knowledgeBase/v1/findDepartment")
    Observable<EnquiryBean> getEnquiry();
    //病友圈列表展示
    //http://172.17.8.100/health/doctor/sickCircle/v1/findSickCircleList
    @GET("doctor/sickCircle/v1/findSickCircleList")
    Observable<SuffererDetailBean> getSuffererDetail(@Query("departmentId")int departmentId,
                                                     @Query("page")int page,
                                                     @Query("count")int count);
    //查询病友圈详情
    //http://172.17.8.100/health/doctor/sickCircle/v1/findSickCircleInfo
    @GET("doctor/sickCircle/v1/findSickCircleInfo")
    Observable<SuffererOutBean> getSuffererOut(@Header("doctorId")int doctorId,
                                               @Header("sessionId")String sessionId,
                                               @Query("sickCircleId")int sickCircleId);
    //根据关键词查询病友圈
    //http://172.17.8.100/health/doctor/sickCircle/v1/searchSickCircle
    @GET("doctor/sickCircle/v1/searchSickCircle")
    Observable<SearchSuffererBean> getSearchSufferer(@Query("keyWord")String keyWord);
}
