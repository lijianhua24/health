package com.wd.doctor.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;
import com.baidu.ocr.sdk.model.IDCardParams;
import com.baidu.ocr.sdk.model.IDCardResult;
import com.baidu.ocr.ui.camera.CameraActivity;
import com.baidu.ocr.ui.camera.CameraNativeHelper;
import com.baidu.ocr.ui.camera.CameraView;
import com.google.gson.Gson;
import com.wd.doctor.R;
import com.wd.doctor.bean.ToBindBean;
import com.wd.doctor.contract.ToBindContract;
import com.wd.doctor.presenter.ToBindPresenter;
import com.wd.doctor.utils.ApiServers;
import com.wd.doctor.utils.FileUtil;
import com.wd.doctor.utils.RetrofitManager;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;
import com.wd.mylibrary.Test.ToastUtils;
import com.wd.mylibrary.app.App;
import com.wd.mylibrary.utils.RsaCoder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class BindIdActivity extends BaseActivity<ToBindPresenter> implements ToBindContract.iView {
    private static final String TAG = "BindIdActivity";
    private static final int REQUEST_CODE_CAMERA = 103;
    @BindView(R.id.bindid_iv_back)
    ImageView bindidIvBack;
    @BindView(R.id.bindid_rl_Portrait)
    RelativeLayout bindidRlPortrait;
    @BindView(R.id.bindid_rl_emblem)
    RelativeLayout bindidRlEmblem;
    @BindView(R.id.bindid_btn_next)
    Button bindidBtnNext;
    @BindView(R.id.btn_finish_id_card)
    Button btnFinishIdCard;
    @BindView(R.id.img_cuowu_front)
    ImageView imgCuowuFront;
    @BindView(R.id.img_cuowu_back)
    ImageView imgCuowuBack;
    @BindView(R.id.img_id_card_front)
    ImageView imgIdCardFront;
    @BindView(R.id.img_id_card_back)
    ImageView imgIdCardBack;
    private SharedPreferences sharedPreferences;
    private int doctorId;
    private String sessionId;
    private String name;
    private String sex;
    private String nation;
    private String num;
    private String birthday;
    private String address;
    private String issueAuthority;

    @Override
    protected ToBindPresenter providePresenter() {
        return new ToBindPresenter();
    }

    @Override
    protected void initData() {
        OCR.getInstance(App.getAppContext()).initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken result) {
                // 调用成功，返回AccessToken对象
                String token = result.getAccessToken();
            }

            @Override
            public void onError(OCRError error) {
                // 调用失败，返回OCRError子类SDKError对象
            }
        }, getApplicationContext());
    }

    //初始化回调监听
    private void initLicense() {
        CameraNativeHelper.init(this, OCR.getInstance(this).getLicense(),
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
                                Toast.makeText(BindIdActivity.this,
                                        "本地质量控制初始化错误，错误原因： " + msg, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
    }

    @Override
    protected void initView() {
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        doctorId = sharedPreferences.getInt("doctorId", 0);
        sessionId = sharedPreferences.getString("sessionId", "");
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_bind_id;
    }

    @Override
    public void onToBindSuccess(ToBindBean toBindBean) {
        Logger.d("BindIdActivity2432423423", "" + toBindBean.getMessage());
        if (toBindBean.getStatus().equals("0000")) {
            Intent intent = new Intent(this, DoctorWalletActivity.class);
            startActivity(intent);
        } else if (toBindBean.getStatus().equals("8002")){
            ToastUtils.show(toBindBean.getMessage());
            finish();
        }
    }

    @Override
    public void onToBindFailure(Throwable e) {
        ToastUtils.show(e.getMessage());
    }

    @Override
    public void onBindBankSuccess(ToBindBean toBindBean) {

    }

    @Override
    public void onBindBankFailure(Throwable e) {

    }


    @OnClick({R.id.bindid_iv_back, R.id.bindid_rl_Portrait, R.id.bindid_rl_emblem, R.id.bindid_btn_next,
            R.id.btn_finish_id_card, R.id.img_cuowu_front, R.id.img_cuowu_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bindid_iv_back:
                finish();
                break;
            case R.id.bindid_rl_Portrait:
                Intent intent = new Intent(this, CameraActivity.class);
                intent.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                intent.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_FRONT);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);
                break;
            case R.id.bindid_rl_emblem:
                Intent intent2 = new Intent(this, CameraActivity.class);
                intent2.putExtra(CameraActivity.KEY_OUTPUT_FILE_PATH,
                        FileUtil.getSaveFile(getApplication()).getAbsolutePath());
                intent2.putExtra(CameraActivity.KEY_CONTENT_TYPE, CameraActivity.CONTENT_TYPE_ID_CARD_BACK);
                startActivityForResult(intent2, REQUEST_CODE_CAMERA);
                break;
            case R.id.bindid_btn_next:
                btnFinishIdCard.setVisibility(View.VISIBLE);
                bindidBtnNext.setVisibility(View.GONE);
                imgCuowuFront.setVisibility(View.VISIBLE);
                imgCuowuBack.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_finish_id_card:
//                Map<Object, Object> params = new HashMap<>();
//                params.put("doctorId", doctorId);
//                params.put("name", name);
//                params.put("sex", sex);
//                params.put("nation", nation);
//                params.put("birthday", birthday);
//                params.put("address", address);
//                params.put("idNumber", num);
//                params.put("issueAuthority", issueAuthority);
//                Gson gson = new Gson();
//                String s = gson.toJson(params);
//                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"),s);
                try {
                    name = RsaCoder.encryptByPublicKey(name);
                    sex = RsaCoder.encryptByPublicKey(sex);
                    nation = RsaCoder.encryptByPublicKey(nation);
                    birthday = RsaCoder.encryptByPublicKey(birthday);
                    address = RsaCoder.encryptByPublicKey(address);
                    num = RsaCoder.encryptByPublicKey(num);
                    issueAuthority = RsaCoder.encryptByPublicKey(issueAuthority);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("doctorId",doctorId);
                    jsonObject.put("name",name);
                    jsonObject.put("sex",sex);
                    jsonObject.put("nation",nation);
                    jsonObject.put("birthday",birthday);
                    jsonObject.put("address",address);
                    jsonObject.put("num",num);
                    jsonObject.put("issueAuthority",issueAuthority);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JSONArray put = jsonArray.put(jsonObject);
                Map<String,Object> BodyMap = new HashMap<>();
                BodyMap.put("BodyMap", put);
                Log.d("doctorId",doctorId+"");
                Log.d("sessionId",sessionId+"");
                mPresenter.getToBindPresenter(doctorId, sessionId,BodyMap);

                break;
            case R.id.img_cuowu_front:
                imgIdCardFront.setImageDrawable(null);
                imgCuowuFront.setVisibility(View.GONE);
                bindidRlPortrait.setVisibility(View.VISIBLE);
                break;
            case R.id.img_cuowu_back:
                imgIdCardBack.setImageDrawable(null);
                imgCuowuBack.setVisibility(View.GONE);
                bindidRlEmblem.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                String contentType = data.getStringExtra(CameraActivity.KEY_CONTENT_TYPE);
                String filePath = FileUtil.getSaveFile(getApplicationContext()).getAbsolutePath();
                if (!TextUtils.isEmpty(contentType)) {
                    if (CameraActivity.CONTENT_TYPE_ID_CARD_FRONT.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_FRONT, filePath);
                        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                        imgIdCardFront.setImageBitmap(bitmap);
                        bindidRlPortrait.setVisibility(View.GONE);
                    } else if (CameraActivity.CONTENT_TYPE_ID_CARD_BACK.equals(contentType)) {
                        recIDCard(IDCardParams.ID_CARD_SIDE_BACK, filePath);
                        Bitmap bitmap = BitmapFactory.decodeFile(filePath);
                        imgIdCardBack.setImageBitmap(bitmap);
                        bindidRlEmblem.setVisibility(View.GONE);
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
        OCR.getInstance(BindIdActivity.this).recognizeIDCard(param, new OnResultListener<IDCardResult>() {

            @Override
            public void onResult(IDCardResult result) {
                if (result != null) {
                    name = "";
                    sex = "";
                    nation = "";
                    num = "";
                    birthday = "";
                    address = "";
                    issueAuthority = "";
                    if (result.getName() != null) {
                        name = result.getName().toString();
                    }
                    if (result.getGender() != null) {
                        sex = result.getGender().toString();
                    }
                    if (result.getEthnic() != null) {
                        nation = result.getEthnic().toString();
                    }
                    if (result.getIdNumber() != null) {
                        num = result.getIdNumber().toString();
                    }
                    if (result.getBirthday() != null) {
                        birthday = result.getBirthday().toString();
                    }
                    if (result.getAddress() != null) {
                        address = result.getAddress().toString();
                    }
                    if (result.getIssueAuthority() != null) {
                        issueAuthority = result.getIssueAuthority().toString();
                    }
                    if (idCardSide.equals("front")) {
                        ToastUtils.show("姓名: " + name + "\n" +
                                "性别: " + sex + "\n" +
                                "民族: " + nation + "\n" +
                                "身份证号码: " + num + "\n" +
                                "生日" + birthday + "\n" +
                                "住址: " + address + "\n");

                        /*idCard = new SPUtils(IdCardActivity.this, "IdCard");
                        idCard.SharedPreferenceput("frontyes",true);
                        idCard.SharedPreferenceput("",false);
                        idCard.SharedPreferenceput("name", name);
                        idCard.SharedPreferenceput("sex", sex);
                        idCard.SharedPreferenceput("birthday", birthday);
                        idCard.SharedPreferenceput("address", address);
                        idCard.SharedPreferenceput("num", num);*/
                    } else if (idCardSide.equals("back")) {
                        ToastUtils.show("签发机关: " + issueAuthority + "\n");
                        /*idCard2 = new SPUtils(IdCardActivity.this, "IdCard2");
                        idCard2.SharedPreferenceput("backyes",true);*/
                    }
                }
            }

            @Override
            public void onError(OCRError error) {
                Toast.makeText(BindIdActivity.this, "识别出错,请查看log错误代码", Toast.LENGTH_SHORT).show();
                Log.d("MainActivity", "onError: " + error.getMessage());
            }
        });
    }

}
