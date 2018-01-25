package com.nova.bedshow;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDexApplication;

import com.nova.bedshow.utils.SystemUtil;

/**
 * Created by lu on 2018/1/9.
 */

public class BSApplication extends MultiDexApplication {
    private static BSApplication mInstance = null;
    public static String PROXY_SERVER_URL = "";
    //版本号
    public static int versionCode = 1;
    public PackageInfo packageInfo;
    protected void initVersion(){
        try {
            packageInfo = getApplicationContext().getPackageManager().getPackageInfo(getPackageName(),0);
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onCreate(){
        super.onCreate();
        mInstance = this;
        //初始化版本号
        initVersion();
        PROXY_SERVER_URL =  SystemUtil.getMetaDataString("URL");


    }


    public static BSApplication getInstance(){
        return mInstance;
    }
































}
