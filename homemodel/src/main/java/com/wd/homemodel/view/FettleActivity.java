package com.wd.homemodel.view;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.tabs.TabLayout;
import com.wd.homemodel.R;
import com.wd.homemodel.app.App;
import com.wd.homemodel.fragment.commonFragment.DepartmentFragment;
import com.wd.homemodel.fragment.commonFragment.FettleFragment;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FettleActivity extends BaseActivity {

    @BindView(R.id.home_touxiang)
    SimpleDraweeView homeTouxiang;
    @BindView(R.id.home_xiaoxi)
    ImageView homeXiaoxi;
    @BindView(R.id.fettle_tab)
    TabLayout fettleTab;
    @BindView(R.id.fettle_pager)
    ViewPager fettlePager;
    private ArrayList<Fragment> list;
    private ArrayList<String> name;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {
        list.add(new FettleFragment());
        list.add(new DepartmentFragment());
        name.add("常见病状");
        name.add("常用药品");
        fettlePager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return name.get(position);
            }
        });
        fettleTab.setupWithViewPager(fettlePager);
       // fettleTab.setSelectedTabIndicatorHeight(0);
        boolean one = getIntent().getBooleanExtra("one", false);
            if (fettleTab!=null){
                if (one){
                    fettleTab.getTabAt(0).select();
                }else {
                    fettleTab.getTabAt(1).select();
            }
        }

    }

    @Override
    protected void initView() {
        list = new ArrayList<>();
        name = new ArrayList<>();

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_fettle;
    }

}
