package com.wd.health.view.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.wd.mylibrary.Base.BaseActivity;
import com.wd.mylibrary.utils.ImageUtil;

import java.io.File;
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
    @BindView(R.id.release_sickCircle_iv_user_head_pic)
    ImageView release_sickCircle_iv_user_head_pic;
    @BindView(R.id.patient_iv_user_message)
    ImageView patient_iv_user_message;
    @BindView(R.id.release_circle_et_title)
    EditText release_circle_et_title;
    @BindView(R.id.release_circle_tv_choose_department)
    TextView release_circle_tv_choose_department;
    @BindView(R.id.release_circle_iv_choose_department)
    RelativeLayout release_circle_iv_choose_department;
    @BindView(R.id.release_circle_tv_choose_disease)
    TextView release_circle_tv_choose_disease;
    @BindView(R.id.release_circle_iv_choose_disease)
    RelativeLayout release_circle_iv_choose_disease;
    @BindView(R.id.release_circle_et_detail)
    EditText release_circle_et_detail;
    @BindView(R.id.release_circle_et_treatmentHospital)
    EditText release_circle_et_treatmentHospital;
    @BindView(R.id.release_circle_tv_startTime)
    TextView release_circle_tv_startTime;
    @BindView(R.id.release_circle_iv_startTime)
    RelativeLayout release_circle_iv_startTime;
    @BindView(R.id.release_circle_tv_endTime)
    TextView release_circle_tv_endTime;
    @BindView(R.id.release_circle_iv_endTime)
    RelativeLayout release_circle_iv_endTime;
    @BindView(R.id.release_circle_et_treatmentProcess)
    EditText release_circle_et_treatmentProcess;
    @BindView(R.id.release_circle_iv_upload_Picture)
    ImageView release_circle_iv_upload_Picture;
    @BindView(R.id.release_circle_iv_delete_Picture)
    ImageView release_circle_iv_delete_Picture;
    @BindView(R.id.item_switch)
    Switch item_switch;
    @BindView(R.id.button_hbi3)
    Button button_hbi3;
    @BindView(R.id.aaa)
    TextView aaa;
    @BindView(R.id.xuanshangedu_linear)
    LinearLayout linearLayout;
    @BindView(R.id.release_circle_btn_publish)
    Button release_circle_btn_publish;
    @BindView(R.id.release_circle_linear_sick_circle)
    LinearLayout  release_circle_linear_sick_circle;
    Calendar calendar = Calendar.getInstance(Locale.CHINA);
    private int id;
    private PopupWindow popWindow;
    private PopupWindow popWindowDisease;
    private MultipartBody.Part picture;
    private String path;
    private int sickCircleId;
    private RecyclerView popup_recycler_department;
    private RecyclerView popup_recycler_disease;
    private int userId;
    private String sessionId;

    @Override
    protected DepartmentListPresenter providePresenter() {
        return new DepartmentListPresenter();
    }

    @Override
    protected void initData() {
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

        //打开相册
        release_circle_iv_upload_Picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ReleaseCirclesActivity.this, "打开相册", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
        //删除选中图片
        release_circle_iv_delete_Picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                release_circle_iv_upload_Picture.setImageResource(R.mipmap.add);
            }
        });

       /* shapeLoadingDialog = new ShapeLoadingDialog.Builder(ReleaseCirclesActivity.this)
                .loadText("上传图片中...")
                RepleaseCircleBean
                .build();*/
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
                map.put("amount", 0);
                //调发布圈子接口
                userId = App.sharedPreferences.getInt("userId", 0);
                sessionId = App.sharedPreferences.getString("sessionId", null);
                mPresenter.getReleasePatientsPresenter(ReleaseCirclesActivity.this.userId, ReleaseCirclesActivity.this.sessionId, map);
                mPresenter.getuploadPatient(ReleaseCirclesActivity.this.userId, ReleaseCirclesActivity.this.sessionId, sickCircleId, picture);

                /*loadingDailog.show();*/
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
            if (picture != null) {
                mPresenter.getuploadPatient(userId, sessionId, sickCircleId, picture);
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
//                shapeLoadingDialog.dismiss();
            finish();
        } else {
            Toast.makeText(this, uploadPatientBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void uploadPatientFailure(Throwable e) {

    }

    @Override
    public void DoTasksuccess(DoTaskBean doTaskBean) {
        if (doTaskBean.getStatus().equals("0000")) {
            Toast.makeText(this, "每日首发病友圈完成!快去领取奖励吧", Toast.LENGTH_SHORT).show();
            mPresenter.getuploadPatient(userId, sessionId, sickCircleId, picture);
        }
    }


    @Override
    public void DoTaskFailure(Throwable e) {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //判断是不是选中图片了
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getData();
                if (uri != null) {
                    //用一个工具类获取图片的绝对路径,我会粘到下方
                    path = ImageUtil.getPath(this, uri);
                    Glide.with(this).load(path)
                            .placeholder(R.mipmap.add)
                            .error(R.mipmap.add)
                            .into(release_circle_iv_upload_Picture);
                    if (path != null) {
                        //转换为file类型
                        File file = new File(path);
                        //进行类型转换,因为在RetrofitService定义的是@Part MultipartBody.Part,所以要转成这样的格式
                        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        picture = MultipartBody.Part.createFormData("picture", file.getName(), requestBody);
                    }
                }
            } else {
                Toast.makeText(this, "取消相册", Toast.LENGTH_SHORT).show();
            }
        }
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
}
