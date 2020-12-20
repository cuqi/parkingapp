package com.example.proektna;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// TODO
// ADD VALIDATIONS
public class Register extends AppCompatActivity {
    EditText fname_input, lname_input, username_input, pass1_input, pass2_input;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void register(View view) {
        fname_input    = (EditText) findViewById(R.id.fname_input);
        lname_input    = (EditText) findViewById(R.id.lname_input);
        username_input = (EditText) findViewById(R.id.username_input);
        pass1_input    = (EditText) findViewById(R.id.pass1_input);
        pass2_input    = (EditText) findViewById(R.id.pass2_input);

        String fnameInput   = fname_input.getText().toString().trim();
        String lnameInput   = lname_input.getText().toString().trim();
        String usernameInput = username_input.getText().toString().trim();
        String pass1Input   = pass1_input.getText().toString().trim();
        String pass2Input   = pass2_input.getText().toString().trim();


        if(!pass1Input.equals(pass2Input)) {
            Toast toast = Toast.makeText(getApplicationContext(), "Enter the same password in both inputs", Toast.LENGTH_LONG);
            toast.show();
        }

        if(usernameInput.length() > 20) {
            Toast toast = Toast.makeText(getApplicationContext(), "Username exceeds maximum length", Toast.LENGTH_LONG);
            toast.show();
        }

        DBHelper dbHelper = new DBHelper(Register.this);
        int nextId = dbHelper.getNextId() + 1;
        UserModel userModel = new UserModel(nextId, fnameInput, lnameInput, usernameInput, pass1Input);

        boolean isRegistered = dbHelper.registerUser(userModel);

        Toast toast;
        if(isRegistered) {
            toast = Toast.makeText(getApplicationContext(), "Successfully registered!", Toast.LENGTH_LONG);
        } else {
            toast = Toast.makeText(getApplicationContext(), "Unsuccessful registration!", Toast.LENGTH_LONG);
        }
        toast.show();
    }
}