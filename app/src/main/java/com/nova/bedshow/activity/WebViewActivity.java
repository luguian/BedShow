package com.nova.bedshow.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nova.bedshow.R;

/**
 * Created by lu on 2018/2/25.
 */

public class WebViewActivity extends BaseActivity implements View.OnClickListener {

    public static void startFrom(Context context, String url, String title) {
        context.startActivity(new Intent(context, WebViewActivity.class)
                .putExtra(WebViewActivity.KEY_URL, url)
                .putExtra(WebViewActivity.KEY_TITLE, title));
    }

    public static final String KEY_TITLE = "title";
    public static final String KEY_URL = "url";

    public static final String USER_PROTOCOL = "http://static.fallchat.com/michun_xieyi/index.html";// 用户协议




    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void initview() {

    }

    @Override
    protected void initdata() {

    }
}
