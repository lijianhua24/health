package com.wd.homemodel.app;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;

public class App extends com.wd.mylibrary.app.App {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
            context = this;
        Fresco.initialize(context);
    }
}
