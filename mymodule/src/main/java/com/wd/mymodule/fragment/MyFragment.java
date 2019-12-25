package com.wd.mymodule.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.mylibrary.Base.BaseFragment;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mymodule.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <p>文件描述：<p>
 * <p>作者：黎怡志<p>
 * <p>创建时间：2019/12/16<p>
 * <p>更改时间：2019/12/16<p>
 */
public class MyFragment extends BaseFragment {
    @BindView(R.id.mine_activity_iv_user_head_pic)
    ImageView mineActivityIvUserHeadPic;
    @BindView(R.id.mine_activity_tv_nick_name)
    TextView mineActivityTvNickName;
    @BindView(R.id.mine_activity_btn_login)
    Button mineActivityBtnLogin;
    @BindView(R.id.mine_activity_btn_sign_in)
    Button mineActivityBtnSignIn;
    @BindView(R.id.patient_relative_titlebar)
    LinearLayout patientRelativeTitlebar;
    @BindView(R.id.current)
    ImageView current;
    @BindView(R.id.history)
    ImageView history;
    @BindView(R.id.file)
    ImageView file;
    @BindView(R.id.mine_activity_linear_my_money)
    LinearLayout mineActivityLinearMyMoney;
    @BindView(R.id.favorite)
    ImageView favorite;
    @BindView(R.id.yijian)
    ImageView yijian;
    @BindView(R.id.attention)
    ImageView attention;
    @BindView(R.id.mine_activity_iv_my_task)
    ImageView mineActivityIvMyTask;
    @BindView(R.id.mine_activity_iv_set_up)
    ImageView mineActivityIvSetUp;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.myfragment;
    }

    @OnClick({R.id.mine_activity_iv_user_head_pic, R.id.mine_activity_tv_nick_name, R.id.mine_activity_btn_login, R.id.mine_activity_btn_sign_in, R.id.patient_relative_titlebar, R.id.current, R.id.history, R.id.file, R.id.mine_activity_linear_my_money, R.id.favorite, R.id.yijian, R.id.attention, R.id.mine_activity_iv_my_task, R.id.mine_activity_iv_set_up})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_activity_iv_user_head_pic:
                break;
            case R.id.mine_activity_tv_nick_name:
                break;
            case R.id.mine_activity_btn_login:
                break;
            case R.id.mine_activity_btn_sign_in:
                break;
            case R.id.patient_relative_titlebar:
                break;
            case R.id.current:
                break;
            case R.id.history:
                break;
            case R.id.file:
                break;
            case R.id.mine_activity_linear_my_money:
                break;
            case R.id.favorite:
                break;
            case R.id.yijian:
                break;
            case R.id.attention:
                break;
            case R.id.mine_activity_iv_my_task:
                break;
            case R.id.mine_activity_iv_set_up:
                break;
        }
    }
}
