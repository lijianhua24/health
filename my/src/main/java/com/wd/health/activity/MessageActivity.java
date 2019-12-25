package com.wd.health.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.wd.health.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends AppCompatActivity {

    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.text_biaoji)
    TextView textBiaoji;
    @BindView(R.id.system_message)
    LinearLayout systemMessage;
    @BindView(R.id.wenzhen_message)
    LinearLayout wenzhenMessage;
    @BindView(R.id.H_message)
    LinearLayout HMessage;
    @BindView(R.id.image_none)
    ImageView imageNone;
    @BindView(R.id.btn_none)
    Button btnNone;
    @BindView(R.id.lin_none)
    LinearLayout linNone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.fanhui, R.id.text_biaoji, R.id.image_none, R.id.btn_none, R.id.system_message, R.id.wenzhen_message, R.id.H_message})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fanhui:
                finish();
                break;
            case R.id.text_biaoji:
                break;
            case R.id.image_none:
                linNone.setVisibility(View.GONE);
                break;
            case R.id.btn_none:
                linNone.setVisibility(View.GONE);
                break;
            case R.id.system_message:
                Intent intent = new Intent(MessageActivity.this, SystemMessageActivity.class);
                startActivity(intent);
                break;
            case R.id.wenzhen_message:
                Intent InquiryNoticeListActivity = new Intent(MessageActivity.this, InquiryNoticeListActivity.class);
                startActivity(InquiryNoticeListActivity);
                break;
            case R.id.H_message:
                Intent HealthyCurrency = new Intent(MessageActivity.this, HealthyCurrencyActivity.class);
                startActivity(HealthyCurrency);
                break;
        }
    }
}
