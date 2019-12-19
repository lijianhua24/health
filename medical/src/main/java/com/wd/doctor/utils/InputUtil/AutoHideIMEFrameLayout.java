package com.wd.doctor.utils.InputUtil;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 作者:徐晨曦
 * 时间：2019/12/19
 * 描述:Doctor  隐藏键盘
 * 功能：
 **/
public class AutoHideIMEFrameLayout extends FrameLayout {
    public AutoHideIMEFrameLayout(@NonNull Context context) {
        super(context);
    }

    public AutoHideIMEFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AutoHideIMEFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            Context context = getContext();
            if (context == null || !(context instanceof Activity)) {
                return super.dispatchTouchEvent(ev);
            }
            Activity activity = (Activity) context;
            View focusView = activity.getCurrentFocus();
            if (focusView != null && shouldHideInputMethod(focusView, ev)) {
                hideInputMethod(focusView);
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    private boolean shouldHideInputMethod(View focusView, MotionEvent event) {
        Rect rect = new Rect();
        focusView.getHitRect(rect);
        return !rect.contains((int) event.getX(), (int) event.getY());
    }
    private void hideInputMethod(View currentFocus) {
        if (currentFocus == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) currentFocus.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
