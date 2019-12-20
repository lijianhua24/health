package com.wd.homemodel.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wd.homemodel.R;
import com.wd.homemodel.adapter.CommentAdapter;
import com.wd.homemodel.adapter.MyGiftAdapter;
import com.wd.homemodel.bean.DoctorDetailsBean;
import com.wd.homemodel.contract.HomeContract;
import com.wd.homemodel.presenter.DoctorListPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import java.util.List;

import butterknife.BindView;

public class PersonalActivity extends BaseActivity<DoctorListPresenter> implements HomeContract.CheckDoctorsContreact.IView {
    private static final String TAG = "PersonalActivity";
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.rela)
    RelativeLayout rela;
    @BindView(R.id.head_img)
    ImageView headImg;
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
    @BindView(R.id.gift)
    TextView gift;
    @BindView(R.id.recrivegift)
    TextView recrivegift;
    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.like)
    ImageView like;
    @BindView(R.id.jieshao)
    TextView jieshao;
    @BindView(R.id.scaddress)
    TextView scaddress;
    @BindView(R.id.commit_count)
    TextView commitCount;
    @BindView(R.id.recy_commit)
    RecyclerView recyCommit;
    @BindView(R.id.more)
    TextView more;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.go_now)
    Button goNow;
    @BindView(R.id.nolike)
    ImageView nolike;

    private int doctorId;
    private int userId;
    private String sesssionId;
    private int servicePrice;
    @Override
    protected DoctorListPresenter providePresenter() {
        return new DoctorListPresenter();
    }

    @Override
    protected void initData() {

        doctorId =this.getIntent().getIntExtra("doctorId", 0);
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        userId = user.getInt("userId", 0);
        sesssionId = user.getString("sesssionId", "");
        if (userId != 0 && !sesssionId.isEmpty()) {
            mPresenter.getDoctorDetailsPresenter(userId+"", sesssionId, doctorId+"");
        } else {
            mPresenter.getDoctorDetailsPresenter("0", null, doctorId+"");
        }
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_personal;
    }

    @Override
    public void onCheckDoctorsSuccess(Object data) {

    }

    @Override
    public void onCheckDoctorsFailure(Throwable e) {

    }

    @Override
    public void onDoctorDetailsSuccess(Object data) {
        DoctorDetailsBean doctorInfoBean = (DoctorDetailsBean) data;
        if (doctorInfoBean.getStatus().equals("0000")) {
            DoctorDetailsBean.ResultBean result = doctorInfoBean.getResult();
            if (result != null) {
                int followFlag = result.getFollowFlag();
                if (followFlag == 1) {
                    nolike.setVisibility(View.GONE);
                    like.setVisibility(View.VISIBLE);
                }
                servicePrice = result.getServicePrice();
                price.setText( servicePrice + "H币/次");
                Glide.with(this).load(result.getImagePic()).into(headImg);
                name.setText(result.getDoctorName());
                work.setText(result.getJobTitle());
                address.setText(result.getInauguralHospital());
                good.setText("好评率 " + result.getPraise());
                number.setText("服务患者数 " + result.getPraiseNum());
                List<DoctorDetailsBean.ResultBean.DoctorReceiveGiftListBean> list = result.getDoctorReceiveGiftList();
                if (!list.isEmpty()) {
                    recrivegift.setVisibility(View.GONE);
                    recy.setVisibility(View.VISIBLE);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PersonalActivity.this);
                    linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                    recy.setLayoutManager(linearLayoutManager);
                    MyGiftAdapter myGiftAdapter = new MyGiftAdapter(list, PersonalActivity.this);
                    recy.setAdapter(myGiftAdapter);
                } else {
                    recrivegift.setVisibility(View.VISIBLE);
                    recy.setVisibility(View.GONE);
                }
                jieshao.setText(result.getPersonalProfile());
                scaddress.setText(result.getGoodField());
                commitCount.setText("(" + result.getCommentNum() + "条评论)");
                if (result.getCommentNum() != 0) {
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PersonalActivity.this);
                    linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                    recyCommit.setLayoutManager(linearLayoutManager);
                    List<DoctorDetailsBean.ResultBean.CommentListBean> commentList = result.getCommentList();
                    CommentAdapter commentAdapter = new CommentAdapter(commentList, PersonalActivity.this);
                    recyCommit.setAdapter(commentAdapter);
                    if (commentList.size() == 3 && result.getCommentNum() > 3) {
                        more.setVisibility(View.VISIBLE);
                        more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //Intent intent = new Intent(PersonalActivity.this, CommentActivity.class);
                               // startActivity(intent);
                            }
                        });
                    }
                }
            } else {
                Toast.makeText(this, doctorInfoBean.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, doctorInfoBean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDoctorDetailsFailure(Throwable e) {

    }
}
