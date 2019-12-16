package com.wd.mymodule;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;

import java.util.ArrayList;

public class MyActivity extends BaseActivity {

    private ArrayList<Fragment> list;
    private ArrayList<String> name;
    private NoScrollViewPager noScrollViewPager;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {
        list.add(new MyFragment());
        noScrollViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
    }

    @Override
    protected void initView() {
        noScrollViewPager = findViewById(R.id.my_viewpager);
        list = new ArrayList<>();
        name = new ArrayList<>();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_my;
    }
}
