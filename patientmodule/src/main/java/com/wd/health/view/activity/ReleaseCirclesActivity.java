package com.wd.health.view.activity;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.bean.CircleListShowBean;
import com.wd.health.bean.DepartmentListBean;
import com.wd.health.bean.DoTaskBean;
import com.wd.health.bean.KeywordSearchBean;
import com.wd.health.bean.ReleasePatientsBean;
import com.wd.health.bean.UnitDiseaseBean;
import com.wd.health.bean.UploadPatientBean;
import com.wd.health.contract.IContract;
import com.wd.health.model.App;
import com.wd.health.presenter.DepartmentListPresenter;
import com.wd.health.view.adapter.ConsultationTwoAdapter;
import com.wd.health.view.adapter.IllnessAdapter;
import com.wd.health.view.custom.CustomImgPickerPresenter;
import com.wd.health.view.custom.WeChatPresenter;
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.Test.Logger;
import com.ypx.imagepicker.ImagePicker;
import com.ypx.imagepicker.bean.ImageItem;
import com.ypx.imagepicker.data.OnImagePickCompleteListener;
import com.ypx.imagepicker.presenter.IPickerPresenter;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class ReleaseCirclesActivity extends BaseActivity<DepartmentListPresenter> implements IContract.iView {
    @BindView(R2.id.release_sickCircle_iv_user_head_pic)
    ImageView release_sickCircle_iv_user_head_pic;
    @BindView(R2.id.patient_iv_user_message)
    ImageView patient_iv_user_message;
    @BindView(R2.id.release_circle_et_title)
    EditText release_circle_et_title;
    @BindView(R2.id.release_circle_tv_choose_department)
    TextView release_circle_tv_choose_department;
    @BindView(R2.id.release_circle_iv_choose_department)
    RelativeLayout release_circle_iv_choose_department;
    @BindView(R2.id.release_circle_tv_choose_disease)
    TextView release_circle_tv_choose_disease;
    @BindView(R2.id.release_circle_iv_choose_disease)
    RelativeLayout release_circle_iv_choose_disease;
    @BindView(R2.id.release_circle_et_detail)
    EditText release_circle_et_detail;
    @BindView(R2.id.release_circle_et_treatmentHospital)
    EditText release_circle_et_treatmentHospital;
    @BindView(R2.id.release_circle_tv_startTime)
    TextView release_circle_tv_startTime;
    @BindView(R2.id.release_circle_iv_startTime)
    RelativeLayout release_circle_iv_startTime;
    @BindView(R2.id.release_circle_tv_endTime)
    TextView release_circle_tv_endTime;
    @BindView(R2.id.release_circle_iv_endTime)
    RelativeLayout release_circle_iv_endTime;
    @BindView(R2.id.release_circle_et_treatmentProcess)
    EditText release_circle_et_treatmentProcess;
    @BindView(R2.id.release_circle_iv_upload_Picture)
    GridLayout release_circle_iv_upload_Picture;
    @BindView(R2.id.item_switch)
    Switch item_switch;
    @BindView(R2.id.button_hbi3)
    Button button_hbi3;
    @BindView(R2.id.aaa)
    TextView aaa;
    @BindView(R2.id.xuanshangedu_linear)
    LinearLayout linearLayout;
    @BindView(R2.id.release_circle_btn_publish)
    Button release_circle_btn_publish;
    @BindView(R2.id.release_circle_linear_sick_circle)
    LinearLayout release_circle_linear_sick_circle;
    Calendar calendar = Calendar.getInstance(Locale.CHINA);
    private int id;
    private PopupWindow popWindow;
    private PopupWindow popWindowDisease;
    private String path;
    private int sickCircleId;
    private List<MultipartBody.Part> parts = new ArrayList<>();
    private RecyclerView popup_recycler_department;
    private RecyclerView popup_recycler_disease;
    private int userId;
    private String sessionId;
    private List<String> list;
    private ArrayList<ImageItem> picList = new ArrayList<>();

    @Override
    protected DepartmentListPresenter providePresenter() {
        return new DepartmentListPresenter();
    }

    @Override
    protected void initData() {
        refreshGridLayout();
        //悬赏额度的开关
        item_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    linearLayout.setVisibility(View.VISIBLE);
                } else {
                    linearLayout.setVisibility(View.GONE);
                }
            }
        });
        //设置在activity启动的时候输入法默认是不开启的
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //开始时间
        release_circle_iv_startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ReleaseCirclesActivity.this);
                final View view = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_date, null);
                final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
                //设置日期简略显示 否则详细显示 包括:星期\周
                datePicker.setCalendarViewShown(false);
                //初始化当前日期
                calendar.setTimeInMillis(System.currentTimeMillis());
                datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH), null);
                //设置date布局
                builder.setView(view);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //日期格式
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        release_circle_tv_startTime.setText(sb);
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });

        //结束时间
        release_circle_iv_endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ReleaseCirclesActivity.this);
                final View view = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_date, null);
                final DatePicker datePicker = (DatePicker) view.findViewById(R.id.date_picker);
                //设置日期简略显示 否则详细显示 包括:星期\周
                datePicker.setCalendarViewShown(false);
                //初始化当前日期
                calendar.setTimeInMillis(System.currentTimeMillis());
                datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH), null);
                //设置date布局
                builder.setView(view);

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //日期格式
                        StringBuffer sb = new StringBuffer();
                        sb.append(String.format("%d-%02d-%02d",
                                datePicker.getYear(),
                                datePicker.getMonth() + 1,
                                datePicker.getDayOfMonth()));
                        release_circle_tv_endTime.setText(sb);
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
            }
        });
        //请选择就诊科室
        release_circle_iv_choose_department.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopWindowDepartment(v);
            }
        });
        //对应病症
        release_circle_iv_choose_disease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopWindowDisease(v);
            }
        });
        release_circle_btn_publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //标题
                String title = release_circle_et_title.getText().toString().trim();
                //病症详情
                String detail = release_circle_et_detail.getText().toString().trim();
                //病症描述
                String disease = release_circle_tv_choose_disease.getText().toString().trim();
                //治疗医院
                String treatmentHospital = release_circle_et_treatmentHospital.getText().toString().trim();
                //治疗开始时间 格式’2018-3-26’
                String treatmentStartTime = release_circle_tv_startTime.getText().toString().trim();
                //	治疗结束时间 格式’2018-6-26’
                String treatmentEndTime = release_circle_tv_endTime.getText().toString().trim();

                String treatmentProcess = release_circle_et_treatmentProcess.getText().toString().trim();

                if (TextUtils.isEmpty(title)) {
                    Toast.makeText(ReleaseCirclesActivity.this, "标题不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(detail)) {
                    Toast.makeText(ReleaseCirclesActivity.this, "请输入病症详情", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(disease)) {
                    Toast.makeText(ReleaseCirclesActivity.this, "病症描述", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(disease)) {
                    Toast.makeText(ReleaseCirclesActivity.this, "病症描述", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(treatmentStartTime)) {
                    Toast.makeText(ReleaseCirclesActivity.this, "请选择治疗开始时间", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(treatmentEndTime)) {
                    Toast.makeText(ReleaseCirclesActivity.this, "请选择治疗结束时间", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(treatmentHospital)) {
                    Toast.makeText(ReleaseCirclesActivity.this, "请输入治疗医院", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(treatmentProcess)) {
                    Toast.makeText(ReleaseCirclesActivity.this, "治疗过程描述", Toast.LENGTH_SHORT).show();
                    return;
                }

                Map<String, Object> map = new HashMap<>();
                map.put("title", title);
                map.put("departmentId", id);
                map.put("disease", disease);
                map.put("detail", detail);
                map.put("treatmentHospital", treatmentHospital);
                map.put("treatmentStartTime", treatmentStartTime);
                map.put("treatmentEndTime", treatmentEndTime);
                map.put("treatmentProcess", treatmentProcess);
                map.put("amount", 100);
                //调发布圈子接口
                userId = App.sharedPreferences.getInt("userId", 0);
                sessionId = App.sharedPreferences.getString("sessionId", null);
                mPresenter.getReleasePatientsPresenter(userId, sessionId, map);
                mPresenter.getuploadPatient(userId, sessionId, sickCircleId, parts);
            }
        });

    }

    @Override
    protected void initView() {
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_release_circles;
    }

    @Override
    public void DepartmentListsuccess(DepartmentListBean departmentListBean) {
        final List<DepartmentListBean.ResultBean> result = departmentListBean.getResult();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        ConsultationTwoAdapter consultationTwoAdapter = new ConsultationTwoAdapter(result, this);
        popup_recycler_department.setLayoutManager(gridLayoutManager);
        popup_recycler_department.setAdapter(consultationTwoAdapter);

        consultationTwoAdapter.onItemClickListener(new ConsultationTwoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                id = result.get(position).getId();
                release_circle_tv_choose_department.setText(result.get(position).getDepartmentName());
                Toast.makeText(ReleaseCirclesActivity.this, result.get(position).getDepartmentName(), Toast.LENGTH_SHORT).show();
                popWindow.dismiss();
            }
        });
    }

    @Override
    public void DepartmentListFailure(Throwable e) {

    }

    @Override
    public void CircleListShowsuccess(CircleListShowBean circleListShowBean) {

    }

    @Override
    public void CircleListShowFailure(Throwable e) {

    }

    @Override
    public void KeywordSearchsuccess(KeywordSearchBean keywordSearchBean) {

    }

    @Override
    public void KeywordSearchFailure(Throwable e) {
    }

    @Override
    public void ReleasePatientssuccess(ReleasePatientsBean ReleasePatientsBean) {
        if (ReleasePatientsBean.getStatus().equals("0000")) {
            Toast.makeText(this, ReleasePatientsBean.getMessage(), Toast.LENGTH_SHORT).show();
            sickCircleId = ReleasePatientsBean.getResult();
            Log.i("sickCircleId", "publishSuccess: " + "sickCircleId" + sickCircleId);
            if (parts != null) {
                mPresenter.getuploadPatient(userId, sessionId, sickCircleId, parts);
                Log.i("jjjj", "initModel: 1111" + mPresenter);
            } else {
                //做任务
                mPresenter.getDoTask(userId, sessionId, 1003);
//                    shapeLoadingDialog.dismiss();
                finish();
            }
        } else {
            Toast.makeText(this, ReleasePatientsBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void ReleasePatientsFailure(Throwable e) {

    }

    @Override
    public void UnitDiseasessuccess(UnitDiseaseBean unitDiseaseBean) {
        final List<UnitDiseaseBean.ResultBean> result = unitDiseaseBean.getResult();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        IllnessAdapter illnessAdapter = new IllnessAdapter(result, this);
        popup_recycler_disease.setLayoutManager(gridLayoutManager);
        popup_recycler_disease.setAdapter(illnessAdapter);

        illnessAdapter.onItemClickListener(new IllnessAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String name = result.get(position).getName();
                release_circle_tv_choose_disease.setText(name + "");
                popWindowDisease.dismiss();
            }
        });
    }

    @Override
    public void UnitDiseaseFailure(Throwable e) {

    }

    @Override
    public void uploadPatientsuccess(UploadPatientBean uploadPatientBean) {
        if (uploadPatientBean.getStatus().equals("0000")) {
            Toast.makeText(this, uploadPatientBean.getMessage(), Toast.LENGTH_SHORT).show();
            //做任务
            mPresenter.getDoTask(userId, sessionId, 1003);
            finish();
        } else {
            Toast.makeText(this, uploadPatientBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void uploadPatientFailure(Throwable e) {
        Toast.makeText(this, "请求失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
        Logger.d("fdddds",e.getMessage()+"");
        finish();
    }

    @Override
    public void DoTasksuccess(DoTaskBean doTaskBean) {
        if (doTaskBean.getStatus().equals("0000")) {
            Toast.makeText(this, "每日首发病友圈完成!快去领取奖励吧", Toast.LENGTH_SHORT).show();
            mPresenter.getuploadPatient(userId, sessionId, sickCircleId, parts);
        }
    }
    @Override
    public void DoTaskFailure(Throwable e) {
    }

    ////科室列表
    private void initPopWindowDepartment(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_popip_department, null, false);
        popup_recycler_department = view.findViewById(R.id.popup_recycler_department);
        popWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popWindow.setAnimationStyle(R.anim.anim_pop);
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popWindow.showAsDropDown(v, 30, 30);
        mPresenter.getDepartmentListPresenter();
    }

    //根据科室查询对应病症
    private void initPopWindowDisease(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.item_popip_disease, null, false);
        popup_recycler_disease = view.findViewById(R.id.popup_recycler_disease);
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
        popWindowDisease = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popWindowDisease.setAnimationStyle(R.anim.anim_pop);  //设置加载动画
        popWindowDisease.setTouchable(true);
        popWindowDisease.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindowDisease.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效
        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindowDisease.showAsDropDown(v, 50, 0);

        //根据科室查询对应病症
        mPresenter.getUnitDiseasePresenter(id);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    /**
     * 刷新图片显示
     **/
    private void refreshGridLayout() {
        release_circle_iv_upload_Picture.setVisibility(View.VISIBLE);
        release_circle_iv_upload_Picture.removeAllViews();
        int num = picList.size();
        final int picSize = (getScreenWidth() - dp(20)) / 4;
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(picSize, picSize);
        if (num >= 6) {
            release_circle_iv_upload_Picture.setVisibility(View.VISIBLE);
            for (int i = 0; i < num; i++) {
                RelativeLayout view = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.a_layout_pic_select, null);
                view.setLayoutParams(params);
                view.setPadding(dp(5), dp(5), dp(5), dp(5));
                setPicItemClick(view, i);
                release_circle_iv_upload_Picture.addView(view);
            }
        } else {
            release_circle_iv_upload_Picture.setVisibility(View.VISIBLE);
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(dp(5), dp(5), dp(5), dp(5));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startPick();
                }
            });
            for (int i = 0; i < num; i++) {
                RelativeLayout view = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.a_layout_pic_select, null);
                view.setLayoutParams(params);
                view.setPadding(dp(5), dp(5), dp(5), dp(5));
                setPicItemClick(view, i);
                release_circle_iv_upload_Picture.addView(view);
                Log.e("qwe", "" + view);
            }
            release_circle_iv_upload_Picture.addView(imageView);
        }
    }

    public void setPicItemClick(RelativeLayout layout, final int pos) {
        ImageView iv_pic = (ImageView) layout.getChildAt(0);
        ImageView iv_close = (ImageView) layout.getChildAt(1);
        Glide.with(this).load(picList.get(pos).path).into(iv_pic);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picList.remove(pos);
                refreshGridLayout();
            }
        });
        iv_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preview(pos);
            }
        });
    }

    public int dp(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, this.getResources().getDisplayMetrics());
    }

    public int getScreenWidth() {
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        assert wm != null;
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    private void startPick() {

        pick(6 - picList.size());
    }

    private void preview(int pos) {
        IPickerPresenter presenter = true ? new WeChatPresenter() : new CustomImgPickerPresenter();
        //开启编辑预览
        ImagePicker.preview(this, presenter, picList, pos, new OnImagePickCompleteListener() {
            @Override
            public void onImagePickComplete(ArrayList<ImageItem> items) {
                //图片编辑回调，主线程
                picList.clear();
                picList.addAll(items);
                refreshGridLayout();
            }
        });
    }

    private void pick(int count) {
        final IPickerPresenter presenter = true ? new WeChatPresenter() : new CustomImgPickerPresenter();
        ImagePicker.withMulti(presenter)//指定presenter
                .setMaxCount(count)//设置选择的最大数
                .setColumnCount(4)//设置显示列数
                .showCamera(true)//设置是否显示拍照按钮（在列表第一个）
                .setMaxVideoDuration(120000)//设置视频可选择的最大时长
                //设置只能选择视频或图片
                .setSinglePickImageOrVideoType(true)
                //设置只能选择一个视频
                .setVideoSinglePick(true)
                //设置下次选择需要屏蔽的图片或视频（简单点就是不可重复选择）
                .setShieldList(new ArrayList<String>())
                //设置下次选择需要带入的图片和视频（简单点就是记录上次选择的图片，可以取消之前选择）
                .setLastImageList(new ArrayList<String>())
                //调用多选
                .pick(this, new OnImagePickCompleteListener() {

                    private String picture      ;

                    @Override
                    public void onImagePickComplete(ArrayList<ImageItem> items) {
                        //处理回调回来的图片信息，主线程
                        picList.addAll(items);
                        list = new ArrayList<>();
                        for (int i = 0; i < items.size(); i++) {
                            list.add(items.get(i).path);
                        }
                        for (int i = 0; i < list.size(); i++) {
                            picture = list.get(i);
                        }
                        File file = new File(picture);
                        RequestBody requestBody = MultipartBody.create(MediaType.parse("image/*"), file);
                        MultipartBody.Part part = MultipartBody.Part.createFormData("picture", file.getName(), requestBody);
                        parts.add(part);
                        refreshGridLayout();
                    }
                });

    }

}
