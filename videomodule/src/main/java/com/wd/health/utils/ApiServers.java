package com.wd.health.utils;

import com.wd.health.bean.HealthBuyBean;
import com.wd.health.bean.HealthSortBean;
import com.wd.health.bean.QvideoListBean;
import com.wd.health.bean.VideoSortBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/19<p>
 * <p>更改时间：2019/12/19<p>
 */
public interface ApiServers {

    //    查询健康讲堂类目
    @GET("user/video/v1/findVideoCategoryList")
    Observable<HealthSortBean> healthsprtbean();

    //    根据视频类目查询视频列表
    @GET("user/video/v1/findVideoVoList")
    Observable<VideoSortBean> videosortbean(
            @Header("userId")int userId,
            @Header("sessionId")String sessionId,
            @Query("categoryId") int categoryId,
            @Query("page") int page,
            @Query("count") int count
    );


    @POST("user/video/verify/v1/videoBuy")
    Observable<HealthBuyBean>healthbuybean(
            @Header("userId")int userId,
            @Header("sessionId")String sessionId,
            @Query("videoId") int videoId,
            @Query("price") int price
    );

    @POST("user/video/verify/v1/addUserVideoCollection")
    Observable<HealthBuyBean>HealthCollection(
            @Header("userId")int userId,
            @Header("sessionId")String sessionId,
            @Query("videoId") int  videoId
    );

    @GET("user/video/v1/findVideoCommentList")
    Observable<QvideoListBean>QvideoList(
            @Header("userId")int userId,
            @Header("sessionId")String sessionId,
            @Query("videoId") int videoId
    );

    @POST("user/video/verify/v1/addVideoComment")
    Observable<HealthBuyBean>VideoComment(
            @Header("userId")int userId,
            @Header("sessionId")String sessionId,
            @Query("videoId") int videoId,
            @Query("content") String content
    );

}
