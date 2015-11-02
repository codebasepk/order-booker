package com.byteshaft.order_booker.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.R;
import com.byteshaft.order_booker.utils.Helpers;

public class MainActivity extends AppCompatActivity {

    private EditText mAddress;
    private EditText mPersonsName;
    private Button mContinueButton;
    private Helpers mHelpers;
    private EditText mPhoneNumber;
    private TextView mNameTextView;
    private TextView mAddressTextView;
    private TextView mobileNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface typeFace = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/BradBunR.ttf");
        mNameTextView = (TextView) findViewById(R.id.name_text_view);
        mAddressTextView = (TextView) findViewById(R.id.address_text_view);
        mAddress = (EditText) findViewById(R.id.address_et);
        mPersonsName = (EditText) findViewById(R.id.name_et);
        mContinueButton = (Button) findViewById(R.id.continue_button);
        mPhoneNumber = (EditText) findViewById(R.id.mobile_editText);
        mobileNumberTextView = (TextView) findViewById(R.id.mobile_text_view);
        mNameTextView.setTypeface(typeFace);
        mAddressTextView.setTypeface(typeFace);
        mContinueButton.setTypeface(typeFace);
        mobileNumberTextView.setTypeface(typeFace);
        mHelpers = new Helpers();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAddress.getText().toString().trim().isEmpty() ||
                        mPersonsName.getText().toString().trim().isEmpty() ||
                        mPhoneNumber.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "All fields must be filled",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
                    mHelpers.setValuesOfStrings(mPersonsName.getText().toString(),
                            mPhoneNumber.getText().toString(), mAddress.getText().toString());
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
        if (sharedPreferences.contains(AppGlobals.KEY_MOBILE_NUMBER)) {
            mPhoneNumber.setText(mHelpers.getDataFromSharedPreference(AppGlobals.KEY_MOBILE_NUMBER));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        startActivity(startMain);
        MainActivity.this.finish();
    }
}
