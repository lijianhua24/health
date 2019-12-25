package com.wd.health.model;

import com.wd.health.bean.AddSignBean;
import com.wd.health.bean.VideoBean;
import com.wd.health.contract.VideoContract;
import com.wd.health.utils.RetrofitManager;
import com.wd.mylibrary.utils.CommonObserver;
import com.wd.mylibrary.utils.CommonSchedulers;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/24 20:06
 */
public class VideoModel implements VideoContract.IModel {
    @Override
    public void getVideoData(String userId, String sessionId, String page, String count, VideoContractCallback videoContractCallback) {
        RetrofitManager.getInstance().create().video(userId, sessionId,page,count)
                .compose(CommonSchedulers.io2main())
                .subscribe(new CommonObserver<VideoBean>() {
                    @Override
                    public void onNext(VideoBean bean) {
                       videoContractCallback.onVideoSuccess(bean);
                    }

                    @Override
                    public void onError(Throwable e) {
                       videoContractCallback.onFailure(e);
                    }
                });
    }
}
