package com.example.proektna;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ReservationForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_form);

        Intent intent = getIntent();
        String city_name = intent.getStringExtra("city_name");
    }

//    public void selectZone(View view) {
//        Intent intent = new Intent(this, ParkingPlaces.class);
//        startActivity(intent);
//    }
}