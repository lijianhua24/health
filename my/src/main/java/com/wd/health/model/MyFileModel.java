package com.wd.health.model;


/*
 *@auther:胡明明
 *@Date: 2019/12/23
 *@Time:21:11
 *@Description:我的文档model
 **/


import com.wd.health.bean.user.AddArchivesBean;
import com.wd.health.bean.user.DeleteArchivesBean;
import com.wd.health.bean.user.UpdateArchivesBean;
import com.wd.health.bean.user.UserArchivesBean;
import com.wd.health.bean.user.UserArchivesPictureBean;
import com.wd.health.contract.MyFileContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

import java.util.Map;

import okhttp3.MultipartBody;

public class MyFileModel implements MyFileContract.Imodel {
    @Override
    public void getModelarchives(String userId, String sessionId, ImodelCallback imodelCallback) {

        RetrofitManager.getInstance().create().getarchives(userId, sessionId)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<UserArchivesBean>() {
                    @Override
                    public void onNext(UserArchivesBean userArchivesBean) {
                        imodelCallback.onMyFilesuccess(userArchivesBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        imodelCallback.onMyFileFaiure(e);
                    }
                });
    }

    @Override
    public void getModeldeletearchives(String userId, String sessionId, String archivesId, ImodelCallback imodelCallback) {
        RetrofitManager.getInstance().create().getdeleteUserArchives(userId, sessionId, archivesId)
                .compose(CommonSchedulers.<DeleteArchivesBean>io2main())
                .subscribe(new CommonObserver<DeleteArchivesBean>() {
                    @Override
                    public void onNext(DeleteArchivesBean deleteArchivesBean) {
                        imodelCallback.onMyFilesuccess(deleteArchivesBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        imodelCallback.onMyFileFaiure(e);
                    }
                });
    }

    @Override
    public void getModeladdarchives(String userId, String sessionId, Map<String, Object> map, ImodelCallback imodelCallback) {
        RetrofitManager.getInstance().create()
                .getaddUserArchives(userId, sessionId, map)
                .compose(CommonSchedulers.<AddArchivesBean>io2main())
                .subscribe(new CommonObserver<AddArchivesBean>() {
                    @Override
                    public void onNext(AddArchivesBean addArchivesBean) {
                        imodelCallback.onMyFilesuccess(addArchivesBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                            imodelCallback.onMyFileFaiure(e);
                    }
                });
    }

    @Override
    public void getModelupdatearchives(String userId, String sessionId, Map<String, Object> map, ImodelCallback imodelCallback) {
        RetrofitManager.getInstance().create().getupdateUserArchives(userId, sessionId, map)
                .compose(CommonSchedulers.<UpdateArchivesBean>io2main())
                .subscribe(new CommonObserver<UpdateArchivesBean>() {
                    @Override
                    public void onNext(UpdateArchivesBean updateArchivesBean) {
                        imodelCallback.onMyFilesuccess(updateArchivesBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        imodelCallback.onMyFileFaiure(e);
                    }
                });
    }

    @Override
    public void getModelpicture(String userId, String sessionId,MultipartBody.Part picture, ImodelCallback imodelCallback) {
        RetrofitManager.getInstance().create()
                .getpicture(userId, sessionId, picture)
                .compose(CommonSchedulers.<UserArchivesPictureBean>io2main())
                .subscribe(new CommonObserver<UserArchivesPictureBean>() {
                    @Override
                    public void onNext(UserArchivesPictureBean userArchivesPictureBean) {
                        imodelCallback.onMyFilesuccess(userArchivesPictureBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        imodelCallback.onMyFileFaiure(e);
                    }
                });
    }
}
