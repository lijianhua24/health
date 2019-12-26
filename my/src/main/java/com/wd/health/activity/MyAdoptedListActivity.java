package com.wd.health.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.wd.health.R;
import com.wd.health.R2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyAdoptedListActivity extends AppCompatActivity {
    public static final String TAG="MyAdoptedListActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R2.layout.activity_my_adopted_list);

        String md5 = MD5("123456");
        Log.d(TAG, "onCreate: "+md5);
    }

    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

}
