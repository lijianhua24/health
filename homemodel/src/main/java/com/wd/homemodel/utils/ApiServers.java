package com.wd.homemodel.utils;

import com.wd.homemodel.bean.BannerBean;
import com.wd.homemodel.bean.DepartmentBean;
import com.wd.homemodel.bean.InfoSectionBean;
import com.wd.homemodel.bean.SearchBean;
import com.wd.homemodel.bean.SectionBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServers {
    //banner
    @GET("share/v1/bannersShow")
    Observable<BannerBean> getBanner();

   /* //首页搜索
    @GET("share/v1/homePageSearch")
    Observable<SearchBean> getSearBean(@Query("keyWord") String keyWord);*/

    //查询健康资讯板块
    @GET("share/information/v1/findInformationPlateList")
    Observable<SectionBean> getSection();

    //查询科室列表
    @GET("share/knowledgeBase/v1/findDepartment")
    Observable<DepartmentBean> getDepartment();

    //根据资讯板块查询资讯列表
    @GET("share/information/v1/findInformationList")
    Observable<InfoSectionBean> getInFoSection(@Query("plateId") String plateId,@Query("page") Integer page,@Query("count") Integer count);

    //
}
