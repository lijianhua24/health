package com.wd.doctor.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.wd.doctor.R;

public class WelcomeActivity extends AppCompatActivity {
    private static final String TAG = "WelcomeActivity";
    private int a=2;
    private Handler handler =new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (a > 0) {
                    edit.putBoolean("aa",false);
                    edit.commit();
                    a--;
                    handler.sendEmptyMessageDelayed(0,1000);
                }
                else {
                    edit.putBoolean("aa",true);
                    edit.commit();
                        startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                        finish();
                }
            }
        }
    };
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        sharedPreferences = getSharedPreferences("config", MODE_PRIVATE);
        edit = sharedPreferences.edit();
        boolean zz = sharedPreferences.getBoolean("aa", false);
        if (zz) {
            startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
            finish();
        }else {
            handler.sendEmptyMessageDelayed(0,1000);
        }

    }
}
