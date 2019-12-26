package com.wd.health.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.bean.PerfectUserInfoBean;
import com.wd.health.bean.evebus.SeekBarBean;
import com.wd.health.contract.PerfectUserInfoContract;
import com.wd.health.presenter.PerfectUserInfoPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignActivity extends BaseActivity<PerfectUserInfoPresenter> implements PerfectUserInfoContract.IView {


    @BindView(R2.id.fanhui)
    ImageView fanhui;
    @BindView(R2.id.image_height)
    ImageView imageHeight;
    @BindView(R2.id.sign_seekbar)
    SeekBar signSeekbar;
    @BindView(R2.id.image_weight)
    ImageView imageWeight;
    @BindView(R2.id.we_seekbar)
    SeekBar weSeekbar;
    @BindView(R2.id.age_weight)
    ImageView ageWeight;
    @BindView(R2.id.age_seekbar)
    SeekBar ageSeekbar;
    @BindView(R2.id.btn_finish_sign)
    Button btnFinishSign;
    @BindView(R2.id.text_progress)
    TextView textProgress;
    @BindView(R2.id.text2_progress)
    TextView text2Progress;
    @BindView(R2.id.text3_progress)
    TextView text3Progress;
    private int height;
    private int weight;
    private int age;


    @Override
    protected int provideLayoutId() {
        return R2.layout.activity_sign;
    }


    @Override
    protected void initView() {
        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String id = sp.getString("id", "");
        String sessionId = sp.getString("sessionId", "");
        btnFinishSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.PerfectUserInfoPresenter(id, sessionId, 18+age + "", 50+height + "", 30+weight + "");
                finish();
            }
        });

    }

    @Override
    protected void initData() {
        signSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textProgress.setText(50+progress+"cm");
                int position = signSeekbar.getProgress()-200;
                int x = seekBar.getWidth();
                int seekbarWidth = signSeekbar.getWidth();
                float width = (position*x)/200+seekbarWidth; //seekbar当前位置的宽度
                textProgress.setX(width);
               // Toast.makeText(SignActivity.this, "" + progress, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
              //  Toast.makeText(SignActivity.this, "开始：" + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                height = seekBar.getProgress();
                EventBus.getDefault().postSticky(new SeekBarBean(50 + height + "", 18 + age + "", 30 + weight + ""));
            }
        });

        ageSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                text3Progress.setText(18+progress+"");
                int position = ageSeekbar.getProgress()-102;
                int x = seekBar.getWidth();
                int seekbarWidth = ageSeekbar.getWidth();
                float width = (position*x)/102+seekbarWidth; //seekbar当前位置的宽度
                text3Progress.setX(width);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                age = seekBar.getProgress();
                EventBus.getDefault().postSticky(new SeekBarBean(50 + height + "", 18 + age + "", 30 + weight + ""));
            }
        });

        weSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                text2Progress.setText(30+progress+"kg");
                int position = weSeekbar.getProgress()-120;
                int x = seekBar.getWidth();
                int seekbarWidth = weSeekbar.getWidth();
                float width = (position*x)/120+seekbarWidth; //seekbar当前位置的宽度
                text2Progress.setX(width);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                weight = seekBar.getProgress();
                EventBus.getDefault().postSticky(new SeekBarBean(50 + height + "", 18 + age + "", 30 + weight + ""));
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    protected PerfectUserInfoPresenter providePresenter() {
        return new PerfectUserInfoPresenter();
    }

    @OnClick(R.id.fanhui)
    public void onClick() {
        finish();
    }

    @Override
    public void onPerfectUserInfoSuccess(PerfectUserInfoBean bean) {
        if (bean.getStatus().equals("0000")) {
            Toast.makeText(this, "" + bean.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }
}
