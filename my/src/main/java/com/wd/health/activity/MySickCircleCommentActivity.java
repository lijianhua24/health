package com.wd.health.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.adapter.MySickCircleCommentListAdapter;
import com.wd.health.bean.user.MySickCircleCommentListBean;
import com.wd.health.bean.user.MySickCircleListBean;
import com.wd.health.contract.MySinkCircleContract;
import com.wd.health.presenter.MySickCirclePresenter;
import com.wd.mylibrary.Base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MySickCircleCommentActivity extends BaseActivity<MySickCirclePresenter> implements MySinkCircleContract.IView {


    @BindView(R.id.message_fanhui)
    ImageView messageFanhui;
    @BindView(R.id.mysickcircle_rlv)
    RecyclerView mysickcircleRlv;
    @BindView(R.id.message_include_img)
    SimpleDraweeView messageIncludeImg;
    @BindView(R.id.message_include_text)
    TextView messageIncludeText;
    @BindView(R.id.message_include_relat)
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
        return R.layout.activity_my_sick_circle_comment;
    }

    @Override
    protected void initView() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String id = sp.getString("id", "");
        String sessionId = sp.getString("sessionId", "");

        mPresenter.MySickCircleCommentPresenter(id, sessionId,"",1+"",5+"");
        //bujuguanliqi 布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mysickcircleRlv.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onMySinkCircleSuccess(MySickCircleListBean bean) {

    }

    @Override
    public void onMySickCircleCommentSuccess(MySickCircleCommentListBean bean) {
        Log.d(TAG, "onMySickCircleCommentSuccess: "+bean.getMessage());
        if (bean.getStatus().equals("0000")) {
            List<MySickCircleCommentListBean.ResultBean.OtherSickCircleCommentListBean> otherSickCircleCommentList = bean.getResult().getOtherSickCircleCommentList();
            if (otherSickCircleCommentList != null) {
                if (otherSickCircleCommentList.isEmpty()) {
                    messageIncludeRelat.setVisibility(View.VISIBLE);
                    mysickcircleRlv.setVisibility(View.GONE);
                    messageIncludeText.setText("暂无评论");
                } else {
                    messageIncludeRelat.setVisibility(View.GONE);
                    mysickcircleRlv.setVisibility(View.VISIBLE);
                    //适配器
                    MySickCircleCommentListAdapter mySickCircleCommentListAdapter = new MySickCircleCommentListAdapter(this);
                    mySickCircleCommentListAdapter.setList(otherSickCircleCommentList);
                    mysickcircleRlv.setAdapter(mySickCircleCommentListAdapter);

                }
            }
        } else {
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
        }
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

    @OnClick(R.id.message_fanhui)
    public void onClick() {
        finish();
    }
}
