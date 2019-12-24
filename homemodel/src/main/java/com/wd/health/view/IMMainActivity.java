package com.wd.health.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wd.health.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.api.BasicCallback;

public class IMMainActivity extends AppCompatActivity {

    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.editText4)
    EditText editText4;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immain);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button, R.id.button2})
    public void onViewClicked(View view) {
        String name = editText3.getText().toString();
        String pwd = editText4.getText().toString();
        switch (view.getId()) {
            case R.id.button:
                JMessageClient.register(name, pwd, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        if (i==0){
                            Toast.makeText(IMMainActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(IMMainActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.button2:
                JMessageClient.login(name, pwd, new BasicCallback() {
                    @Override
                    public void gotResult(int i, String s) {
                        Log.i("1", "gotResult: "+s);

                        if (i==0){
                           startActivity(new Intent(IMMainActivity.this, JgActivity.class));
                            Toast.makeText(IMMainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(IMMainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}
