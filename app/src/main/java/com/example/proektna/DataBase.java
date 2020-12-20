package com.example.proektna;

import android.provider.BaseColumns;

// Korisnik(id*, ime, prezime, username, password, ?)
// Parking mesto(id*, zona, cena/saat, ?)
// Rezervacija(id*, korisnik_id*, pmesto_id*)

public final class DataBase {
    public DataBase() {}

    public static class USER implements BaseColumns {
        public static final String TABLE_NAME = "user";

        public static final String COLUMN_FIRST_NAME = "first_name";
        public static final String COLUMN_LAST_NAME = "last_name";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
    }

    public static class PARKING_SPOT implements BaseColumns {
        public static final String TABLE_NAME = "parking_spot";

        public static final String COLUMN_ZONE = "zone";
        public static final String COLUMN_PRICE = "price";
    }

    public static class RESERVATION implements BaseColumns {
        public static final String TABLE_NAME = "reservation";

        public static final String COLUMN_USERID = "user_id";
        public static final String COLUMN_PARKINGID = "parking_id";
    }

    public static String SQL_CREATE_USERS =
            "CREATE TABLE " + USER.TABLE_NAME + " (" +
                    USER._ID + " INTEGER PRIMARY KEY," +
                    USER.COLUMN_FIRST_NAME + " TEXT," +
                    USER.COLUMN_LAST_NAME + " TEXT," +
                    USER.COLUMN_USERNAME + " TEXT," +
                    USER.COLUMN_PASSWORD + " TEXT)";

    public static final String SQL_DELETE_USERS =
            "DROP TABLE IF EXISTS " + USER.TABLE_NAME;

    public static final String SQL_CREATE_PARKINGS =
            "CREATE TABLE " + PARKING_SPOT.TABLE_NAME + " (" +
                    PARKING_SPOT._ID + " INTEGER PRIMARY KEY," +
                    PARKING_SPOT.COLUMN_PRICE + " TEXT," +
                    PARKING_SPOT.COLUMN_ZONE + " TEXT)";

    public static final String SQL_DELETE_PARKINGS =
            "DROP TABLE IF EXISTS " + PARKING_SPOT.TABLE_NAME;

    public static final String SQL_CREATE_RESERVATION =
            "CREATE TABLE " + RESERVATION.TABLE_NAME + " (" +
                    RESERVATION._ID + " INTEGER PRIMARY KEY," +
                    RESERVATION.COLUMN_USERID + " TEXT," +
                    RESERVATION.COLUMN_PARKINGID + " TEXT)";

    public static final String SQL_DELETE_RESERVATIONS =
            "DROP TABLE IF EXISTS " + RESERVATION.TABLE_NAME;
}
