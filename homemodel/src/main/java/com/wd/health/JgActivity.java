package com.wd.health;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;
import java.util.Map;

import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.Message;

public class JgActivity extends AppCompatActivity {

    private static final String TAG = "JgActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jg);
        List<Conversation> conversationList = JMessageClient.getConversationList();
       Conversation conversation = JMessageClient.getSingleConversation("lzk6", "c7f6a1d56cb8da740fd18bfa");
        String id = conversationList.get(0).getId();
        Object targetInfo = conversation.getTargetInfo();
        String title = conversation.getTitle();
        Log.i(TAG, "onCreate1: "+title);
    }
}
