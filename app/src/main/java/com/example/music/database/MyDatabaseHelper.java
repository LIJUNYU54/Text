package com.example.music.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.music.R;
import com.example.music.type.Img;
import com.example.music.type.Music;
import com.example.music.type.Usertext;

import java.util.ArrayList;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase db;


    public MyDatabaseHelper(Context context) {
        super(context, "data", null, 1);
        db = getReadableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS Usertext(" +
                "name TEXT," +
                "password TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Img(" +
                "songimg IMG," +
                "singer TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS COLL(" +
                " id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Coll TEXT)");
        db.execSQL("CREATE TABLE IF NOT EXISTS music(" +
                "title TEXT," +
                "data TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Usertext");
        db.execSQL("DROP TABLE IF EXISTS Img");
        db.execSQL("DROP TABLE IF EXISTS COLL");
        db.execSQL("DROP TABLE IF EXISTS music");
        onCreate(db);
    }

    public void useradd(String name, String password) {
        db.execSQL("INSERT INTO Usertext (name,password) VALUES(?,?)", new Object[]{name, password});
    }
    public void music(String musictitle, String data) {
        db.execSQL("INSERT INTO music (musictitle,data) VALUES(?,?)", new Object[]{musictitle, data});
    }



//    public void imgadd(int[] songimg, String singer) {
//        db.execSQL("INSERT INTO Img (songimg,singer) VALUES (?, ?)", new Object[]{songimg, singer});
//    }


//    public void add(String name,String password){
//        db.execSQL(");
//    }
    public void Colladd(String Coll){
       ContentValues contentValues=new ContentValues();
       contentValues.put("allaroundme",Coll);
     getWritableDatabase().insert("COLL","Coll",contentValues);
    }
    public  void Colldelete(String Coll){
       db.delete("COLL","Coll=?",new String[]{Coll});
    }
//    public  void Colladd(String songname,String singer){
//        ContentValues cv=new ContentValues();
//        cv.put("青花瓷", songname);
//        cv.put("jaychou", singer);
//        getWritableDatabase().insert("COLL", "songname", cv);
//        db.close();
//    }
//     public ArrayList<Message> checkcode(){
//         ArrayList<Message>list =new ArrayList<Message>();
//        Cursor cursor = db.query("message", null, null, null, null, null, "null");
//        while (cursor.moveToNext()) {
//            @SuppressLint("Range") String code = cursor.getString(cursor.getColumnIndex("code"));
//            list.add(new Message(code));
//        }
//        return list;
//    }

    public ArrayList<Usertext> getAllDATA() {
        ArrayList<Usertext> list = new ArrayList<Usertext>();
        Cursor cursor = db.query("Usertext", null, null, null, null, null, "name DESC");
        while (cursor.moveToNext()) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String password = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new Usertext(name, password));
        }
        return list;
    }

    public ArrayList<Integer> getImages() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(R.drawable.play_album);
        return  list;
    }
}
