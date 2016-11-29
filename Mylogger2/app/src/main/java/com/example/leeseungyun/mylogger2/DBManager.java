package com.example.leeseungyun.mylogger2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Lee Seung Yun on 2016-11-26.
 */
public class DBManager extends SQLiteOpenHelper {
    public DBManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }
    public void onCreate(SQLiteDatabase db) {
//        db.execSQL("CREATE TABLE EventDB( _id INTEGER PRIMARY KEY AUTOINCREMENT, lat REAL, lon REAL, year INTEGER, month INTEGER, day INTEGER, group TEXT, content TEXT");
        db.execSQL("CREATE TABLE EventDB( _id INTEGER PRIMARY KEY AUTOINCREMENT, lat REAL, lon REAL, year INTEGER, month INTEGER, day INTEGER, category TEXT, content TEXT);");
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public void insert(String query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public void delete(String query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public void update(String query){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(query);
        db.close();
    }

    public String select(String query){
        SQLiteDatabase db = getReadableDatabase();
        String str = "";
        Cursor cursor = db.rawQuery("select *from EventDB where category = '"+query+"'",null);//select 조건 추가
        while(cursor.moveToNext()){
            str += cursor.getInt(3)+"년 "+cursor.getInt(4)+"월 "+cursor.getInt(5)+"일 <"+cursor.getString(6)+"> : "+cursor.getString(7)+"\n\n";
        }
        return str;
    }

    public void clear(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from EventDB");
        db.close();
    }

    public String printData(){
        SQLiteDatabase db = getReadableDatabase();
        String str = "";

        Cursor cursor = db.rawQuery("select * from EventDB",null);
        while(cursor.moveToNext()){
            str += cursor.getInt(3)+"년 "+cursor.getInt(4)+"월 "+cursor.getInt(5)+"일 <"+cursor.getString(6)+"> : "+cursor.getString(7)+"\n\n";// yyyy년 mm월 dd일 분류 : ~~ 내용 : ~~
        }
        return str;
    }
}
