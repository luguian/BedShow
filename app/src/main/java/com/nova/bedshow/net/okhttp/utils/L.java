package com.nova.bedshow.net.okhttp.utils;

import android.util.Log;

/**
 * Created by Administrator on 2017/12/10.
 */

public class L {
    private static boolean debug = false;

    public static void e(String msg)
    {
        if (debug)
        {
            Log.e("OkHttp", msg);
        }
    }
}
