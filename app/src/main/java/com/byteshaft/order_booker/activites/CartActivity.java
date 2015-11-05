package com.byteshaft.order_booker.activites;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.R;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private TextView itemCount;
    private RelativeLayout cartLayout;
    private TextView totalAmountTextView;
    private ArrayList<String> finalItems;
    private ArrayList<String> allValues;
    private int amount = 0;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        finalItems = new ArrayList<>();
        allValues = new ArrayList<>();
        getProductsFromHashMap();
        itemCount = (TextView) findViewById(R.id.item_count);
        cartLayout = (RelativeLayout) findViewById(R.id.cart_layout);
        totalAmountTextView = (TextView) findViewById(R.id.total_amount);
        listView = (ListView) findViewById(R.id.list_view_cart);
        initializeAllData();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void getProductsFromHashMap() {
        for ( String key : AppGlobals.getFinalOrdersHashMap().keySet() ) {
            finalItems.add(key);
        }
    }

    private void initializeAllData() {
        itemCount.setText("(" + AppGlobals.getFinalOrdersHashMap().size() + ")");
        if (AppGlobals.getFinalOrdersHashMap().size() == 0) {
            alertDialog();
            cartLayout.setVisibility(View.INVISIBLE);
        } else {
            cartLayout.setVisibility(View.VISIBLE);
        }
        for ( String key : AppGlobals.getFinalOrdersHashMap().keySet() ) {
            allValues.add(AppGlobals.getFinalOrdersHashMap().get(key));
        }
        for(String value: allValues) {
            int val =  Integer.valueOf(value.replaceAll("[a-zA-Z]", "").replace(".", "").replace(" ", ""));
            amount = amount+val;
        }
        totalAmountTextView.setText(String.valueOf(amount)+ " L.L");
    }
    public void alertDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("No item");
        alertDialogBuilder
                .setMessage("You have no item in the cart")
                .setCancelable(false)
                .setPositiveButton("continue", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(AppGlobals.getContext(), ProductsActivity.class);
                        startActivity(intent);
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    

}
