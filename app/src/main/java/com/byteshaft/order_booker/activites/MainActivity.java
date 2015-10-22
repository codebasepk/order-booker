package com.byteshaft.order_booker.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.R;
import com.byteshaft.order_booker.utils.Helpers;

public class MainActivity extends AppCompatActivity {

    private EditText mAddress;
    private EditText mPersonsName;
    private Button mContinueButton;
    private Helpers mHelpers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelpers = new Helpers();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mAddress = (EditText) findViewById(R.id.address_et);
        mPersonsName = (EditText) findViewById(R.id.name_et);
        mContinueButton = (Button) findViewById(R.id.continue_button);
        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAddress.getText().toString().trim().isEmpty() ||
                        mPersonsName.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All fields must be filled",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                    mHelpers.setValuesOfStrings(mPersonsName.getText().toString(),
                            mAddress.getText().toString());
                    startActivity(intent);

                }
            }
        });

        SharedPreferences sharedPreferences = Helpers.getPreferenceManager();
        if (sharedPreferences.contains(AppGlobals.KEY_Name)) {
            mPersonsName.setText(mHelpers.getDataFromSharedPreference(AppGlobals.KEY_Name));
        }
        if (sharedPreferences.contains(AppGlobals.KEY_address)) {
            mAddress.setText(mHelpers.getDataFromSharedPreference(AppGlobals.KEY_address));
        }
    }
}
