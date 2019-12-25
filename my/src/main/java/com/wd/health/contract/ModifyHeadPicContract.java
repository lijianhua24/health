package com.wd.health.contract;

import com.wd.health.bean.GetUserInfoByIdBean;
import com.wd.health.bean.ModifyHeadPicBean;
import com.wd.health.bean.ModifyNickNameBean;
import com.wd.health.bean.UpdateUserSexBean;
import com.wd.mylibrary.Base.IBaseView;

import okhttp3.MultipartBody;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:04
 */
public interface ModifyHeadPicContract {
    interface IView extends IBaseView {
        void onModifyHeadPicSuccess(ModifyHeadPicBean bean);

        void onUpdateSexSuccess(UpdateUserSexBean bean);

        void onModifyNickNameSuccess(ModifyNickNameBean bean);

        void onGetUserInfoSuccess(GetUserInfoByIdBean bean);
        void onFailure(Throwable e);
    }

    interface IModel {
        void getModifyHeadPicData(String userId, String sessionId, MultipartBody.Part image, ModifyHeadPicContractCallback modifyHeadPicContractCallback);

        void geUpdateUserSexData(String userId, String sessionId, String sex, ModifyHeadPicContractCallback modifyHeadPicContractCallback);

        void geModifyNickNameData(String userId, String sessionId, String nickName, ModifyHeadPicContractCallback modifyHeadPicContractCallback);
        void getGetUserInfoData(String userId, String sessionId, ModifyHeadPicContractCallback modifyHeadPicContractCallback);

        interface ModifyHeadPicContractCallback {
            void onModifyHeadPicSuccess(ModifyHeadPicBean bean);

            void onUpdateSexSuccess(UpdateUserSexBean bean);

            void onModifyNickNameSuccess(ModifyNickNameBean bean);

            void onGetUserInfoSuccess(GetUserInfoByIdBean bean);

            void onFailure(Throwable e);
        }
    }

    interface IPresenter {
        void ModifyHeadPicPresenter(String userId, String sessionId, MultipartBody.Part image);

        void geUpdateUserSexPresenter(String userId, String sessionId, String sex);

        void getModifyNickNamePresenter(String userId, String sessionId, String nickName);

        void getGetUserInfoPresenter(String userId, String sessionId);
    }
}
