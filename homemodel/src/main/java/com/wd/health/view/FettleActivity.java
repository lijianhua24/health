package com.wd.health.view;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.tabs.TabLayout;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.fragment.commonFragment.DepartmentFragment;
import com.wd.health.fragment.commonFragment.FettleFragment;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;

import java.util.ArrayList;

import butterknife.BindView;

public class FettleActivity extends BaseActivity {

    @BindView(R2.id.home_touxiang)
    SimpleDraweeView homeTouxiang;
    @BindView(R2.id.home_xiaoxi)
    ImageView homeXiaoxi;
    @BindView(R2.id.fettle_tab)
    TabLayout fettleTab;
    @BindView(R2.id.fettle_pager)
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
        return R2.layout.activity_fettle;
    }

}
