package com.wd.health.model;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/13<p>
 * <p>更改时间：2019/12/13<p>
 */
public class App  extends com.wd.mylibrary.app.App {
    public static Context context;
    public static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("sessionId","1577771346413445");
        edit.putInt("userId",445);
        edit.commit();
    }
}
