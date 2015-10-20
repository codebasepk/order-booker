package com.byteshaft.order_booker.activites;


import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.byteshaft.order_booker.R;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setTitle("Fill in the details");



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_done) {
//            ParseQuery<ParseInstallation> parseQuery = ParseQuery.getQuery(ParseInstallation.class);
//            parseQuery.whereEqualTo("admin", "admin_order_receiver");
//            ParsePush.sendMessageInBackground("", parseQuery);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
