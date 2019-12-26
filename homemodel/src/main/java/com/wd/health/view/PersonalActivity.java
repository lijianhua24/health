package com.wd.health.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.CommentAdapter;
import com.wd.health.adapter.MyGiftAdapter;
import com.wd.health.app.App;
import com.wd.health.bean.AttentionBean;
import com.wd.health.bean.DoctorDetailsBean;
import com.wd.health.bean.UnsubscribeBean;
import com.wd.health.contract.HomeContract;
import com.wd.health.presenter.DoctorListPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import java.util.List;

import butterknife.BindView;

public class PersonalActivity extends BaseActivity<DoctorListPresenter> implements HomeContract.CheckDoctorsContreact.IView {
    private static final String TAG = "PersonalActivity";
    @BindView(R2.id.back)
    ImageView back;
    @BindView(R2.id.rela)
    RelativeLayout rela;
    @BindView(R2.id.head_img)
    ImageView headImg;
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
    @BindView(R2.id.gift)
    TextView gift;
    @BindView(R2.id.recrivegift)
    TextView recrivegift;
    @BindView(R2.id.recy)
    RecyclerView recy;
    @BindView(R2.id.jieshao)
    TextView jieshao;
    @BindView(R2.id.scaddress)
    TextView scaddress;
    @BindView(R2.id.commit_count)
    TextView commitCount;
    @BindView(R2.id.recy_commit)
    RecyclerView recyCommit;
    @BindView(R2.id.more)
    TextView more;
    @BindView(R2.id.price)
    TextView price;
    @BindView(R2.id.go_now)
    Button goNow;
    @BindView(R2.id.nolike)
    ImageView nolike;

    private int doctorId;
    private int servicePrice;
    private String userId;
    private String sessionId;
    private boolean Guanzhu;
    @Override
    protected DoctorListPresenter providePresenter() {
        return new DoctorListPresenter();
    }

    @Override
    protected void initData() {

        doctorId =this.getIntent().getIntExtra("doctorId", 0);
        userId = App.sharedPreferences.getString("userId", null);
        sessionId = App.sharedPreferences.getString("sessionId", null);
        if (userId !=null && sessionId!=null) {
            mPresenter.getDoctorDetailsPresenter(userId, sessionId, doctorId+"");
        } else {
            mPresenter.getDoctorDetailsPresenter("0", null, doctorId+"");
        }
        nolike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Guanzhu =!Guanzhu;
                if (userId!=null && sessionId!=null){

                    if (Guanzhu){
                        mPresenter.getAttentionPresenter(userId,sessionId,doctorId+"");
                        nolike.setBackgroundResource(R.mipmap.common_icon_attention_small_s);
                    }else {
                        mPresenter.getUnsubscribePresenter(userId,sessionId,doctorId+"");
                        nolike.setBackgroundResource(R.mipmap.common_icon_attention_small_n);
                    }
                }
            }
        });
        goNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.getConsultPresenter(userId,sessionId,doctorId+"");
            }
        });
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
        Toast.makeText(this, ""+doctorInfoBean.getMessage(), Toast.LENGTH_SHORT).show();
        if (doctorInfoBean.getStatus().equals("0000")) {
            DoctorDetailsBean.ResultBean result = doctorInfoBean.getResult();
            if (result != null) {
                int followFlag = result.getFollowFlag();
                Toast.makeText(this, ""+followFlag, Toast.LENGTH_SHORT).show();
                if (followFlag == 1) {
                    Guanzhu = true;
                    nolike.setBackgroundResource(R.mipmap.common_icon_attention_small_s);

                }else {
                    Guanzhu = false;
                    nolike.setBackgroundResource(R.mipmap.common_icon_attention_small_n);
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

    @Override
    public void onAttentionSuccess(Object data) {
        AttentionBean attentionBean = (AttentionBean) data;
        Toast.makeText(this, ""+attentionBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAttentionFailure(Throwable e) {

    }

    @Override
    public void onUnsubscribeSuccess(Object data) {
        UnsubscribeBean unsubscribeBean = (UnsubscribeBean) data;
        Toast.makeText(this, ""+unsubscribeBean.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUnsubscribeFailure(Throwable e) {

    }

    @Override
    public void onConsultSuccess(Object data) {

    }

    @Override
    public void onConsultFailure(Throwable e) {

    }
}
