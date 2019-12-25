package com.wd.health.contract;


/*
 *@auther:胡明明
 *@Date: 2019/12/23
 *@Time:20:49
 *@Description:我的文档契约类
 **/


import com.wd.health.bean.user.AddArchivesBean;
import com.wd.health.bean.user.DeleteArchivesBean;
import com.wd.health.bean.user.UpdateArchivesBean;
import com.wd.health.bean.user.UserArchivesBean;
import com.wd.health.bean.user.UserArchivesPictureBean;
import com.wd.mylibrary.Base.IBaseView;

import java.util.Map;

import okhttp3.MultipartBody;

public interface MyFileContract {
    //view
    interface Iview extends IBaseView {
        //查看自己的档案
        void onMyFilesuccess(UserArchivesBean userArchivesBean);
        //删除档案
        void onMyFilesuccess(DeleteArchivesBean deleteArchivesBean);
        //用户添加档案
        void onMyFilesuccess(AddArchivesBean addArchivesBean);
        //用户修改档案
        void onMyFilesuccess(UpdateArchivesBean updateArchivesBean);
        //用户档案上传图片
        void onMyFilesuccess(UserArchivesPictureBean userArchivesPictureBean);

        void onMyFileFaiure(Throwable e);

    }
    interface Imodel{
        //查看自己的档案
        void getModelarchives(String userId, String sessionId, ImodelCallback imodelCallback);
        //删除档案
        void getModeldeletearchives(String userId, String sessionId, String archivesId, ImodelCallback imodelCallback);
        //用户添加档案
        void getModeladdarchives(String userId, String sessionId, Map<String, Object> map, ImodelCallback imodelCallback);
        //用户修改档案
        void getModelupdatearchives(String userId, String sessionId, Map<String, Object> map, ImodelCallback imodelCallback);
        //用户档案上传图片
        void getModelpicture(String userId, String sessionId, MultipartBody.Part picture, ImodelCallback imodelCallback);

        interface ImodelCallback{
            //查看自己的档案
            void onMyFilesuccess(UserArchivesBean userArchivesBean);
            //删除档案
            void onMyFilesuccess(DeleteArchivesBean deleteArchivesBean);
            //用户添加档案
            void onMyFilesuccess(AddArchivesBean addArchivesBean);
            //用户修改档案
            void onMyFilesuccess(UpdateArchivesBean updateArchivesBean);
            //用户档案上传图片
            void onMyFilesuccess(UserArchivesPictureBean userArchivesPictureBean);

            void onMyFileFaiure(Throwable e);
        }
    }
    interface Ipresenter{
        //查看自己的档案
        void getPresenterarchives(String userId, String sessionId);
        //删除档案
        void getPresenterdeletearchives(String userId, String sessionId, String archivesId);
        //用户添加档案
        void getPresenteraddarchives(String userId, String sessionId, Map<String, Object> map);
        //用户修改档案
        void getPresenterupdatearchives(String userId, String sessionId, Map<String, Object> map);
        //用户档案上传图片
        void getPresenterpicture(String userId, String sessionId, MultipartBody.Part picture);

    }

}
