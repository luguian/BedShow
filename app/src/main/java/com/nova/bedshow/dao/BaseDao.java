package com.nova.bedshow.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.nova.bedshow.F;
import com.nova.bedshow.dao.orm.TxDb;

/**
 * Created by lu on 2018/2/19.
 */

public class BaseDao {
    protected TxDb db;

    public String CREATE_USER = "CREATE TABLE if not exist user (_id INTEGER PRIMARY KEY autoincrement,"
            + "id LONG,"
            + "phone TEXT,"
            + "username TEXT,"
            + "sex TEXT,"
            + "token TEXT,"
            + "isMe TEXT"
            + ");";

    //更新数据库
    private String CREATE_TEMP_USER = "alter table user rename to _temp_user";
    //删除数据库
    private String DROP_USER = "drop table _temp_user";


    public BaseDao(Context context){
        TxDb.DaoConfig daoConfig = new TxDb.DaoConfig();
        daoConfig.setContext(context);
        daoConfig.setDbName(F.DB_NAME);
        daoConfig.setDebug(true);
        daoConfig.setDbUpdateListener(new TxDb.DbUpdateListener() {
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            }
        });
        db = TxDb.create(daoConfig);

    }
    //创建表结构
    public void init(){
        //创建表语句 用户表
        String sqltxUse = CREATE_USER;
        db.execSql(sqltxUse);
    }








}
