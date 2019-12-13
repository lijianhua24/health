package com.wd.homemodel.utils;

import com.wd.homemodel.bean.BannerBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiServers {
    @GET("share/v1/bannersShow")
    Observable<BannerBean> getBanner();
}
