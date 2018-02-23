package com.nova.bedshow;

import com.nova.bedshow.utils.StringUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lu on 2018/2/23.
 */

public class BaseService {
    private static final String SERVER_URL = F.PROXY_SERVER_URL;
    /**
     * 所有接口的公有参数
     *
     */

    public static Map<String, String> commonParam() {
        Map<String, String> map = new HashMap<>();
        if (F.user() != null) {
            String token = F.user().getToken();
            if (StringUtil.isNotBlank(token))
                map.put("token", token);
        }
        return map;
    }

    //注册
    public static final String MOBILEREGIST = SERVER_URL +"/api/user/register.do";

}
