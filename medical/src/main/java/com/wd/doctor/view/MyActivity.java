package com.wd.doctor.view;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.doctor.R;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Base.BasePresenter;
import com.wd.mylibrary.Test.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyActivity extends BaseActivity {
    private static final String TAG = "MyActivity";
    @BindView(R.id.my_iv_touxiang)
    SimpleDraweeView myIvTouxiang;
    @BindView(R.id.my_iv_back)
    ImageView myIvBack;
    @BindView(R.id.my_iv_xiaoxin)
    ImageView myIvXiaoxin;
    @BindView(R.id.my_tv_geren)
    TextView myTvGeren;
    @BindView(R.id.my_rbton_lishiwenzheng)
    RadioButton myRbtonLishiwenzheng;
    @BindView(R.id.my_rbton_qianbao)
    RadioButton myRbtonQianbao;
    @BindView(R.id.my_rbton_jianyi)
    RadioButton myRbtonJianyi;
    @BindView(R.id.my_rbton_huifu)
    RadioButton myRbtonHuifu;
    private String imagePic;
    private boolean a;
    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {

        //个人形象照
        Intent intent = getIntent();
        String imagePic = intent.getStringExtra("imagePic");
//        myIvTouxiang.setImageURI("res://mipmap/" + R.mipmap.z);
            myIvTouxiang.setImageURI(imagePic);

         //设置消息自动回复
            myRbtonHuifu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MyActivity.this,ReplyActivity.class));
                }
            });


        //我的钱包
        myRbtonQianbao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyActivity.this,DoctorWalletActivity.class));
            }
        });


        //返回
        myIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //个人信息
        myTvGeren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyActivity.this,DoctorInforActivity.class));
            }
        });

        //更换形象照
        myIvTouxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(MyActivity.this, R.style.DialogTheme);
                View inflate = View.inflate(MyActivity.this, R.layout.xingxiang_tanchu, null);
                Button genghuan = inflate.findViewById(R.id.xingxiang_btn_genghuan);
                Button quxiao = inflate.findViewById(R.id.xingxiang_btn_quxiao);
                dialog.setContentView(inflate);
                Window window = dialog.getWindow();
                window.setGravity(Gravity.BOTTOM);
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.show();
//                inflate.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialog.dismiss();
//                    }
//                });
                //更换形象照
                genghuan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MyActivity.this,ImageQueryActivity.class));
                        dialog.dismiss();
                    }
                });
                //取消
                quxiao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_my;
    }

}