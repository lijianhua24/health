package com.wd.health.view.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;

import com.wd.health.R;
import com.ypx.imagepicker.utils.PStatusBarUtil;
import com.ypx.imagepicker.views.base.SingleCropControllerView;
import com.ypx.imagepicker.widget.cropimage.CropImageView;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/27<p>
 * <p>更改时间：2019/12/27<p>
 */
public class CustomCropControllerView extends SingleCropControllerView {
    private ImageView mCloseImg;
    private ImageView mOkImg;

    public CustomCropControllerView(Context context) {
        super(context);
    }

    /**
     * @return item布局id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.layout_custom_crop;
    }

    /**
     * @param view 初始化view
     */
    @Override
    protected void initView(View view) {
        mCloseImg = view.findViewById(R.id.mCloseImg);
        mOkImg = view.findViewById(R.id.mOkImg);
        mCloseImg.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    public void setStatusBar() {
        PStatusBarUtil.fullScreenWithCheckNotch((Activity) getContext(), Color.BLACK);
    }

    @Override
    public View getCompleteView() {
        return mOkImg;
    }

    @Override
    public void setCropViewParams(CropImageView cropImageView, MarginLayoutParams params) {
        params.bottomMargin = dp(60);
    }

}

