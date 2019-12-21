package com.wd.health.contract;

import com.wd.health.bean.HealthBuyBean;
import com.wd.health.bean.HealthSortBean;
import com.wd.health.bean.VideoSortBean;
import com.wd.mylibrary.Base.IBaseView;

public interface IContract extends IBaseView {
    interface iView extends IBaseView {
        void healthSortsuccess(HealthSortBean healthSortBean);

        void healthSortFailure(Throwable e);

        void VideoSortsuccess(VideoSortBean videoSortBean);

        void VideoSortFailure(Throwable e);

        void HealthBuysuccess(HealthBuyBean healthBuyBean);

        void HealthBuyFailure(Throwable e);

        void HealthCollectionsuccess(HealthBuyBean healthBuyBean);

        void HealthCollectionFailure(Throwable e);
    }

    interface iModel {
        void getHealthSort(iDepartmentListCallBack callBack);

        void getVideoSort(int userId, String sessionId, int categoryId, int page, int count, iDepartmentListCallBack callBack);

        void getHealthBuy(int userId, String sessionId, int videoId, int price, iDepartmentListCallBack callBack);

        void getHealthCollection(int userId, String sessionId, int videoId, iDepartmentListCallBack callBack);

        interface iDepartmentListCallBack {
            void healthSortsuccess(HealthSortBean healthSortBean);

            void healthSortFailure(Throwable e);

            void VideoSortsuccess(VideoSortBean videoSortBean);

            void VideoSortFailure(Throwable e);

            void HealthBuysuccess(HealthBuyBean healthBuyBean);

            void HealthBuyFailure(Throwable e);

            void HealthCollectionsuccess(HealthBuyBean healthBuyBean);

            void HealthCollectionFailure(Throwable e);
        }

    }

    interface iPresenter {
        void getHealthSort();

        void getVideoSort(int userId, String sessionId, int categoryId, int page, int count);

        void getHealthBuy(int userId, String sessionId, int videoId, int price);

        void getHealthCollection(int userId, String sessionId, int videoId);
    }
}
