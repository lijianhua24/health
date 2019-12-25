package com.wd.health.contract;
import com.wd.health.bean.AddSignBean;
import com.wd.health.bean.user.DoTaskBean;
import com.wd.health.bean.user.MySickCircleCommentListBean;
import com.wd.health.bean.user.MySickCircleListBean;
import com.wd.mylibrary.Base.IBaseView;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/13 17:04
 */
public interface MySinkCircleContract {
    interface IView extends IBaseView {
        void onMySinkCircleSuccess(MySickCircleListBean bean);

        void onMySickCircleCommentSuccess(MySickCircleCommentListBean bean);

        void onFailure(Throwable e);
    }

    interface IModel {
        void getMySinkCircleData(String userId, String sessionId, String page, String count, MySinkCircleContractCallback mySinkCircleContractCallback);

        void getMySickCircleCommentData(String userId, String sessionId, String sickCircleId, String page, String count, MySinkCircleContractCallback mySinkCircleContractCallback);


        interface MySinkCircleContractCallback {
            void onMySinkCircleSuccess(MySickCircleListBean bean);

            void onMySickCircleCommentSuccess(MySickCircleCommentListBean bean);

            void onFailure(Throwable e);
        }
    }

    interface IPresenter {
        void MySinkCirclePresenter(String userId, String sessionId, String page, String count);
        void MySickCircleCommentPresenter(String userId, String sessionId, String sickCircleId, String page, String count);

    }
}
