package com.wd.health.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.IDCardParams;
import com.baidu.ocr.sdk.model.IDCardResult;

import com.baidu.ocr.ui.camera.CameraActivity;
import com.baidu.ocr.ui.camera.CameraNativeHelper;
import com.baidu.ocr.ui.camera.CameraView;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.bean.user.IdBean;
import com.wd.health.contract.IdContract;
import com.wd.health.presenter.IdPresenter;
import com.wd.health.utils.FileUtil;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.utils.RsaCoder;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IdCardActivity extends BaseActivity<IdPresenter> implements IdContract.IView {


    @BindView(R2.id.back)
    RelativeLayout back;
    @BindView(R2.id.id_card_front_button_auto)
    ImageView idCardFrontButtonAuto;
    @BindView(R2.id.id_card_back_button_auto)
    ImageView idCardBackButtonAuto;
    private static final int REQUEST_CODE_CAMERA = 102;
    private static final int REQUEST_CODE_DRIVING_LICENSE = 103;
    private static final int REQUEST_CODE_VEHICLE_LICENSE = 104;
    @BindView(R2.id.btn_next)
    Button btnNext;
    @BindView(R2.id.btn_ok)
    Button btnOk;
    private SharedPreferences sp1;
    private SharedPreferences sp;
    private String id;
    private String sessionId;
    private String birthdays;
    private String expiryDates;
    private String nums;
    private String addresss;
    private String issueAuthoritys;
    private String nations;
    private String sexs;
    private String names;

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        //获取SP
        sp = getSharedPreferences("front", MODE_PRIVATE);
        sp1 = getSharedPreferences("back", MODE_PRIVATE);

        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        id = sp.getString("id", "");
        sessionId = sp.getString("sessionId", "");
        // 初始化
        initAccessTokenWithAkSk();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected IdPresenter providePresenter() {
        return new IdPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int provideLayoutId() {
        return R2.layout.activity_id_card;
    }
    @Override
    public void onIdSuccess(IdBean bean) {
        if (bean.getStatus().equals("0000")){
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, bean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }




    @OnClick({R.id.id_card_front_button_auto, R.id.id_card_back_button_auto,R.id.btn_next,R.id.btn_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_card_front_button_auto:
                Intent intent = new Intent(IdCardActivity.this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_NATIVE_ENABLE, true);
                // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
                // 请手动使用CameraNativeHelper初始化和释放模型
                // 推荐这样做，可以避免一些activity切换导致的不必要的异常
                intent.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true);
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);

                break;
            case R.id.id_card_back_button_auto:
                Intent intent2 = new Intent(IdCardActivity.this, CameraActivity.class);
                intent2.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                intent2.putExtra(CameraActivity.KEY_NATIVE_ENABLE, true);
                // KEY_NATIVE_MANUAL设置了之后CameraActivity中不再自动初始化和释放模型
                // 请手动使用CameraNativeHelper初始化和释放模型
                // 推荐这样做，可以避免一些activity切换导致的不必要的异常
                intent2.putExtra(CameraActivity.KEY_NATIVE_MANUAL, true);
                intent2.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
                startActivityForResult(intent2, REQUEST_CODE_CAMERA);

                break;
            case R.id.btn_next:
               /* btnNext.setVisibility(View.GONE);
                btnOk.setVisibility(View.VISIBLE);*/
                break;
            case R.id.btn_ok:

               mPresenter.IdPresenter(id,sessionId,id,names,sexs,nations,birthdays,addresss,nums,issueAuthoritys);
                Log.d("sd", "onClick: ");

                Intent intent1 = new Intent(IdCardActivity.this,IdOKActivity.class);
                startActivity(intent1);
                break;
        }
    }


    //初始化
    private void initAccessTokenWithAkSk() {
        OCR.getInstance(IdCardActivity.this).initAccessTokenWithAkSk(
                new OnResultListener<AccessToken>() {
                    @Override
                    public void onResult(AccessToken result) {
                        // 本地自动识别需要初始化
                        initLicense();
                        Log.d("MainActivity", "onResult: " + result.toString());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(IdCardActivity.this, "初始化认证成功", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onError(OCRError error) {
                        error.printStackTrace();
                        Log.e("MainActivity", "onError: " + error.getMessage());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(IdCardActivity.this, "初始化认证失败,请检查 key", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }, getApplicationContext(),
                // 需要自己配置 https://console.bce.baidu.com
                "P3sgLV9vZBWlYcYvEWkmpKEg",
                // 需要自己配置 https://console.bce.baidu.com
                "Brfa7PUwGvSQZaQlpljxvQhHUVoA4eab");
    }

    //初始化回调监听
    private void initLicense() {
        CameraNativeHelper.init(this, OCR.getInstance(IdCardActivity.this).getLicense(),
                new CameraNativeHelper.CameraNativeInitCallback() {
                    @Override
                    public void onError(int errorCode, Throwable e) {
                        final String msg;
                        switch (errorCode) {
                            case CameraView.NATIVE_SOLOAD_FAIL:
                                msg = "加载so失败，请确保apk中存在ui部分的so";
                                break;
                            case CameraView.NATIVE_AUTH_FAIL:
                                msg = "授权本地质量控制token获取失败";
                                break;
                            case CameraView.NATIVE_INIT_FAIL:
                                msg = "本地质量控制";
                                break;
                            default:
                                msg = String.valueOf(errorCode);
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(IdCardActivity.this,
                                        "本地质量控制初始化错误，错误原因： " + msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();


                if (!TextUtils.isEmpty(contentType)) {
                    if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
                        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                        idCardFrontButtonAuto.setImageBitmap(bitmap);
                    } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
                        Bitmap bitmap1 = BitmapFactory.decodeFile(filePath);
                        idCardBackButtonAuto.setImageBitmap(bitmap1);
                    }

                }
            }
        }

    }

    /**
     * 解析身份证图片
     *
     * @param filePath 图片路径
     * @a0GPi4bzLzuT9dDpU5Zp1Ce7InFDAjUw param idCardSide 身份证正反面
     */
    private void recIDCard(final String idCardSide, String filePath) {
        IDCardParams param = new IDCardParams();
        param.setImageFile(new File(filePath));
        // 设置身份证正反面
        param.setIdCardSide(idCardSide);
        // 设置方向检测
        param.setDetectDirection(true);
        // 设置图像参数压缩质量0-100, 越大图像质量越好但是请求时间越长。 不设置则默认值为20
        param.setImageQuality(40);
        OCR.getInstance(IdCardActivity.this).recognizeIDCard(param, new OnResultListener<IDCardResult>() {


            private String birthday;

            @Override
            public void onResult(IDCardResult result) {
                if (result != null) {
                    String name = "";
                    String sex = "";
                    String nation = "";
                    String num = "";
                    String address = "";
                    String issueAuthority = "";
                    String expiryDate = "";
                    if (result.getName() != null) {
                        name = result.getName().toString();
                        try {
                            names = RsaCoder.encryptByPublicKey(name);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (result.getGender() != null) {
                        sex = result.getGender().toString();
                        try {
                            sexs = RsaCoder.encryptByPublicKey(sex);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (result.getEthnic() != null) {
                        nation = result.getEthnic().toString();
                        try {
                            nations = RsaCoder.encryptByPublicKey(nation);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (result.getIdNumber() != null) {
                        num = result.getIdNumber().toString();

                        birthday = num.substring(6, 14);
                        try {
                            birthdays = RsaCoder.encryptByPublicKey(birthday);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            nums = RsaCoder.encryptByPublicKey(num);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (result.getAddress() != null) {
                        address = result.getAddress().toString();
                        try {
                            addresss = RsaCoder.encryptByPublicKey(address);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (result.getIssueAuthority() != null) {
                        issueAuthority = result.getIssueAuthority().toString();
                        try {
                            issueAuthoritys = RsaCoder.encryptByPublicKey(issueAuthority);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (result.getExpiryDate() != null) {
                        expiryDate = result.getExpiryDate().toString();
                        try {
                            expiryDates = RsaCoder.encryptByPublicKey(expiryDate);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (idCardSide.equals("front")) {

                        Toast.makeText(IdCardActivity.this, "姓名: " + name + "\n" +
                                "性别: " + sex + "\n" +
                                "民族: " + nation + "\n" +
                                "身份证号码: " + num + "\n" +
                                "生日: " + birthday + "\n" +
                                "住址: " + address + "\n", Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean("frontyes", true);
                        editor.putBoolean("", false);
                        editor.putString("name", name);
                        editor.putString("sex", sex);
                        editor.putString("address", address);
                        editor.putString("nation", nation);
                        editor.putString("num", num);
                        editor.commit();
                    } else if (idCardSide.equals("back")) {
                        Toast.makeText(IdCardActivity.this, "签发机关: " + issueAuthority + "\n" +
                                "到期时间: " + expiryDate + "\n", Toast.LENGTH_SHORT).show();

                        SharedPreferences.Editor editor1 = sp1.edit();
                        editor1.putString("expiryDate", expiryDate);
                        editor1.putBoolean("backyes", true);
                        editor1.commit();
                        getIntentOne();
                    }

                }
            }

            //判断跳转
            private void getIntentOne() {
                if (sp.getBoolean("frontyes", true) && sp1.getBoolean("backyes", true)) {
                   // startActivity(new Intent(IdCardActivity.this, IdOKActivity.class));
                    btnNext.setVisibility(View.GONE);
                    btnOk.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onError(OCRError error) {
                Toast.makeText(IdCardActivity.this, "识别出错,请查看log错误代码", Toast.LENGTH_SHORT).show();
                Log.d("MainActivity", "onError: " + error.getMessage());
            }
        });
    }

    @Override
    protected void onDestroy() {
        CameraNativeHelper.release();
        // 释放内存资源
        OCR.getInstance(IdCardActivity.this).release();
        super.onDestroy();

    }


}
