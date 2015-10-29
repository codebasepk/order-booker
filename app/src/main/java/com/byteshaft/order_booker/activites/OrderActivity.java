package com.byteshaft.order_booker.activites;


import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.R;
import com.byteshaft.order_booker.utils.Helpers;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class OrderActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText orderThingName;
    private EditText fromWhere;
    private Button orderTimeDate;
    private Helpers mHelpers;
    private boolean mNetworkAvailable = false;
    private ProgressDialog mProgressDialog;
    private boolean showingProgressBar = false;
    private int year;
    private int month;
    private int day;
    private Calendar calendar;
    private boolean dateSelected = false;
    private String mDate;
    private Button mNowbutton;
    String deliveryTime = null;
    private boolean dateFromDatePicker = false;
    private String mTime;
    private int mHours;
    private int mMinutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        mHelpers = new Helpers();
        orderThingName = (EditText) findViewById(R.id.order_et);
        fromWhere = (EditText) findViewById(R.id.from_where_et);
        orderTimeDate = (Button) findViewById(R.id.time_date_button);
        mNowbutton = (Button) findViewById(R.id.nowButton);
        mNowbutton.setOnClickListener(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Fill in the details");
        String message = "Select Date";
        orderTimeDate.setText(message);
        orderTimeDate.setOnClickListener(this);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        mHours = calendar.get(Calendar.HOUR_OF_DAY);
        mMinutes = calendar.get(Calendar.MINUTE);
    }

    public void alertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Confirmation");
        alertDialogBuilder
                .setMessage("You will receive a message within few moments for order confirmation")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(AppGlobals.getContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent upIntent = new Intent(this, MainActivity.class);
        switch (item.getItemId()) {
            case R.id.action_done:
                String orderProduct = orderThingName.getText().toString();
                String from = fromWhere.getText().toString();
                if (dateSelected && dateFromDatePicker) {
                    deliveryTime = orderTimeDate.getText().toString();
                }
                if (orderProduct.isEmpty() || from.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "You must fill all the fields",
                            Toast.LENGTH_SHORT).show();
                    return false;
                } else if (deliveryTime == null && !dateSelected) {
                    Toast.makeText(getApplicationContext(), "Please select date",
                            Toast.LENGTH_SHORT).show();
                    return false;
                } else {
                    System.out.println(deliveryTime);
                    String[] array = new String[] {orderProduct, from, deliveryTime};
                    new CheckInternet().execute(array);
                    return true;
                }

            case android.R.id.home:
                NavUtils.navigateUpTo(this, upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog = null;
        if (id == 21) {
            dialog =  new DatePickerDialog(this, myDateListener,year, month, day);
        } else if (id == 12) {
            dialog =  new TimePickerDialog(this, timeListener, mHours, mMinutes, true);
        }
        return dialog;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            mDate = arg3 + "-" + (arg2+1) +"-"+ arg1;
            System.out.println(mDate);
            orderTimeDate.setText(mDate);
            dateSelected = true;
            dateFromDatePicker = true;
        }
    };

    private TimePickerDialog.OnTimeSetListener timeListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mTime = hourOfDay + ":" + minute;
            showDialog(21);

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.time_date_button:
                showDialog(12);
                break;
            case R.id.nowButton:
                deliveryTime = Helpers.getTimeStamp();
                dateSelected = true;
                Toast.makeText(OrderActivity.this, "Time & Date Selected", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    class CheckInternet extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(OrderActivity.this);
            mProgressDialog.setMessage("Processing");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            showingProgressBar = true;
        }

        @Override
        protected String doInBackground(String... params) {
            mNetworkAvailable = mHelpers.isInternetWorking();

            ParseQuery<ParseInstallation> parseQuery = ParseQuery.getQuery(ParseInstallation.class);
            parseQuery.whereEqualTo("admin", "admin_order_receiver");
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("name", mHelpers.getDataFromSharedPreference(AppGlobals.KEY_Name));
                jsonObject.put("phone", mHelpers.getDataFromSharedPreference(AppGlobals.KEY_MOBILE_NUMBER));
                jsonObject.put("address", mHelpers.getDataFromSharedPreference(AppGlobals.KEY_address));
                jsonObject.put("product", params[0]);
                jsonObject.put("from", params[1]);
                jsonObject.put("delivery_time", params[2]);
                jsonObject.put("sender_id", AppGlobals.sAndroid_id.trim());

            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (mHelpers.isNetworkAvailable(AppGlobals.getContext()) && mNetworkAvailable) {
                if (showingProgressBar) {
                    mProgressDialog.dismiss();
                }
                ParsePush.sendDataInBackground(jsonObject, parseQuery);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
            if(!mNetworkAvailable) {
                Toast.makeText(OrderActivity.this, "Internet not available", Toast.LENGTH_SHORT).show();
            } else {
                alertDialog();
            }
        }
    }
}
