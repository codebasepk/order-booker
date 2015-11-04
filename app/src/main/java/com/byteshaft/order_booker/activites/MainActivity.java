package com.byteshaft.order_booker.activites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
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
        setContentView(R.layout.content_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
                    if (AppGlobals.getSnacksSessionStatus() &&
                            !AppGlobals.getSuperMarketSessionStatus()) {
                        startActivity(intent);
                    } else if (AppGlobals.getSuperMarketSessionStatus() &&
                            !AppGlobals.getSnacksSessionStatus()) {
                        startActivity(new Intent(getApplicationContext(), ProductsActivity.class));

                    }

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
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent upIntent = new Intent(this, MainActivity.class);
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
