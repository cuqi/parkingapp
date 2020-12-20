package com.example.proektna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
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
//        UserModel um1 = new UserModel(1, "Vladimir", "Krstikj", "vkrstik", "123");
//        UserModel um2 = new UserModel(2, "Neznam", "Nebitno", "test", "123");
//        dbHelper.addUser(um1);
//        dbHelper.addUser(um2);

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
                Intent intent = new Intent(this, Cities.class);
                intent.putExtra("username", userModel.getUsername());
                intent.putExtra("name", userModel.getFname() + " " + userModel.getLname());
                startActivity(intent);

            }
        } else {
            Toast toast = Toast.makeText(getApplicationContext(), "Your username or password is incorrect!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

//    public void registerUser(View view) {
//        DBHelper dbHelper = new DBHelper(MainActivity.this);
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//        String username = "newUser";
//        String password = "newUser";
//        ContentValues cv = new ContentValues();
//        cv.put(DBHelper.COLUMN_USERNAME, username);
//        cv.put(DBHelper.COLUMN_PASSWORD, password);
//
//        long newRowId = db.insert(DBHelper.USER_TABLE, null, cv);
//
//    }

    public void toRegisterActivity(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void printUser(View view) {
        DBHelper dbHelper = new DBHelper(MainActivity.this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                BaseColumns._ID,
                DBHelper.COLUMN_USERNAME,
                DBHelper.COLUMN_PASSWORD
        };

        String selection = DBHelper.COLUMN_USERNAME + " = ?";
        String[] selectionArgs = {"vlatkok"};

        Cursor cursor = db.query(
                DBHelper.USER_TABLE,
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
                    cursor.getColumnIndexOrThrow(DBHelper.COLUMN_ID)
            );
            itemIds.add(itemId);
        }
        cursor.close();
    }

}