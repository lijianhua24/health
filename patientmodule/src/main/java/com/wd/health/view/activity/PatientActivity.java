package com.wd.health.view.activity;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.view.custom.NoScrollViewPager;
import com.wd.health.view.fragment.PatientFragment;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.Test.ToastUtils;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
public class PatientActivity extends BaseActivity {


    @BindView(R2.id.patient_pager)
    NoScrollViewPager patient_pager;
    private ArrayList<Fragment> list;
    private ArrayList<String> name;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }
    @Override
    protected void initData() {
        if ( hasNetwork()){
            ToastUtils.show("有网");
        }else {
            showNoNetTip();
        }
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
        list = new ArrayList<>();
        name = new ArrayList<>();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_patient;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
