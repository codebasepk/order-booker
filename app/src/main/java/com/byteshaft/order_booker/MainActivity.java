package com.byteshaft.order_booker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText userName;
    private EditText password;
    private EditText mobileNumber;
    private EditText address;
    private EditText personsName;
    private Button sendButton;
    private String getUserName;
    private String getPassword;
    private String getMobileNumber;
    private String getAddress;
    private String getPersonsName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userName = (EditText) findViewById(R.id.user_name_et);
        password = (EditText) findViewById(R.id.password_et);
        mobileNumber = (EditText) findViewById(R.id.number_et);
        address = (EditText) findViewById(R.id.address_et);
        personsName = (EditText) findViewById(R.id.name_et);
        sendButton = (Button) findViewById(R.id.send);
        getUserName = userName.getText().toString();
        getPassword = password.getText().toString();
        getPersonsName = personsName.getText().toString();
        getAddress = address.getText().toString();
        getMobileNumber = mobileNumber.getText().toString();
        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                startActivity(intent);
                }
        });
    }
    private void setValuesOfStrings() {
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString("username", userName.getText().toString());
        editor.putString("password", password.getText().toString());
        editor.putString("personsName", personsName.getText().toString());
        editor.putString("address", address.getText().toString());
        editor.putString("mobileNumber", mobileNumber.getText().toString());
        editor.apply();
    }

    private void getValuesOfStrings() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        preferences.getString("username", getUserName);
        preferences.getString("password", getPassword);
        preferences.getString("personsName", getPersonsName);
        preferences.getString("address", getAddress);
        preferences.getString("mobileNumber", getMobileNumber);
    }
}
