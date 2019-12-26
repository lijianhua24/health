package com.wd.doctor.utils;


import com.wd.doctor.bean.DoctorInforBean;
import com.wd.doctor.bean.DoctorWalletBean;
import com.wd.doctor.bean.EnquiryBean;
import com.wd.doctor.bean.ImageQueryBean;
import com.wd.doctor.bean.LoginBean;
import com.wd.doctor.bean.PostReviewBean;
import com.wd.doctor.bean.QueryIdBean;
import com.wd.doctor.bean.ResidencyBean;
import com.wd.doctor.bean.SearchSuffererBean;
import com.wd.doctor.bean.SimagePhotosBean;
import com.wd.doctor.bean.SuffererDetailBean;
import com.wd.doctor.bean.SuffererOutBean;
import com.wd.doctor.bean.ToBindBean;
import com.wd.doctor.bean.UploadPhotoBean;

import java.util.Map;

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
    //发表评论
    //http://172.17.8.100/health/doctor/sickCircle/verify/v1/publishComment
    @POST("doctor/sickCircle/verify/v1/publishComment")
    Observable<PostReviewBean> getPostReview(@Header("doctorId")int doctorId,
                                             @Header("sessionId")String sessionId,
                                             @Query("sickCircleId")int sickCircleId,
                                             @Query("content")String content);
    //根据关键词查询病友圈
    //http://172.17.8.100/health/doctor/sickCircle/v1/searchSickCircle
    @GET("doctor/sickCircle/v1/searchSickCircle")
    Observable<SearchSuffererBean> getSearchSufferer(@Query("keyWord")String keyWord);

    //查询系统形象照
    //http://172.17.8.100/health/doctor/v1/findSystemImagePic
        @GET("doctor/v1/findSystemImagePic")
        Observable<ImageQueryBean> getImageQuery();

    //选择系统提供形象照
    //http://172.17.8.100/health/doctor/verify/v1/chooseImagePic
    @POST("doctor/verify/v1/chooseImagePic")
    Observable<SimagePhotosBean> getSimagePhotos(@Header("doctorId")int doctorId,
                                                 @Header("sessionId")String sessionId,
                                                 @Query("imagePic")String imagePic);

    //上传形象照
    //http://172.17.8.100/health/doctor/verify/v1/uploadImagePic
    @FormUrlEncoded
    @POST("doctor/verify/v1/uploadImagePic")
    Observable<UploadPhotoBean> getUploadPhoto(@Header("doctorId")int doctorId,
                                                @Header("sessionId")String sessionId,
                                                @Field("imagePic") String imagePic);
    //查询医生钱包
    //http://172.17.8.100/health/doctor/verify/v1/findDoctorWallet
    @GET("doctor/verify/v1/findDoctorWallet")
    Observable<DoctorWalletBean> getDoctorWallet(@Header("doctorId")int doctorId,
                                                 @Header("sessionId")String sessionId);

    //绑定身份证
    //http://172.17.8.100/health/doctor/verify/v1/bindDoctorIdCard
    @Headers({"Content-Type: application/json","Accept: application/json"})
    @POST("doctor/verify/v1/bindDoctorIdCard")
    Observable<ToBindBean> getToBind(@Header("doctorId")int doctorId,
                                     @Header("sessionId")String sessionId,
                                     @Body Map<String,Object> BodyMap);
    //绑定银行卡
    //http://172.17.8.100/health/doctor/verify/v1/bindDoctorBankCard
    @POST("octor/verify/v1/bindDoctorBankCard")
    Observable<ToBindBean>getbindBank(@Header("doctorId")int doctorId,
                                      @Header("sessionId")String sessionId,
                                      @Query("bankCardNumber")String bankCardNumber,
                                      @Query("bankName")String bankName,
                                      @Query("bankCardType")int bankCardType);
    //查询医生身份证信息
    //http://172.17.8.100/health/doctor/verify/v1/findDoctorIdCardInfo
    @GET("doctor/verify/v1/findDoctorIdCardInfo")
    Observable<QueryIdBean> getQueryId(@Header("doctorId")int doctorId,
                                       @Header("sessionId")String sessionId);
}
