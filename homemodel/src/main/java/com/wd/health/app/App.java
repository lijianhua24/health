package com.wd.health.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.wd.health.view.JgActivity;

import cn.jpush.im.android.api.JMessageClient;

public class App extends com.wd.mylibrary.app.App {
    public static Context context;
    public static SharedPreferences sharedPreferences1,sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
            context = this;
        Fresco.initialize(context);
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        sharedPreferences1 = getSharedPreferences("user1", Context.MODE_PRIVATE);
        JMessageClient.init(this,true);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("userId","456");
        edit.putString("sessionId","1577245543320456");
        edit.commit();

    }
}
