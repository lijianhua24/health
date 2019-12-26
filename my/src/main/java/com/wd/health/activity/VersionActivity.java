package com.wd.health.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.wd.health.R;
import com.wd.health.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class VersionActivity extends AppCompatActivity {

    @BindView(R2.id.fanhui)
    ImageView fanhui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fanhui)
    public void onClick() {
        finish();
    }
}
