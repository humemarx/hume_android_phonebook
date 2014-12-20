package com.example.hume_android_phonebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tcp on 2014/12/19.
 * 自定义数据库类
 */
public class MySQLiteDB extends SQLiteOpenHelper {
    /*数据库名称*/
    private static final String DB_NAME = "myphone.db";
    /*表名*/
    private static final String TAB_NAME = "mytable1";
    /*表中的字段*/
    private static final String Phone_id = "_id";
    private static final String Phone_name = "name";
    private static final String Phone_num = "num";
    private static final String Phone_icon = "image";
    private static final String Phone_compus = "compus";
    private static final String Phone_email = "email";
    private static final String Phone_work = "work";
    /*创建表*/
    private static final String CREATE_TBL = " create table "+TAB_NAME+"("+Phone_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+Phone_name+"text,"
            +Phone_num+"text,"+Phone_icon+"image,"+Phone_compus+"text,"+Phone_email+"text,"+Phone_work+"text)";

    /*构造函数*/
    private SQLiteDatabase db;
    MySQLiteDB(Context context){
        super(context,DB_NAME,null,2);
    }

    @Override
    /*新建数据库*/
    public void onCreate(SQLiteDatabase db){
        this.db = db;
        db.execSQL(CREATE_TBL);
    }

    /*数据库插入方法*/
    public void insert(ContentValues values){
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TAB_NAME,null,values);
        db.close();
    }

    /*获取数据库的游标*/
    public Cursor query(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor c = db.query(TAB_NAME,null,null,null,null,null,null);
        return c;
    }

    /*数据库删除方法——指定id*/
    public void del(int id){
        if(db == null){
            db = getWritableDatabase();
        }
        db.delete(TAB_NAME,"_id=?",new String[]{String.valueOf(id)});
    }

    /*释放数据库*/
    public void close(){
        if(db!=null)db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}
