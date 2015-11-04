package com.byteshaft.order_booker.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

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
        CustomGrid adapter = new CustomGrid(ProductsActivity.this, imageId);
        mGridView = (GridView) findViewById(R.id.grid);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // start activity
                //startActivity(new Intent());

            }
        });
    }
}
