package com.wd.health;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.wd.health.bean.Jgbean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.enums.ContentType;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

public class JgActivity extends AppCompatActivity {

    private static final String TAG = "JgActivity";
    @BindView(R.id.editText3)
    EditText editText3;
    @BindView(R.id.btton3)
    Button btton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jg);
        ButterKnife.bind(this);
        List<Conversation> conversationList = JMessageClient.getConversationList();
        Conversation conversation = JMessageClient.getSingleConversation("lhb1", "c7f6a1d56cb8da740fd18bfa");
        String id = conversationList.get(0).getId();
        //Object targetInfo = conversation.getTargetInfo();
        String title = conversation.getTitle();
        Log.i(TAG, "onCreate1: "+title);

        List<Message> allMessage = conversation.getAllMessage();
        Log.d(TAG, "onCreate12: "+allMessage.toString());
        for (int i = 0; i < allMessage.size(); i++) {

            String fromType = allMessage.get(i).toString();
            ContentType content = allMessage.get(i).getContent().getContentType();
            Message message = conversation.getMessage(0);
           // MessageContent content1 = message.getContent();

            content.equals("text");
            Log.d("fromType",content.toString());
        }
        String s = allMessage.get(0).getContent().toJson();
        Gson gson = new Gson();
        Jgbean jgbean = gson.fromJson(s, Jgbean.class);
        String text = jgbean.getText();
        Log.d(TAG, "onCreate: "+text);
        Toast.makeText(this, ""+text, Toast.LENGTH_SHORT).show();
        JMessageClient.registerEventReceiver(this);
    }

    @OnClick(R.id.btton3)
    public void onViewClicked() {
        String s = editText3.getText().toString();
        Conversation.createSingleConversation("lhb1", "c7f6a1d56cb8da740fd18bfa");
        Message message = JMessageClient.createSingleTextMessage("lhb1", "c7f6a1d56cb8da740fd18bfa", s);
        JMessageClient.sendMessage(message);
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }
    public void onEventMainThread(MessageEvent event) {
        //获取事件发生的会话对象
        Message newMessage = event.getMessage();//获取此次离线期间会话收到的新消息列表
        switch (newMessage.getContentType()){
            case text:
                TextContent content= (TextContent) newMessage.getContent();
                String text = content.getText();
                Log.i(TAG, "onEvent: "+text);
                break;
        }
    }
    public void onEvent(NotificationClickEvent event) {
        finish();
        Intent notificationIntent = new Intent(this, JgActivity.class);
        this.startActivity(notificationIntent);// 自定义跳转到指定页面
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        JMessageClient.unRegisterEventReceiver(this);
    }
}
