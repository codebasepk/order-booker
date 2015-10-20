package com.byteshaft.order_booker.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.R;
import com.byteshaft.order_booker.utils.Helpers;

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
    private SharedPreferences sharedPreferences;
    private Helpers mHelpers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelpers = new Helpers();
        sharedPreferences = Helpers.getPrefrenceManager();
        if (!sharedPreferences.contains(AppGlobals.KEY_USERNAME)) {
            startActivity(new Intent(getApplicationContext(), RegisterUserActivity.class));
        }

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
}
