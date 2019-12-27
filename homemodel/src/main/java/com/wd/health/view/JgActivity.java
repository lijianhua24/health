package com.wd.health.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wd.health.R;
import com.wd.health.R2;
import com.wd.health.adapter.RecordingAdapter;
import com.wd.health.app.App;
import com.wd.health.bean.CurrentBean;
import com.wd.health.bean.PushMessageBean;
import com.wd.health.bean.RecordingBean;
import com.wd.health.contract.IMContract;
import com.wd.health.presenter.ImPresenterPresenter;
import com.wd.mylibrary.Base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jmessage.biz.httptask.task.GetEventNotificationTaskMng;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.content.EventNotificationContent;
import cn.jpush.im.android.api.content.MessageContent;
import cn.jpush.im.android.api.content.TextContent;
import cn.jpush.im.android.api.event.MessageEvent;
import cn.jpush.im.android.api.event.NotificationClickEvent;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.api.BasicCallback;

import static cn.jpush.im.android.api.model.Conversation.createSingleConversation;

public class JgActivity extends BaseActivity<ImPresenterPresenter> implements IMContract.IView {


    @BindView(R2.id.titles_touxiang)
    ImageView titlesTouxiang;
    @BindView(R2.id.titles_name)
    TextView titlesName;
    @BindView(R2.id.home_xiaoxi)
    ImageView homeXiaoxi;
    @BindView(R2.id.im_recycler)
    RecyclerView imRecycler;
    @BindView(R2.id.im_smart)
    SmartRefreshLayout imSmart;
    @BindView(R2.id.im_edit)
    EditText imEdit;
    @BindView(R2.id.im_fs_tv)
    TextView imFsTv;
    private String userId;
    private String sessionId;
    private RecordingAdapter recordingAdapter;
    private int doctorId;
    private int recordId;
    private CurrentBean.ResultBean result;
    private List<RecordingBean.ResultBean> result1;
    private String APPKEY = "e10adc3949ba59abbe56e057f20f883e";
    private String userName;

    @Override
    protected ImPresenterPresenter providePresenter() {
        return new ImPresenterPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        userId = App.sharedPreferences.getString("userId", null);
        sessionId = App.sharedPreferences.getString("sessionId", null);
        mPresenter.getCurrentPresenter(userId,sessionId);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
       linearLayoutManager.setReverseLayout(true);//布局反向
        linearLayoutManager.setStackFromEnd(true);//数据反向

        imRecycler.setLayoutManager(linearLayoutManager);
        JMessageClient.registerEventReceiver(this);


    }

    @Override
    protected int provideLayoutId() {
        return R2.layout.activity_jg;
    }

    @Override
    public void onCurrentSuccess(Object data) {
        CurrentBean currentBean = (CurrentBean) data;
        result = currentBean.getResult();
        if (result !=null){
            recordId = result.getRecordId();
            doctorId = result.getDoctorId();
            String doctorName = result.getDoctorName();
            titlesName.setText(doctorName);
            userName = result.getUserName();
            mPresenter.getRecordingPresenter(userId,sessionId, 3882,1,10);
            JMessageClient.login("IStXNe896745795", "e10adc3949ba59abbe56e057f20f883e", new BasicCallback() {
                @Override
                public void gotResult(int i, String s) {

                }
            });



        }
    }

    @Override
    public void onCurrentFailure(Throwable e) {

    }

    @Override
    public void onRecordingSuccess(Object data) {
        RecordingBean recordingBean = (RecordingBean) data;
        result1 = recordingBean.getResult();
        if (result1 !=null){
            imRecycler.setSelected(false);
            recordingAdapter = new RecordingAdapter(this, result1);
            imRecycler.setAdapter(recordingAdapter);
            String userName = result.getUserName();
            recordingAdapter.setId(new RecordingAdapter.getposition() {
                @Override
                public void getposition(int position) {
                    imRecycler.scrollToPosition(position);
                }
            });
        }
    }

    @Override
    public void onRecordingFailure(Throwable e) {

    }

    @Override
    public void onMessageSuccess(Object data) {
        PushMessageBean messageBean = (PushMessageBean) data;
        String message = messageBean.getMessage();
        if (message.contains("推送成功")){
            if (recordingAdapter!=null){
                recordingAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onMessageFailure(Throwable e) {

    }



    @OnClick({R.id.titles_touxiang, R.id.im_fs_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.titles_touxiang:
                fileList();
                break;
            case R.id.im_fs_tv:
                String s = imEdit.getText().toString();
                if (s!=null){
                    mPresenter.getMessagePresenter(userId,sessionId,3882,s,1,156);

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                    linearLayoutManager.setReverseLayout(true);//布局反向
                    linearLayoutManager.setStackFromEnd(true);//数据反向
//创建跨应用会话
                    Conversation con = createSingleConversation(userName, APPKEY);
                    MessageContent content = new TextContent(s);
//创建一条消息
                    Message message = con.createSendMessage(content);
//发送消息
                    JMessageClient.sendMessage(message);
                    imRecycler.setLayoutManager(linearLayoutManager);
                    imEdit.setText(null);
                }else {
                    Toast.makeText(this, "请输入内容", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public void onEventMainThread(MessageEvent event) {
        Message msg = event.getMessage();
        switch (msg.getContentType()) {
            case text:
                // 处理文字消息
                TextContent textContent = (TextContent) msg.getContent();
                textContent.getText();
                mPresenter.getCurrentPresenter(userId,sessionId);
                break;
        }
    }
    public void onEvent(NotificationClickEvent event) {
        mPresenter.getCurrentPresenter(userId,sessionId);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        JMessageClient.unRegisterEventReceiver(this);
    }
}
