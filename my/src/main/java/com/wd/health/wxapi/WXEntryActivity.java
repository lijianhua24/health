package com.wd.health.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.annotation.Nullable;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.wd.health.R;
import com.wd.health.activity.LoginActivity;
import com.wd.health.app.App;

/**
 * @name APP
 * @class name：net.sourceforge.simcpux
 * @class describe
 * @anthor 24673
 * @time 2019/11/21 15:09
 * @change
 * @chang time
 * @class describe
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    private String CODE;
    public static boolean isOK = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        // 隐藏状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //接收到分享以及登录的intent传递handleIntent方法，处理结果
        App.api = WXAPIFactory.createWXAPI(this, "wxe3fcbe8a55cd33ff", false);
        App.api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        //登录回调
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                SendAuth.Resp sendResp= (SendAuth.Resp) baseResp;
                CODE = sendResp.code;
                isOK=true;
//              WXEntryActivity.this.finish();
                Intent intent = new Intent(WXEntryActivity.this, LoginActivity.class);
                intent.putExtra("code",CODE);
                startActivity(intent);
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED://用户拒绝授权
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL://用户取消
                finish();
                break;
            default:
                finish();
                break;
        }
    }
}
