package com.wd.homemodel.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.drawee.backends.pipeline.Fresco;

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
    }
}
