package com.wd.health.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wd.health.R;
import com.wd.health.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InquiryNoticeListActivity extends AppCompatActivity {

    @BindView(R2.id.fanhui)
    ImageView fanhui;
   /* @BindView(R.id.xrecy_wenzhen)
    XRecyclerView xrecyWenzhen;*/
    @BindView(R2.id.no_imageview)
    ImageView noImageview;
    @BindView(R2.id.no_message)
    TextView noMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R2.layout.activity_inquiry_notice_list);
        ButterKnife.bind(this);
        //xrecyWenzhen.setVisibility(View.GONE);
        noImageview.setVisibility(View.VISIBLE);
        noMessage.setText("暂无消息");
    }

    @OnClick(R.id.fanhui)
    public void onClick() {
        finish();
    }
}
