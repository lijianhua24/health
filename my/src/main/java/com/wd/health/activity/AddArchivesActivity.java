package com.wd.health.activity;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.TuAdapter;
import com.wd.health.bean.user.AddArchivesBean;
import com.wd.health.bean.user.DeleteArchivesBean;
import com.wd.health.bean.user.UpdateArchivesBean;
import com.wd.health.bean.user.UserArchivesBean;
import com.wd.health.bean.user.UserArchivesPictureBean;
import com.wd.health.contract.MyFileContract;
import com.wd.health.presenter.MyFilePresenter;
import com.wd.mylibrary.Base.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddArchivesActivity extends BaseActivity<MyFilePresenter> implements MyFileContract.Iview {

    @BindView(R2.id.message_fanhui)
    ImageView messageFanhui;
    @BindView(R2.id.diseaseMain)
    EditText diseaseMain;
    @BindView(R2.id.diseaseNow)
    EditText diseaseNow;
    @BindView(R2.id.diseaseBefore)
    EditText diseaseBefore;
    @BindView(R2.id.treatmentHospitalRecent)
    EditText treatmentHospitalRecent;
    @BindView(R2.id.start)
    TextView start;
    @BindView(R2.id.edit_starttime)
    TextView editStarttime;
    @BindView(R2.id.img_startTime)
    ImageView imgStartTime;
    @BindView(R2.id.startTime)
    RelativeLayout startTime;
    @BindView(R2.id.end)
    TextView end;
    @BindView(R2.id.edit_endtime)
    TextView editEndtime;
    @BindView(R2.id.img_endTime)
    ImageView imgEndTime;
    @BindView(R2.id.endTime)
    RelativeLayout endTime;
    @BindView(R2.id.treatmentProcess)
    EditText treatmentProcess;
    @BindView(R2.id.bo_image_list)
    RecyclerView boImageList;
    @BindView(R2.id.add_image)
    ImageView addImage;
    @BindView(R2.id.addFiles)
    Button addFiles;
    private Unbinder bind;
    private SharedPreferences sp;
    private String userId;
    private String sessionId;
    private ArrayList<File> fileList =new ArrayList<>();
    private ArrayList<MultipartBody.Part> partList =new ArrayList<>();
    private MultipartBody.Part part;
    Calendar calendar = Calendar.getInstance(Locale.CHINA);
    @Override
    protected MyFilePresenter providePresenter() {
        return new MyFilePresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_add_archives;
    }

    @Override
    protected void initView() {
        // TODO: add setContentView(...) invocation
        bind = ButterKnife.bind(this);
    }

    @Override
    protected void initData() {
        //取userId和sessionId
        sp = getSharedPreferences("health", Context.MODE_PRIVATE);
        userId = sp.getString("id","");
        sessionId = sp.getString("sessionId", null);
        //点击保存
        addFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取输入框的内容
                //主要病症
                String main = diseaseMain.getText().toString().trim();
                //现病史
                String now = diseaseNow.getText().toString().trim();
                //既往史
                String before = diseaseBefore.getText().toString().trim();
                //最近治疗医院
                String recent = treatmentHospitalRecent.getText().toString().trim();
                //治疗过程
                String process = treatmentProcess.getText().toString().trim();
                String startte = editStarttime.getText().toString().trim();
                String endte = end.getText().toString().trim();
                Map<String, Object> map = new HashMap<>();
                map.put("diseaseMain",main);
                map.put("diseaseNow",now);
                map.put("diseaseBefore",before);
                map.put("treatmentHospitalRecent",recent);
                map.put("treatmentProcess",process);
                map.put("treatmentStartTime",startte);
                map.put("treatmentEndTime",endte);
                mPresenter.getPresenteraddarchives(userId,sessionId,map);
                mPresenter.getPresenterpicture(userId,sessionId,part);

            }
        });
        //点击图片进入相册
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fileList.size() >=6){
                    Toast.makeText(AddArchivesActivity.this, "最多选取6张", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent1.setType("image/*");
                    startActivityForResult(intent1, 1);
                }
            }
        });
        //设置在activity启动的时候输入法默认是不开启的
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //开始时间
        imgStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(AddArchivesActivity.this);
                final View view = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog_dates, null);
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
                        editStarttime.setText(sb);
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
        imgEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(AddArchivesActivity.this);
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
                        end.setText(sb);
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

    }

    @Override
    public void onMyFilesuccess(UserArchivesBean userArchivesBean) {

    }

    @Override
    public void onMyFilesuccess(DeleteArchivesBean deleteArchivesBean) {

    }

    @Override
    public void onMyFilesuccess(AddArchivesBean addArchivesBean) {
                if (addArchivesBean.getStatus().equals("0000")){
                    Toast.makeText(this, addArchivesBean.getMessage(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddArchivesActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(this, addArchivesBean.getMessage(), Toast.LENGTH_SHORT).show();
                }
    }

    @Override
    public void onMyFilesuccess(UpdateArchivesBean updateArchivesBean) {

    }

    @Override
    public void onMyFilesuccess(UserArchivesPictureBean userArchivesPictureBean) {
            if (userArchivesPictureBean.getStatus().equals("0000")){
                Toast.makeText(this, userArchivesPictureBean.getMessage(), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, userArchivesPictureBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
    }

    @Override
    public void onMyFileFaiure(Throwable e) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode ==1 & data !=null){
            Uri dataData = data.getData();

            String[] str={MediaStore.Images.Media.DATA};
            //获取内容解析器
            ContentResolver contentResolver = getContentResolver();
            Cursor cursor = contentResolver.query(dataData, str, null, null, null);
            //移至第一位
            cursor.moveToFirst();
            //获取下标
            int columnIndex = cursor.getColumnIndex(str[0]);
            String cursorString = cursor.getString(columnIndex);
            File file = new File(cursorString);
            fileList.add(file);

            //图片的布局
            GridLayoutManager gridLayoutManager = new GridLayoutManager(AddArchivesActivity.this, 3);
            boImageList.setLayoutManager(gridLayoutManager);
            //适配器
            TuAdapter tuAdapter = new TuAdapter(fileList,AddArchivesActivity.this);
            boImageList.setAdapter(tuAdapter);

            //格式
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            //创建表格数据
            part = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
            partList.add(part);

            mPresenter.getPresenterpicture(userId,sessionId, part);


        }
    }


    @OnClick(R2.id.message_fanhui)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
