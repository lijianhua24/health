package com.wd.health.model;

import android.content.Context;
import android.content.SharedPreferences;
public class App  extends com.wd.mylibrary.app.App {
    public static Context context;
    public static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
         sharedPreferences = getSharedPreferences("user", context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("sessionId","1577443752019445");
        edit.putInt("userId",445);
        edit.commit();

    }
}
