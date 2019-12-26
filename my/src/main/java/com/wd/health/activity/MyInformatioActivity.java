package com.wd.health.activity;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.health.R;
import com.wd.health.bean.GetUserInfoByIdBean;
import com.wd.health.bean.ModifyHeadPicBean;
import com.wd.health.bean.ModifyNickNameBean;
import com.wd.health.bean.UpdateUserSexBean;
import com.wd.health.bean.evebus.EventBusBean;
import com.wd.health.bean.evebus.SeekBarBean;
import com.wd.health.bean.evebus.SexbeanBus;
import com.wd.health.bean.evebus.imageBus;
import com.wd.health.contract.ModifyHeadPicContract;
import com.wd.health.presenter.ModifyHeadPicPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * @describe(描述)：MyInformatioActivity 我的信息界面
 * @data（日期）: 2019/12/19
 * @time（时间）: 8:22
 * @author（作者）: 张恩
 **/
public class MyInformatioActivity extends BaseActivity<ModifyHeadPicPresenter> implements ModifyHeadPicContract.IView {

    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.my_information_Avatar)
    SimpleDraweeView myInformationAvatar;
    @BindView(R.id.my_information_Avatar_a)
    RelativeLayout myInformationAvatarA;
    @BindView(R.id.my_information_name)
    TextView myInformationName;
    @BindView(R.id.a2)
    ImageView a2;
    @BindView(R.id.my_information_name_a)
    RelativeLayout myInformationNameA;
    @BindView(R.id.my_information_gender)
    ImageView myInformationGender;
    @BindView(R.id.b1)
    ImageView b1;
    @BindView(R.id.my_information_gender_a)
    RelativeLayout myInformationGenderA;
    @BindView(R.id.my_information_height)
    TextView myInformationHeight;
    @BindView(R.id.my_information_bodyweight)
    TextView myInformationBodyweight;
    @BindView(R.id.my_information_age)
    TextView myInformationAge;
    @BindView(R.id.b2)
    ImageView b2;
    @BindView(R.id.my_information_Sign)
    RelativeLayout myInformationSign;
    @BindView(R.id.my_information_mailbox)
    TextView myInformationMailbox;
    @BindView(R.id.my_information_mailbox_a)
    RelativeLayout myInformationMailboxA;
    @BindView(R.id.my_information_weixin)
    TextView myInformationWeixin;
    @BindView(R.id.c2)
    ImageView c2;
    @BindView(R.id.my_information_weixin_a)
    RelativeLayout myInformationWeixinA;
    @BindView(R.id.my_information_Certification)
    TextView myInformationCertification;
    @BindView(R.id.d1)
    ImageView d1;
    @BindView(R.id.my_information_Certification_a)
    RelativeLayout myInformationCertificationA;
    @BindView(R.id.my_information_Bankcard)
    TextView myInformationBankcard;
    @BindView(R.id.d2)
    ImageView d2;
    @BindView(R.id.my_information_Bankcard_a)
    RelativeLayout myInformationBankcardA;
    private String nickName;
    private String email;
    private AlertDialog.Builder builder;
    private LayoutInflater inflater;
    private View layout;
    private AlertDialog dialog;
    private String name;
    private MultipartBody.Part picture;
    private String id;
    private String sessionId;
    public static final String TAG = "MyInformatioActivity";

    @Override
    protected ModifyHeadPicPresenter providePresenter() {
        return new ModifyHeadPicPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_my_informatio;
    }


    @Override
    protected void initView() {
        EventBus.getDefault().register(MyInformatioActivity.this);
        myInformationCertification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyInformatioActivity.this,IdCardActivity.class));
            }
        });
    }

    @Override
    protected void initData() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);

        id = sp.getString("id", "");
        sessionId = sp.getString("sessionId", "");
        mPresenter.getGetUserInfoPresenter(id, sessionId);


        myInformationBankcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MyInformatioActivity.this,BankActivity.class));
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessage(EventBusBean busBean) {

        Log.d(TAG, "getMessage: " + name);
        if (busBean.getSex().equals("2")) {
            myInformationGender.setImageResource(R.mipmap.common_icon_girl_n);
        } else {
            myInformationGender.setImageResource(R.mipmap.my_common_icon_boy_n);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessageheight(SeekBarBean barBean) {
        String seekBar = barBean.getSeekBar();
        myInformationHeight.setText(seekBar + "cm");
        String age = barBean.getAge();
        myInformationAge.setText(age + "岁");
        String weight = barBean.getWeight();
        myInformationBodyweight.setText(weight + "kg");
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getname(SexbeanBus sexbeanBus) {
        name = sexbeanBus.getSex();
        if (name!=null){
            myInformationName.setText(name);
        }
    }

    @OnClick({R.id.fanhui, R.id.my_information_Avatar, R.id.my_information_Avatar_a, R.id.my_information_name_a, R.id.my_information_gender_a, R.id.my_information_Sign, R.id.my_information_mailbox_a, R.id.my_information_weixin_a, R.id.my_information_Certification_a, R.id.my_information_Bankcard_a})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.my_information_Avatar:

                builder = new AlertDialog.Builder(this);//创建对话框
                inflater = getLayoutInflater();
                layout = inflater.inflate(R.layout.dialog_select_photo, null);
                builder.setView(layout);//设置对话框的布局
                dialog = builder.create();
                dialog.show();//显示对话框
                dialog.getWindow().setGravity(Gravity.BOTTOM);
                TextView takePhotoTV = layout.findViewById(R.id.photograph);
                TextView choosePhotoTV = layout.findViewById(R.id.photo);
                TextView cancelTV = layout.findViewById(R.id.cancel);

                takePhotoTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (Build.VERSION.SDK_INT >= 23) {
                            //权限不够
                            if (ContextCompat.checkSelfPermission(MyInformatioActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                ActivityCompat.requestPermissions(MyInformatioActivity.this, new String[]{Manifest.permission.CAMERA}, 3);
                            }
                        }
                        //已经有权限
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(intent, 101);

                    }

                });

                choosePhotoTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_PICK);
                        intent.setType("image/*");
                        startActivityForResult(intent, 100);

                        dialog.dismiss();

                    }
                });


                cancelTV.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                break;
            case R.id.my_information_Avatar_a:
                break;
            case R.id.my_information_name_a:
                Intent intent1 = new Intent(MyInformatioActivity.this, ModifyNickNameActivity.class);
                intent1.putExtra("nickName", nickName);
                startActivity(intent1);
                break;
            case R.id.my_information_gender_a:
                Intent intentuser = new Intent(MyInformatioActivity.this, UpdateUserSexActivity.class);
                startActivity(intentuser);
                break;
            case R.id.my_information_Sign:
                startActivity(new Intent(MyInformatioActivity.this, SignActivity.class));
                break;
            case R.id.my_information_mailbox_a:
                break;
            case R.id.my_information_weixin_a:
                break;
            case R.id.my_information_Certification_a:
                startActivity(new Intent(MyInformatioActivity.this,IdOKActivity.class));
                break;
            case R.id.my_information_Bankcard_a:
                break;
        }
    }

    @Override
    public void onModifyHeadPicSuccess(ModifyHeadPicBean bean) {
        if (bean.getStatus().equals("0000")) {
            Log.d(TAG, "onModifyHeadPicSuccess: " + bean.getMessage());
            mPresenter.getGetUserInfoPresenter(id, sessionId);
            EventBus.getDefault().postSticky(new imageBus(bean.getResult()));
            Toast.makeText(this, "" + bean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpdateSexSuccess(UpdateUserSexBean bean) {

    }

    @Override
    public void onModifyNickNameSuccess(ModifyNickNameBean bean) {

    }

    @Override
    public void onGetUserInfoSuccess(GetUserInfoByIdBean bean) {
        if (bean.getStatus().equals("0000")) {

            if (bean.getResult().getSex() == 1) {
                myInformationGender.setImageResource(R.mipmap.my_common_icon_boy_n);
            }
            myInformationAvatar.setImageURI(bean.getResult().getHeadPic());
            myInformationName.setText(bean.getResult().getNickName());
            myInformationMailbox.setText(bean.getResult().getEmail());
            myInformationHeight.setText(bean.getResult().getHeight() + "cm");
            myInformationAge.setText(bean.getResult().getAge() + "岁");
            myInformationBodyweight.setText(bean.getResult().getWeight() + "kg");
            Toast.makeText(this, "" + bean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            //相机回调
        if (requestCode == 101) {
            if (data == null) {
                return;
            }
            Bitmap bitmap = data.getParcelableExtra("data");
            File file = saveBitmapFile(bitmap);
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part formData = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
            mPresenter.ModifyHeadPicPresenter(id, sessionId, formData);

        }
        //选择相册回调
        if (requestCode == 100) {
            if (data == null) {
                return;
            }

            Uri uri = data.getData();
            String[] str = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(uri, str, null, null, null);
            int columnIndexOrThrow = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            String path = cursor.getString(columnIndexOrThrow);
            File file = new File(path);

            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part formData = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
            mPresenter.ModifyHeadPicPresenter(id, sessionId, formData);
        }
    }


    public File saveBitmapFile(Bitmap bitmap) {
        String timeStamp = String.valueOf(new Date().getTime());
        File file = new File(Environment.getExternalStorageDirectory() +
                File.separator + timeStamp + ".jpg");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


}
