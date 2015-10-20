package com.byteshaft.order_booker.activites;


import android.os.Bundle;

import android.widget.EditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import com.byteshaft.order_booker.R;

public class OrderActivity extends AppCompatActivity {

    private EditText orderThingName;
    private EditText fromWhere;
    private EditText orderTimeDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_activity);
        orderThingName = (EditText) findViewById(R.id.order_et);
        fromWhere = (EditText) findViewById(R.id.from_where_et);
        orderTimeDate = (EditText) findViewById(R.id.time_date_et);
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
