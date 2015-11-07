package com.byteshaft.order_booker.activites;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.utils.Helpers;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.ParseQuery;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by s9iper1 on 11/6/15.
 */
public class SendDataTask extends AsyncTask<String, String, String> {

    private Activity mActivity;
    private ProgressDialog mProgressDialog;
    private boolean mNetworkAvailable = false;
    public static boolean showingProgressBar = false;
    private Helpers mHelpers;

    public SendDataTask(Activity activity) {
        this.mActivity = activity;
        mHelpers = new Helpers();

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = new ProgressDialog(mActivity);
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
        if (!mNetworkAvailable) {
            Toast.makeText(mActivity, "Internet not available", Toast.LENGTH_SHORT).show();
        } else {
            alertDialog();
            AppGlobals.setSnacksSessionStatus(false);
            AppGlobals.reInitializeHaspMapToClearThem();
        }
    }
    public void alertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mActivity);
        alertDialogBuilder.setTitle("Confirmation");
        alertDialogBuilder
                .setMessage("You will receive a message within few moments for order confirmation")
                .setCancelable(false)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(AppGlobals.getContext(), PreMainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        AppGlobals.getContext().startActivity(intent);
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
