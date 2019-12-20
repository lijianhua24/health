package com.wd.health.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.adapter.GengDuoAdapter;
import com.wd.health.app.App;
import com.wd.health.bean.InfoSectionBean;
import com.wd.health.contract.HomeContract;
import com.wd.health.presenter.SectionPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import java.util.List;

import butterknife.BindView;

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
