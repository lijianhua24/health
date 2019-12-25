package com.wd.health.utils;

import com.wd.health.bean.AddSignBean;
import com.wd.health.bean.CheckCodeBean;
import com.wd.health.bean.EndInquiryBean;
import com.wd.health.bean.FocusBean;
import com.wd.health.bean.GetUserInfoByIdBean;
import com.wd.health.bean.HealthyCurrencyBean;
import com.wd.health.bean.InvitationCodeBean;
import com.wd.health.bean.JKZXBean;
import com.wd.health.bean.ModifyHeadPicBean;
import com.wd.health.bean.ModifyNickNameBean;
import com.wd.health.bean.PerfectUserInfoBean;
import com.wd.health.bean.RegBean;
import com.wd.health.bean.ResetUserPwdBean;
import com.wd.health.bean.SystemMessageBean;
import com.wd.health.bean.UpdateUserPwdBean;
import com.wd.health.bean.UpdateUserSexBean;
import com.wd.health.bean.UserConsumptionRecordBean;
import com.wd.health.bean.UserInvitationCodeBean;
import com.wd.health.bean.UserTaskListBean;
import com.wd.health.bean.VideoBean;
import com.wd.health.bean.WalletYUERBean;

import com.wd.health.bean.WxLogBean;
import com.wd.health.bean.user.AddArchivesBean;
import com.wd.health.bean.user.BindUserBankCardBean;
import com.wd.health.bean.user.DeleteArchivesBean;
import com.wd.health.bean.user.DoTaskBean;
import com.wd.health.bean.user.EmailBean;
import com.wd.health.bean.user.FindUserSignBean;
import com.wd.health.bean.user.HistoryBean;
import com.wd.health.bean.user.IdBean;
import com.wd.health.bean.user.InquiryRecordBean;
import com.wd.health.bean.user.LoginBean;
import com.wd.health.bean.user.MySickCircleCommentListBean;
import com.wd.health.bean.user.MySickCircleListBean;
import com.wd.health.bean.user.ReceiveReWardBean;
import com.wd.health.bean.user.UpdateArchivesBean;
import com.wd.health.bean.user.UserArchivesBean;
import com.wd.health.bean.user.UserArchivesPictureBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/12 20:01
 */
public interface IApi {
    //登录
    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<LoginBean> getLogin(@Field("email") String email, @Field("pwd") String pwd);

    //获取验证码
    @FormUrlEncoded
    @POST("user/v1/sendOutEmailCode")
    Observable<EmailBean> getEmail(@Field("email") String email);

    //注册
    @FormUrlEncoded
    @POST("user/v1/register")
    Observable<RegBean> getReg(@Field("email") String email, @Field("code") String code, @Field("pwd1") String pwd1
            , @Field("pwd2") String pwd2, @Field("invitationCode") String invitationCode);

    //忘记密码
    @PUT("user/v1/resetUserPwd")
    Observable<ResetUserPwdBean> resetUserPwd(@Query("email") String email, @Query("pwd1") String pwd1, @Query("pwd2") String pwd2);

    //查询我的关注
    @GET("user/verify/v1/findUserDoctorFollowList")
    Observable<FocusBean> getFocus(@Header("userId") String userId, @Header("sessionId") String sessionId,
                                   @Query("page") String page, @Query("count") String count);

    //查询健康咨询
    @GET("user/verify/v1/findUserInfoCollectionList")
    Observable<JKZXBean> getJKZX(@Header("userId") String userId, @Header("sessionId") String sessionId,
                                 @Query("page") String page, @Query("count") String count);

    //查查询用户系统通知列表
    @GET("user/verify/v1/findSystemNoticeList")
    Observable<SystemMessageBean> getSystemMessage(@Header("userId") String userId, @Header("sessionId") String sessionId,
                                                   @Query("page") String page, @Query("count") String count);

    //查询用户H币通知列表
    @GET("user/verify/v1/findHealthyCurrencyNoticeList")
    Observable<HealthyCurrencyBean> getHealthyCurrency(@Header("userId") String userId, @Header("sessionId") String sessionId,
                                                       @Query("page") String page, @Query("count") String count);

    //查询用户问诊通知列表
    @GET("user/verify/v1/findInquiryNoticeList")
    Observable<HealthyCurrencyBean> getInquiryNoticeList(@Header("userId") String userId, @Header("sessionId") String sessionId,
                                                         @Query("page") String page, @Query("count") String count);

    //用户签到
    @POST("user/verify/v1/addSign")
    Observable<AddSignBean> getaddSign(@Header("userId") String userId, @Header("sessionId") String sessionId);

    //做任务
    @GET("user/verify/v1/findUserTaskList")
    Observable<UserTaskListBean> getUserTaskList(@Header("userId") String userId, @Header("sessionId") String sessionId);

    //我的钱包
    @GET("user/verify/v1/findUserWallet")
    Observable<WalletYUERBean> getWallet(@Header("userId") String userId, @Header("sessionId") String sessionId);

    //校验验证码
    @FormUrlEncoded
    @POST("user/v1/checkCode")
    Observable<CheckCodeBean> getCheckCode(@Field("email") String email, @Field("code") String code);

    //查询用户消费记录
    @GET("user/verify/v1/findUserConsumptionRecordList")
    Observable<UserConsumptionRecordBean> getUserConsumptionRecord(@Header("userId") String userId, @Header("sessionId") String sessionId,
                                                                   @Query("page") String page, @Query("count") String count);

    //生成邀请码
    @POST("user/verify/v1/makeInvitationCode")
    Observable<InvitationCodeBean> getMakeCode(@Header("userId") String userId, @Header("sessionId") String sessionId);

    //查询邀请码
    @GET("user/verify/v1/findUserInvitationCode")
    Observable<UserInvitationCodeBean> getUserCode(@Header("userId") String userId, @Header("sessionId") String sessionId);

    //上传头像
    @Multipart

    @POST("user/verify/v1/modifyHeadPic")
    Observable<ModifyHeadPicBean> getModifyHeadPic(@Header("userId") String userId, @Header("sessionId") String sessionId, @Part MultipartBody.Part image);

    //修改性别
    @PUT("user/verify/v1/updateUserSex")
    Observable<UpdateUserSexBean> UpdateUserSex(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("sex") String sex);

    //修改昵称
    @PUT("user/verify/v1/modifyNickName")
    Observable<ModifyNickNameBean> ModifyNickName(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("nickName") String nickName);

    //修改密码
    @PUT("user/verify/v1/updateUserPwd")
    Observable<UpdateUserPwdBean> UpdateUserPwd(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("oldPwd") String oldPwd, @Query("newPwd") String newPwd);

    //根据ID查询用户信息
    @GET("user/verify/v1/getUserInfoById")
    Observable<GetUserInfoByIdBean> getUserInfo(@Header("userId") String userId, @Header("sessionId") String sessionId);

    //完善用户信息
    @PUT("user/verify/v1/perfectUserInfo")
    Observable<PerfectUserInfoBean> PerfectUserInfo(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("age") String age, @Query("height") String height, @Query("weight") String weight);

    //查询用户连续签到天数
    @GET("user/verify/v1/findUserSign")
    Observable<FindUserSignBean> FindUserSign(@Header("userId") String userId, @Header("sessionId") String sessionId);//

    //做任务
    @POST("user/verify/v1/doTask")
    Observable<DoTaskBean> doTask(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("taskId") String taskId);

    //领取任务奖励
    @POST("user/verify/v1/receiveReward")
    Observable<ReceiveReWardBean> ReceiveReward(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("taskId") String taskId);

    //微信登录
    @FormUrlEncoded
    @POST("user/v1/weChatLogin")
    Observable<WxLogBean> getWxlog(@Field("wxCode") String wxCode);

    //充值
    @POST("user/verify/v1/recharge")
    Observable<ReceiveReWardBean> recharge(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("money") String money, @Query("payType") String payType);


    //我的病友圈
    //http://172.17.8.100/health/user/sickCircle/verify/v1/findMySickCircleList?page=1&count=5
    @GET("user/sickCircle/verify/v1/findMySickCircleList")
    Observable<MySickCircleListBean> getMySickCircleList(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("page") String page, @Query("count") String count);

    //查询我的病友圈帖子的评论列表
    //http://172.17.8.100/health/user/sickCircle/verify/v1/findMySickCircleCommentList?sickCircleId=1796&page=1&count=5
    @GET("user/sickCircle/verify/v1/findMySickCircleCommentList")
    Observable<MySickCircleCommentListBean> getMySickCircleCommentList(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("sickCircleId") String sickCircleId, @Query("page") String page, @Query("count") String count);

    //绑定身份证
    @FormUrlEncoded
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("user/verify/v1/bindUserIdCard")
    Observable<IdBean> IdCard(@Header("userId") String userId, @Header("sessionId") String sessionId,
                              @Query("userId") String Id, @Query("name") String name, @Query("sex") String sex, @Query("nation") String nation
            , @Query("birthday") String birthday, @Query("address") String address, @Query("idNumber") String idNumber, @Query("issueOffice") String issueOffice);

    //绑定银行卡
    @POST("user/verify/v1/bindUserBankCard")
    Observable<BindUserBankCardBean> bindUserBankCard(@Header("userId") String userId, @Header("sessionId") String sessionId,
                                                      @Query("bankCardNumber") String bankCardNumber, @Query("bankName") String bankName, @Query("bankCardType") String bankCardType);

    //查询历史问诊聊天记录
    @GET("user/inquiry/verify/v1/findHistoryInquiryRecord")
    Observable<HistoryBean> history(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("page") String page, @Query("count") String count);

    //查看当前问诊
    @GET("user/inquiry/verify/v1/findCurrentInquiryRecord")
    Observable<InquiryRecordBean> InquiryRecord(@Header("userId") String userId, @Header("sessionId") String sessionId);//

    //结束问诊
    @PUT("user/inquiry/verify/v1/endInquiry")
    Observable<EndInquiryBean> endInquiry(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("recordId") String recordId);

    //我的档案
    //查看自己的档案
    @GET("user/verify/v1/findUserArchives")
    Observable<UserArchivesBean> getarchives(@Header("userId") String userId, @Header("sessionId") String sessionId);

    //删除档案
    @DELETE("user/verify/v1/deleteUserArchives")
    Observable<DeleteArchivesBean> getdeleteUserArchives(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("archivesId") String archivesId);
    //用户修改档案

    @PUT("user/verify/v1/updateUserArchives")
    Observable<UpdateArchivesBean> getupdateUserArchives(@Header("userId") String userId, @Header("sessionId") String sessionId, @Body Map<String, Object> map);

    //用户添加档案
    @POST("user/verify/v1/addUserArchives")
    Observable<AddArchivesBean> getaddUserArchives(@Header("userId") String userId, @Header("sessionId") String sessionId, @Body Map<String, Object> map);

    //用户档案上传图片
    @Multipart
    @POST("user/verify/v1/uploadArchivesPicture")
    Observable<UserArchivesPictureBean> getpicture(@Header("userId") String userId, @Header("sessionId") String sessionId, @Part MultipartBody.Part picture);

    //查询用户购买视频列表
    @GET("user/verify/v1/findUserVideoBuyList")
    Observable<VideoBean> video(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("page") String page, @Query("count") String count);//

    //取消关注医生
    @DELETE("user/inquiry/verify/v1/cancelFollow")
    Observable<DeleteArchivesBean> cancelFollow(@Header("userId") String userId, @Header("sessionId") String sessionId, @Query("doctorId") String doctorId);

}
