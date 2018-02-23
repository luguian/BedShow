package com.nova.bedshow.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.nova.bedshow.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by lu on 2018/2/19.
 */

public class StartActivity extends BaseActivity {

  @Bind(R.id.iv_start)
  ImageView iv_start;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
    }




    @Override
    protected void onResume(){
        super.onResume();
        iv_start.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        },3000);

    }

    @Override
    protected void initview() {

    }

    @Override
    protected void initdata() {

    }

    /**
     * 检查是否自动登录
     *
     */



}
