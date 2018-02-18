package com.nova.bedshow.net.okhttp;

import com.nova.bedshow.net.ViewResult;
import com.nova.bedshow.net.okhttp.callback.Callback;
import com.nova.bedshow.utils.JsonUtil;
import com.nova.bedshow.utils.LogUtil;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by lu on 2018/1/30.
 */

public abstract class ViewResultCallback extends Callback<ViewResult> {
    @Override
    public ViewResult parseNetworkResponse(Response response) throws IOException {
        String json = response.body().string();
        LogUtil.json(json);
        return JsonUtil.Json2T(json, ViewResult.class);
    }
}
