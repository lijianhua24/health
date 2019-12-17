package com.wd.homemodel.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.homemodel.R;
import com.wd.homemodel.adapter.GengDuoAdapter;
import com.wd.homemodel.app.App;
import com.wd.homemodel.bean.InfoSectionBean;
import com.wd.homemodel.contract.HomeContract;
import com.wd.homemodel.presenter.SectionPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionActivity extends BaseActivity<SectionPresenter> implements HomeContract.InfoSectionContreact.IView {


    @BindView(R.id.section_recy_gengduo)
    RecyclerView sectionRecyGengduo;

    @Override
    protected SectionPresenter providePresenter() {
        return new SectionPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        sectionRecyGengduo.setLayoutManager(new LinearLayoutManager(this));
        int i1 = App.sharedPreferences.getInt("i1", 0);
       if (i1!=0){
           mPresenter.getInfoSectionPresenter(i1 + "", 1, 100);
       }else {
           mPresenter.getInfoSectionPresenter("1", 1, 100);
       }
        sectionRecyGengduo.setNestedScrollingEnabled(false);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_section;
    }

    @Override
    public void onInfoSectionSuccess(Object data) {
        InfoSectionBean infoSectionBean = (InfoSectionBean) data;
        List<InfoSectionBean.ResultBean> result = infoSectionBean.getResult();
        GengDuoAdapter gengDuoAdapter = new GengDuoAdapter(this, result);
        sectionRecyGengduo.setAdapter(gengDuoAdapter);

    }

    @Override
    public void onInfoSectionFailure(Throwable e) {

    }


}
