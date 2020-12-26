package com.example.proektna;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

// Korisnik(id*, ime, prezime, username, password, ?) DONE
// Parking mesto(id*, zona, cena/saat, ?)
// Rezervacija(id*, korisnik_id*, pmesto_id*)

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "database2.db";

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    public static final String COLUMN_LAST_NAME = "LAST_NAME";
    public static final String COLUMN_USERNAME = "USERNAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";

    public static final String PARKING_TABLE  = "PARKING_TABLE";
    public static final String COLUMN_PARKING_ID = "ID";
    public static final String COLUMN_PARKING_NAME = "PARKING_NAME";
    public static final String COLUMN_CITY = "CITY_NAME";
    public static final String COLUMN_FREE = "FREE";
    public static final String COLUMN_TAKEN = "TAKEN";
//    public static final String COLUMN_TIME_SLOT = "TIME_SLOT";
//    public static final String COLUMN_DATE = "DATE";

    public static final String RESERVATION_TABLE = "RESERVATION_TABLE";
    public static final String COLUMN_USER_FOREIGN_ID = "USER_ID";
    public static final String COLUMN_PARKING_FOREIGN_ID = "PARKING_ID";
    public static final String COLUMN_TIME_SLOT = "TIME_SLOT";
    public static final String COLUMN_DATE = "DATE";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTableStatement = "CREATE TABLE " + USER_TABLE +"(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRST_NAME + " VARCHAR(20), " +
                COLUMN_LAST_NAME + " VARCHAR(20), " +
                COLUMN_USERNAME + " VARCHAR(15), " +
                COLUMN_PASSWORD + " VARCHAR(128))";

        db.execSQL(createUserTableStatement);

        String createParkingTableStatement = "CREATE TABLE " + PARKING_TABLE+"(" +
                COLUMN_PARKING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PARKING_NAME + " VARCHAR(50), " +
                COLUMN_CITY + " VARCHAR(20), " +
                COLUMN_FREE + " INTEGER, " +
                COLUMN_TAKEN + " INTEGER )";

        db.execSQL(createParkingTableStatement);

        String createReservationTableStatement = "CREATE TABLE " + RESERVATION_TABLE+"(" +
                COLUMN_USER_FOREIGN_ID + " INTEGER, " +
                COLUMN_PARKING_FOREIGN_ID + " INTEGER, " +
                COLUMN_TIME_SLOT + " VARCHAR(50), " +
                COLUMN_DATE + " VARCHAR(50) )";

        db.execSQL(createReservationTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DataBase.SQL_DELETE_USERS);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean populateDataBase(ParkingModel parkingModel) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PARKING_NAME, parkingModel.getParking_name());
        cv.put(COLUMN_CITY, parkingModel.getCity_name());
        cv.put(COLUMN_FREE, parkingModel.getFree());
        cv.put(COLUMN_TAKEN, parkingModel.getTaken());

        long insert = db.insert(PARKING_TABLE, null, cv);

        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean reserveParking(ReservationModel reservationModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_FOREIGN_ID, reservationModel.getUserID());
        cv.put(COLUMN_PARKING_FOREIGN_ID, reservationModel.getParkingID());
        cv.put(COLUMN_TIME_SLOT, reservationModel.getTimeSlot());
        cv.put(COLUMN_DATE, reservationModel.getDate());

        long insert = db.insert(RESERVATION_TABLE, null, cv);

        if(insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public List<ParkingModel> getParkings (String city_name) {
        List<ParkingModel> returnList = new ArrayList<>();
        String query = "SELECT * FROM " + PARKING_TABLE + " WHERE CITY_NAME = ?";
        String[] args = {city_name};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, args);

        if (cursor.moveToNext()) {
            do {
                int userID = cursor.getInt(0);
                String parkingName = cursor.getString(1);
                String cityName = cursor.getString(2);
                int free = cursor.getInt(3);
                int taken = cursor.getInt(4);

                ParkingModel pm = new ParkingModel(userID, parkingName, cityName, free, taken);
                returnList.add(pm);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return returnList;
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
