package com.byteshaft.order_booker.activites;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.R;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView itemCount;
    private RelativeLayout cartLayout;
    private TextView totalAmountTextView;
    private ArrayList<String> finalItems;
    private ArrayList<String> allValues;
    private int amount;
    private ListView listView;
    private View viewLine;
    private Button mCheckOutButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_activity);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        finalItems = new ArrayList<>();
        getProductsFromHashMap();
        itemCount = (TextView) findViewById(R.id.item_count);
        itemCount.setTypeface(AppGlobals.typeface);
        cartLayout = (RelativeLayout) findViewById(R.id.cart_layout);
        totalAmountTextView = (TextView) findViewById(R.id.total_amount);
        totalAmountTextView.setTypeface(AppGlobals.typeface);
        listView = (ListView) findViewById(R.id.list_view_cart);
        viewLine = findViewById(R.id.viewLine);
        mCheckOutButton = (Button) findViewById(R.id.check_out);
        mCheckOutButton.setOnClickListener(this);
        initializeAllData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent upIntent = new Intent(this, ProductsDetailActivity.class);
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpTo(this, upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayAdapter<String> arrayAdapter = new CartAdapter(getApplicationContext(),
                R.layout.single_cart_item, finalItems);
        listView.setAdapter(arrayAdapter);
    }

    private void getProductsFromHashMap() {
        for ( String key : AppGlobals.getFinalOrdersHashMap().keySet() ) {
            finalItems.add(key);
        }
    }

    private void initializeAllData() {
        amount = 0;
        allValues = new ArrayList<>();
        itemCount.setText("(" + AppGlobals.getFinalOrdersHashMap().size() + ")");
        if (AppGlobals.getFinalOrdersHashMap().size() == 0) {
            alertDialog();
            cartLayout.setVisibility(View.INVISIBLE);
            viewLine.setVisibility(View.INVISIBLE);
        } else {
            cartLayout.setVisibility(View.VISIBLE);
            viewLine.setVisibility(View.VISIBLE);
        }
        for (String key : AppGlobals.getFinalOrdersHashMap().keySet()) {
            if (AppGlobals.getQuantityHashMap().containsKey(key)) {
                int value = Integer.valueOf(AppGlobals.getFinalOrdersHashMap().get(key).
                        replaceAll("[a-zA-Z]", "").replace(".", "").replace(" ", "")) *
                        Integer.valueOf(AppGlobals.getQuantityHashMap().get(key));
                allValues.add(String.valueOf(value));
            } else {
                allValues.add(AppGlobals.getFinalOrdersHashMap().get(key));
            }
        }
        for(String value: allValues) {
            int val =  Integer.valueOf(value.replaceAll("[a-zA-Z]", "").replace(".", "").replace(" ", ""));
            amount = amount+val;
        }
        totalAmountTextView.setText("Total amount: "+String.valueOf(amount)+ " L.L");
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.check_out:
                int quantity;
                StringBuilder stringBuilder = new StringBuilder();
                for (String product : AppGlobals.getFinalOrdersHashMap().keySet()) {
                    if (AppGlobals.getQuantityHashMap().containsKey(product)) {
                        quantity = AppGlobals.getQuantityHashMap().get(product);
                        stringBuilder.append(quantity+" "+product.replace("_", "->") + ",");
                    } else {
                        stringBuilder.append("1 "+product.replace("_", "->")+",");
                    }

                }
                String[] array = new String[] {stringBuilder.toString(),
                        AppGlobals.getCurrentSelectedStore(), ""};
                new SendDataTask(CartActivity.this).execute(array);
                break;
        }
    }

    class CartAdapter extends ArrayAdapter<String> {

        private ArrayList<String> arrayList;
        private int layoutResource;
        private ViewHolder holder;

        public CartAdapter(Context context, int resource, ArrayList<String> items) {
            super(context, resource, items);
            this.arrayList = items;
            this.layoutResource = resource;
        }

        @Override
        public View getView(final int position, View convertView, final ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = getLayoutInflater();
                convertView = inflater.inflate(layoutResource, parent, false);
                holder = new ViewHolder();
                holder.deleteItem = (ImageButton) convertView.findViewById(R.id.delete);
                holder.productName = (TextView) convertView.findViewById(R.id.product_name);
                holder.productName.setTypeface(AppGlobals.typeface);
                holder.productPrice = (TextView) convertView.findViewById(R.id.product_price);
                holder.productPrice.setTypeface(AppGlobals.typeface);
                holder.quantityTextView = (TextView) convertView.findViewById(R.id.quantity_text_view);
                holder.quantityTextView.setTypeface(AppGlobals.typeface);
                holder.itemTotalAmount = (TextView) convertView.findViewById(R.id.item_amount);
                holder.itemTotalAmount.setTypeface(AppGlobals.typeface);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.productName.setText(arrayList.get(position).replace("_", "->"));
            holder.productPrice.setText(AppGlobals.getFinalOrdersHashMap().get(arrayList.get(position)));
            if (AppGlobals.getQuantityHashMap().containsKey(arrayList.get(position))) {
                int singleItemAmount = Integer.valueOf(AppGlobals.getFinalOrdersHashMap()
                        .get(arrayList.get(position)).
                                replaceAll("[a-zA-Z]", "").replace(".", "").replace(" ", ""));
                holder.itemTotalAmount.setText("Total: "+ (singleItemAmount * AppGlobals.getQuantityHashMap()
                        .get(arrayList.get(position)))+ "L.L");
            } else {
                holder.itemTotalAmount.setText("Total: " + (AppGlobals.getFinalOrdersHashMap()
                        .get(arrayList.get(position))));

            }
            if (AppGlobals.getQuantityHashMap().containsKey(arrayList.get(position))) {
                holder.quantityTextView
                        .setText("Quantity: " + AppGlobals.getQuantityHashMap().get(arrayList.get(position)));
            } else {
                holder.quantityTextView.setText("Quantity: 1");
            }
            holder.deleteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(AppGlobals.getQuantityHashMap().containsKey(arrayList.get(position)));
                    if (AppGlobals.getQuantityHashMap().containsKey(arrayList.get(position))) {
                        AppGlobals.removeQuantityFromHashMap(arrayList.get(position));
                    }
                    AppGlobals.removeOrderFromHashMap(arrayList.get(position));
                    finalItems.remove(arrayList.get(position));
                    totalAmountTextView.setText("");
                    allValues = null;
                    notifyDataSetChanged();
                    initializeAllData();
                }
            });
            return convertView;
        }
    }

    static class ViewHolder {
        public ImageButton deleteItem;
        public TextView productName;
        public TextView productPrice;
        public TextView quantityTextView;
        public TextView itemTotalAmount;
    }
}
