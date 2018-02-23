package com.nova.bedshow.task;

import com.nova.bedshow.BaseService;
import com.nova.bedshow.net.BaseTask;
import com.nova.bedshow.net.ViewResult;

/**
 * Created by lu on 2018/2/23.
 */

public class MobileRegisterTask extends BaseTask<ViewResult> {


    @Override
    public String getUrl() {
        return BaseService.MOBILEREGIST;
    }

    @Override
    public void doSuccess(ViewResult mViewResult, String tips) throws Exception {

    }

    @Override
    public void doFail(ViewResult result, String msg) {

    }

    @Override
    public void doLogin() {

    }

    @Override
    public ViewResult getEntity() {
        return null;
    }

    @Override
    public void doAfter() {

    }
}
