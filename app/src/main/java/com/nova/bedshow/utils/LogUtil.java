package com.nova.bedshow.utils;


import com.orhanobut.logger.Logger;

/**
 * Created by Administrator on 2017/12/8.
 */

public class LogUtil {
    private static String TAG = "paul";
    private static boolean isDebug = false;


    public static void initLOgger(boolean isDebug){
        LogUtil.isDebug = isDebug;
        Logger.init(TAG);

    }

    public static void d(String msg) {
        if (isDebug)
            Logger.d(msg);
    }

    public static void e(String msg) {
        if (isDebug)
            Logger.e(msg);
    }

    public static void json(String json) {
        if (isDebug)
            Logger.json(json);
    }

}
