package com.example.proektna;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "database.db";

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    public static final String COLUMN_LAST_NAME = "LAST_NAME";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE +"(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRST_NAME + " VARCHAR(20), " +
                COLUMN_LAST_NAME + " VARCHAR(20), " +
                COLUMN_USERNAME + " VARCHAR(15), " +
                COLUMN_PASSWORD + " VARCHAR(128))";

        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DataBase.SQL_DELETE_USERS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean addUser(UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        //cv.put(COLUMN_ID, userModel.getId());
        cv.put(COLUMN_FIRST_NAME, userModel.getFname());
        cv.put(COLUMN_LAST_NAME, userModel.getLname());
        cv.put(COLUMN_USERNAME, userModel.getUsername());
        cv.put(COLUMN_PASSWORD, userModel.getPassword());

        long insert = db.insert(USER_TABLE, null, cv);
        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<UserModel> getUsers() {
        List<UserModel> returnList = new ArrayList<>();
        String query = "SELECT * FROM " + USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery( query, null );

        if (cursor.moveToNext()) {
            do {
                int userID = cursor.getInt(0);
                String fname = cursor.getString(1);
                String lname = cursor.getString(2);
                String username = cursor.getString(3);
                String password = cursor.getString(4);

                UserModel um = new UserModel(userID, fname, lname, username, password);
                returnList.add(um);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
    }

    public UserModel getUser(String username, String password) {
        String query = "SELECT * FROM " + USER_TABLE + " WHERE USERNAME = ? and PASSWORD = ?";
        String[] args = {username, password};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, args);

        if (cursor.moveToFirst()) {
            int userID = cursor.getInt(0);
            String fname = cursor.getString(1);
            String lname = cursor.getString(2);
            String uname = cursor.getString(3);
            String pass = cursor.getString(4);

            UserModel um = new UserModel(userID, fname, lname, uname, pass);
            cursor.close();
            db.close();
            return um;
        } else return null;
    }

    public int getNextId() {
        String query = "SELECT * FROM " + USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToNext()) {
            int userID = cursor.getInt(0);

            cursor.close();
            db.close();
            return userID;
        } else return -1;
    }

    public boolean registerUser(UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FIRST_NAME, userModel.getFname());
        cv.put(COLUMN_LAST_NAME, userModel.getLname());
        cv.put(COLUMN_USERNAME, userModel.getUsername());
        cv.put(COLUMN_PASSWORD, userModel.getPassword());

        long insert = db.insert(USER_TABLE, null, cv);
        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }
}
