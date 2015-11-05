package com.byteshaft.order_booker.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.R;


public class CartActivity extends AppCompatActivity {

    private TextView itemCount;
    private TextView cartEmpty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);
        itemCount = (TextView) findViewById(R.id.item_count);
        cartEmpty = (TextView) findViewById(R.id.cart_empty);
        initializeAllData();



    }

    private void initializeAllData() {
        itemCount.setText("("+ AppGlobals.getFinalOrdersHashMap().size() + ")");
        if (AppGlobals.getFinalOrdersHashMap().size() == 0) {
            cartEmpty.setVisibility(View.VISIBLE);
        } else {
            cartEmpty.setVisibility(View.INVISIBLE);
        }

    }
}
