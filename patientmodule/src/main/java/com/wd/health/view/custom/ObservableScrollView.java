package com.wd.health.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/13<p>
 * <p>更改时间：2019/12/13<p>
 */
public class ObservableScrollView extends ScrollView {
    //在Java代码时候回调
    public ObservableScrollView(Context context) {
        this(context,null);
    }

    //在XML里面回调
    public ObservableScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    //在XML设置样式的时候回掉
    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //ScollView自带的一个滑动监听方法,新的X,Y坐标,和旧的X,Y坐标
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        //ScrollView暴露一个接口
        if (mScrollViewListener !=null){
            mScrollViewListener.onScrollChanged(this,l,t,oldl,oldt);
        }
    }

    //定义一个接口
    public interface ScrollViewListener{
        void onScrollChanged(ObservableScrollView scrollView, int l, int t, int oldl, int oldt);
    }

    //自己的监听对象
    private ScrollViewListener mScrollViewListener;

    //提供一个方法,去让外界设置ScrollViewListener监听对象
    public void setScrollViewListener ( ScrollViewListener scrollViewListener){
        mScrollViewListener =scrollViewListener;
    }

}
