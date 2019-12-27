package com.wd.health.utils;

import com.wd.health.bean.CircleListShowBean;
import com.wd.health.bean.CommentCircleBean;
import com.wd.health.bean.DepartmentListBean;
import com.wd.health.bean.DoTaskBean;
import com.wd.health.bean.KeywordSearchBean;
import com.wd.health.bean.OpinionBean;
import com.wd.health.bean.PatientDetailsBean;
import com.wd.health.bean.QueryCommentBean;
import com.wd.health.bean.ReleasePatientsBean;
import com.wd.health.bean.UnitDiseaseBean;
import com.wd.health.bean.UploadPatientBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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
    //查询病友圈详情
    @GET("user/sickCircle/v1/findSickCircleCommentList")
    Observable<QueryCommentBean>querycommentbean(
            @Header("userId")       int userId,
            @Header("sessionId")    String sessionId,
            @Query("sickCircleId") int sickCircleId,
            @Query("page") int page,
            @Query("count") int count
    );
    //病友圈发表评论
    @FormUrlEncoded
    @POST("user/sickCircle/verify/v1/publishComment")
    Observable<CommentCircleBean>commentcirclebean(
            @Header("userId")       int userId,
            @Header("sessionId")    String sessionId,
            @Field("sickCircleId") int sickCircleId,
            @Field("content") String content
    );
    //上传用户病友圈相关图片
    @Multipart
    @POST("user/sickCircle/verify/v1/uploadSickCirclePicture")
    Observable<UploadPatientBean> UploadPatient(@Header("userId") int userId,
                                                @Header("sessionId") String sessionId,
                                                @Query("sickCircleId") int sickCircleId,
                                                @Part List<MultipartBody.Part> parts
    );

    //做任务
    @POST("user/verify/v1/doTask")
    Observable<DoTaskBean> dotaskbean(
            @Header("userId")       int userId,
            @Header("sessionId")    String sessionId,
            @Query("taskId")        int taskId
    );

    //发表观点
    @PUT("user/sickCircle/verify/v1/expressOpinion")
    Observable<OpinionBean> opinionbean(
            @Header("userId")       int userId,
            @Header("sessionId")    String sessionId,
            @Query("commentId")        int commentId,
            @Query("opinion")        int opinion
    );
}
