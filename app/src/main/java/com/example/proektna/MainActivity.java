package com.example.proektna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.BaseColumns;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;

    // Korisnik(id*, ime, prezime, username, password, ?)
    // Parking mesto(id*, zona, cena/saat, ?)
    // Rezervacija(id*, korisnik_id*, pmesto_id*)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(MainActivity.this);
        ParkingModel pm1 = new ParkingModel(1, "151 E 9th St. - Lot", "Austin", 25, 16, (float)30.232902639979944, (float)-97.76919063289102);
        ParkingModel pm2 = new ParkingModel(2, "513 W 6th St - IBC Lot", "Austin", 35, 22, (float)30.269335712440324, (float)-97.74807328867234);
        ParkingModel pm3 = new ParkingModel(3, "727 W 7th St. - The Roosevelt", "Los Angeles", 73, 12, (float)34.04855421205298, (float)-118.25831090210151);
        ParkingModel pm4 = new ParkingModel(4, "530 S Grand Ave - Valet - PacMutual Building Garage", "Los Angeles", 64, 17, (float)34.04904349639571, (float)-118.25490760210151);
        ParkingModel pm5 = new ParkingModel(5, "150 SE 2nd Ave. - Chase Garage Valet", "Miami", 21, 47, (float)25.773039045672515, (float)-80.19052561572767);
        ParkingModel pm6 = new ParkingModel(6, "60 SE 2nd St. - Lot", "Miami", 81, 10, (float)25.88650237725539, (float)-80.1980236276794);
        ParkingModel pm7 = new ParkingModel(7, "181 N Clark St. - Government Center Garage", "Chicago", 55, 14, (float) 41.88534471588416, (float)-87.6306910731046);
        ParkingModel pm8 = new ParkingModel(8, "601 W 5th St. - Cal Edison Building - Valet-Assist Garage", "New York", 10, 88, (float) 40.75758446302945, (float)-73.97758664429473);
        ParkingModel pm9 = new ParkingModel(9, "465 S Flower St. - Westin Bonaventure Garage", "New York", 61, 3, (float)40.705991977412936, (float)-74.00362880196637);
        ParkingModel pm10 = new ParkingModel(10, "300 SW 7th St", "Oklahoma", 4, 26, (float)35.45771834083528, (float)-97.51858008858056);
        ParkingModel pm11 = new ParkingModel(11, "222 E Sheridan Ave", "Oklahoma", 22, 23, (float) 35.46641877277743, (float) -97.50784558673409);
        ParkingModel pm12 = new ParkingModel(12, "1600 SW 4th Ave", "Portland", 34, 81, (float) 45.51215643563634, (float) -122.67923444418341);
        ParkingModel pm13 = new ParkingModel(13, "121 SW 4th Ave", "Portland", 67, 21, (float)45.52251805185998, (float)-122.67483643068856);
        ParkingModel pm14 = new ParkingModel(14, "333 Post St", "San Francisco", 46, 44, (float) 37.78806556206423, (float)-122.40740904251193);
        ParkingModel pm15 = new ParkingModel(15, "555 Jackson St", "San Francisco", 34, 47, (float)37.79627905947331, (float)-122.40464679833576);
        ParkingModel pm16 = new ParkingModel(16, "430 S Clark St", "Chicago", 67, 24, (float)41.87627853925142, (float)-87.6310686307752);

        dbHelper.populateDataBase(pm1);
        dbHelper.populateDataBase(pm2);
        dbHelper.populateDataBase(pm3);
        dbHelper.populateDataBase(pm4);
        dbHelper.populateDataBase(pm5);
        dbHelper.populateDataBase(pm6);
        dbHelper.populateDataBase(pm7);
        dbHelper.populateDataBase(pm8);
        dbHelper.populateDataBase(pm9);
        dbHelper.populateDataBase(pm10);
        dbHelper.populateDataBase(pm11);
        dbHelper.populateDataBase(pm12);
        dbHelper.populateDataBase(pm13);
        dbHelper.populateDataBase(pm14);
        dbHelper.populateDataBase(pm15);
        dbHelper.populateDataBase(pm16);

        UserModel um1 = new UserModel(1, "Vladimir", "Krstikj", "vkrstik", "123");
        dbHelper.addUser(um1);

    }

    public void checkUser(View view) {
        EditText et1 = (EditText) findViewById(R.id.username);
        EditText et2 = (EditText) findViewById(R.id.password);

        String usernameText = et1.getText().toString();
        String passwordText = et2.getText().toString();

        DBHelper dbHelper = new DBHelper(MainActivity.this);

        UserModel userModel = dbHelper.getUser(usernameText, passwordText);

        if (userModel != null) {
            if(userModel.getUsername().equals(usernameText) && userModel.getPassword().equals(passwordText)) {
                Toast toast = Toast.makeText(getApplicationContext(), "You are logged in!", Toast.LENGTH_SHORT);
                toast.show();

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt("user_id", userModel.getId());
                editor.putString("username", userModel.getUsername());
                editor.putString("password", userModel.getPassword());
                editor.commit();

                Intent intent = new Intent(this, Cities.class);
                intent.putExtra("username", userModel.getUsername());
                intent.putExtra("name", userModel.getFname() + " " + userModel.getLname());
                intent.putExtra("password", userModel.getPassword());
                startActivity(intent);
            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Your username or password is incorrect!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void toRegisterActivity(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void toReservationActivity(View view) {
        Intent intent = new Intent(this, ReservationForm.class);
        startActivity(intent);
    }

    public void printParking(View view) {
        DBHelper dbHelper = new DBHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                DBHelper.COLUMN_PARKING_NAME,
                DBHelper.COLUMN_CITY
        };

        String selection = DBHelper.COLUMN_CITY + " = ?";
        String[] selectionArgs = {"Miami"};

        Cursor cursor = db.query(
                DBHelper.PARKING_TABLE,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(DBHelper.COLUMN_PARKING_ID)
            );
            itemIds.add(itemId);
        }
        String somestring = cursor.getString(2);
        cursor.close();
        Toast.makeText( this, somestring, Toast.LENGTH_SHORT).show();
    }

}