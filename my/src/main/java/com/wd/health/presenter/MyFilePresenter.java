package com.wd.health.presenter;


/*
 *@auther:胡明明
 *@Date: 2019/12/23
 *@Time:21:18
 *@Description:我的档案presenter
 **/

import com.wd.health.bean.user.AddArchivesBean;
import com.wd.health.bean.user.DeleteArchivesBean;
import com.wd.health.bean.user.UpdateArchivesBean;
import com.wd.health.bean.user.UserArchivesBean;
import com.wd.health.bean.user.UserArchivesPictureBean;
import com.wd.health.contract.MyFileContract;
import com.wd.health.model.MyFileModel;
import com.wd.mylibrary.Base.BasePresenter;

import java.util.Map;

import okhttp3.MultipartBody;

public class MyFilePresenter extends BasePresenter<MyFileContract.Iview> implements MyFileContract.Ipresenter {

    private MyFileModel myFileModel;

    @Override
    protected void initModel() {
        myFileModel = new MyFileModel();
    }

    @Override
    public void getPresenterarchives(String userId, String sessionId) {
            myFileModel.getModelarchives(userId, sessionId, new MyFileContract.Imodel.ImodelCallback() {
                @Override
                public void onMyFilesuccess(UserArchivesBean userArchivesBean) {
                    getView().onMyFilesuccess(userArchivesBean);
                }

                @Override
                public void onMyFilesuccess(DeleteArchivesBean deleteArchivesBean) {

                }

                @Override
                public void onMyFilesuccess(AddArchivesBean addArchivesBean) {

                }

                @Override
                public void onMyFilesuccess(UpdateArchivesBean updateArchivesBean) {

                }

                @Override
                public void onMyFilesuccess(UserArchivesPictureBean userArchivesPictureBean) {

                }

                @Override
                public void onMyFileFaiure(Throwable e) {
                    getView().onMyFileFaiure(e);
                }
            });
    }

    @Override
    public void getPresenterdeletearchives(String userId, String sessionId, String archivesId) {
            myFileModel.getModeldeletearchives(userId, sessionId, archivesId, new MyFileContract.Imodel.ImodelCallback() {
                @Override
                public void onMyFilesuccess(UserArchivesBean userArchivesBean) {

                }

                @Override
                public void onMyFilesuccess(DeleteArchivesBean deleteArchivesBean) {
                        getView().onMyFilesuccess(deleteArchivesBean);
                }

                @Override
                public void onMyFilesuccess(AddArchivesBean addArchivesBean) {

                }

                @Override
                public void onMyFilesuccess(UpdateArchivesBean updateArchivesBean) {

                }

                @Override
                public void onMyFilesuccess(UserArchivesPictureBean userArchivesPictureBean) {

                }

                @Override
                public void onMyFileFaiure(Throwable e) {
                    getView().onMyFileFaiure(e);
                }
            });
    }

    @Override
    public void getPresenteraddarchives(String userId, String sessionId, Map<String, Object> map) {
            myFileModel.getModeladdarchives(userId, sessionId, map, new MyFileContract.Imodel.ImodelCallback() {
                @Override
                public void onMyFilesuccess(UserArchivesBean userArchivesBean) {

                }

                @Override
                public void onMyFilesuccess(DeleteArchivesBean deleteArchivesBean) {

                }

                @Override
                public void onMyFilesuccess(AddArchivesBean addArchivesBean) {
                    getView().onMyFilesuccess(addArchivesBean);
                }

                @Override
                public void onMyFilesuccess(UpdateArchivesBean updateArchivesBean) {

                }

                @Override
                public void onMyFilesuccess(UserArchivesPictureBean userArchivesPictureBean) {

                }

                @Override
                public void onMyFileFaiure(Throwable e) {
                    getView().onMyFileFaiure(e);
                }
            });
    }

    @Override
    public void getPresenterupdatearchives(String userId, String sessionId, Map<String, Object> map) {
                myFileModel.getModelupdatearchives(userId, sessionId, map, new MyFileContract.Imodel.ImodelCallback() {
                    @Override
                    public void onMyFilesuccess(UserArchivesBean userArchivesBean) {

                    }

                    @Override
                    public void onMyFilesuccess(DeleteArchivesBean deleteArchivesBean) {

                    }

                    @Override
                    public void onMyFilesuccess(AddArchivesBean addArchivesBean) {

                    }

                    @Override
                    public void onMyFilesuccess(UpdateArchivesBean updateArchivesBean) {
                        getView().onMyFilesuccess(updateArchivesBean);
                    }

                    @Override
                    public void onMyFilesuccess(UserArchivesPictureBean userArchivesPictureBean) {

                    }

                    @Override
                    public void onMyFileFaiure(Throwable e) {
                        getView().onMyFileFaiure(e);
                    }
                });
    }

    @Override
    public void getPresenterpicture(String userId, String sessionId, MultipartBody.Part picture) {
                myFileModel.getModelpicture(userId, sessionId, picture, new MyFileContract.Imodel.ImodelCallback() {
                    @Override
                    public void onMyFilesuccess(UserArchivesBean userArchivesBean) {

                    }

                    @Override
                    public void onMyFilesuccess(DeleteArchivesBean deleteArchivesBean) {

                    }

                    @Override
                    public void onMyFilesuccess(AddArchivesBean addArchivesBean) {

                    }

                    @Override
                    public void onMyFilesuccess(UpdateArchivesBean updateArchivesBean) {

                    }

                    @Override
                    public void onMyFilesuccess(UserArchivesPictureBean userArchivesPictureBean) {
                        getView().onMyFilesuccess(userArchivesPictureBean);
                    }

                    @Override
                    public void onMyFileFaiure(Throwable e) {
                        getView().onMyFileFaiure(e);
                    }
                });
    }
}
