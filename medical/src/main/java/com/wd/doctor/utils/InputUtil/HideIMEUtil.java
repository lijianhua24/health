package com.wd.doctor.utils.InputUtil;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

/**
 * 作者:徐晨曦
 * 时间：2019/12/19
 * 描述:Doctor
 * 功能：
 **/
public class HideIMEUtil {
    public static void wrap(Activity activity) {
        ViewGroup contentParent = (ViewGroup) activity.findViewById(android.R.id.content); wrap(contentParent);
    }
    public static void wrap(Fragment fragment) {
        ViewGroup contentParent = (ViewGroup) fragment.getView().getParent();
        wrap(contentParent);
    }
    public static void wrap(ViewGroup contentParent) {
        View content = contentParent.getChildAt(0);
        contentParent.removeView(content);
        ViewGroup.LayoutParams p = content.getLayoutParams();
        AutoHideIMEFrameLayout layout = new AutoHideIMEFrameLayout(content.getContext());layout.addView(content);
        contentParent.addView(layout, new ViewGroup.LayoutParams(p.width, p.height));
    }


}
