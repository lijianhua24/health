package com.wd.health.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.recyclerview.widget.LinearLayoutManager;

/**
 * @author: 张恩
 * @description:
 * @date :2019/12/16 9:11
 */
public class MyRecycleview extends LinearLayoutManager {

    private boolean isScrollEnabled = true;

    public MyRecycleview(Context context) {
        super(context);
    }

    public MyRecycleview(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MyRecycleview(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setScrollEnabled(boolean flag) {
        this.isScrollEnabled = flag;
    }

    @Override
    public boolean canScrollVertically() {
        return isScrollEnabled && super.canScrollVertically();
    }


}
