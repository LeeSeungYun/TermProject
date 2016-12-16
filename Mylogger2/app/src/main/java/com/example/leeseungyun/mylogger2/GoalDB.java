package com.example.leeseungyun.mylogger2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lee Seung Yun on 2016-12-16.
 */
public class GoalDB extends SQLiteOpenHelper {
    public GoalDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context,name,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE GoalDB( _id INTEGER PRIMARY KEY AUTOINCREMENT, goal_value INTEGER, key INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void update(String query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }
    public void clear(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from GoalDB");
        db.close();
    }
    public void insert(String query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }
    public int select(int index){
        SQLiteDatabase db = getReadableDatabase();
        int result = 9;
        Cursor cursor = db.rawQuery("select *from GoalDB",null);
        while(cursor.moveToNext()){
            if(cursor.getInt(2) == index){
                result = cursor.getInt(1);
            }
        }
        return result;
    }


}
