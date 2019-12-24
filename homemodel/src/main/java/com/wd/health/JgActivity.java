package com.wd.health;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.JMessageClient;

public class JgActivity extends AppCompatActivity {

    private static final String TAG = "JgActivity";
    @BindView(R.id.jg_details_back)
    ImageView jgDetailsBack;
    @BindView(R.id.jg_details_title)
    TextView jgDetailsTitle;
    @BindView(R.id.jg_details_recy)
    RecyclerView jgDetailsRecy;
    @BindView(R.id.jg_details_edit)
    EditText jgDetailsEdit;
    @BindView(R.id.jg_details_img)
    Button jgDetailsImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jg);
        ButterKnife.bind(this);

        jgDetailsRecy.setLayoutManager(new LinearLayoutManager(this));
       /* mAdapter = new JG_details_Adapter(this);
        jgDetailsRecy.setAdapter(mAdapter);

        position = getIntent().getIntExtra("position", 0);
        //设置消息接收 监听
        GlobalEventListener.setJG(this, false);

        JMessageClient.enterSingleConversation(this.userName);*/


    }

}
