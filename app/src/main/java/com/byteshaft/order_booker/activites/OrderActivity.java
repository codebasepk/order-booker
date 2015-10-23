package com.byteshaft.order_booker.activites;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.R;
import com.byteshaft.order_booker.utils.Helpers;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderActivity extends AppCompatActivity {

    private EditText orderThingName;
    private EditText fromWhere;
    private EditText orderTimeDate;
    private Helpers mHelpers;
    private boolean mNetworkAvailable = false;
    private ProgressDialog mProgressDialog;
    private boolean showingProgressBar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        mHelpers = new Helpers();
        orderThingName = (EditText) findViewById(R.id.order_et);
        fromWhere = (EditText) findViewById(R.id.from_where_et);
        orderTimeDate = (EditText) findViewById(R.id.time_date_et);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Fill in the details");
    }

    public void alertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("conformation");
        alertDialogBuilder
                .setMessage("You Will receive a message during few minutes for order conformation.")
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
                String deliveryTime = orderTimeDate.getText().toString();
                if (orderProduct.isEmpty() || from.isEmpty() || deliveryTime.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "you must fill all the fields",
                            Toast.LENGTH_SHORT).show();
                    return false;
                } else {
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
