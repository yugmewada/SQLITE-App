package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    Context context;

    public DBHelper(@Nullable Context context) {
        super(context, "USER DATA.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user_info"+"(" +
                "id INTEGER Primary key Autoincrement, fname VARCHAR(255), " +
                "lname VARCHAR(255), email varchar(255), " +
                "password VARCHAR(255), contact VARCHAR(255))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user_info");
    }

    public boolean addData(String fname, String lname, String email, String password, String contact){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("fname", fname);
        contentValues.put("lname", lname);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("contact", contact);

        sqLiteDatabase.insert("user_info",null,contentValues );
        sqLiteDatabase.close();
        return true;
    }


    public ArrayList<UserDataHandler>getAlldata(){
        ArrayList<UserDataHandler>list= new ArrayList<>();
        SQLiteDatabase sqLiteDatabase= this.getReadableDatabase();
        String Query= "SELECT * FROM user_info";
        Cursor cursor= sqLiteDatabase.rawQuery(Query, null);

        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String fname = cursor.getString(1);
            String lname = cursor.getString(2);
            String email = cursor.getString(3);
            String password = cursor.getString(4);
            String contact = cursor.getString(5);

            UserDataHandler data= new UserDataHandler(id, fname, lname, email, password, contact);
            list.add(data);
        }
        return list;
    }

    public Integer deleteData(String id){
        SQLiteDatabase sqLiteDatabase= this.getReadableDatabase();
        return sqLiteDatabase.delete("user_info", "id=?",new String[]{id});
    }

    public Integer updateData(String id, String fname, String lname, String email, String password, String contact){
        SQLiteDatabase sqLiteDatabase= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put("fname", fname);
        contentValues.put("lname", lname);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("contact", contact);

        return sqLiteDatabase.update("user_info",contentValues,"id=?",new String[]{id});
    }
}
