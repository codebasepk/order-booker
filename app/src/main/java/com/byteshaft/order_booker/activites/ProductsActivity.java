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
            R.drawable.sandwitchs,
            R.drawable.latour,
            R.drawable.dip,
            R.drawable.subz,
            R.drawable.massaad,
            R.drawable.ricardo_snack,
            R.drawable.bur,
            R.drawable.poulet,
            R.drawable.charboul,
            R.drawable.dagher,
            R.drawable.croissant,
            R.drawable.pizzaria,
            R.drawable.shawarma
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
                System.out.println(position);
                AppGlobals.setCurrentSelectedStore(getItemNameFromPosition(position));
                startActivity(new Intent(getApplicationContext(), ProductsDetailActivity.class));
            }
        });
    }

    private String getItemNameFromPosition(int position) {
        switch (position) {
            case 0:
                return "Adonis";
            case 1:
                    return "latour";
            case 2:
                return "dip N dip";
            case 3:
                return "Subz";
            case 4:
                return "massad";
            case 5:
                return "ricardo_snack";
            case 6:
                return "burger_house";
            case 7:
                return "le_poulet";
            case 8:
                return "snack_charboul";
            case 9:
                return "dagher";
            case 10:
                return "croissant";
            case 11:
                return "pizzaria";
            case 12:
                return "shawarma";
        }
        return "";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent upIntent = new Intent(getApplicationContext(), PreMainActivity.class);
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(getApplicationContext(), PreMainActivity.class);
        startActivity(intent);
    }
}
