package com.wd.health.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;

import com.wd.health.R;
import com.wd.health.view.custom.NoScrollViewPager;
import com.wd.health.view.fragment.PatientFragment;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private NoScrollViewPager patient_pager;
    private ArrayList<Fragment> list;
    private ArrayList<String> name;
    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {
        list.add(new PatientFragment());
        patient_pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
        patient_pager = findViewById(R.id.patient_pager);
        list = new ArrayList<>();
        name = new ArrayList<>();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }
}
