package com.wd.health;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.wd.health.fragment.HomeFragment;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;

import java.util.ArrayList;

import butterknife.BindView;


//@Route(path = "/app/sMainActivity")
public class HomeActivity extends BaseActivity {


    @BindView(R2.id.home_pager)
    ViewPager homePager;

    private ArrayList<Fragment> list;
    private ArrayList<String> name;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {
        list.add(new HomeFragment());
        homePager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
        list = new ArrayList<>();
        name = new ArrayList<>();

    }

    @Override
    protected int provideLayoutId() {
        return R2.layout.activity_home;
    }



}