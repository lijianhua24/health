package com.wd.health.presenter;

import com.wd.health.bean.GetUserInfoByIdBean;
import com.wd.health.bean.ModifyHeadPicBean;
import com.wd.health.bean.ModifyNickNameBean;
import com.wd.health.bean.UpdateUserSexBean;
import com.wd.health.contract.ModifyHeadPicContract;
import com.wd.health.model.ModifyHeadPicModel;
import com.wd.mylibrary.Base.BasePresenter;

import okhttp3.MultipartBody;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/17 16:28
 */
public class ModifyHeadPicPresenter extends BasePresenter<ModifyHeadPicContract.IView> implements ModifyHeadPicContract.IPresenter {

    private ModifyHeadPicModel model;

    @Override
    protected void initModel() {
        model = new ModifyHeadPicModel();
    }


    @Override
    public void ModifyHeadPicPresenter(String userId, String sessionId, MultipartBody.Part image) {
        model.getModifyHeadPicData(userId, sessionId, image, new ModifyHeadPicContract.IModel.ModifyHeadPicContractCallback() {
            @Override
            public void onModifyHeadPicSuccess(ModifyHeadPicBean bean) {
                getView().onModifyHeadPicSuccess(bean);
            }

            @Override
            public void onUpdateSexSuccess(UpdateUserSexBean bean) {

            }

            @Override
            public void onModifyNickNameSuccess(ModifyNickNameBean bean) {

            }

            @Override
            public void onGetUserInfoSuccess(GetUserInfoByIdBean bean) {

            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void geUpdateUserSexPresenter(String userId, String sessionId, String sex) {
        model.geUpdateUserSexData(userId, sessionId, sex, new ModifyHeadPicContract.IModel.ModifyHeadPicContractCallback() {
            @Override
            public void onModifyHeadPicSuccess(ModifyHeadPicBean bean) {

            }

            @Override
            public void onUpdateSexSuccess(UpdateUserSexBean bean) {
                getView().onUpdateSexSuccess(bean);
            }

            @Override
            public void onModifyNickNameSuccess(ModifyNickNameBean bean) {

            }

            @Override
            public void onGetUserInfoSuccess(GetUserInfoByIdBean bean) {

            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void getModifyNickNamePresenter(String userId, String sessionId, String nickName) {
        model.geModifyNickNameData(userId, sessionId, nickName, new ModifyHeadPicContract.IModel.ModifyHeadPicContractCallback() {
            @Override
            public void onModifyHeadPicSuccess(ModifyHeadPicBean bean) {

            }

            @Override
            public void onUpdateSexSuccess(UpdateUserSexBean bean) {

            }

            @Override
            public void onModifyNickNameSuccess(ModifyNickNameBean bean) {
                getView().onModifyNickNameSuccess(bean);
            }

            @Override
            public void onGetUserInfoSuccess(GetUserInfoByIdBean bean) {

            }

            @Override
            public void onFailure(Throwable e) {
                getView().onFailure(e);
            }
        });
    }

    @Override
    public void getGetUserInfoPresenter(String userId, String sessionId) {
            model.getGetUserInfoData(userId, sessionId, new ModifyHeadPicContract.IModel.ModifyHeadPicContractCallback() {
                @Override
                public void onModifyHeadPicSuccess(ModifyHeadPicBean bean) {

                }

                @Override
                public void onUpdateSexSuccess(UpdateUserSexBean bean) {

                }

                @Override
                public void onModifyNickNameSuccess(ModifyNickNameBean bean) {

                }

                @Override
                public void onGetUserInfoSuccess(GetUserInfoByIdBean bean) {
                    getView().onGetUserInfoSuccess(bean);
                }

                @Override
                public void onFailure(Throwable e) {
                    getView().onFailure(e);
                }
            });
    }
}
