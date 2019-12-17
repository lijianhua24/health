package com.wd.health;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.wd.mylibrary.Test.ToastUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ToastUtils.show("李建华");
    }
}
