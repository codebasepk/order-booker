package com.byteshaft.order_booker.activites;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.EditText;

import com.byteshaft.order_booker.R;

public class OrderActivity extends Activity {

    private EditText orderThingName, fromWhere, orderTimeDate;
    private String getOrderThingName, getFromWhere, getOrderTimeDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        orderThingName = (EditText) findViewById(R.id.order_et);
        fromWhere = (EditText) findViewById(R.id.from_where_et);
        orderTimeDate = (EditText) findViewById(R.id.time_date_et);

        getOrderThingName = orderThingName.getText().toString();
        getFromWhere = fromWhere.getText().toString();
        getOrderTimeDate = orderTimeDate.getText().toString();
    }

    private void setOrderValuesOfStrings() {
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString("orderThingName", orderThingName.getText().toString());
        editor.putString("fromWhere", fromWhere.getText().toString());
        editor.putString("orderTimeDate", orderTimeDate.getText().toString());
        editor.apply();
    }

    private void getOrderValuesOfStrings() {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        preferences.getString("orderThingName", getOrderThingName);
        preferences.getString("fromWhere", getFromWhere);
        preferences.getString("orderTimeDate", getOrderTimeDate);
    }
}
