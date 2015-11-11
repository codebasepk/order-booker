package com.byteshaft.order_booker.activites;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.R;

public class PreMainActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout mCustomSnaksButton;
    private RelativeLayout CustomSuperMarketButton;
    private TextView snackTextView;
    private TextView restaurantTextView;
    private TextView superMarketTextView;
    private TextView othersTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        Typeface typeFace = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/BradBunR.ttf");
        snackTextView = (TextView) findViewById(R.id.snacks);
        snackTextView.setTypeface(typeFace);
        restaurantTextView = (TextView) findViewById(R.id.restaurant);
        restaurantTextView.setTypeface(typeFace);
        superMarketTextView = (TextView) findViewById(R.id.super_market);
        superMarketTextView.setTypeface(typeFace);
        othersTextView = (TextView) findViewById(R.id.others);
        othersTextView.setTypeface(typeFace);
        mCustomSnaksButton = (RelativeLayout) findViewById(R.id.snack_custom_button);
        CustomSuperMarketButton = (RelativeLayout) findViewById(R.id.custom_super_market_button);
        mCustomSnaksButton.setOnClickListener(this);
        CustomSuperMarketButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppGlobals.setSuperMarketSessionStatus(false);
        AppGlobals.setSnacksSessionStatus(false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.snack_custom_button:
                AppGlobals.setSnacksSessionStatus(true);
                startActivity(new Intent(getApplicationContext(), ProductsActivity.class));
                break;
            case R.id.custom_super_market_button:
                AppGlobals.setSuperMarketSessionStatus(true);
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_LAUNCHED_FROM_HISTORY);
        startActivity(startMain);
        PreMainActivity.this.finish();
    }
}
