package com.wd.health.presenter;

import com.wd.health.contract.IMContract;
import com.wd.health.model.HomeModel;
import com.wd.mylibrary.Base.BasePresenter;

public class ImPresenterPresenter extends BasePresenter<IMContract.IView> implements IMContract.IPresenter {

    private HomeModel homeModel;

    @Override
    public void getCurrentPresenter(String userId, String sessionId) {
        homeModel.getCurrentModel(userId, sessionId, new IMContract.IModel.IModelCurrentCallback() {
            @Override
            public void onCurrentSuccess(Object data) {
                getView().onCurrentSuccess(data);
            }

            @Override
            public void onCurrentFailure(Throwable e) {
                getView().onCurrentFailure(e);
            }
        });
    }

    @Override
    public void getRecordingPresenter(String userId, String sessionId, int inquiryId, int page, int count) {
            homeModel.getRecordingModel(userId, sessionId, inquiryId, page, count, new IMContract.IModel.IModelRecordingCallback() {
                @Override
                public void onRecordingSuccess(Object data) {
                    getView().onRecordingSuccess(data);
                }

                @Override
                public void onRecordingFailure(Throwable e) {
                    getView().onRecordingFailure(e);
                }
            });
    }

    @Override
    public void getMessagePresenter(String userId, String sessionId, int inquiryId, String content, int type, int doctorId) {
            homeModel.getMessageDataModel(userId, sessionId, inquiryId, content, type, doctorId, new IMContract.IModel.IModelMessageCallback() {
                @Override
                public void onMessageSuccess(Object data) {
                    getView().onMessageSuccess(data);
                }

                @Override
                public void onMessageFailure(Throwable e) {
                    getView().onMessageFailure(e);
                }
            });
    }

    @Override
    protected void initModel() {
        homeModel = new HomeModel();
    }
}
