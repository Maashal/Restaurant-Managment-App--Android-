package com.testphase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "project.DB";
    public static final String USERS = "users";


    Cursor cursor;

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users (ID INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, email VARCHAR,  " +
                " role VARCHAR,contact_No VARCHAR, password VARCHAR)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE  IF EXISTS " + USERS);
    }

    public Boolean register(String name, String email, String role, String cellNo, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("role", role);
        contentValues.put("contact_No", cellNo);
        contentValues.put("password", password);

        long result = db.insert(USERS, null, contentValues);
        return result != -1;
    }

    public boolean login(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + USERS + " WHERE  email =? AND password =?", new String[]{email, password});
        if(cursor.getCount() > 0){
            return true;
        }
        else{
            return false;
        }
    }
}