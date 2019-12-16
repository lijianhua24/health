package com.wd.health.utils;


import com.wd.health.bean.LoginBean;
import com.wd.health.bean.RegisteredBean;
import com.wd.health.bean.SendEmilBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiServers {
    //登录
    //http://172.17.8.100/health/user/v1/login
    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<LoginBean> getLogin(@Field("email")String email,@Field("pwd")String pwd);
    //注册
    //http://172.17.8.100/health/user/v1/register
    @FormUrlEncoded
    @POST("user/v1/register")
    Observable<RegisteredBean> getRegister(@Field("email")String email,
                                           @Field("code")String code,
                                           @Field("pwd1")String pwd1,
                                           @Field("pwd2")String pwd2,
                                           @Field("invitationCode")String invitationCode);
    //发送邮箱验证码
    //http://172.17.8.100/health/user/v1/sendOutEmailCode
    @POST("user/v1/sendOutEmailCode")
    Observable<SendEmilBean> getSendEmil(@Query("email")String email);
}
