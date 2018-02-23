package com.nova.bedshow.model;

import com.nova.bedshow.dao.orm.annotation.Id;
import com.nova.bedshow.dao.orm.annotation.Table;

import java.io.Serializable;

/**
 * Created by lu on 2018/2/19.
 */

@Table("user")
public class UserModel implements Serializable {
    @Id
    int _id;


    private long id;

    /**
     * 是否为自己,客户端存储本地数据库时需要用到
     */
    private boolean isMe;
    /**
     * 手机
     *
     */
    private String phone;
    /**
     * 用户名
     *
     *
     */
    private String username;
    /**
     * 性别
     *
     */
    private String sex;
    /**
     * token
     *
     */
    private String token;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isMe() {
        return isMe;
    }

    public void setMe(boolean me) {
        isMe = me;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
