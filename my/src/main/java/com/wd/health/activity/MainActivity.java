package com.wd.health.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.bean.AddSignBean;
import com.wd.health.bean.evebus.SettingBus;
import com.wd.health.bean.evebus.SexbeanBus;
import com.wd.health.bean.evebus.imageBus;
import com.wd.health.bean.user.DoTaskBean;
import com.wd.health.contract.AddSignContract;
import com.wd.health.presenter.AddSignPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

    /**
     *@describe(描述)：MainActivity   我的界面
     *@data（日期）: 2019/12/19
     *@time（时间）: 8:22
     *@author（作者）: 张恩
     **/
public class MainActivity extends BaseActivity<AddSignPresenter> implements AddSignContract.IView {

    @BindView(R2.id.iamge_icon)
    SimpleDraweeView iamgeIcon;
    @BindView(R2.id.toolbar)
    Toolbar toolbar;
    @BindView(R2.id.my_return)
    ImageView myReturn;
    @BindView(R2.id.my_Message)
    ImageView myMessage;
    @BindView(R2.id.di1)
    RelativeLayout di1;
    @BindView(R2.id.my_name)
    TextView myName;
    @BindView(R2.id.my_Checkin)
    Button myCheckin;
    @BindView(R2.id.di2)
    RelativeLayout di2;
    @BindView(R2.id.my_i)
    TextView myI;
    @BindView(R2.id.my_inquir)
    ImageView myInquir;
    @BindView(R2.id.my_inquiry)
    RelativeLayout myInquiry;
    @BindView(R2.id.my_inq)
    ImageView myInq;
    @BindView(R2.id.my_history_inquiry)
    RelativeLayout myHistoryInquiry;
    @BindView(R2.id.wen)
    RelativeLayout wen;
    @BindView(R2.id.my_file)
    RadioButton myFile;
    @BindView(R2.id.my_wallet)
    RadioButton myWallet;
    @BindView(R2.id.my_Collection)
    RadioButton myCollection;
    @BindView(R2.id.rg1)
    RadioGroup rg1;
    @BindView(R2.id.my_Suggest)
    RadioButton mySuggest;
    @BindView(R2.id.my_video)
    RadioButton myVideo;
    @BindView(R2.id.my_circle)
    RadioButton myCircle;
    @BindView(R2.id.rg2)
    RadioGroup rg2;
    @BindView(R2.id.my_attention)
    RadioButton myAttention;
    @BindView(R2.id.my_task)
    RadioButton myTask;
    @BindView(R2.id.my_Setting)
    RadioButton mySetting;
    @BindView(R2.id.rg3)
    RadioGroup rg3;
    private String id;
    private String sessionId;
    public static final String TAG = "MainActivity";
    private String name;
    boolean as=false;
    @Override
    protected int provideLayoutId() {
        return R2.layout.activity_main;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        EventBus.getDefault().register(MainActivity.this);
        myHistoryInquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,HistoryRecordListActivity.class));
            }
        });

    }

    @Override
    protected void initData() {
        //头像点击进入登录界面
        iamgeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String headPic = sp.getString("headPic", "");

        String nickName = sp.getString("nickName", "");

        Log.d(TAG, "initData: "+nickName);
        id = sp.getString("id", "");
        sessionId = sp.getString("sessionId", "");
        if (id.equals("") && sessionId.equals("")){

        }else {
            iamgeIcon.setImageURI(headPic);
            myName.setText(nickName);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected AddSignPresenter providePresenter() {
        return new AddSignPresenter();
    }


    @Override
    public void onAddSignSuccess(AddSignBean bean) {
        Log.d(TAG, "onAddSignSuccess: " + bean.getMessage());
        if (bean.getStatus().equals("0000")) {
            Toast.makeText(this, "" + bean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

        @Override
        public void onDoTaskSuccess(DoTaskBean bean) {
            if (bean.getStatus().equals("0000")){
                Toast.makeText(this, ""+bean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
    public void onFailure(Throwable e) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessage(SexbeanBus busBean) {
        name = busBean.getSex();
        myName.setText(name);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getImage(imageBus bus) {
        String image = bus.getImage();
        iamgeIcon.setImageURI(image);
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getShow(SettingBus bus) {
        String image = bus.getImage();
        String name = bus.getName();
        iamgeIcon.setImageURI(image);
        myName.setText(name);
    }
    @OnClick({R.id.my_return, R.id.my_Message, R.id.my_Checkin, R.id.di2, R.id.my_inquir, R.id.my_inquiry, R.id.my_inq, R.id.my_file, R.id.my_wallet, R.id.my_Collection, R.id.my_Suggest, R.id.my_video, R.id.my_circle, R.id.my_attention, R.id.my_task, R.id.my_Setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_return:
                break;
            case R.id.my_Message:
                Intent Message = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(Message);
                break;
            case R.id.my_Checkin:
                mPresenter.AddSignresenter(id, sessionId);
                as=true;
                mPresenter.getDoTaskPresenter(id,sessionId,"1001");
               if (as=true){
                   myCheckin.setText("已签到");
               }
                break;
            case R.id.di2:
                break;
            case R.id.my_inquir:
                break;
            case R.id.my_inquiry:
                startActivity(new Intent(MainActivity.this,FindCurrentInquiryRecordActivity.class));
                break;
            case R.id.my_inq:
                break;
            case R.id.my_file:
                startActivity(new Intent(MainActivity.this,MyArchivesActivity.class));
                break;
            case R.id.my_wallet:
                Intent intentwallet = new Intent(MainActivity.this, WalletActivity.class);
                startActivity(intentwallet);
                break;
            case R.id.my_Collection:
                Intent intentcollection = new Intent(MainActivity.this, CollectionActivity.class);
                startActivity(intentcollection);
                break;
            case R.id.my_Suggest:
                startActivity(new Intent(MainActivity.this,MyAdoptedListActivity.class));
                break;
            case R.id.my_video:
                startActivity(new Intent(MainActivity.this,VideoActivity.class));
                break;
            case R.id.my_circle:
                Intent intentcircle = new Intent(MainActivity.this, MySickCircleActivity.class);
                startActivity(intentcircle);
                break;
            case R.id.my_attention:
                Intent intent = new Intent(MainActivity.this, FocusActivity.class);
                startActivity(intent);
                break;
            case R.id.my_task:
                Intent findUserTaskListActivity = new Intent(MainActivity.this, findUserTaskListActivity.class);
                startActivity(findUserTaskListActivity);
                break;
            case R.id.my_Setting:
                Intent SettingActivity = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(SettingActivity);
                break;
        }
    }

}
