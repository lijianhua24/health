package com.wd.health.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.MySickCircleListAdapter;
import com.wd.health.bean.user.MySickCircleCommentListBean;
import com.wd.health.bean.user.MySickCircleListBean;
import com.wd.health.contract.MySinkCircleContract;
import com.wd.health.presenter.MySickCirclePresenter;
import com.wd.mylibrary.Base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySickCircleActivity extends BaseActivity<MySickCirclePresenter> implements MySinkCircleContract.IView {

    @BindView(R2.id.message_fanhui)
    ImageView messageFanhui;
    @BindView(R2.id.mysickcircle_rlv)
    RecyclerView mysickcircleRlv;
    @BindView(R2.id.message_include_img)
    SimpleDraweeView messageIncludeImg;
    @BindView(R2.id.message_include_text)
    TextView messageIncludeText;
    @BindView(R2.id.mysickcircle_button)
    Button mysickcircleButton;
    @BindView(R2.id.message_include_relat)
    RelativeLayout messageIncludeRelat;
    public static final String TAG="MySickCircleActivity";
    @Override
    protected MySickCirclePresenter providePresenter() {
        return new MySickCirclePresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int provideLayoutId() {
        return R2.layout.activity_my_sick_circle;
    }

    @Override
    protected void initView() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String id = sp.getString("id", "");
        String sessionId = sp.getString("sessionId", "");
        mPresenter.MySinkCirclePresenter(id, sessionId,1+"",5+"");
        //bujuguanliqi 布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mysickcircleRlv.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onMySinkCircleSuccess(MySickCircleListBean bean) {
        Log.d(TAG, "onMySinkCircleSuccess: "+bean.getMessage());
        if (bean.getStatus().equals("0000")) {
            List<MySickCircleListBean.ResultBean> result = bean.getResult();
            if (result != null) {
                if (result.isEmpty()) {
                    messageIncludeRelat.setVisibility(View.VISIBLE);
                    mysickcircleRlv.setVisibility(View.GONE);
                } else {
                    messageIncludeRelat.setVisibility(View.GONE);
                    mysickcircleRlv.setVisibility(View.VISIBLE);
                    //适配器
                    MySickCircleListAdapter mySickCircleListAdapter = new MySickCircleListAdapter(this);
                    mySickCircleListAdapter.setList(result);
                    mysickcircleRlv.setAdapter(mySickCircleListAdapter);
                    mySickCircleListAdapter.setItemOnClick(new MySickCircleListAdapter.ItemOnClick() {
                        @Override
                        public void onitem(int p) {
                            Intent intent = new Intent(MySickCircleActivity.this, MySickCircleCommentActivity.class);
                            intent.putExtra("sickCircleId", p);
                            startActivity(intent);
                        }
                    });
                }
            } else {
                Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMySickCircleCommentSuccess(MySickCircleCommentListBean bean) {

    }

    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.message_fanhui, R.id.mysickcircle_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.message_fanhui:
                finish();
                break;
            case R.id.mysickcircle_button:
                break;
        }
    }
}
