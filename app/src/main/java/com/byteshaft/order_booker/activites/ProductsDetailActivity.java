package com.byteshaft.order_booker.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.byteshaft.order_booker.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductsDetailActivity extends AppCompatActivity {

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    // for Adonis
    private List<String> listDataHeaderForAdonis;
    private HashMap<String, List<String>> listDataChildForAdonis;
    /// for Latour
    private List<String> listDataHeaderForLatour;
    private HashMap<String, List<String>> listDataChildForLatour;

    private HashMap<String, String> priceHashMap;
    private HashMap<String, String[]> newPriceHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        addPriceDetailsToHashMap();
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        prepareListDataForAdonis();
        listAdapter = new ExpandableListAdapter(this, listDataHeaderForAdonis, listDataChildForAdonis, priceHashMap);
        expListView.setAdapter(listAdapter);
        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        listDataHeaderForAdonis.get(groupPosition)
                                + " : "
                                + listDataChildForAdonis.get(
                                listDataHeaderForAdonis.get(groupPosition)).get(
                                childPosition), Toast.LENGTH_SHORT)
                        .show();
                return true;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                // do nothing when expand
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                // do nothing when collapse
            }
        });
    }

    private void addPriceDetailsToHashMap() {
        priceHashMap = new HashMap<>();
        priceHashMap.put("Roast beef", "6000 L.L");
        priceHashMap.put("Ham & cheese" ,"5000 L.L");
        priceHashMap.put("Tuna", "5000 L.L");
        priceHashMap.put("Frankfurter", "5000 L.L");
        priceHashMap.put("Tawouk", "6000 L.L");
        priceHashMap.put("Chicken sub", "7000 L.L");
        priceHashMap.put("Soujok", "5000 L.L");
        priceHashMap.put("Makanek", "6000 L.L");
        priceHashMap.put("Sub Marine", "6000 L.L");
        priceHashMap.put("Jumbo burger", "8000 L.L");
        priceHashMap.put("Phili steak", "6000 L.L");
        priceHashMap.put("Kachkawen", "4000 L.L");
        priceHashMap.put("+add cheese", "1000 L.L");
        priceHashMap.put("+add ham&cheese", "2000 L.L");
        priceHashMap.put("Strawberry", "6000 L.L");
        priceHashMap.put("Mixed cocktail", "6000 L.L");
        priceHashMap.put("Pineapple", "7000 L.L");
        priceHashMap.put("Mango", "7000 L.L");
        priceHashMap.put("Guayava", "5000 L.L");
        priceHashMap.put("Orange", "4000 L.L");
        priceHashMap.put("Pomegrenade (winter)", "7000 L.L");
        priceHashMap.put("Lemonade  (summer) ", "4000 L.L");
        priceHashMap.put("Banana milk shake ", "4000 L.L");
        priceHashMap.put("Strawbanana shake", "6000 L.L");
        priceHashMap.put("Strawbanana milk shake", "5000 L.L");
        priceHashMap.put("Melon", "4000 L.L");
        priceHashMap.put("Apple", "5000 L.L");
        priceHashMap.put("Carrot", "5000 L.L");
        priceHashMap.put("Grapefruit", "5000 L.L");
        priceHashMap.put("Strawberry", "7000 L.L");
        priceHashMap.put("Mango", "7000 L.L");
        priceHashMap.put("Strawberry/mango", "7000 L.L");
        priceHashMap.put("Avocado", "8000 L.L");
        priceHashMap.put("Avocado/strawberry", "7000 L.L");
        priceHashMap.put("Avocado/mango", "7000 L.L");
        priceHashMap.put("TRIO", "7000 L.L");
    }

    private void addPriceDetailsToHashMapForLatour() {
        newPriceHashMap.put("Pizza  Marguerita",new String[] {"7000","9000", "(Tomato sauce, Mozzarella cheese, oregano)"});
    }

    private void prepareListDataForAdonis() {
        listDataHeaderForAdonis = new ArrayList<>();
        listDataChildForAdonis = new HashMap<>();

        // Adding child data
        listDataHeaderForAdonis.add("Sandwiches");
        listDataHeaderForAdonis.add("Fresh juices");
        listDataHeaderForAdonis.add("Fruit cocktails");


        // Adding child data
        List<String> sandwichies = new ArrayList<>();
        sandwichies.add("Roast beef");
        sandwichies.add("Ham & cheese");
        sandwichies.add("Tuna");
        sandwichies.add("Frankfurter");
        sandwichies.add("Tawouk");
        sandwichies.add("Chicken sub");
        sandwichies.add("Soujok");
        sandwichies.add("Makanek");
        sandwichies.add("Sub Marine");
        sandwichies.add("Jumbo burger");
        sandwichies.add("Phili steak");
        sandwichies.add("Kachkawen");
        sandwichies.add("+add cheese");
        sandwichies.add("+add ham&cheese");

        List<String> freshJuices = new ArrayList<String>();
        freshJuices.add("Strawberry");
        freshJuices.add("Mixed cocktail");
        freshJuices.add("Pineapple");
        freshJuices.add("Mango");
        freshJuices.add("Guayava");
        freshJuices.add("Orange");
        freshJuices.add("Pomegrenade (winter)");
        freshJuices.add("Lemonade  (summer) ");
        freshJuices.add("Banana milk shake ");
        freshJuices.add("Strawbanana shake");
        freshJuices.add("Strawbanana milk shake");
        freshJuices.add("Melon");
        freshJuices.add("Apple");
        freshJuices.add("Carrot");
        freshJuices.add("Grapefruit");


        List<String> fruitCocktails = new ArrayList<>();
        fruitCocktails.add("Strawberry");
        fruitCocktails.add("Mango");
        fruitCocktails.add("Strawberry/mango");
        fruitCocktails.add("Avocado");
        fruitCocktails.add("Avocado/strawberry");
        fruitCocktails.add("Avocado/mango");
        fruitCocktails.add("TRIO");

        listDataChildForAdonis.put(listDataHeaderForAdonis.get(0), sandwichies); // Header, Child data
        listDataChildForAdonis.put(listDataHeaderForAdonis.get(1), freshJuices);
        listDataChildForAdonis.put(listDataHeaderForAdonis.get(2), fruitCocktails);
    }

    private void prepareListDataForLatour() {
        listDataHeaderForAdonis = new ArrayList<>();
        listDataChildForLatour = new HashMap<>();

        listDataHeaderForLatour.add("Pizza");
        listDataHeaderForLatour.add("Pasta Fiesta");
        listDataHeaderForLatour.add("Hot  Sandwishes");
        listDataHeaderForLatour.add("Platters");
        listDataHeaderForLatour.add("Mana’ish");
        listDataHeaderForLatour.add("Drinks");

        List<String> pizza = new ArrayList<>();
        pizza.add("Pizza  Marguerita");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_details_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent upIntent = new Intent(this, ProductsActivity.class);
        switch (item.getItemId()) {
            case R.id.action_checkOut:
                startActivity(new Intent(getApplicationContext(), CartActivity.class));

                break;
            case android.R.id.home:
                NavUtils.navigateUpTo(this, upIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
