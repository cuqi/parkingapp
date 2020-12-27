package com.example.proektna;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ReservationConfirmation extends AppCompatActivity {
    public String parking_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_confirmation);

        Intent intent = getIntent();
        parking_name = intent.getStringExtra("parking_name");
    }

    public void toMapsActivity(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("parking_name", parking_name);
        startActivity(intent);
    }
}
