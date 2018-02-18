package com.nova.bedshow.net;

import com.nova.bedshow.BSApplication;
import com.nova.bedshow.net.okhttp.ViewResultCallback;
import com.nova.bedshow.net.okhttp.builder.OkHttpRequestBuilder;
import com.nova.bedshow.utils.JsonUtil;
import com.nova.bedshow.utils.LogUtil;
import com.nova.bedshow.utils.StringUtil;
import com.nova.bedshow.utils.SystemUtil;
import com.squareup.okhttp.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/10.
 */

public abstract class BaseTask<T> {
    private Map<String, String> mParams = new HashMap<>();
    private TaskListener<T> listener;

    //false post请求  true,get请求
    public void request(OkHttpRequestBuilder builder) {
        LogUtil.d(getUrl() + "\n" + getUrl());
        if (!SystemUtil.isNetworkConnected(BSApplication.getInstance())) {
            doFail(null, "网络已断开,请检查你的网络!");
            doAfter();
            if (listener != null) {
                listener.doFial(null, "网络已断开,请检查你的网络!");
                listener.doAfter();
            }
            return;
        }
        if (mParams == null) return;
        builder.url(getUrl()).params(mParams).build().execute(new ViewResultCallback() {
            @Override
            public void onError(Request request, Exception e) {
                String message = "服务未响应，请稍后再试";
                LogUtil.e("postFail" + e.getMessage());
                if (!StringUtil.isBlank(e.toString())) {
                    if (e.toString().contains("ConnectException")) {
                        message = "服务未响应，请稍后再试";
                        //java.net.ConnectException: Failed to connect to /xxx.xxx.xxx.xxx
                    } else if (e.toString().contains("SocketTimeoutException")) {
                        message = "网络请求超时，请检查你的网路!";
                        // java.net.SocketTimeoutException: failed to connect to /xxx.xxx.xxx.xxx after 10000ms
                    }
                }
                //测试
                ViewResult result = JsonUtil.Json2T(e.getMessage(), ViewResult.class);
                if (result != null && !StringUtil.isBlank(result.getErrDesc()))
                    message = result.getErrDesc();
                doFail(result, message);
                if (listener != null) {
                    listener.doFial(result, message);
                }
            }

            @Override
            public void onResponse(ViewResult result) {
                try {
                    if (result.isFlag()) {
                        //请求成功
                     //   if (!result.isRelogin()) {
                            //token未过期
                            doSuccess(result, result.getErrDesc());
                            if (listener != null)
                                listener.doSuccess(getEntity(), result.getErrDesc());
                  //      }
//                        else {
//                            //token过期
//                            doLogin();
//                            String message = "登录验证过期,请重新登录!\n" + mParams.get("token");
//                            doFail(result, message);
//                            if (listener != null)
//                                listener.doFial(result, message);
//                        }
                    } else {
                        //请求失败
                        String message = "未知错误";
                        if (!StringUtil.isBlank(result.getErrDesc())) {
                            message = result.getErrDesc();
                        }
                        doFail(result, message);
                        if (listener != null)
                            listener.doFial(result, message);
                    }
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    return;
                }
            }

            @Override
            public void onAfter() {
                super.onAfter();
                doAfter();
                if (listener != null)
                    listener.doAfter();
            }
        });
    }

    public void putParam(String key, String value) {
        mParams.put(key, value);
    }

    public void putParam(Map<String, String> param) {
        mParams.putAll(param);
    }

    public Map<String, String> getParam() {
        return mParams;
    }

    public abstract String getUrl();

    public abstract void doSuccess(ViewResult mViewResult, String tips) throws Exception;

    public abstract void doFail(ViewResult result, String msg);

    public abstract void doLogin();

    public abstract T getEntity();

    public abstract void doAfter();

    /**
     * 用于外部调用，如更新UI等
     */
    public interface TaskListener<T> {
        public void doSuccess(T obj, String tips);

        public void doFial(ViewResult result, String msg);

        public void doAfter();
    }

    public void setTaskListener(TaskListener<T> listener) {
        this.listener = listener;
    }
}
