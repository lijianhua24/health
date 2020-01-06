package com.wd.health.view;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.PrecautionsAdapter;
import com.wd.health.app.App;
import com.wd.health.bean.FindBean;
import com.wd.health.contract.HomeContract;
import com.wd.health.presenter.FindPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class FindActivity extends BaseActivity<FindPresenter> implements HomeContract.FindContreact.IView {


    @BindView(R2.id.home_touxiang)
    SimpleDraweeView homeTouxiang;
    @BindView(R2.id.home_xiaoxi)
    ImageView homeXiaoxi;
    @BindView(R2.id.find_chengfen)
    TextView findChengfen;
    @BindView(R2.id.find_jinji)
    TextView findJinji;
    @BindView(R2.id.find_zhuzhi)
    TextView findZhuzhi;
    @BindView(R2.id.find_yongliang)
    TextView findYongliang;
    @BindView(R2.id.find_xingzhuang)
    TextView findXingzhuang;
    @BindView(R2.id.find_guige)
    TextView findGuige;
    @BindView(R2.id.find_fanying)
    TextView findFanying;
    @BindView(R2.id.find_tiaojian)
    TextView findTiaojian;
    @BindView(R2.id.find_shixiang)
    RecyclerView findShixiang;
    @BindView(R2.id.find_wenhao)
    TextView findWenhao;
    @BindView(R2.id.find_name)
    TextView findName;

    @Override
    protected FindPresenter providePresenter() {
        return new FindPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        int id = App.sharedPreferences.getInt("id", 0);
        if (id != 0) {
            mPresenter.getFindPresenter(id);
        }
        findShixiang.setNestedScrollingEnabled(false);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_find;
    }


    @SuppressLint("InvalidR2Usage")
    @OnClick({R2.id.home_touxiang, R2.id.home_xiaoxi})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.home_touxiang:
                finish();
                break;
            case R2.id.home_xiaoxi:
                break;
        }
    }

    @Override
    public void onFindSuccess(Object data) {
        FindBean findBean = (FindBean) data;
        FindBean.ResultBean result = findBean.getResult();
        if (result != null) {
            findName.setText(result.getName());
            findChengfen.setText(result.getComponent());
            findJinji.setText(result.getTaboo());
            findZhuzhi.setText(result.getEffect());
            findYongliang.setText(result.getUsage());
            findXingzhuang.setText(result.getShape());
            findGuige.setText(result.getPacking());
            findFanying.setText(result.getSideEffects());
            findTiaojian.setText(result.getStorage());

            String mindMatter = result.getMindMatter();
            String[] split = mindMatter.split("。");
            findShixiang.setLayoutManager(new LinearLayoutManager(this));
            findShixiang.setAdapter(new PrecautionsAdapter(this,split));
            findWenhao.setText(result.getApprovalNumber());

        }
    }

    @Override
    public void onFindFailure(Throwable e) {

    }


}
