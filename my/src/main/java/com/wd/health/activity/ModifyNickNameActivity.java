package com.wd.health.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.bean.GetUserInfoByIdBean;
import com.wd.health.bean.ModifyHeadPicBean;
import com.wd.health.bean.ModifyNickNameBean;
import com.wd.health.bean.UpdateUserSexBean;
import com.wd.health.bean.evebus.SexbeanBus;
import com.wd.health.contract.ModifyHeadPicContract;
import com.wd.health.presenter.ModifyHeadPicPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModifyNickNameActivity extends BaseActivity<ModifyHeadPicPresenter> implements ModifyHeadPicContract.IView {

    @BindView(R2.id.fanhui)
    ImageView fanhui;
    @BindView(R2.id.btn_finish_name)
    Button btnFinishName;
    @BindView(R2.id.edit_name_name)
    EditText editNameName;
    @BindView(R2.id.iamge_name)
    ImageView iamgeName;
    private String sessionId;
    private String id;
    private String nickName;
    private AlertDialog dialog;

    @Override
    protected ModifyHeadPicPresenter providePresenter() {
        return new ModifyHeadPicPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R2.layout.activity_modify_nick_name;
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        id = sp.getString("id", "");
        sessionId = sp.getString("sessionId", "");

        Intent intent = getIntent();
        nickName = intent.getStringExtra("nickName");
        editNameName.setHint(nickName);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onModifyHeadPicSuccess(ModifyHeadPicBean bean) {

    }

    @Override
    public void onUpdateSexSuccess(UpdateUserSexBean bean) {

    }

    @Override
    public void onModifyNickNameSuccess(ModifyNickNameBean bean) {
        if (bean.getStatus().equals("0000")) {
            Toast.makeText(this, "" + bean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onGetUserInfoSuccess(GetUserInfoByIdBean bean) {

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

    @OnClick({R.id.fanhui, R.id.btn_finish_name, R.id.iamge_name})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.btn_finish_name:
                String newname = editNameName.getText().toString();

                AlertDialog.Builder builder = new AlertDialog.Builder(ModifyNickNameActivity.this);
                View myview = getLayoutInflater().inflate(R.layout.dialog_date, null);
                Button button = myview.findViewById(R.id.btn_null);
                Button buttonok = myview.findViewById(R.id.btn_ok);
                builder.setView(myview);
                dialog = builder.create();
                buttonok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.getModifyNickNamePresenter(id, sessionId, newname);
                        EventBus.getDefault().post(new SexbeanBus(newname));
                        finish();

                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                builder.show();

                break;
            case R.id.iamge_name:

                editNameName.setText(null);
                break;
        }
    }
}
