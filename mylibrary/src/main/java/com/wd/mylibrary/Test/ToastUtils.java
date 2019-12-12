package com.wd.mylibrary.Test;

import android.content.Context;
import android.widget.Toast;

//吐司
public class ToastUtils {
    private static Toast mToast;

    public static void init(Context context) {
        mToast = Toast.makeText(context, null, Toast.LENGTH_SHORT);
    }

    public static void show(int resId) {
        mToast.setText(resId);
        mToast.show();
    }

    public static void show(String charSequence) {
        mToast.setText(charSequence);
        mToast.show();
    }
}