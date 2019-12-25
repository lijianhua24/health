package com.wd.health.model;

import com.wd.health.bean.GetUserInfoByIdBean;
import com.wd.health.bean.ModifyHeadPicBean;
import com.wd.health.bean.ModifyNickNameBean;
import com.wd.health.bean.UpdateUserSexBean;
import com.wd.health.contract.ModifyHeadPicContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

import okhttp3.MultipartBody;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/17 16:25
 */
public class ModifyHeadPicModel implements ModifyHeadPicContract.IModel {
    @Override
    public void getModifyHeadPicData(String userId, String sessionId, MultipartBody.Part image, ModifyHeadPicContractCallback modifyHeadPicContractCallback) {
        RetrofitManager.getInstance().create().getModifyHeadPic(userId, sessionId,image)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<ModifyHeadPicBean>() {
                    @Override
                    public void onNext(ModifyHeadPicBean bean) {
                       modifyHeadPicContractCallback.onModifyHeadPicSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                       modifyHeadPicContractCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void geUpdateUserSexData(String userId, String sessionId, String sex, ModifyHeadPicContractCallback modifyHeadPicContractCallback) {
        RetrofitManager.getInstance().create().UpdateUserSex(userId, sessionId,sex)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<UpdateUserSexBean>() {
                    @Override
                    public void onNext(UpdateUserSexBean bean) {
                        modifyHeadPicContractCallback.onUpdateSexSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        modifyHeadPicContractCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void geModifyNickNameData(String userId, String sessionId, String nickName, ModifyHeadPicContractCallback modifyHeadPicContractCallback) {
        RetrofitManager.getInstance().create().ModifyNickName(userId, sessionId,nickName)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<ModifyNickNameBean>() {
                    @Override
                    public void onNext(ModifyNickNameBean bean) {
                        modifyHeadPicContractCallback.onModifyNickNameSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        modifyHeadPicContractCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void getGetUserInfoData(String userId, String sessionId, ModifyHeadPicContractCallback modifyHeadPicContractCallback) {
        RetrofitManager.getInstance().create().getUserInfo(userId, sessionId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<GetUserInfoByIdBean>() {
                    @Override
                    public void onNext(GetUserInfoByIdBean bean) {
                        modifyHeadPicContractCallback.onGetUserInfoSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        modifyHeadPicContractCallback.onFailure(e);
                    }
                });
    }
}
