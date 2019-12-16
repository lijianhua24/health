package com.wd.homemodel.utils;

import android.content.Intent;

import com.wd.homemodel.bean.BannerBean;
import com.wd.homemodel.bean.CmedicinesBean;
import com.wd.homemodel.bean.DepartmentBean;
import com.wd.homemodel.bean.DrugBean;
import com.wd.homemodel.bean.FindBean;
import com.wd.homemodel.bean.InfoSectionBean;
import com.wd.homemodel.bean.SearchBean;
import com.wd.homemodel.bean.SectionBean;
import com.wd.homemodel.bean.SpyDetailsBean;
import com.wd.homemodel.bean.SubjectBean;
import com.wd.homemodel.bean.UnitDiseaseBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
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

    //
}
