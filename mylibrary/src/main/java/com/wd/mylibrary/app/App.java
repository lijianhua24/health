package com.wd.mylibrary.app;

import android.app.Application;


import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.wd.mylibrary.Test.ToastUtils;


public class App extends Application {
    //全局上下文
    private static App sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        ToastUtils.init(sContext);
        Fresco.initialize(this);
       /* ARouter.openLog();
        ARouter.openDebug();*/
        ARouter.init(this);

    }

    public static App getAppContext() {
        return sContext;
    }
}
