package com.wd.health.presenter;

import android.content.Context;
import android.widget.ImageView;

import com.ypx.imagepicker.adapter.multi.MultiGridAdapter;
import com.ypx.imagepicker.bean.ImageItem;
import com.ypx.imagepicker.bean.PickerUiConfig;
import com.ypx.imagepicker.presenter.IMultiPickerBindPresenter;

import java.util.ArrayList;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/20<p>
 * <p>更改时间：2019/12/20<p>
 */
public class WXImgPickerPresenter implements IMultiPickerBindPresenter {
    @Override
    public void displayListImage(ImageView imageView, ImageItem item, int size) {

    }

    @Override
    public void displayPerViewImage(ImageView imageView, String url) {

    }

    @Override
    public PickerUiConfig getUiConfig(Context context) {
        PickerUiConfig config=new PickerUiConfig();

        return null;
    }

    @Override
    public void tip(Context context, String msg) {

    }

    @Override
    public void imageItemClick(Context context, ImageItem imageItem, ArrayList<ImageItem> selectImageList, ArrayList<ImageItem> allSetImageList, MultiGridAdapter adapter) {

    }
}
