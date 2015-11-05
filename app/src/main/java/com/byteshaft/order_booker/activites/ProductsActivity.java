package com.byteshaft.order_booker.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.R;


public class ProductsActivity extends AppCompatActivity {

    private GridView mGridView;
    private int[] imageId = {
            R.drawable.sandwitchs
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_layout);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        CustomGrid adapter = new CustomGrid(ProductsActivity.this, imageId);
        mGridView = (GridView) findViewById(R.id.grid);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                 start activity
                startActivity(new Intent(getApplicationContext(), ProductsDetailActivity.class));
                AppGlobals.initializeOrderHashMap();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent upIntent = new Intent(getApplicationContext(), OrderActivity.class);
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
