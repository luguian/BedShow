package com.nova.bedshow;

import com.nova.bedshow.dao.orm.UserDao;
import com.nova.bedshow.model.UserModel;
import com.nova.bedshow.utils.JsonUtil;
import com.nova.bedshow.utils.PropertiesUtil;
import com.nova.bedshow.utils.StringUtil;

/**
 * Created by lu on 2018/2/19.
 */

public class F {
    public static String PROXY_SERVER_URL = "";
    //用户资料
    private static UserModel user = null;
    //本地数据库
    public static final String DB_NAME = "bedshow.db";
    //应用
    public static BSApplication APPLICATION = null;
    //版本号
    public static int versionCode = 1;
    public static UserModel user(){
        if(user == null){
            user = UserDao.getInstance(BSApplication.getInstance()).getUser();
        }
        if(user == null){
            String userJson = PropertiesUtil.getInstance().getString(PropertiesUtil.SpKey.ISME, "");
            if (!StringUtil.isBlank(userJson)){
                user = JsonUtil.Json2T(userJson, UserModel.class);
            }

        }
        return user;
    }







}
