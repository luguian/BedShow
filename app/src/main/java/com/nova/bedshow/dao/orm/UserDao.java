package com.nova.bedshow.dao.orm;

import android.content.Context;
import android.util.Log;

import com.nova.bedshow.dao.BaseDao;
import com.nova.bedshow.model.UserModel;
import com.nova.bedshow.utils.JsonUtil;
import com.nova.bedshow.utils.PropertiesUtil;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by lu on 2018/2/23.
 */

public class UserDao extends BaseDao {
    private static Map<Long, UserModel> userSoCache = new WeakHashMap<Long, UserModel>();

    private static UserDao userDao = null;

    /**
     * 用户信息DB单例对象
     *
     * @param context
     * @return
     */
    public static UserDao getInstance(Context context) {
        if (userDao == null) {
            userDao = new UserDao(context);
        }
        return userDao;
    }

    public UserDao(Context context) {
        super(context);
    }


    public UserModel getUser() {
        return db.findForObject(UserModel.class, "isMe=?",
                new String[]{Boolean.toString(true)});
//        return db.findForObject(UserModel.class, "isMe=?",
//                new String[]{Boolean.toString(true)});
    }


    public long addUser(UserModel user) {
        if (null != userSoCache) {
            userSoCache.put(user.getId(), user);
        }
        return db.insert(user);
    }

    public int updateUser(UserModel user) {
        return db.update(user, "userId=?",
                new String[]{String.valueOf(user.getId())});
    }

    public boolean deleteUser(long uid) {
        if (null != userSoCache) {
            userSoCache.remove(uid);
        }
        int rst = db.delete(UserModel.class, "userId=?", new String[]{String.valueOf(uid)});
        Log.d("UserDao", "deleteUser result:" + rst);
        return rst > 0;
    }

    public boolean deleteUser() {
        int rst = db.delete(UserModel.class, "isMe=?", new String[]{Boolean.toString(true)});
        Log.d("UserDao", "deleteUser result:" + rst);
        return rst > 0;
    }

    private UserModel getUserByUid(Long uid) {
        return db.findForObject(UserModel.class, "userId=?",
                new String[]{uid.toString()});
    }


    public UserModel saveOrUpdateUser(UserModel user) {
        UserModel tmp = getUserByUid(user.getId());
        if (null == tmp) {
            // 插入数据库
            addUser(user);
        } else {
            // 更新用户数据
            updateUser(user);
        }
        tmp = getUserByUid(user.getId());
        if (tmp != null && tmp.isMe()) {
            PropertiesUtil.getInstance().setString(PropertiesUtil.SpKey.ISME, JsonUtil.Object2Json(tmp));
        }
        return tmp;
    }

    public void clear() {
        userSoCache = null;
        db.delete(UserModel.class, null, new String[]{});
    }


}
