package com.wd.doctor.view;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.doctor.R;
import com.wd.doctor.adapter.SearchSuffererAdapter;
import com.wd.doctor.bean.SearchSuffererBean;
import com.wd.doctor.contract.SearchSuffererContract;
import com.wd.doctor.presenter.SearchSuffererPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchSuffererActivity extends BaseActivity<SearchSuffererPresenter> implements SearchSuffererContract.iView {

    private static final String TAG = "SearchSuffererActivity";
    @BindView(R.id.search_iv_back)
    ImageView searchIvBack;
    @BindView(R.id.search_et_shu)
    EditText searchEtShu;
    @BindView(R.id.search_tv_sousuo)
    TextView searchTvSousuo;
    @BindView(R.id.search_rv_recycler)
    RecyclerView searchRvRecycler;
    @BindView(R.id.search_iv_shione)
    ImageView searchIvShione;
    @BindView(R.id.search_tv_shitwo)
    TextView searchTvShitwo;

    @Override
    protected SearchSuffererPresenter providePresenter() {
        return new SearchSuffererPresenter();
    }

    @Override
    protected void initData() {
        searchIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //模糊查询
        searchTvSousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = searchEtShu.getText().toString().trim();
                mPresenter.getSearchSuffererPresenter(trim);
            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_search_sufferer;
    }

    @Override
    public void onSearchSuffererSuccess(SearchSuffererBean searchSuffererBean) {
        Logger.d("", "" + searchSuffererBean.getMessage());
        List<SearchSuffererBean.ResultBean> result = searchSuffererBean.getResult();
        if (!result.isEmpty()) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            searchRvRecycler.setLayoutManager(linearLayoutManager);
            SearchSuffererAdapter searchSuffererAdapter = new SearchSuffererAdapter(result, this);
            searchRvRecycler.setAdapter(searchSuffererAdapter);

            searchRvRecycler.setVisibility(View.VISIBLE);
            searchIvShione.setVisibility(View.GONE);
            searchTvShitwo.setVisibility(View.GONE);
        } else {
            searchRvRecycler.setVisibility(View.GONE);
            searchIvShione.setVisibility(View.VISIBLE);
            searchTvShitwo.setVisibility(View.VISIBLE);
            String trim = searchEtShu.getText().toString().trim();
            searchTvShitwo.setText("抱歉！没有找到  “"+trim+"”  的相关病友圈");
        }

    }

    @Override
    public void onSearchSuffererFailure(Throwable e) {

    }

    /**
     * EditText 禁止换行
     * 设置相关监听器
     */
    private void setListener(){
        searchEtShu.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                return (event.getKeyCode()==KeyEvent.KEYCODE_ENTER);

            }
        });
    }

}
