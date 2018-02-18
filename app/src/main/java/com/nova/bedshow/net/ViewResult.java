package com.nova.bedshow.net;

/**
 * Created by Administrator on 2017/12/10.
 */

public class ViewResult {
    /**
     * 返回是否成功
     */
   private boolean flag;


    /**
     * 返回的结果数据
     */
    private Object detail;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getDetail() {
        return detail;
    }

    public void setDetail(Object detail) {
        this.detail = detail;
    }

    public String getErrDesc() {
        return errDesc;
    }

    public void setErrDesc(String errDesc) {
        this.errDesc = errDesc;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    /**
     * 如果错误，则返回错误信息
     * @return
     */

    private String errDesc = "";


    private String errCode = "";


}
