package com.wd.mymodule.fragment;
import com.wd.mylibrary.Base.BaseFragment;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mymodule.R;
/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/16<p>
 * <p>更改时间：2019/12/16<p>
 */
public class MyFragment  extends BaseFragment {
    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.myfragment;
    }
}
