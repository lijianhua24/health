package com.wd.mymodule.fragment;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wd.mylibrary.Base.BaseFragment;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mymodule.R;

import butterknife.BindView;

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
}
