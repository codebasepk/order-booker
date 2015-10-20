package com.byteshaft.order_booker.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.R;
import com.byteshaft.order_booker.utils.Helpers;

public class MainActivity extends AppCompatActivity {

    private EditText mMobileNumber;
    private EditText mAddress;
    private EditText mPersonsName;
    private Button mContinueButton;
    private SharedPreferences mSharedPreferences;
    private Helpers mHelpers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHelpers = new Helpers();
        mSharedPreferences = Helpers.getPreferenceManager();
        if (!mSharedPreferences.contains(AppGlobals.KEY_USERNAME)) {
//            startActivity(new Intent(getApplicationContext(), RegisterUserActivity.class));
        }

        mMobileNumber = (EditText) findViewById(R.id.number_et);
        mAddress = (EditText) findViewById(R.id.address_et);
        mPersonsName = (EditText) findViewById(R.id.name_et);
        mContinueButton = (Button) findViewById(R.id.continue_button);
        mContinueButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if ((mMobileNumber.getText().toString().trim()).isEmpty() ||
                        mAddress.getText().toString().trim().isEmpty() ||
                        mPersonsName.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All fields must be filled",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                    mHelpers.setValuesOfStrings(mPersonsName.getText().toString(), AppGlobals.KEY_address,
                            AppGlobals.KEY_MOBILE_NUMBER, AppGlobals.KEY_ORDER_NAME,
                            AppGlobals.KEY_FROM_WHERE, AppGlobals.KEY_ORDER_TIME_DATE);
                    mHelpers.getPersonName(AppGlobals.KEY_Name);
                    System.out.println(mHelpers.getPersonName(AppGlobals.KEY_Name));
//                    startActivity(intent);

                }

            }
        });
    }
}
