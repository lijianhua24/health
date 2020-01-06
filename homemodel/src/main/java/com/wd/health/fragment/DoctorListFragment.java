package com.wd.health.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.wd.health.R2;
import com.wd.health.view.JgActivity;
import com.wd.health.R;
import com.wd.health.adapter.CheckDortorsAdapter;
import com.wd.health.app.App;
import com.wd.health.bean.CheckDoctorsBean;
import com.wd.health.bean.ConsultBean;
import com.wd.health.contract.HomeContract;
import com.wd.health.presenter.CheckDoctorsPresenter;
import com.wd.health.view.PersonalActivity;
import com.wd.mylibrary.Base.BaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class DoctorListFragment extends BaseFragment<CheckDoctorsPresenter> implements HomeContract.CheckDoctorsContreact.IView {

    private static final String TAG = "asdasdasdas";
    @BindView(R2.id.tablayout)
    TabLayout tablayout;
    @BindView(R2.id.imgs)
    ImageView img;
    @BindView(R2.id.name)
    TextView name;
    @BindView(R2.id.work)
    TextView work;
    @BindView(R2.id.address)
    TextView address;
    @BindView(R2.id.good)
    TextView good;
    @BindView(R2.id.number)
    TextView number;
    @BindView(R2.id.more)
    ImageView more;
    @BindView(R2.id.money)
    TextView money;
    @BindView(R2.id.btn_go)
    Button btnGo;
    @BindView(R2.id.up)
    ImageView up;
    @BindView(R2.id.recy)
    RecyclerView recy;
    @BindView(R2.id.next)
    ImageView next;
    @BindView(R2.id.page)
    TextView page1;
    private String userId;
    private String sessionId;
    private int departmentId;
    private int position;
    int page = 1;
    private int doctorId;
    private int doctorId1;

    @Override
    protected CheckDoctorsPresenter providePresenter() {
        return new CheckDoctorsPresenter();
    }

    @Override
    protected void initData() {
        userId = App.sharedPreferences.getString("userId", null);
        sessionId = App.sharedPreferences.getString("sessionId", null);
        int id = App.sharedPreferences.getInt("id", 0);
        Bundle arguments = getArguments();
        departmentId = arguments.getInt("departmentId");

           //if (departmentId == id){
               mPresenter.getCheckDoctorsPresenter(departmentId, 1, 0, page, 4);
          // }
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if (userId!=null && sessionId!=null){
                mPresenter.getConsultPresenter(userId,sessionId,doctorId+"");
            }
            }
        });
    }

    @Override
    protected void initView() {

        tablayout.addTab(tablayout.newTab().setText("综合"));
        tablayout.addTab(tablayout.newTab().setText("好评"));
        tablayout.addTab(tablayout.newTab().setText("咨询数"));
        tablayout.addTab(tablayout.newTab().setText("价格"));
        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                if (position==0){
                    mPresenter.getCheckDoctorsPresenter(departmentId, 1, 0, page, 4);
                }
                if (position==1){
                    mPresenter.getCheckDoctorsPresenter(departmentId, 2, 0, page, 4);
                }
                if (position==2){
                    mPresenter.getCheckDoctorsPresenter(departmentId, 3, 0, page, 4);
                }
                if (position==3){
                    mPresenter.getCheckDoctorsPresenter(departmentId, 4, 0, page, 4);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    protected int provideLayoutId() {
        return R2.layout.fragment_doctor_list;
    }


    @Override
    public void onCheckDoctorsSuccess(Object data) {
        CheckDoctorsBean checkDoctorsBean = (CheckDoctorsBean) data;
        Log.d(TAG, "onCheckDoctorsSuccess: "+checkDoctorsBean.getMessage());
        if (checkDoctorsBean.getStatus().equals("0000")) {
            List<CheckDoctorsBean.ResultBean> result = checkDoctorsBean.getResult();
            if (!result.isEmpty()) {
                if (result!=null){
                    doctorId1 = result.get(0).getDoctorId();
                    doctorId = result.get(0).getDoctorId();
                    Glide.with(getContext()).load(result.get(0).getImagePic()).into(img);
                    name.setText(result.get(0).getDoctorName());
                    address.setText(result.get(0).getInauguralHospital());
                    good.setText("好评率 " + result.get(0).getPraise());
                    number.setText("服务患者数 " + result.get(0).getPraiseNum());
                    money.setText(result.get(0).getServicePrice() + "H币/次");
                    page1.setText("" + page);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                    recy.setLayoutManager(linearLayoutManager);
                    CheckDortorsAdapter myAdapter = new CheckDortorsAdapter(result, getActivity());
                    recy.setAdapter(myAdapter);
                    if (page <= 1) {
                        up.setVisibility(View.GONE);
                    } else {
                        up.setVisibility(View.VISIBLE);
                    }
                    if (result.size() <= 3) {
                        next.setVisibility(View.GONE);
                    } else {
                        next.setVisibility(View.VISIBLE);
                    }

                    myAdapter.setOnCLickListener(new CheckDortorsAdapter.OnCLickListener() {
                        @Override
                        public void onclick(int position) {
                            doctorId = result.get(position).getDoctorId();
                            Glide.with(getContext()).load(result.get(position).getImagePic()).into(img);
                            name.setText(result.get(position).getDoctorName());
                            address.setText(result.get(position).getInauguralHospital());
                            good.setText("好评率 " + result.get(position).getPraise());
                            number.setText("服务患者数 " + result.get(position).getPraiseNum());
                            money.setText(result.get(position).getServicePrice() + "H币/次");
                        }
                    });
                }

            } else {
                Toast.makeText(getActivity(), "暂无更多数据", Toast.LENGTH_SHORT).show();
                next.setVisibility(View.GONE);
            }
        } else {
            Toast.makeText(getActivity(), checkDoctorsBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCheckDoctorsFailure(Throwable e) {
        Log.d(TAG, "onCheckDoctorsFailure: "+e);
    }

    @Override
    public void onDoctorDetailsSuccess(Object data) {

    }

    @Override
    public void onDoctorDetailsFailure(Throwable e) {

    }

    @Override
    public void onAttentionSuccess(Object data) {

    }

    @Override
    public void onAttentionFailure(Throwable e) {

    }

    @Override
    public void onUnsubscribeSuccess(Object data) {

    }

    @Override
    public void onUnsubscribeFailure(Throwable e) {

    }

    @Override
    public void onConsultSuccess(Object data) {
        startActivity(new Intent(getActivity(), JgActivity.class));
        ConsultBean consultBean = (ConsultBean) data;
        if (consultBean.getMessage().contains("查询成功")){
            Dialog dialog = new Dialog(getActivity(), R.style.DialogTheme);
            View inflate = View.inflate(getActivity(), R.layout.one_layout, null);
            Button one_diss = inflate.findViewById(R.id.one_diss);
            Button one_go = inflate.findViewById(R.id.one_go);
            dialog.setContentView(inflate);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.CENTER);
            window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.show();
            one_diss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            one_go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), JgActivity.class));
                }
            });
        }else if (consultBean.getMessage().contains("有正在沟通中的咨询")){
            Dialog dialog = new Dialog(getActivity(), R.style.DialogTheme);
            View inflate = View.inflate(getActivity(), R.layout.two_layout, null);
            Button one_diss = inflate.findViewById(R.id.two_diss);
            Button one_go = inflate.findViewById(R.id.two_go);
            dialog.setContentView(inflate);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.CENTER);
            window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.show();
            one_diss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });
            one_go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    @Override
    public void onConsultFailure(Throwable e) {

    }

    @SuppressLint("InvalidR2Usage")
    @OnClick({R2.id.up, R2.id.next, R2.id.more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R2.id.up:
                page = page - 1;
                if (position == 0) {
                    mPresenter.getCheckDoctorsPresenter(departmentId, 1, 0, page, 4);
                }
                if (position == 1) {
                    mPresenter.getCheckDoctorsPresenter(departmentId, 2, 0, page, 4);
                }
                if (position == 2) {
                    mPresenter.getCheckDoctorsPresenter(departmentId, 3, 0, page, 4);
                }
                if (position == 3) {
                    mPresenter.getCheckDoctorsPresenter(departmentId, 4, 0, page, 4);
                }
                page1.setText("" + page);
                break;
            case R2.id.next:
                page++;
                if (position == 0) {
                    mPresenter.getCheckDoctorsPresenter(departmentId, 1, 0, page, 4);
                }
                if (position == 1) {
                    mPresenter.getCheckDoctorsPresenter(departmentId, 2, 0, page, 4);
                }
                if (position == 2) {
                    mPresenter.getCheckDoctorsPresenter(departmentId, 3, 0, page, 4);
                }
                if (position == 3) {
                    mPresenter.getCheckDoctorsPresenter(departmentId, 4, 0, page, 4);
                }
                page1.setText("" + page);
                break;
            case R2.id.more:
                Intent intent = new Intent(getActivity(), PersonalActivity.class);
                intent.putExtra("doctorId",doctorId);
                startActivity(intent);
                break;
        }
    }
}
