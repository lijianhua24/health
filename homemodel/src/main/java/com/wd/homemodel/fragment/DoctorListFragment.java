package com.wd.homemodel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;
import com.wd.homemodel.R;
import com.wd.homemodel.adapter.CheckDortorsAdapter;
import com.wd.homemodel.adapter.MyAdapter;
import com.wd.homemodel.app.App;
import com.wd.homemodel.bean.CheckDoctorsBean;
import com.wd.homemodel.contract.HomeContract;
import com.wd.homemodel.presenter.CheckDoctorsPresenter;
import com.wd.mylibrary.Base.BaseFragment;
import com.wd.mylibrary.Base.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class DoctorListFragment extends BaseFragment<CheckDoctorsPresenter> implements HomeContract.CheckDoctorsContreact.IView {

    private static final String TAG = "asdasdasdas";
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.imgs)
    ImageView img;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.work)
    TextView work;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.good)
    TextView good;
    @BindView(R.id.number)
    TextView number;
    @BindView(R.id.more)
    ImageView more;
    @BindView(R.id.money)
    TextView money;
    @BindView(R.id.btn_go)
    Button btnGo;
    @BindView(R.id.up)
    ImageView up;
    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.next)
    ImageView next;
    @BindView(R.id.page)
    TextView page1;

    /*@BindView(R.id.tablayout)
    TabLayout tab;
    @BindView(R.id.pager)
    NoScrollViewPager pager;*/

    /*private ArrayList<String> list;
    private ArrayList<Fragment> list1;*/
    private int departmentId;
    private int position;
    int page = 1;
    private int doctorId;

    @Override
    protected CheckDoctorsPresenter providePresenter() {
        return new CheckDoctorsPresenter();
    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        departmentId = arguments.getInt("departmentId");
        mPresenter.getCheckDoctorsPresenter(departmentId, 1, 0, page, 4);
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
        return R.layout.fragment_doctor_list;
    }


    @Override
    public void onCheckDoctorsSuccess(Object data) {
        CheckDoctorsBean checkDoctorsBean = (CheckDoctorsBean) data;
        Log.d(TAG, "onCheckDoctorsSuccess: "+checkDoctorsBean.getMessage());
        if (checkDoctorsBean.getStatus().equals("0000")) {
            List<CheckDoctorsBean.ResultBean> result = checkDoctorsBean.getResult();
            if (!result.isEmpty()) {
                if (result!=null){
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
    }

    @Override
    public void onDoctorDetailsSuccess(Object data) {

    }

    @Override
    public void onDoctorDetailsFailure(Throwable e) {

    }
    @OnClick({R.id.up, R.id.next, R.id.more})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.up:
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
            case R.id.next:
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
            case R.id.more:
                /*Intent intent = new Intent(getActivity(),PersonalActivity.class);
                intent.putExtra("doctorId",doctorId);
                startActivity(intent);*/
                break;
        }
    }
}
