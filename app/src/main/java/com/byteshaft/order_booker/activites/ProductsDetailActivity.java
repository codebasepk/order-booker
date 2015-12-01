package com.byteshaft.order_booker.activites;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ProductsDetailActivity extends AppCompatActivity {

    private RelativeLayout noteLayout;
    private View separator;

    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    // for Adonis
    private List<String> listDataHeaderForAdonis;
    private HashMap<String, List<String>> listDataChildForAdonis;
    /// for Latour
    private List<String> listDataHeaderForLatour;
    private HashMap<String, List<String>> listDataChildForLatour;

    // for Ricaardo
    private List<String> listDataHeaderForRicaardo;
    private HashMap<String, List<String>> listDataChildForRicaardo;

    // for Massaad
    private List<String> listDataHeaderForMassaad;
    private HashMap<String, List<String>> listDataChildForMassaad;

    // for Subz
    private List<String> listDataHeaderForSubz;
    private HashMap<String, List<String>> listDataChildForSubz;

    // for Dip n Dip
    private List<String> listDataHeaderForDipNdip;
    private HashMap<String, List<String>> listDataChildForDipNdip;

    // for ShawarmaBar
    private List<String> listDataHeaderForshawarmaBar;
    private HashMap<String, List<String>> listDataChildForshawarmaBar;

    // for Snack Charboul
    private List<String> listDataHeaderForsnackCharboul;
    private HashMap<String, List<String>> listDataChildForsnackCharboul;

    //for CROISSANTS
    private List<String> listDataHeaderForCroissants;
    private HashMap<String, List<String>>  listDataChildForCroiassants;

    private List<String> listDataHeaderForDagher;
    private HashMap<String, List<String>> listDataChildForDagher;

    //for Z Bureger House
    private List<String> listDataHeaderForBurgerHouse;
    private HashMap<String, List<String>>  listDataChildForBuregerHouse;


    private HashMap<String, String[]> priceHashMap;
    private HashMap<String, String[]> newPriceHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products_detail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        separator = findViewById(R.id.separator_view);
        noteLayout = (RelativeLayout) findViewById(R.id.note_layout);

        if (AppGlobals.getCurrentSelectedStore().equals("Adonis")) {
            separator.setVisibility(View.VISIBLE);
            noteLayout.setVisibility(View.VISIBLE);
        } else {
            separator.setVisibility(View.GONE);
            noteLayout.setVisibility(View.GONE);
        }

        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        expListView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        // Listview on child click listener
        loadSpecificData(AppGlobals.getCurrentSelectedStore());
        System.out.println(AppGlobals.getCurrentSelectedStore());
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
                InputMethodManager inputManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (getWindow().getCurrentFocus() != null) {
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    getCurrentFocus().clearFocus();
                }
            }
        });

//        expListView.setOnScrollListener(new AbsListView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                InputMethodManager inputManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                if (getWindow().getCurrentFocus() != null) {
//                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//                    getCurrentFocus().clearFocus();
//                }
//
//            }
//
//            @Override
//            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//
//            }
//        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                // do nothing when collapse
                InputMethodManager inputManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (getWindow().getCurrentFocus() != null) {
                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    getCurrentFocus().clearFocus();
                }
            }
        });
    }

    private void loadSpecificData(String restaurant) {
        switch (restaurant) {
            case "Adonis":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMap();
                prepareListDataForAdonis();
                listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeaderForAdonis,
                        listDataChildForAdonis, priceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "latour":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                prepareListDataForLatour();
                listAdapter = new ExpandableListAdapter(getApplicationContext(),listDataHeaderForLatour,
                        listDataChildForLatour, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "dip N dip":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                prepareListDataForDipnDip();
                listAdapter = new ExpandableListAdapter(getApplicationContext(),listDataHeaderForDipNdip,
                        listDataChildForDipNdip, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "Subz":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                preparedListDataForSubz();
                listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeaderForSubz,
                        listDataChildForSubz, newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "massad":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                preparedListDataForMassaad();
                listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeaderForMassaad, listDataChildForMassaad,
                        newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
            case "ricardo_snack":
                newPriceHashMap = new HashMap<>();
                addPriceDetailsToHashMapForLatour();
                preparedListDataForRicaardo();
                listAdapter = new ExpandableListAdapter(getApplicationContext(), listDataHeaderForRicaardo, listDataChildForRicaardo,
                        newPriceHashMap);
                expListView.setAdapter(listAdapter);
                break;
        }
    }

    private void addPriceDetailsToHashMap() {
        priceHashMap = new HashMap<>();
        priceHashMap.put("Roast beef", new String[]{"6000", "", ""});
        priceHashMap.put("Ham & cheese" ,new String[]{"5000", "", ""});
        priceHashMap.put("Tuna", new String[]{"5000", "", ""});
        priceHashMap.put("Frankfurter", new String[]{"5000", "", ""});
        priceHashMap.put("Tawouk", new String[]{"6000", "", ""});
        priceHashMap.put("Chicken sub", new String[]{"7000", "", ""});
        priceHashMap.put("Soujok", new String[]{"5000", "", ""});
        priceHashMap.put("Makanek", new String[]{"6000", "", ""});
        priceHashMap.put("Sub Marine", new String[]{"6000", "", ""});
        priceHashMap.put("Jumbo burger", new String[]{"8000", "", ""});
        priceHashMap.put("Phili steak", new String[]{"6000", "", ""});
        priceHashMap.put("Kachkawen", new String[]{"4000", "", ""});
        priceHashMap.put("+add cheese", new String[]{"1000", "", ""});
        priceHashMap.put("+add ham&cheese", new String[]{"2000", "", ""});
        priceHashMap.put("Strawberry", new String[]{"6000", "", ""});
        priceHashMap.put("Mixed cocktail", new String[]{"6000", "", ""});
        priceHashMap.put("Pineapple", new String[]{"7000", "", ""});
        priceHashMap.put("Mango", new String[]{"7000", "", ""});
        priceHashMap.put("Guayava", new String[]{"5000", "", ""});
        priceHashMap.put("Orange", new String[]{"4000", "", ""});
        priceHashMap.put("Pomegrenade (winter)", new String[]{"7000", "", ""});
        priceHashMap.put("Lemonade  (summer) ", new String[]{"4000", "", ""});
        priceHashMap.put("Banana milk shake ", new String[]{"4000", "", ""});
        priceHashMap.put("Strawbanana shake", new String[]{"6000", "", ""});
        priceHashMap.put("Strawbanana milk shake", new String[]{"5000", "", ""});
        priceHashMap.put("Melon", new String[]{"4000", "", ""});
        priceHashMap.put("Apple", new String[]{"5000", "", ""});
        priceHashMap.put("Carrot", new String[]{"5000", "", ""});
        priceHashMap.put("Grapefruit", new String[]{"5000", "", ""});
        priceHashMap.put("Strawberry", new String[]{"7000", "", ""});
        priceHashMap.put("Mango", new String[]{"7000", "", ""});
        priceHashMap.put("Strawberry/mango", new String[]{"7000", "", ""});
        priceHashMap.put("Avocado", new String[]{"8000", "", ""});
        priceHashMap.put("Avocado/strawberry", new String[]{"7000", "", ""});
        priceHashMap.put("Avocado/mango", new String[]{"7000", "", ""});
        priceHashMap.put("TRIO", new String[]{"7000", "", ""});
    }

    private void addPriceDetailsToHashMapForLatour() {
        if (AppGlobals.getCurrentSelectedStore().equals("latour")) {
            newPriceHashMap.put("Pizza Marguerita", new String[]{"7000", "9000", "(Tomato sauce, Mozzarella cheese, oregano)"});

            newPriceHashMap.put("Pizza 3 Fromages", new String[]{"7500", "10000", "(Tomato sauce, 3 cheese mix)"});
            newPriceHashMap.put("Pizza Vegetarian", new String[]{"8500", "11000", "(Tomato sauce, artichokes, corn, mushrooms, olives, green pepper, cheese)"});
            newPriceHashMap.put("Pizza Jambon / Dinde", new String[]{"9000", "12000", "(Tomato sauce, ham, mushroom, olives, cheese)"});
            newPriceHashMap.put("Pizza Pepperoni", new String[]{"9000", "12000", "(Tomato sauce, pepperoni, mushrooms, olives, cheese)"});
            newPriceHashMap.put("Pizza Mexichicken", new String[]{"10000", "14000", "(Mexican sauce, chicken, mushroom, cheese)"});

            //  Pasta Fiesta
            newPriceHashMap.put("Penne Arabiata", new String[]{"11000", "", ""});
            newPriceHashMap.put("Chicken Pesto", new String[]{"14000", "", ""});
            newPriceHashMap.put("Carbonara", new String[]{"14000", "", ""});

            // Hot Sandwiches
            newPriceHashMap.put("Tawook", new String[]{"4000", " ", "(Tawook, garlic spread, coleslaw, pickles, French fries)"});
            newPriceHashMap.put("American Frankfurter", new String[]{"5000", " ", "(Hotdog, lettuce, corn, cocktail sauce, French fries)"});
            newPriceHashMap.put("Hamburger", new String[]{"4500", " ", "(Grilled beef meat, lettuce, colseslaw, tomatoes, ketchup, mayo, French fries)"});
//        newPriceHashMap.put("Fish Burger", new String[]{"5000", "", ""});
            newPriceHashMap.put("Fish Burger", new String[]{"5000", " ", "(Fish, lettuce, tartar sauce, pickles, French fries)"});
            newPriceHashMap.put("Fish Filet", new String[]{"5000", " ", "(Grilled  hamour  filet, tartar sauce,  pickles)"});
            newPriceHashMap.put("Chicken Burger", new String[]{"5500", "", "(Grilled chicken breast, garlic mayo spread, tomatoes, lettuce, pickles, French fries)"});
            newPriceHashMap.put("Fajita", new String[]{"6500", " ", "(Marinated chicken, mushrooms, corn, green pepper, onions, melted cheese)"});
            newPriceHashMap.put("Philadelphia", new String[]{"6500", "", "(Beef meat marinated, mushrooms, corn, green pepper, onions, melted cheese)"});
            newPriceHashMap.put("Chicken La Tour", new String[]{"7500", "", "(Grilled chicken, lettuce, corn, mayo sauce)"});
            newPriceHashMap.put("Beef Shawarma", new String[]{"7500", "", "(Marinated beef meat, beef shawarma sauce, parsley, onions, tomatoes, pickles, French fries)"});
            newPriceHashMap.put("Chicken Shawarma", new String[]{"7500", "", "(Chicken, shawarma sauce, lettuce, tomatoes, pickles, French fries)"});
            newPriceHashMap.put("Chicken Submarine", new String[]{"8000", "", "(Chicken, mayo, lettuce, cheddar cheese, tomatoes, pickles, French fries)"});
            newPriceHashMap.put("Chicken Club Sandwish", new String[]{"8500", "", "(Chicken, mayo spread, lettuce, bacon, eggs, cheddar  cheese, served with French  fries and coleslaw)"});

            // Platters
            newPriceHashMap.put("Tawook", new String[]{"12000", "", ""});
            newPriceHashMap.put("Beef Shawarma", new String[]{"13000", "", ""});
            newPriceHashMap.put("Chicken Shawarma", new String[]{"14000", "", ""});
            newPriceHashMap.put("Fish & Chips", new String[]{"14000", "", ""});
            newPriceHashMap.put("Grilled Hamour Filet", new String[]{"14000", "", ""});
            newPriceHashMap.put("Chicken Breast", new String[]{"15000", "", ""});
            newPriceHashMap.put("Chicken Escalope", new String[]{"16000", "", ""});
            newPriceHashMap.put("Chicken Fungi", new String[]{"17000", "", ""});

            // Mana’ish
            newPriceHashMap.put("Zaatar", new String[]{"1000", "", ""});
            newPriceHashMap.put("Zaatar light", new String[]{"1500", "", ""});
            newPriceHashMap.put("Zaatar and Labneh", new String[]{"2000", "", ""});
            newPriceHashMap.put("Zaatar & Cheese", new String[]{"3000", "", ""});
            newPriceHashMap.put("Cheese", new String[]{"3000", "", ""});
            newPriceHashMap.put("Spinach", new String[]{"2500", "", ""});
            newPriceHashMap.put("Keshek", new String[]{"2000", "", ""});
            newPriceHashMap.put("Akkawi", new String[]{"3000", "", ""});
            newPriceHashMap.put("Halloum", new String[]{"3000", "", ""});
            newPriceHashMap.put("Bulgari", new String[]{"3000", "", ""});
            newPriceHashMap.put("Bulgari harra", new String[]{"3000", "", ""});
            newPriceHashMap.put("Cheese Mix", new String[]{"4000", "", ""});
            newPriceHashMap.put("Labneh", new String[]{"3000", "", ""});
            newPriceHashMap.put("Lahme & Kawarma", new String[]{"4000", "", ""});
            newPriceHashMap.put("Lahme b3ajin", new String[]{"3000", "", ""});
            newPriceHashMap.put("Lahme b3ajin & Cheese", new String[]{"4000", "", ""});
            newPriceHashMap.put("Pesto Cheese", new String[]{"3500", "", ""});
            newPriceHashMap.put("Feta Mix", new String[]{"3500", "", ""});
            newPriceHashMap.put("Turkey & Cheese", new String[]{"4000", "", ""});
            newPriceHashMap.put("Ham & Cheese", new String[]{"4000", "", ""});
            newPriceHashMap.put("Salami & Cheese", new String[]{"4000", "", ""});
            newPriceHashMap.put("Hotdog & Cheese", new String[]{"4000", "", ""});
            newPriceHashMap.put("La Tour Special", new String[]{"4000", "", ""});
            newPriceHashMap.put("Soujouk & Cheese", new String[]{"5000", "", ""});
            newPriceHashMap.put("Halloum Bacon", new String[]{"5000", "", ""});
            newPriceHashMap.put("Chocoba", new String[]{"5000", "", ""});

            // Drinks
            newPriceHashMap.put("pepsi", new String[]{"1000", "", ""});
            newPriceHashMap.put("7up", new String[]{"1000", "", ""});
            newPriceHashMap.put("Water", new String[]{"500", "", ""});
        }

        /////////////////////////// Latour Ended ////////////////////////////////

        ////////////////////// Ricaardo Started ///////////////////////////////
        // distinguishing the same itesm using the word  "r" from the hotel Ricaardo
        // so they don't mach with the other hotels items
        if (AppGlobals.getCurrentSelectedStore().equals("ricardo_snack")) {
            newPriceHashMap.put("Philadelphia", new String[]{"6000", "", "(Steak, mushroom, onions, cheese, sauce)"});
            newPriceHashMap.put("Spanish Steak", new String[]{"6000", "", "(Steak, onions, cheese, special sauce)"});
            newPriceHashMap.put("Francisco", new String[]{"6000", "","(Chicken,corn,lettuce,tomato,sauce)"});
            newPriceHashMap.put("Hamburger", new String[]{"5000", "7000", "(Meat,lettuce,tomato,fries,sauce. Add: Bacon, Cheese )"});
            newPriceHashMap.put("Shrimp", new String[]{"7000", "", ""});
            newPriceHashMap.put("Calamari", new String[]{"6000", "", "(Calamari, lettuce, sauce)"});
            newPriceHashMap.put("Roast Beef", new String[]{"6000", "", "(Meat, pickles, tomato, mayo sauce)"});
            newPriceHashMap.put("Grilled chicken breast", new String[]{"5000", "", "chicken, garlic, pickles, lettuce, fries"});
            newPriceHashMap.put("Canarias", new String[]{"6000", "", "(Chicken, mushroom, smoked bacon, cheese)"});
            newPriceHashMap.put("Mexican chicken", new String[]{"6000", "", "(Chicken, mushroom, corn, green pepper, tomato, coriander, onion, garlic, fries, sauce)"});
            newPriceHashMap.put("Ricky", new String[]{"6000", "", "(Chicken breast, fries, lettuce, corn, sauce)"});
            newPriceHashMap.put("Fajita", new String[]{"6000", "", "(Chicken breast, fries, lettuce, corn, sauce)"});

            // drinks
            newPriceHashMap.put("pepsi", new String[]{"1000", "", ""});
            newPriceHashMap.put("Mirinda", new String[]{"1000", "", ""});
            newPriceHashMap.put("Seven up", new String[]{"1000", "", ""});
            newPriceHashMap.put("Water", new String[]{"500", "", ""});
        }

        ///////////////////////////Ricaardo Ended //////////////////////


        ///////////////////////// Massaad Started //////////////////////
        if (AppGlobals.getCurrentSelectedStore().equals("massad")) {

            newPriceHashMap.put("Salatat", new String[]{"7000", "", ""});
            newPriceHashMap.put("Fattouch", new String[]{"7000", "", ""});
            newPriceHashMap.put("Tabboulé", new String[]{"7000", "", ""});

            /// Mou2abbalet
            newPriceHashMap.put("7ommos", new String[]{"5000", "", ""});
            newPriceHashMap.put("Mtabbal", new String[]{"5000" , "" , ""});
            newPriceHashMap.put("Labné", new String[]{"5000", "", ""});
            newPriceHashMap.put("Batata", new String[]{"5000", "", ""});
            newPriceHashMap.put("Habra zened l 3abed", new String[]{"6000", "", ""});

            // sandwishet
            newPriceHashMap.put("Tawou2", new String[]{"5000", "", ""});
            newPriceHashMap.put("La7em Mechwé", new String[]{"6000", "", ""});
            newPriceHashMap.put("Kabab", new String[]{"5000", "", ""});
            newPriceHashMap.put("Hamburger", new String[]{"6000", "", ""});
            newPriceHashMap.put("Batata", new String[]{"3000", "", ""});

            /// S7oun
            newPriceHashMap.put("Law7et Tawou2", new String[]{"7000", "", ""});
            newPriceHashMap.put("Law7et la7em mechwé", new String[]{"7000", "", ""});
            newPriceHashMap.put("Law7et kabab", new String[]{"7000", "", ""});
            newPriceHashMap.put("3arayess kafta", new String[]{"12000", "", ""});
            newPriceHashMap.put("Hamburger", new String[]{"12000", "", ""});
            newPriceHashMap.put("Sa7n tawou2", new String[]{"13000", "", ""});
            newPriceHashMap.put("Sa7n la7em mechwé", new String[]{"15000", "", ""});
            newPriceHashMap.put("Sa7n kabab", new String[]{"12000", "", ""});
            newPriceHashMap.put("Sa7n machawé", new String[]{"15000", "", ""});
            newPriceHashMap.put("Kilo machawé mechwé mchakkal", new String[]{"50000", "", ""});
            newPriceHashMap.put("Kilo la7em mechwé", new String[]{"60000", "", ""});
            newPriceHashMap.put("Kilo tawou2", new String[]{"35000", "", ""});
            newPriceHashMap.put("Kilo kabab", new String[]{"45000", "", ""});

            // soft drinks
            newPriceHashMap.put("Pepsi", new String[]{"2000", "", ""});
            newPriceHashMap.put("Mirinda", new String[]{"2000", "", ""});
            newPriceHashMap.put("7up", new String[]{"2000", "", ""});
            newPriceHashMap.put("3assir bourtoukal tabi3é", new String[]{"5000", "", ""});
            newPriceHashMap.put("Perrier", new String[]{"5000", "", ""});
            newPriceHashMap.put("Laymounada", new String[]{"4000", "", ""});
            newPriceHashMap.put("May kbiré", new String[]{"2000", "", ""});
            newPriceHashMap.put("May zghiré", new String[]{"1000", "", ""});
            newPriceHashMap.put("3iran", new String[]{"2000", "", ""});
        }

        ///////////////////////// Massaad Ended //////////////////////


        ///////////////////////// SUBZ Started //////////////////////
        if (AppGlobals.getCurrentSelectedStore().equals("Subz")) {

            newPriceHashMap.put("Chicken Caesar", new String[]{"9000", "", "(Chicken, Iceberg, croutons, parmesan cheese)"});
            newPriceHashMap.put("Chef Salad", new String[]{"8000", "", "(Ham & cheese, chicken, iceberg, cherry tomato, corn)"});
            newPriceHashMap.put("Crab Salad", new String[]{"8000", "", "(Crab sticks, iceberg, pineapple, carrots, corn)"});
            newPriceHashMap.put("Tuna Pasta Salad", new String[]{"8000", "", "(Tuna, olives, pasta, fresh mushroom, cherry tomato, corn, iceberg)"});
            newPriceHashMap.put("Subz Salad", new String[]{"9000", "", "(Iceberg, cucumber, fresh mushroom, cherry tomato, thym, black olives, feta cheese, dried grapes walnuts)"});

            // burgers
            newPriceHashMap.put("Classic burger", new String[]{"5000", "", "(Beef patty, coleslaw, tomato, onion, French fries, ketchup, mustard)"});
            newPriceHashMap.put("Subz Burger", new String[]{"6000", "", "(Beef patty, mushroom, onion, cheese, our home made special sauce)"});
            newPriceHashMap.put("Chicken Burger", new String[]{"5500", "", "(Chicken patty, iceberg, pickles, French fries, garlic mayo)"});

            /// SUBz SPECIAL  :

            newPriceHashMap.put("Fajitas", new String[]{"6500", "", "(Chicken marinated, mushroom, onion, green pepper, mayo, mozzarella, iceberg)"});
            newPriceHashMap.put("Philadelphia", new String[]{"6500", "", "(Steak marinated, mushroom, onion, green pepper, mozzarella, mayo sauce)"});
            newPriceHashMap.put("Francisco", new String[]{"6000", "", "(Marinated chicken, mozzarella, corn, pickles, iceberg, soya sauce, mayo)"});
            newPriceHashMap.put("Chicken Sub", new String[]{"6000", "", "(Marinated chicken,  mozzarella, pickles, iceberg, garlic mayo)"});
            newPriceHashMap.put("Mexican Chicken", new String[]{"6500", "", "(Marinated chicken, cheddar cheese, green & red pepper,  corn, onion, spicy, home made sauce,mozzarella)"});
            newPriceHashMap.put("Chicken Escalope", new String[]{"6000", "", "(Breaded fried chicken, coleslaw, French fries, pickles, garlic)"});
            newPriceHashMap.put("Torpedo", new String[]{"6500", "", "(Marinated chicken, mushroom, onion, cheese, iceberg, pickles, fries, garlic mayo)"});
            newPriceHashMap.put("Subz Steak", new String[]{"6500", "", "(Marinated steak, hot paste, onion, mushroom, cheese, home made sauce)"});
            newPriceHashMap.put("Submarine", new String[]{"6500", "", "(Ham & cheese, salami, iceberg, pickles, tomato, mayo)"});
            newPriceHashMap.put("Taouk", new String[]{"5500", "", "(Chicken, garlic, pickles, French fries, coleslaw)"});
            newPriceHashMap.put("Crispy", new String[]{"6500", "", "(Breaded chicken, pickles, lettuce, fries, turkey, cheese, home made sauce)"});
            newPriceHashMap.put("Potato", new String[]{"3000", "", ""});
            newPriceHashMap.put("Crispy Plat", new String[]{"8000", "", "(4 pieces crispy chicken, coleslaw, fries)"});

            // SUBZ FRIES :
            newPriceHashMap.put("French Fries", new String[]{"4000", "", ""});
            newPriceHashMap.put("Wedges", new String[]{"5000", "", ""});
            newPriceHashMap.put("Twister", new String[]{"7000", "", ""});


            // drinks
            newPriceHashMap.put("Pepsi", new String[]{"1000", "", ""});
            newPriceHashMap.put("Seven Up", new String[]{"1000", "", ""});
            newPriceHashMap.put("Mirinda", new String[]{"1000", "", ""});
            newPriceHashMap.put("Water Small", new String[]{"500", "", ""});
            newPriceHashMap.put("Water Big", new String[]{"1000", "", ""});
            newPriceHashMap.put("Beer Almaza", new String[]{"2000", "", ""});
            newPriceHashMap.put("Ice Tea", new String[]{"1500", "", ""});
        }
        ///////////////////////// SUBZ Ended ///////////////////////////////////////////////////////

        ///////////////////////// Dip n Dip Started //////////////////////
        if (AppGlobals.getCurrentSelectedStore().equals("dip N dip")) {
            newPriceHashMap.put("Dip n dip crêpe", new String[]{"13000", "", "(strawberry, banana, pineapple, kiwi)"});
            newPriceHashMap.put("Dip n dip crêpe with scoop", new String[]{"16000", "", "icecream(vanilla, chocolate or strawberry)"});
            newPriceHashMap.put("Fettuccini  crêpe full", new String[]{"12500", "", "(crêpe, cut fettuccini style, with 1 scoop of your choice of ice cream, tapped with your choice of chocolate"});
            newPriceHashMap.put("Fettuccini  crêpe small", new String[]{"11000", "", "(crêpe, cut fettuccini style, with 1 scoop of your choice of ice cream, tapped with your choice of chocolate"});
            newPriceHashMap.put("Cinnamon crêpe pouch", new String[]{"10500", "", ""});
            newPriceHashMap.put("Tripple chocolate crêpe", new String[]{"8,000", "", "(Folded crêpe, filled with chocolate and trapped with 3 kinds of chocolate (milk, dark, white)"});
            newPriceHashMap.put("Cookies crêpe", new String[]{"10000", "", "(Folded crêpe, filled with cookies, and tapped with 3 kinds of chocolate(milk, dark, white)"});
            newPriceHashMap.put("Mallow crêpe", new String[]{"10000", "", "(Folded crêpe, filled with marshmallows, and tapped with 3 kinds of chocolate (milk, dark , white)"});
            newPriceHashMap.put("Brownies crêpe", new String[]{"10500", "", "(Folded crêpe, filled with brownies, and tapped with 3 kinds of chocolate (milk, dark, white)"});
            newPriceHashMap.put("Krispy crêpe", new String[]{"10000", "", "(Folded crêpe, filled with ice cream of choice and rice krispies, and tapped with chocolate)"});
            newPriceHashMap.put("Banana wrap", new String[]{"9000", "", "(A full banana wrapped with crêpe and tapped with chocolate and whipped cream"});

            // Waffle:
            newPriceHashMap.put("Dip n dip waffle", new String[]{"13000", "", "(waffle with your choice of fruit " +
                    "(strawberry, banana, pineapple, kiwi) or a mix, all tapped with the great dip n dip real Belgian chocolate of your choice)"});
            newPriceHashMap.put("Dip n dip waffle with ice cream", new String[]{"16000", "", "dip n dip waffle + icecream (vanilla, chocolate or strawberry)"});
            newPriceHashMap.put("Chocolate waffle", new String[]{"9500", "", "(waffle tapped with your choice of chocolate)"});
            newPriceHashMap.put("Waffle stick", new String[]{"8000", "", "(waffle on a stick dipped in your choice of chocolate)"});

            // Pancake:
            newPriceHashMap.put("Pancake mini tower", new String[]{"8000", "", "(5 pieces of pancake tapped with chocolate)"});

            // Chocolate rich
            newPriceHashMap.put("Fondant", new String[]{"9500", "", "(chocolate rich fondant baked fresh" +
                    " , prepared from our famous Belgian chocolate, served with icecream"});
            newPriceHashMap.put("Molten cake", new String[]{"9500", "", "(same as fondant, more liquid, presented in porcelain bowl right from the oven, presented with icecream)"});
            newPriceHashMap.put("Brownies", new String[]{"7500", "", "(Chocolate heavy brownie, served with ice cream, tapped with our amazing chocolate)"});
            newPriceHashMap.put("Dip n dip pizza", new String[]{"9000", "", "(brownies slice trapped with cheesecake and you pick the toppings: strawberry, banana, pineapple, kiwi."});
            newPriceHashMap.put("Chocolate mousse", new String[]{"7000", "", "(the richest chocolate mousse in town made with our famous Belgian chocolate)"});
            newPriceHashMap.put("Trois chocolat mousse", new String[]{"7000", "", "(Three layers of white, milk, dark chocolate mousse)"});
            newPriceHashMap.put("Succès", new String[]{"8000", "", "(very rich chocolate cake with biscuit," +
                    " just like the ones we used to have in home birthday parties, served in 2 slice"});

            // Baked goods
            newPriceHashMap.put("Mini muffin", new String[]{"7000", "", "(4 mini muffins presented with chocolate shot)"});
            newPriceHashMap.put("Cookies", new String[]{"3500", "", "(2 cookies, dipped with our Belgian chocolate )"});
            newPriceHashMap.put("Dip n Dip éclair pyramid", new String[]{"11500", "", "(11 pieces of mini éclair pyramid style tapped with chocolate)"});
            newPriceHashMap.put("Éclair Kebab", new String[]{"6000", "", "(5 pieces of éclair on a stick dipped in chocolate)"});
            newPriceHashMap.put("Profiterole", new String[]{"8000", "", "(Profiterole pastry filled with vanilla ice cream tapped with chocolate)"});

            /// ice creams
            newPriceHashMap.put("Scoop", new String[]{"3000", "", "(strawberry , chocolate, vanilla)"});
            newPriceHashMap.put("scoop Tapped with chocolate", new String[]{"4000", "", ""});
            newPriceHashMap.put("Crunchy ice cream", new String[]{"7500", "", "(A bowl full of ice cream tapped with chocolate and rice Crispies)"});


            newPriceHashMap.put("Chocolate Shot", new String[]{"4000", "", ""});
            newPriceHashMap.put("Chocolate 1kg", new String[]{"60000", "", "Chocolate for your home fountain (1kg)"});

            // Fried yummies
            newPriceHashMap.put("Pain Perdu", new String[]{"9000", "", "(Milk soaked fried toast offered with your choice of caramel and cinnamon or chocolate. With a scoop of icecream"});
            newPriceHashMap.put("Cheese cake nuggets", new String[]{"9000", "", "(5 pieces of fried cheese cake nuggets offered with chocolate)"});

            // stuff in a cup
            newPriceHashMap.put("Dip Crispies", new String[]{"7000", "", "(A cup filled with cereal and chocolate, tapped with mini marshmallows " +
                    "available cereal : rice crispies, honey smacks, trix)"});
            newPriceHashMap.put("Fruits In a cup", new String[]{"7500", "", "(Fruit salad, tapped with chocolate and whipped cream)"});
            newPriceHashMap.put("Brownies in a cup", new String[]{"8500", "", "(Mashed brownies with ice cream and chocolate in a cup)"});

            ///  Dip n Stick
            newPriceHashMap.put("Dip Sticks 4 pieces", new String[]{"3500", "", "filled with - Strawberry, Kiwi, Marshmallow, Brownies, Pineapple, Banana"});
            newPriceHashMap.put("Dip Sticks 8 pieces", new String[]{"6000", "", "filled with -Strawberry, Kiwi, Marshmallow, Brownies, Pineapple, Banana"});
            newPriceHashMap.put("Dip stick platter", new String[]{"16500", "", "filled with 4 pieces of  7 of your choice from of the following items with chocolate fondue,"});

            //Dip n dip mania
            newPriceHashMap.put("Dip-able items (full plate)", new String[]{"3500", "", "(Strawberry, pineapple, kiwi, banana, marshmallow)"});
            newPriceHashMap.put("Brownies, mini éclair", new String[]{"4000", "", ""});
            newPriceHashMap.put("Ice cream (scoop)", new String[]{"3000", "", ""});
            newPriceHashMap.put("Fondue", new String[]{"7000", "", ""});
            newPriceHashMap.put("Whipped cream", new String[]{"1000", "", ""});


            // COLD DRINKS :
            newPriceHashMap.put("Dip n dip freezy", new String[]{"9500", "", "(The richest chocolate drink ever)"});

            // Milk shakes :
            newPriceHashMap.put("Chocolate milk shake", new String[]{"7000", "", ""});
            newPriceHashMap.put("Vanilla milk shake", new String[]{"7000", "", ""});
            newPriceHashMap.put("Strawberry  milk shake", new String[]{"7000", "", ""});
            newPriceHashMap.put("Coffee shake", new String[]{"9000", "", ""});
            newPriceHashMap.put("Banana milk shake", new String[]{"7500", "", ""});
            newPriceHashMap.put("After eight milk shake", new String[]{"7500", "", "(Mint flavored chocolate milk shake)"});

            // Frappe
            newPriceHashMap.put("Mocha frappe", new String[]{"7500", "", "(Chocolate, espresso in a cold icy creamy mixture)"});
            newPriceHashMap.put("White mocha frappe", new String[]{"7500", "", "(White chocolate, espresso in a cold icy creamy mixture)"});
            newPriceHashMap.put("Caramel frappe", new String[]{"7500", "", "(Caramel flavored frappe)"});
            newPriceHashMap.put("Cookies frappe", new String[]{"8000", "", "(Frappe blended with real cookies)"});
            newPriceHashMap.put("Pink frappe", new String[]{"7500", "", "(Frappe with  strawberry and raspberry flavors)"});

            // Smoothies :
            newPriceHashMap.put("Creamy smoothies", new String[]{"8000", "", ""});
            newPriceHashMap.put("Smoothie float", new String[]{"9000", "", ""});
            newPriceHashMap.put("Oreo milk shake", new String[]{"8000", "", "(original Oreo cookies)"});
            newPriceHashMap.put("Affogato", new String[]{"5000", "", "(A scoop of vanilla ice cream drowned in espresso with your favorite flavor -Caramel -Hazelnut -Cinnamon)"});

            /// Ice tea shakes :
            newPriceHashMap.put("Ice tea shake", new String[]{"7000", "", ""});
            newPriceHashMap.put("Lemon tea shake", new String[]{"7000", "", ""});
            newPriceHashMap.put("Mint tea shake", new String[]{"7000", "", ""});
            newPriceHashMap.put("Peach tea shake", new String[]{"7000", "", ""});
            newPriceHashMap.put("Raspberry  tea shake", new String[]{"7000", "", ""});
            newPriceHashMap.put("Iced cappuccino", new String[]{"7000", "", ""});

            // Soft  drinks
            newPriceHashMap.put("Ice tea", new String[]{"6000", "", "(- Peach   - Raspberry  - Lemon  - Mixed berry)"});
            newPriceHashMap.put("Italian soda", new String[]{"5000", "", "(- Lemon  - Raspberry   - Cherry   - Strawberry   - Peach   - Blue curacao)"});
            newPriceHashMap.put("Flavored cola", new String[]{"5000", "", "(- Vanilla    - Lemon    - Cherry )"});
            newPriceHashMap.put("Pop", new String[]{"3000", "", "(- Pepsi   - Diet pepsi   - 7up   - Diet 7up)"});
            newPriceHashMap.put("Perrier", new String[]{"3000", "", ""});
            newPriceHashMap.put("Redbull", new String[]{"6000", "", ""});
            newPriceHashMap.put("Spring water", new String[]{"1500", "", ""});
        }

        if (AppGlobals.getCurrentSelectedStore().equals("shawarma")) {


            ///////////////////////// Shawarma Bar Started //////////////////////

            newPriceHashMap.put("Chicky Shawarma", new String[]{"3500", "", "(Garlic, pickles, lettuce, fries)"});
            newPriceHashMap.put("Juicy Beef Shawarma", new String[]{"4000", "", "(Tarator, tomato, parsley, pickles, onion, fries)"});
            newPriceHashMap.put("Beef Shawarma Bar", new String[]{"4000", "", "(Tarator, tomato, parsley, pickles, onion and fries topped with our secret sauce)"});
            newPriceHashMap.put("Shawarma Bites", new String[]{"13000", "", "(Beef or chicken  - 10 pcs)"});
            newPriceHashMap.put("Shawarma Soujouk", new String[]{"4000", "", "(Tomato, parsley, pickles, onion and fries topped with our secret sauce)"});

            /// Our special combo
            newPriceHashMap.put("2 Sandwiches of your choice", new String[]{"9000", "", "(coleslow, fries,soft drink)"});
            newPriceHashMap.put("Chicken", new String[]{"15000", "", "(500g of shawarma with vegetables)"});
            newPriceHashMap.put("Beef", new String[]{"18000", "", "(500g of shawarma with vegetables)"});

            /// Soft  drinks
            newPriceHashMap.put("Pepsi", new String[]{"1000", "", ""});
            newPriceHashMap.put("Mirinda", new String[]{"1000", "", ""});
            newPriceHashMap.put("Seven up", new String[]{"1000", "", ""});
            newPriceHashMap.put("Water small", new String[]{"750", "", ""});


            ///////////////////////// Shawarma Bar End //////////////////////
        }
        if (AppGlobals.getCurrentSelectedStore().equals("snack_charboul")) {

            /////////////////////////  Snak charboul Started //////////////////////

            newPriceHashMap.put("Lahme", new String[]{"5000", "", ""});
            newPriceHashMap.put("Tawouk", new String[]{"4000", "", ""});
            newPriceHashMap.put("Kabab", new String[]{"4000", "", ""});
            newPriceHashMap.put("Tawouk Special", new String[]{"5000", "", ""});
            newPriceHashMap.put("Steak", new String[]{"4000", "", ""});
            newPriceHashMap.put("Steak Special", new String[]{"5000", "", ""});
            newPriceHashMap.put("Asabit Djej", new String[]{"4000", "", ""});
            newPriceHashMap.put("Makanek", new String[]{"4000", "", ""});
            newPriceHashMap.put("Soujouk", new String[]{"4000", "", ""});
            newPriceHashMap.put("Hamburger", new String[]{"4000", "", ""});
            newPriceHashMap.put("Cheese Burger", new String[]{"5000", "", ""});
            newPriceHashMap.put("Chicken Burger", new String[]{"4000", "", ""});
            newPriceHashMap.put("Fajita", new String[]{"5000", "", ""});
            newPriceHashMap.put("Philadelphia", new String[]{"5000", "", ""});
            newPriceHashMap.put("Rosto", new String[]{"4000", "", ""});
            newPriceHashMap.put("Hot Dog", new String[]{"4000", "", ""});
            newPriceHashMap.put("Hot Dog Special", new String[]{"5000", "", ""});
            newPriceHashMap.put("Nkha3at", new String[]{"5000", "", ""});
            newPriceHashMap.put("Sanesil", new String[]{"5000", "", ""});
            newPriceHashMap.put("Batata", new String[]{"3000", "", ""});
            newPriceHashMap.put("Kafta Naye", new String[]{"4000", "", ""});
            newPriceHashMap.put("Habra Naye", new String[]{"4000", "", ""});
            newPriceHashMap.put("Shawarma Lahme", new String[]{"4000", "", ""});
            newPriceHashMap.put("Shawarma  Djej", new String[]{"4000", "", ""});
            newPriceHashMap.put("Lsenet", new String[]{"5000", "", ""});
            newPriceHashMap.put("Djej", new String[]{"4000", "", ""});

            /// PLATES:
            newPriceHashMap.put("Lahme Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Tawouk Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Kabab Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Tawouk Special Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Steak Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Steak Special Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Asabit Djej Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Makanek Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Soujouk Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Hamburger Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Cheese Burger Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Chicken Burger Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Fajita Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Philadelphia Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Rosto Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Hot Dog Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Hot Dog Special Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Nkha3at Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Sanesil Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Batata Plate", new String[]{"5000", "", ""});
            newPriceHashMap.put("Kafte Naye Plate", new String[]{"5000", "", ""});
            newPriceHashMap.put("Habra Naye Plate", new String[]{"5000", "", ""});
            newPriceHashMap.put("Shawarma Lahme Plate", new String[]{"6000", "", ""});
            newPriceHashMap.put("Shawarma Djej Plate", new String[]{"4000", "", ""});
            newPriceHashMap.put("Lsenet Plate", new String[]{"7000", "", ""});
            newPriceHashMap.put("Djej Plate", new String[]{"6000", "", ""});

            // COLD   BEVERAGES :
            newPriceHashMap.put("Water Small", new String[]{"500", "", ""});
            newPriceHashMap.put("Water Big", new String[]{"1000", "", ""});
            newPriceHashMap.put("Pepsi", new String[]{"1000", "", ""});
            newPriceHashMap.put("Mirinda", new String[]{"1000", "", ""});
            newPriceHashMap.put("7-Up", new String[]{"1000", "", ""});
            newPriceHashMap.put("Beer", new String[]{"2000", "", ""});

            /////////////////////////  Snak charboul End //////////////////////
        }

        if (AppGlobals.getCurrentSelectedStore().equals("croissant")) {

            //////////////////////// start croissants  ///////////////////////

            // croissants

            newPriceHashMap.put("Chocolate", new String[]{"3000", "", ""});
            newPriceHashMap.put("Knéfé", new String[]{"4000", "", ""});
            newPriceHashMap.put("Zaatar", new String[]{"3000", "", ""});
            newPriceHashMap.put("Zaatar Special", new String[]{"3500", "", ""});
            newPriceHashMap.put("Olive & Mint", new String[]{"3000", "", ""});
            newPriceHashMap.put("Olive & Mint Special", new String[]{"3500", "", ""});
            newPriceHashMap.put("Spicy Cheese", new String[]{"3500", "", ""});
            newPriceHashMap.put("Spicy Cheese Special", new String[]{"3500", "", ""});
            newPriceHashMap.put("Cheese & Ham", new String[]{"3500", "", ""});
            newPriceHashMap.put("Cheese & Ham Special", new String[]{"4000", "", ""});
            newPriceHashMap.put("12  Small  Croissant   of any kind", new String[]{"10000", "", ""});

            //cake & dozens
            newPriceHashMap.put("Tarte", new String[]{"10000", "", ""});
            newPriceHashMap.put("Éclair", new String[]{"10000", "", ""});
            newPriceHashMap.put("Chocolate Cream", new String[]{"10000", "", ""});

            // cake pieces
            newPriceHashMap.put("Baba au Rhum", new String[]{"3000", "", ""});
            newPriceHashMap.put("Baba au Rhum Special", new String[]{"4000", "", ""});
            newPriceHashMap.put("Éclair", new String[]{"3000", "", ""});
            newPriceHashMap.put("Éclair Special", new String[]{"4000", "", ""});
            newPriceHashMap.put("Tarte aux Fruits", new String[]{"3000", "", ""});
            newPriceHashMap.put("Sablé Chocolat", new String[]{"3000", "", ""});
            newPriceHashMap.put("Sablé Jam", new String[]{"3000", "", ""});
            newPriceHashMap.put("Mille Feuilles", new String[]{"3000", "", ""});
            newPriceHashMap.put("Boule Chocolat Coco", new String[]{"3000", "", ""});
            newPriceHashMap.put("Boule Chocolat Almond", new String[]{"3000", "", ""});
            newPriceHashMap.put("Cheese Cake", new String[]{"3000", "", ""});
            newPriceHashMap.put("Swiss Roll", new String[]{"3000", "", ""});

            //fresh juice
            newPriceHashMap.put("Orange", new String[]{"3000", "", ""});
            newPriceHashMap.put("Grape Fruit", new String[]{"4000", "", ""});
            newPriceHashMap.put("Carrot", new String[]{"3000", "", ""});
            newPriceHashMap.put("Melon", new String[]{"5000", "", ""});
            newPriceHashMap.put("Avocado", new String[]{"7000", "", ""});
            newPriceHashMap.put("Apple", new String[]{"5000", "", ""});
            newPriceHashMap.put("Strawberry", new String[]{"5000", "", ""});
            newPriceHashMap.put("Lemonade", new String[]{"4000", "", ""});
            newPriceHashMap.put("Banana With Milk", new String[]{"6000", "", ""});
            newPriceHashMap.put("Kiwi, Strawberry & Lemon", new String[]{"6000", "", ""});
            newPriceHashMap.put("Polo", new String[]{"5000", "", ""});
            newPriceHashMap.put("Cocktail Pieces", new String[]{"6000", "", ""});
            newPriceHashMap.put("Cocktail Juice", new String[]{"6000", "", ""});

            // cold beverages

            newPriceHashMap.put("Pepsi", new String[]{"2000", "", ""});
            newPriceHashMap.put("7-Up", new String[]{"2000", "", ""});
            newPriceHashMap.put("Mirinda", new String[]{"2000", "", ""});
            newPriceHashMap.put("Mexican Beer", new String[]{"5000", "", ""});
            newPriceHashMap.put("Beer", new String[]{"4000", "", ""});
            newPriceHashMap.put("AMP Energy", new String[]{"2000", "", ""});
            newPriceHashMap.put("H2OH", new String[]{"1000", "", ""});
            newPriceHashMap.put("Water (small)", new String[]{"1000", "", ""});
            newPriceHashMap.put("Water (big)", new String[]{"2000", "", ""});
            newPriceHashMap.put("Nuts", new String[]{"2000", "", ""});
            newPriceHashMap.put("Chips", new String[]{"2000", "", ""});

            //fresh mocktails

            newPriceHashMap.put("Strawberry Freez", new String[]{"6000", "", "(Strawberry puree, water, fresh strawberry, ice)"});
            newPriceHashMap.put("Mango Freez", new String[]{"6000", "", "(Mango puree, water, fresh mango slice, ice)"});
            newPriceHashMap.put("Mango Orange Freez", new String[]{"6000", "", "(Mango puree, orange juice, ice, bloody orange syrup)"});
            newPriceHashMap.put("Peach Freez", new String[]{"6000", "", "(Peach  Puree, water, ice,  fresh peach  slice)"});
            newPriceHashMap.put("Tropical Smoothie", new String[]{"6000", "", "(Peach puree, water, ice,  mango puree, strawberry puree)"});
            newPriceHashMap.put("Survivor Smoothie", new String[]{"6000", "", "(Mango puree, water,  ice, pineapple slice,  passion  fruit puree,  coconut  puree)"});
            newPriceHashMap.put("Hulk", new String[]{"6000", "", "(Apple puree, peach puree, lemon, kiwi   puree,  water, ice)"});
            newPriceHashMap.put("Passion Fruit Reez", new String[]{"6000", "", "(Passion  puree,  water, ice)"});
            newPriceHashMap.put("Be Cool", new String[]{"6000", "", "(Strawberry puree, passion  fruit  puree, banana  puree)"});
            newPriceHashMap.put("Italian Lemonade", new String[]{"6000", "", "(Sweet & Sour mix, 7-up,  fresh lemon,  strawberry puree, basil  syrup, ice)"});
            newPriceHashMap.put("Blue Hawaiian Lemonade", new String[]{"6000", "", "(Sweet  &  Sour  mix, 7-Up,  fresh lemon,  coconut puree, blue  curacao  syrup,  ice)"});
            newPriceHashMap.put("Pink Lemonade", new String[]{"6000", "", "(Sweet & Sour mix,  7-Up, fresh lemon,  pink grapefruit  syrup,  grenadine  syrup, ice)"});
            newPriceHashMap.put("Frozen Bloody Orange", new String[]{"6000", "", "(Fresh  orange  juice,  grapefruit  syrup,  bloody  orange  syrup, ice)"});
            newPriceHashMap.put("Orange Mango Smoothie", new String[]{"6000", "", "(Mango puree, orange juice,  ice)"});
            newPriceHashMap.put("Kiwi", new String[]{"6000", "", "(Kiwi  puree,  banana puree,  orange  juice,  ice)"});
            newPriceHashMap.put("Bubble Gum", new String[]{"6000", "", "(Bubble  gum  syrup,  cranberry  juice,  lemon  juice, ice)"});
            newPriceHashMap.put("Virgin Colada", new String[]{"6000", "", "(Coconut  puree,  pineapple  juice,  blue  curacao,  ice)"});


            //frozen shake

            newPriceHashMap.put("Coconut Shake", new String[]{"6000", "", "(coconut  puree, milk,  ice)"});
            newPriceHashMap.put("Melon Shake", new String[]{"6000", "", "(Melon, milk, ice)"});
            newPriceHashMap.put("Strawberry Shake", new String[]{"6000", "", "(Strawberry  puree, milk,  vanilla powder, ice)"});
            newPriceHashMap.put("Vanilla Shake", new String[]{"6000", "", "(Vanilla  syrup,  vanilla  powder,  milk,  ice)"});
            newPriceHashMap.put("Minted Iced Chocolate", new String[]{"6000", "", "(Organic   sweet  chocolate  powder,  dark  chocolate  sauce, green mint  syrup,  vanilla  powder,  ice)"});
            newPriceHashMap.put("Choco Oreo Shake", new String[]{"6000", "", "(Milk,  chocolate  cookies  syrup, oreo  cookies,  white  chocolate powder, ice)"});
            newPriceHashMap.put("Banana Berry Shake", new String[]{"6000", "", "(Milk,  red berries  puree, banana  puree, vanilla  powder,  fresh  banana, ice)"});
            newPriceHashMap.put("Chocolate Milk Shake", new String[]{"6000", "", "(Milk, chocolate  flavor, dark  chocolate  powder, ice)"});
            newPriceHashMap.put("Snickers Milk Shake", new String[]{"6000", "", "(Milk, dark  chocolate, peanut  butter,  mocha  powder,  caramel  sauce, ice)"});
            newPriceHashMap.put("Black & White", new String[]{"6000", "", "(Milk  , one scoop vanilla ice cream , lemon slice, vanilla powder, chocolate sauce, coffee beans, pineapple  juice)"});
            newPriceHashMap.put("Bubble Gum Shake", new String[]{"6000", "", "(Bubble  gum  ice  cream, bubble  gym  syrup,  milk, water, ice)"});

            // icecream

            newPriceHashMap.put("Chocolat Mou", new String[]{"6000", "", ""});
            newPriceHashMap.put("Chikita", new String[]{"6000", "", "(3 scoops  ice cream , fruit  salad, whipped  cream)"});
            newPriceHashMap.put("Banana Split", new String[]{"6000", "", "(Banana  slices, fruits  slices,, whipped  cream, 3 scoops  ice cream)"});

            // ICED   COFFEE   DRINKS

            newPriceHashMap.put("Coffee Frappe", new String[]{"5000", "", "(Espresso, sugar, ice)"});
            newPriceHashMap.put("Nescafe Frappe", new String[]{"5000", "", ""});
            newPriceHashMap.put("Cappuccino Frappe", new String[]{"5000", "", "(Espresso,  milk,  original  mocafé  powder, ice)"});
            newPriceHashMap.put("Caramel Frappucino", new String[]{"5000", "", "(Milk, caramel   sauce,  original  mocafé  powder, ice)"});
            newPriceHashMap.put("Toffee Banana Mocha", new String[]{"5000", "", "(Milk,  banana  puree,  toffee  nuts  syrup,  mocha  powder,  ice)"});
            newPriceHashMap.put("Frozen Tiramisu", new String[]{"5000", "", "(Espresso, milk,   white  chocolate  powder,  tiramisu  syrup,  ice)"});
            newPriceHashMap.put("After Eight Mocha", new String[]{"5000", "", "(Mint  syrup,  mocafé,  milk, ice)"});
            newPriceHashMap.put("Long Island Frappe", new String[]{"5000", "", "(Coconut  syrup  or puree, mocafé, milk, ice)"});
            newPriceHashMap.put("Belgian Fever", new String[]{"5000", "", "(Speculoos  syrup, mocafé, milk, ice)"});
            newPriceHashMap.put("Crunchy", new String[]{"5000", "", "(Milk, vanilla  powder, cookies,  whipped  cream, ice)"});

            // hot beverages

            newPriceHashMap.put("Turkish Coffee", new String[]{"4000", "5000", "(2 cups)  4000 L.L\n  ( 3 cups )  5000 L.L\n"});
            newPriceHashMap.put("Nescafe", new String[]{"3000", "", ""});
            newPriceHashMap.put("Doppio", new String[]{"4000", "", "(Espresso  double)"});
            newPriceHashMap.put("Cappuccino", new String[]{"3000", "", ""});
            newPriceHashMap.put("Flavored Latté", new String[]{"5000", "", "(Espresso, milk,  dark  chocolate  sauce)"});
            newPriceHashMap.put("Mochaccino", new String[]{"5000", "", "(Espresso, milk, dark  chocolate  sauce)"});
            newPriceHashMap.put("Caramel Macchiato", new String[]{"5000", "", "(Espresso, milk, caramel  sauce)"});
            newPriceHashMap.put("Tiramisu Latté", new String[]{"5000", "", "(Espresso, milk,  tiramisu  syrup)"});
            newPriceHashMap.put("Chocolate Cookies Latté", new String[]{"5000", "", "(Espresso, milk,  chocolate  cookies  syrup)"});
            newPriceHashMap.put("Belgian Latté", new String[]{"5000", "", "(Espresso, milk, caramel  sauce, cookies  syrup)"});
            newPriceHashMap.put("Crusty Mocha", new String[]{"5000", "", "(Espresso, milk,  chocolate  sauce,  hazelnut  syrup)"});
            newPriceHashMap.put("Flavored Hot Chocolate", new String[]{"5000", "", "(Chocolate, milk, hazelnut  flavor)"});
            newPriceHashMap.put("Flavored Hot Chocolate", new String[]{"5000", "", "(Chocolate, milk,  vanilla  flavor)"});
            newPriceHashMap.put("Tea With Milk", new String[]{"4000", "", ""});


            /////////////////////////////////////////// end croissants ////////////////////

            /////////////////////////////////////////// Z burger house STARTED ////////////////

            newPriceHashMap.put("Classic Sandwich", new String[]{"6000", "", "(Grilled beef patty with lettuce ,Tomato, sliced onions , pickles ketchup & mustard)"});
            newPriceHashMap.put("Classic Combo", new String[]{"11000", "", "(Grilled beef patty with lettuce ,Tomato, sliced onions , pickles ketchup & mustard)"});
            newPriceHashMap.put("Cheese burger Sandwich", new String[]{"6500", "", "(Grilled beef patty with cheddar cheese, Lettuce, Tomato , sliced onions ,pickles ,Ketchup & mustard )"});
            newPriceHashMap.put("Cheese burger Combo:", new String[]{"11500", "", "(Grilled beef patty with cheddar cheese, Lettuce, Tomato , sliced onions ,pickles ,Ketchup & mustard )"});
            newPriceHashMap.put("Biggy house burger Sandwich:", new String[]{"9000", "", "(double grilled beef patty with double cheddar cheese , lettuce , Tomato , onion , pickles & special Biggy house sauce)"});
            newPriceHashMap.put("Biggy house burger Combo", new String[]{":14000", "", "(double grilled beef patty with double cheddar cheese , lettuce , Tomato , onion , pickles & special Biggy house sauce)"});
            newPriceHashMap.put("BBq pepperoni Sandwich", new String[]{"8500", "", "(Grilled beef patty  with cheddar cheese , crispy pepperoni , fresh mushrooms , lettuce , tomato , pickles & bbq sauce)"});
            newPriceHashMap.put("BBq pepperoni Combo", new String[]{"13500", "", "(Grilled beef patty  with cheddar cheese , crispy pepperoni , fresh mushrooms , lettuce , tomato , pickles & bbq sauce)"});
            newPriceHashMap.put("Z Lebanese Burger Sandwich", new String[]{"6000", "", "(Grilled beef patty , coleslaw , tomato , pickles , onion, fries, grilled bread, Z sauce)"});
            newPriceHashMap.put("Z Lebanese Burger Combo", new String[]{":11000", "", "(Grilled beef patty , coleslaw , tomato , pickles , onion, fries, grilled bread, Z sauce)"});
            newPriceHashMap.put("Steak burger Sandwich", new String[]{"9000", "", "200G of grilled steak filet, lettuce, tomato, onions , Z sauce ,  Fresh Mushroom)"});
            newPriceHashMap.put("Steak burger Combo", new String[]{"14000", "", "200G of grilled steak filet, lettuce, tomato, onions , Z sauce ,  Fresh Mushroom)"});
            newPriceHashMap.put("Deluxe burger Sandwich", new String[]{"8000", "", "(Grilled beef patty with cheddar cheese, fried onion rings , lettuce , pickles and sweet onion rings )"});
            newPriceHashMap.put("Deluxe burger Combo", new String[]{"13000", "", "(Grilled beef patty with cheddar cheese, fried onion rings , lettuce , pickles and sweet onion rings )"});
            newPriceHashMap.put("Chili cheese burger Sandwich", new String[]{"7000", "", "(grilled beef patty with cheddar cheese , lettuce, grilled green pepper & onions, pickles & spicy sauce with caramelized onions )"});
            newPriceHashMap.put("Chili cheese burger Combo", new String[]{"12000", "", "(grilled beef patty with cheddar cheese , lettuce, grilled green pepper & onions, pickles & spicy sauce with caramelized onions )"});
            newPriceHashMap.put("Bacon house Sandwich", new String[]{"8500", "", "(Grilled beef patty with cheddar cheese , crispy bacon , avocado slices , Lettuce, Pickles  & Mild burger house sauce )"});
            newPriceHashMap.put("Bacon house Combo", new String[]{"13500", "", "(Grilled beef patty with cheddar cheese , crispy bacon , avocado slices , Lettuce, Pickles  & Mild burger house sauce )"});
            newPriceHashMap.put("Mozzarella beef burger Sandwich", new String[]{"9000", "", "(Grilled beef patty with breaded froed Mozzarella cheese , lettuce , tomato , pickles & burger house sauce )"});
            newPriceHashMap.put("Mozzarella beef burger Combo", new String[]{"14000", "", "(Grilled beef patty with breaded froed Mozzarella cheese , lettuce , tomato , pickles & burger house sauce )"});
            newPriceHashMap.put("Swiss mushroom Sandwich", new String[]{"8000", "", "(Grilled beef patty with melted Swiss cheese & creamy mushroom sauce )"});
            newPriceHashMap.put("Swiss mushroom Combo", new String[]{"13000", "", "(Grilled beef patty with melted Swiss cheese & creamy mushroom sauce )"});
            newPriceHashMap.put("Sushi burger Sandwich", new String[]{"9500", "", "(Avocado, crab , wasabi sauce , sweet corn ,rice , special sauce )"});
            newPriceHashMap.put("Sushi burger Combo", new String[]{"14500", "", "(Avocado, crab , wasabi sauce , sweet corn ,rice , special sauce )"});
            newPriceHashMap.put("Z angus house Sandwich", new String[]{"9000", "", "(180 G of angus beef , lettuce , pickles , Tomato , Z sauce )"});
            newPriceHashMap.put("Z angus house Combo", new String[]{"14000", "", "(180 G of angus beef , lettuce , pickles , Tomato , Z sauce )"});
            newPriceHashMap.put("Cheese at heart burger Sandwich", new String[]{"9000", "", "(180 G  Meat filled with cheese , Lettuce , tomato , pickles , cheddar slice , crispy nachos )"});
            newPriceHashMap.put("Cheese at heart burger Combo", new String[]{"14000", "", "(180 G  Meat filled with cheese , Lettuce , tomato , pickles , cheddar slice , crispy nachos )"});
            newPriceHashMap.put("Mexican house Sandwich", new String[]{"9000", "", "(180 G of Meat filled with Mexican spices & sauces , tomato , lettuce ,pickles )"});
            newPriceHashMap.put("Mexican house Combo", new String[]{"14000", "", "(180 G of Meat filled with Mexican spices & sauces , tomato , lettuce ,pickles )"});
            newPriceHashMap.put("Soujouk burger Sandwich", new String[]{"7500", "", "(120 G of grilled soujouk beef, lettuce , tomato, pickles , mayo, z sauce)"});
            newPriceHashMap.put("Soujouk burger Combo", new String[]{"12500", "", "(120 G of grilled soujouk beef, lettuce , tomato, pickles , mayo, z sauce)"});
            newPriceHashMap.put("Veggie burger Sandwich", new String[]{"6500", "", "(lettuce, tomato, onions, fried mozzarella veggie , pickles , veggie sauce)"});
            newPriceHashMap.put("Veggie burger Combo", new String[]{"11500", "", "(lettuce, tomato, onions, fried mozzarella veggie , pickles , veggie sauce)"});
            newPriceHashMap.put("Kids meal", new String[]{"9000", "", "(2 mini burgers beef or chicken , French fries , orange juice or soft drink )"});
            newPriceHashMap.put("Minions", new String[]{"11000", "", "(3 mini burgers , French fries )"});

            /// APPETIZERS
            newPriceHashMap.put("Fries basket", new String[]{"4000", "", "(extra crispy French fries)"});
            newPriceHashMap.put("Cheese fries basket", new String[]{"6000", "", "(extra crispy French fries with cheddar)"});
            newPriceHashMap.put("curly fries  basket", new String[]{"6500", "", "(extra crispy French fries with cheddar)"});
            newPriceHashMap.put("baked potatoes", new String[]{"7500", "", "(2 pieces  of baked potato served with sauces  and cheddar  cheese upon request )"});
            newPriceHashMap.put("Potato wedges basket", new String[]{"6000", "", "(extra crispy wedges served  with dipping sauce as requested )"});
            newPriceHashMap.put("Mozzarella sticks", new String[]{"6000", "", "(breaded mozzarella , served with fries & Z sauce)"});
            newPriceHashMap.put("Cheddar balls", new String[]{"6000", "", "(Breaded cheddar , served with fries & z sauce )"});
            newPriceHashMap.put("crispy crab or calamar", new String[]{"7500", "", "(fried crispy crab  or calamar served with fries and z sauce )"});
            newPriceHashMap.put("chicken tenders", new String[]{"8500", "", "(marinated strips of 100% chicken breast served with optional sauce )"});
            newPriceHashMap.put("chicken tenders grilled", new String[]{"8500", "", "(grilled strips 100% chicken breast served with optional sauce)"});
            newPriceHashMap.put("Chicken tenders dipped", new String[]{"9500", "", "(dipped marinated strips of 100% chicken breast served with optional sauce)"});
            newPriceHashMap.put("special crispy wings", new String[]{"9000", "", "(extra crispy wings  served with mixed or dipping sauces as requested sauces available : bbq- buffalo – blue cheese – honey mustard – thousand island – sweet chilli )"});
            newPriceHashMap.put("appetizers combo", new String[]{"15000", "", "(cheddar balls, crispy wings, crispy crab, crispy calamar, mozzarella sticks, potato wedges , coleslaw)"});

            //// SALADS:
            newPriceHashMap.put("Season salad", new String[]{"8500", "", "(iceberg lettuce, rocca, grated carrots, sliced black olives, cucumber , tomato & sweet corn )"});
            newPriceHashMap.put("Halloumi salad", new String[]{"9000", "", "(grilled halloum with iceberg  lettuce , rocca, cucumber, tomato, sliced black olive & fresh mint pesto )"});
            newPriceHashMap.put("Greek salad", new String[]{"9000", "", "(iceberg lettuce , rocca , cucumber, tomato, sliced black olives , mint , green pepper & Feta cheese)"});
            newPriceHashMap.put("Tuna Pasta", new String[]{"9500", "", "(fusilli pasta , tuna ,  grated carrots, sweet corn, fresh mushrooms, olives & tomato )"});
            newPriceHashMap.put("Chef salad", new String[]{"10000", "", "(Smoked  ham with  emmental  cheese , iceberg  lettuce, cucumber, sweet  corn &  tomato)"});
            newPriceHashMap.put("Steak Salad", new String[]{"11000", "", "(Iceberg  lettuce  &  rocca  topped  with  grilled beef  filet, grilled onions  & tomato)"});
            newPriceHashMap.put("Crispy Chicken  Salad", new String[]{"10000", "", "(Crispy  chicken  breast, iceberg  lettuce, sweet  corn & tomato)"});
            newPriceHashMap.put("Chicken Caesar", new String[]{"10000", "", "(Iceberg  lettuce  topped  with  grilled chicken, parmesan  cheese,  Caesar  dressing  &  croutons)"});
            newPriceHashMap.put("Crab Salad", new String[]{"10500", "", "(Iceberg  lettuce, rocca, sweet corn, fresh mushrooms, fresh avocado, tomato & chredded crab )"});
            newPriceHashMap.put("Rocca Salad", new String[]{"9000", "", "(Iceberg  lettuce,  fresh mushrooms, tomato, parmesan cheese, balsamic  sauce)"});
            newPriceHashMap.put("Burger House Salad", new String[]{"11000", "", "(Iceberg  lettuce topped  with  grilled chicken , crispy bacon, shaved cheese, avocado, grated  carrots, sweet corn, fresh mushrooms & tomato)"});

            /// BURGER  (FRESH CHICKEN)
            newPriceHashMap.put("Chicken Breast Burger Sandwich", new String[]{"7000", "", "(Grilled chicken breast, lettuce, pickles, garlic, fries)"});
            newPriceHashMap.put("Chicken Breast Burger Combo", new String[]{"12000", "", "(Grilled chicken breast, lettuce, pickles, garlic, fries)"});
            newPriceHashMap.put("Med Burger Sandwich", new String[]{"7500", "", "(Marinated grilled  chicken breast in fresh coriander, fresh garlic, lettuce, pickles, med sauce)"});
            newPriceHashMap.put("Med Burger Combo", new String[]{"12500", "", "(Marinated grilled  chicken breast in fresh coriander, fresh garlic, lettuce, pickles, med sauce)"});
            newPriceHashMap.put("Crispy Chicken Burger Sandwich", new String[]{"13000", "", "(Crispy chicken patty with cheddar cheese, lettuce, pickles, biggy sauce)"});
            newPriceHashMap.put("Crispy Chicken Burger Combo", new String[]{"8000", "", "(Crispy chicken patty with cheddar cheese, lettuce, pickles, biggy sauce)"});
            newPriceHashMap.put("Crunchy Chicken Fillet Sandwich", new String[]{"8000", "", "(Crunchy  chicken  fillet, cheddar  cheese, lettuce, pickles, spicy bbq sauce)"});
            newPriceHashMap.put("Crunchy Chicken Fillet Combo", new String[]{"13000", "", "(Crunchy  chicken  fillet, cheddar  cheese, lettuce, pickles, spicy bbq sauce)"});

            //// FISH
            newPriceHashMap.put("Crunchy Fishy Fish Sandwich", new String[]{"7000", "", "(Deep fried fish, cheddar cheese, lettuce, pickles, house tartar sauce)"});
            newPriceHashMap.put("Crunchy Fishy Fish Combo", new String[]{"12000", "", "(Deep fried fish, cheddar cheese, lettuce, pickles, house tartar sauce)"});
            newPriceHashMap.put("Fried Fishy Burger Sandwich", new String[]{"7000", "", "(Sauce tartar, lettuce, tomato, mustard, fried fish)"});
            newPriceHashMap.put("Fried Fishy Burger Combo", new String[]{"12000", "", "(Sauce tartar, lettuce, tomato, mustard, fried fish)"});
            newPriceHashMap.put("Grilled Fishy Burger Sandwich", new String[]{"6500", "", "(Lettuce, garlic, pickles, fried potato, grilled fish)"});
            newPriceHashMap.put("Grilled Fishy Burger Combo", new String[]{"11500", "", "(Lettuce, garlic, pickles, fried potato, grilled fish)"});

            ///////////////////////////////////  Z burger House End //////////////////////////////

            newPriceHashMap.put("", new String[]{"", "", ""});
            newPriceHashMap.put("", new String[]{"", "", ""});
            newPriceHashMap.put("", new String[]{"", "", ""});
            newPriceHashMap.put("", new String[]{"", "", ""});
            newPriceHashMap.put("", new String[]{"", "", ""});
            newPriceHashMap.put("", new String[]{"", "", ""});
            newPriceHashMap.put("", new String[]{"", "", ""});
            newPriceHashMap.put("", new String[]{"", "", ""});
            newPriceHashMap.put("", new String[]{"", "", ""});
            newPriceHashMap.put("", new String[]{"", "", ""});
            newPriceHashMap.put("", new String[]{"", "", ""});
            newPriceHashMap.put("", new String[]{"", "", ""});
            newPriceHashMap.put("", new String[]{"", "", ""});
            newPriceHashMap.put("", new String[]{"", "", ""});
            newPriceHashMap.put("", new String[]{"", "", ""});
            newPriceHashMap.put("", new String[]{"", "", ""});
            newPriceHashMap.put("", new String[]{"", "", ""});
        }

        if (AppGlobals.getCurrentSelectedStore().equals("dagher")) {
            newPriceHashMap.put("Samak", new String[]{"4000", "", ""});
            newPriceHashMap.put("Chich Tawouk", new String[]{"5000", "", ""});
            newPriceHashMap.put("Kabab", new String[]{"5000", "", ""});
            newPriceHashMap.put("Lahme Che2af", new String[]{"5000", "", ""});
            newPriceHashMap.put("2asabit Djej", new String[]{"4000", "", ""});
            newPriceHashMap.put("Crab", new String[]{"4000", "", ""});
            newPriceHashMap.put("Sejou2", new String[]{"4000", "", ""});
            newPriceHashMap.put("Marquise", new String[]{"4000", "", ""});
            newPriceHashMap.put("Hot Dog", new String[]{"4000", "", ""});
            newPriceHashMap.put("Hamburger", new String[]{"4000", "", ""});
            newPriceHashMap.put("Cheese Burger", new String[]{"5000", "", ""});
            newPriceHashMap.put("Chicken Burger", new String[]{"5000", "", ""});
            newPriceHashMap.put("Potato", new String[]{"3000", "", ""});
            newPriceHashMap.put("Machewe Mchakal (1 kilo)", new String[]{"35000", "", ""});
            newPriceHashMap.put("Sa7en Machewe (small)", new String[]{"6000", "", ""});
            newPriceHashMap.put("Sa7en Machewe (large)", new String[]{"12000", "", ""});
            newPriceHashMap.put("Potato (big)", new String[]{"5000", "", ""});
            newPriceHashMap.put("Shawarma Lahme", new String[]{"5000", "", ""});
            newPriceHashMap.put("Shawarma Djej", new String[]{"5000", "", ""});
            newPriceHashMap.put("Saniyit Shawarma Lahme (big)", new String[]{"30000", "", ""});
            newPriceHashMap.put("Saniyit Shawarma Lahme (small)", new String[]{"15000", "", ""});
            newPriceHashMap.put("Saniyit Shawarma Djej (big)", new String[]{"30000", "", ""});
            newPriceHashMap.put("Saniyit Shawarma Djej (small)", new String[]{"15000", "", ""});
            newPriceHashMap.put("Sandwich Falefil", new String[]{"3000", "", ""});
            newPriceHashMap.put("Saniyit Falefil (big)", new String[]{"10000", "", ""});
            newPriceHashMap.put("Saniyit Falefil (small)", new String[]{"5000", "", ""});
            newPriceHashMap.put("Broasted", new String[]{"17000", "", ""});
            newPriceHashMap.put("Broasted (sa7en)", new String[]{"10000", "", ""});
            newPriceHashMap.put("Broasted Fish (1 kilo)", new String[]{"17000", "", ""});
            newPriceHashMap.put("Broasted Fish (sa7en)", new String[]{"10000", "", ""});
            newPriceHashMap.put("Pepis", new String[]{"1000", "", ""});
            newPriceHashMap.put("7-UP", new String[]{"1000", "", ""});
            newPriceHashMap.put("Mirinda", new String[]{"1000", "", ""});
            newPriceHashMap.put("Water (big)", new String[]{"1000", "", ""});
            newPriceHashMap.put("Water (small)", new String[]{"500", "", ""});
            newPriceHashMap.put("Laban 3iran", new String[]{"1000", "", ""});
        }
    }

    private void prepareListDataForDipnDip() {
        listDataHeaderForDipNdip = new ArrayList<>();
        listDataChildForDipNdip = new HashMap<>();

        // Adding child data Headers
        listDataHeaderForDipNdip.add("Crêpe");
        listDataHeaderForDipNdip.add("Waffle");
        listDataHeaderForDipNdip.add("Pancake");
        listDataHeaderForDipNdip.add("Chocolate rich");
        listDataHeaderForDipNdip.add("Baked goods");
        listDataHeaderForDipNdip.add("Ice cream");
        listDataHeaderForDipNdip.add("Chocolate shot");
        listDataHeaderForDipNdip.add("Fried yummies");
        listDataHeaderForDipNdip.add("Stuff in a cup");
        listDataHeaderForDipNdip.add("Dip Sticks");
        listDataHeaderForDipNdip.add("Dip n dip mania");
        listDataHeaderForDipNdip.add("COLD DRINKS");
        listDataHeaderForDipNdip.add("Milk shakes");
        listDataHeaderForDipNdip.add("Frappe");
        listDataHeaderForDipNdip.add("Smoothies");
        listDataHeaderForDipNdip.add("Ice tea shakes");
        listDataHeaderForDipNdip.add("Soft  drinks");

        List<String> crepe = new ArrayList<>();
        crepe.add("Dip n dip crêpe");
        crepe.add("Dip n dip crêpe with scoop");
        crepe.add("Fettuccini  crêpe full");
        crepe.add("Fettuccini  crêpe small");
        crepe.add("Cinnamon crêpe pouch");
        crepe.add("Tripple chocolate crêpe");
        crepe.add("Cookies crêpe");
        crepe.add("Mallow crêpe");
        crepe.add("Brownies crêpe");
        crepe.add("Krispy crêpe");
        crepe.add("Banana wrap");

        /// Waffle
        List<String> waffle = new ArrayList<>();
        waffle.add("Dip n dip waffle");
        waffle.add("Dip n dip waffle with ice cream");
        waffle.add("Chocolate waffle");
        waffle.add("Waffle stick");

        // pancake
        List<String> pancake = new ArrayList<>();
        pancake.add("Pancake mini tower");

        // Chocolate rich
        List<String> chocolateRich = new ArrayList<>();
        chocolateRich.add("Fondant");
        chocolateRich.add("Molten cake");
        chocolateRich.add("Brownies");
        chocolateRich.add("Dip n dip pizza");
        chocolateRich.add("Chocolate mousse");
        chocolateRich.add("Trois chocolat mousse");
        chocolateRich.add("Succès");

        /// Baked goods:
        List<String> bakedGoods = new ArrayList<>();

        bakedGoods.add("Mini muffin");
        bakedGoods.add("Cookies");
        bakedGoods.add("Dip n Dip éclair pyramid");
        bakedGoods.add("Éclair Kebab");
        bakedGoods.add("Profiterole");

        /// Ice cream:
        List<String> iceCream = new ArrayList<>();
        iceCream.add("Scoop");
        iceCream.add("scoop Tapped with chocolate");
        iceCream.add("Crunchy ice cream");

        /// Chocolate shot

        List<String> chocolateShot = new ArrayList<>();
        chocolateShot.add("Chocolate Shot");
        chocolateShot.add("Chocolate 1kg");

        //Fried yummies
        List<String> friedYammies = new ArrayList<>();
        friedYammies.add("Pain Perdu");
        friedYammies.add("Cheese cake nuggets");

        /// Stuff in a cup
        List<String> stuffInAcup = new ArrayList<>();
        stuffInAcup.add("Dip Crispies");
        stuffInAcup.add("Fruits In a cup");
        stuffInAcup.add("Brownies in a cup");

        ////  DIp sticks
        List<String> dipSticks = new ArrayList<>();
        dipSticks.add("Dip Sticks 4 pieces");
        dipSticks.add("Dip Sticks 8 pieces");
        dipSticks.add("Dip stick platter");

        // Dip n Dip Mania
        List<String> dipNdipMania = new ArrayList<>();
        dipNdipMania.add("Dip-able items (full plate)");
        dipNdipMania.add("Brownies, mini éclair");
        dipNdipMania.add("Ice cream (scoop)");
        dipNdipMania.add("Fondue");
        dipNdipMania.add("Whipped cream");

        /// cold drinks
        List<String> coldDrinks = new ArrayList<>();
        coldDrinks.add("Dip n dip freezy");

        /// MilkShakes
        List<String> milkShakes = new ArrayList<>();
        milkShakes.add("Chocolate milk shake");
        milkShakes.add("Vanilla milk shake");
        milkShakes.add("Strawberry  milk shake");
        milkShakes.add("Coffee shake");
        milkShakes.add("Banana milk shake");
        milkShakes.add("After eight milk shake");

        //// Frappe
        List<String> frappe = new ArrayList<>();
        frappe.add("Mocha frappe");
        frappe.add("White mocha frappe");
        frappe.add("Caramel frappe");
        frappe.add("Cookies frappe");
        frappe.add("Pink frappe");

        /// Smoothies
        List<String> smoothies = new ArrayList<>();
        smoothies.add("Creamy smoothies");
        smoothies.add("Smoothie float");
        smoothies.add("Oreo milk shake");
        smoothies.add("Affogato");

        /// Ice tea shakes
        List<String> iceTeaShakes = new ArrayList<>();
        iceTeaShakes.add("Ice tea shake");
        iceTeaShakes.add("Lemon tea shake");
        iceTeaShakes.add("Mint tea shake");
        iceTeaShakes.add("Peach tea shake");
        iceTeaShakes.add("Raspberry  tea shake");
        iceTeaShakes.add("Iced cappuccino");

        // Soft  drinks
        List<String> softDrinks = new ArrayList<>();
        softDrinks.add("Ice tea");
        softDrinks.add("Italian soda");
        softDrinks.add("Flavored cola");
        softDrinks.add("Pop");
        softDrinks.add("Perrier");
        softDrinks.add("Redbull");
        softDrinks.add("Spring water");


        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(0), crepe);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(1), waffle);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(2), pancake);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(3), chocolateRich);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(4), bakedGoods);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(5), iceCream);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(6), chocolateShot);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(7), friedYammies);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(8), stuffInAcup);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(9), dipSticks);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(10),dipNdipMania );
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(11), coldDrinks );
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(12), milkShakes );
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(13), frappe);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(14), smoothies);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(15), iceTeaShakes);
        listDataChildForDipNdip.put(listDataHeaderForDipNdip.get(16), softDrinks);
    }

    private void prepareListDataForBurgerHouse() {

        listDataHeaderForBurgerHouse = new ArrayList<>();
        listDataChildForBuregerHouse = new HashMap<>();

        listDataHeaderForBurgerHouse.add("The Authentic BURGER beef");
        listDataHeaderForBurgerHouse.add("APPETIZERS");
        listDataHeaderForBurgerHouse.add("SALADS");
        listDataHeaderForBurgerHouse.add("FISH");

        List<String> authenticBurgers = new ArrayList<>();
        authenticBurgers.add("Classic Sandwich");
        authenticBurgers.add("Classic Combo");
        authenticBurgers.add("Cheese burger Sandwich");
        authenticBurgers.add("Cheese burger Combo");
        authenticBurgers.add("Biggy house burger Sandwich");
        authenticBurgers.add("Biggy house burger Combo");
        authenticBurgers.add("BBq pepperoni Sandwich");
        authenticBurgers.add("BBq pepperoni Combo");
        authenticBurgers.add("Z Lebanese Burger Sandwich");
        authenticBurgers.add("Z Lebanese Burger Combo");
        authenticBurgers.add("Steak burger Sandwich");
        authenticBurgers.add("Steak burger Combo");
        authenticBurgers.add("Deluxe burger Sandwich");
        authenticBurgers.add("Deluxe burger Combo");
        authenticBurgers.add("Chili cheese burger Sandwich");
        authenticBurgers.add("Chili cheese burger Combo");
        authenticBurgers.add("Bacon house Sandwich");
        authenticBurgers.add("Bacon house Combo");
        authenticBurgers.add("Mozzarella beef burger Sandwich");
        authenticBurgers.add("Mozzarella beef burger Combo");
        authenticBurgers.add("Swiss mushroom Sandwich");
        authenticBurgers.add("Swiss mushroom Combo");
        authenticBurgers.add("Sushi burger Sandwich");
        authenticBurgers.add("Sushi burger Combo");
        authenticBurgers.add("Z angus house Sandwich");
        authenticBurgers.add("Z angus house Combo");
        authenticBurgers.add("Cheese at heart burger Sandwich");
        authenticBurgers.add("Cheese at heart burger Combo");
        authenticBurgers.add("Mexican house Sandwich");
        authenticBurgers.add("Mexican house Combo");
        authenticBurgers.add("Soujouk burger Sandwich");
        authenticBurgers.add("Soujouk burger Combo");
        authenticBurgers.add("Veggie burger Sandwich");
        authenticBurgers.add("Veggie burger Combo");
        authenticBurgers.add("Kids meal");
        authenticBurgers.add("Minions");

        /// APPETIZERS
        List<String> appitizers = new ArrayList<>();
        appitizers.add("Fries basket");
        appitizers.add("Cheese fries basket");
        appitizers.add("curly fries  basket");
        appitizers.add("baked potatoes");
        appitizers.add("Potato wedges basket");
        appitizers.add("Mozzarella sticks");
        appitizers.add("Cheddar balls");
        appitizers.add("crispy crab or calamar");
        appitizers.add("chicken tenders");
        appitizers.add("chicken tenders grilled");
        appitizers.add("Chicken tenders dipped");
        appitizers.add("special crispy wings");
        appitizers.add("appetizers combo");

        //// SALADS:
        List<String> salads = new ArrayList<>();
        salads.add("Season salad");
        salads.add("Halloumi salad");
        salads.add("Greek salad");
        salads.add("Tuna Pasta");
        salads.add("Chef salad");
        salads.add("Steak Salad");
        salads.add("Crispy Chicken  Salad");
        salads.add("Chicken Caesar");
        salads.add("Crab Salad");
        salads.add("Rocca Salad");
        salads.add("Burger House Salad");

        /// BURGER  (FRESH CHICKEN)
        List<String> freshChiksBurgers = new ArrayList<>();
        freshChiksBurgers.add("Chicken Breast Burger Sandwich");
        freshChiksBurgers.add("Chicken Breast Burger Combo");
        freshChiksBurgers.add("Med Burger Sandwich");
        freshChiksBurgers.add("Med Burger Combo");
        freshChiksBurgers.add("Crispy Chicken Burger Sandwich");
        freshChiksBurgers.add("Crispy Chicken Burger Combo");
        freshChiksBurgers.add("Crunchy Chicken Fillet Sandwich");
        freshChiksBurgers.add("Crunchy Chicken Fillet Combo");
        freshChiksBurgers.add("Crunchy Fishy Fish Sandwich");
        freshChiksBurgers.add("Crunchy Fishy Fish Combo");
        freshChiksBurgers.add("Fried Fishy Burger Sandwich");
        freshChiksBurgers.add("Fried Fishy Burger Combo");
        freshChiksBurgers.add("Grilled Fishy Burger Sandwich");
        freshChiksBurgers.add("Grilled Fishy Burger Combo");

        listDataChildForBuregerHouse.put(listDataHeaderForBurgerHouse.get(0), authenticBurgers); // Header, Child data
        listDataChildForBuregerHouse.put(listDataHeaderForBurgerHouse.get(1), appitizers);
        listDataChildForBuregerHouse.put(listDataHeaderForBurgerHouse.get(2), salads);
        listDataChildForBuregerHouse.put(listDataHeaderForBurgerHouse.get(3), freshChiksBurgers);
    }
    private void prepareListDataForShawarmaBar() {
        listDataHeaderForshawarmaBar = new ArrayList<>();
        listDataChildForshawarmaBar = new HashMap<>();

        // Adding child data Headers
        listDataHeaderForshawarmaBar.add("Shawarma");
        listDataHeaderForshawarmaBar.add("Our special combo");
        listDataHeaderForshawarmaBar.add("Soft Drinks");


        // Adding child data
        List<String> shawarmas = new ArrayList<>();
        shawarmas.add("Chicky Shawarma");
        shawarmas.add("Juicy Beef Shawarma");
        shawarmas.add("Beef Shawarma Bar");
        shawarmas.add("Shawarma Bites");
        shawarmas.add("Shawarma Soujouk");

        List<String> specialCombo = new ArrayList<>();
        shawarmas.add("2 Sandwiches of your choice");
        shawarmas.add("Chicken");
        shawarmas.add("Beef");


        List<String> drinks = new ArrayList<>();
        drinks.add("Pepsi");
        drinks.add("Mirinda");
        drinks.add("Seven up");
        drinks.add("Water small");

        listDataChildForAdonis.put(listDataHeaderForshawarmaBar.get(0), shawarmas); // Header, Child data
        listDataChildForAdonis.put(listDataHeaderForshawarmaBar.get(1), specialCombo);
        listDataChildForAdonis.put(listDataHeaderForshawarmaBar.get(2), drinks);

    }

    private void prepareListDataForSnackCharboul() {
        listDataHeaderForsnackCharboul = new ArrayList<>();
        listDataChildForsnackCharboul = new HashMap<>();

        // Adding child data Headers
        listDataHeaderForshawarmaBar.add("SANDWICHES");
        listDataHeaderForshawarmaBar.add("PLATES");
        listDataHeaderForshawarmaBar.add("COLD BEVERAGES");


        // Adding child data
        List<String> sandwiches = new ArrayList<>();
        sandwiches.add("Lahme");
        sandwiches.add("Tawouk");
        sandwiches.add("Kabab");
        sandwiches.add("Tawouk Special");
        sandwiches.add("Steak");
        sandwiches.add("Steak Special");
        sandwiches.add("Asabit Djej");
        sandwiches.add("Makanek");
        sandwiches.add("Soujouk");
        sandwiches.add("Hamburger");
        sandwiches.add("Cheese Burger");
        sandwiches.add("Chicken Burger");
        sandwiches.add("Fajita");
        sandwiches.add("Philadelphia");
        sandwiches.add("Rosto");
        sandwiches.add("Hot Dog");
        sandwiches.add("Hot Dog Special");
        sandwiches.add("Nkha3at");
        sandwiches.add("Sanesil");
        sandwiches.add("Batata");
        sandwiches.add("Kafta Naye");
        sandwiches.add("Habra Naye");
        sandwiches.add("Shawarma Lahme");
        sandwiches.add("Shawarma Djej");
        sandwiches.add("Lsenet");
        sandwiches.add("Djej");


        List<String> plates = new ArrayList<>();
        plates.add("Lahme Plate");
        plates.add("Tawouk Plate");
        plates.add("Kabab Plate");
        plates.add("Tawouk Special Plate");
        plates.add("Steak Plate");
        plates.add("Steak Special Plate");
        plates.add("Asabit Djej Plate");
        plates.add("Makanek Plate");
        plates.add("Soujouk Plate");
        plates.add("Hamburger Plate");
        plates.add("Cheese Burger Plate");
        plates.add("Chicken Burger Plate");
        plates.add("Fajita Plate");
        plates.add("Philadelphia Plate");
        plates.add("Rosto Plate");
        plates.add("Hot Dog Plate");
        plates.add("Hot Dog Special Plate");
        plates.add("Nkha3at Plate");
        plates.add("Sanesil Plate");
        plates.add("Batata Plate");
        plates.add("Kafte Naye Plate");
        plates.add("Habra Naye Plate");
        plates.add("Habra Naye Plate");
        plates.add("Shawarma Djej Plate");
        plates.add("Lsenet Plate");
        plates.add("Djej Plate");


        List<String> coldBeverages = new ArrayList<>();
        coldBeverages.add("Water Small");
        coldBeverages.add("Water Big");
        coldBeverages.add("Pepsi");
        coldBeverages.add("Mirinda");
        coldBeverages.add("7-Up");
        coldBeverages.add("Beer");

        // Header, Child data///
        listDataChildForsnackCharboul.put(listDataHeaderForsnackCharboul.get(0), sandwiches);
        listDataChildForsnackCharboul.put(listDataHeaderForsnackCharboul.get(1), plates);
        listDataChildForsnackCharboul.put(listDataHeaderForsnackCharboul.get(2), coldBeverages);
    }

    private void prepareDataForCroissant() {
        listDataHeaderForCroissants = new ArrayList<>();
        listDataChildForCroiassants = new HashMap<>();

        listDataHeaderForCroissants.add("Croissants");
        listDataHeaderForCroissants.add("CAKES / DOZEN");
        listDataHeaderForCroissants.add("CAKES / PIECE");
        listDataHeaderForCroissants.add("FRESH JUICE");
        listDataHeaderForCroissants.add("COLD BEVERAGES");
        listDataHeaderForCroissants.add("FRESH MOCKTAILS");
        listDataHeaderForCroissants.add("ICE CREAM");
        listDataHeaderForCroissants.add("ICED COFFEE DRINKS");
        listDataHeaderForCroissants.add("HOT BEVERAGES");

        List<String> crossants = new ArrayList<>();
        crossants.add("Chocolate");
        crossants.add("Knéfé");
        crossants.add("Zaatar");
        crossants.add("Zaatar Special");
        crossants.add("Olive & Mint");
        crossants.add("Olive & Mint Special");
        crossants.add("Spicy Cheese");
        crossants.add("Spicy Cheese Special");
        crossants.add("Cheese & Ham");
        crossants.add("Cheese & Ham Special");
        crossants.add("12 Small Croissant of any kind");


        List<String> cakes = new ArrayList<>();
        cakes.add("Tartez");
        cakes.add("Éclair");
        cakes.add("Chocolate Cream");


        List<String> cakeAndPiece = new ArrayList<>();
        cakeAndPiece.add("Baba au Rhum");
        cakeAndPiece.add("Baba au Rhum Special");
        cakeAndPiece.add("Éclair");
        cakeAndPiece.add("Éclair Special");
        cakeAndPiece.add("Tarte aux Fruits");
        cakeAndPiece.add("Sablé Chocolat");
        cakeAndPiece.add("Sablé Jam");
        cakeAndPiece.add("Mille Feuilles");
        cakeAndPiece.add("Boule Chocolat Coco");
        cakeAndPiece.add("Boule Chocolat Almond");
        cakeAndPiece.add("Cheese Cake");
        cakeAndPiece.add("Swiss Roll");

        List<String> freshJuice = new ArrayList<>();
        freshJuice.add("Orange");
        freshJuice.add("Grape Fruit");
        freshJuice.add("Carrot");
        freshJuice.add("Melon");
        freshJuice.add("Avocado");
        freshJuice.add("Apple");
        freshJuice.add("Strawberry");
        freshJuice.add("Lemonade");
        freshJuice.add("Banana With Milk");
        freshJuice.add("Kiwi, Strawberry & Lemon");
        freshJuice.add("Polo");
        freshJuice.add("Cocktail Pieces");
        freshJuice.add("Cocktail Juice");

        List<String> coldBeverages = new ArrayList<>();
        coldBeverages.add("Pepsi");
        coldBeverages.add("7-Up");
        coldBeverages.add("Mirinda");
        coldBeverages.add("Mexican Beer");
        coldBeverages.add("Beer");
        coldBeverages.add("AMP Energy");
        coldBeverages.add("H2OH");
        coldBeverages.add("Water (small)");
        coldBeverages.add("Water (big)");
        coldBeverages.add("Nuts");
        coldBeverages.add("Chips");

        List<String> freshMocktails = new ArrayList<>();
        freshMocktails.add("Strawberry Freez");
        freshMocktails.add("Mango Freez");
        freshMocktails.add("Mango Orange Freez");
        freshMocktails.add("Peach Freez");
        freshMocktails.add("Tropical Smoothie");
        freshMocktails.add("Survivor Smoothie");
        freshMocktails.add("Hulk");
        freshMocktails.add("Passion Fruit Reez");
        freshMocktails.add("Be Cool");
        freshMocktails.add("Italian Lemonade");
        freshMocktails.add("Blue Hawaiian Lemonade");
        freshMocktails.add("Pink Lemonade");
        freshMocktails.add("Frozen Bloody Orange");
        freshMocktails.add("Orange Mango Smoothie");
        freshMocktails.add("Kiwi");
        freshMocktails.add("Bubble Gum");
        freshMocktails.add("Virgin Colada ");

        List<String> frozenShakes = new ArrayList<>();
        frozenShakes.add("Coconut Shake");
        frozenShakes.add("Melon Shake");
        frozenShakes.add("Strawberry Shake");
        frozenShakes.add("Vanilla Shake");
        frozenShakes.add("Minted Iced Chocolate");
        frozenShakes.add("Choco Oreo Shake");
        frozenShakes.add("Banana Berry Shake");
        frozenShakes.add("Chocolate Milk Shake");
        frozenShakes.add("Snickers Milk Shake");
        frozenShakes.add("Black & White");
        frozenShakes.add("Bubble Gum Shake");

        List<String> icecream = new ArrayList<>();
        icecream.add("Chocolat Mou");
        icecream.add("Chikita");
        icecream.add("Banana Split");

        List<String> icedCoffeeDrinks = new ArrayList<>();
        icedCoffeeDrinks.add("Coffee Frappe");
        icedCoffeeDrinks.add("Nescafe Frappe");
        icedCoffeeDrinks.add("Cappuccino Frappe");
        icedCoffeeDrinks.add("Caramel Frappucino");
        icedCoffeeDrinks.add("Toffee Banana Mocha");
        icedCoffeeDrinks.add("Frozen Tiramisu");
        icedCoffeeDrinks.add("After Eight Mocha");
        icedCoffeeDrinks.add("Long Island Frappe");
        icedCoffeeDrinks.add("Belgian Fever");
        icedCoffeeDrinks.add("Crunchy");

        List<String> hotBeverages =  new ArrayList<>();
        hotBeverages.add("Turkish Coffee");
        hotBeverages.add("Nescafe");
        hotBeverages.add("Doppio");
        hotBeverages.add("Cappuccino");
        hotBeverages.add("Flavored Latté");
        hotBeverages.add("Mochaccino");
        hotBeverages.add("Caramel Macchiato");
        hotBeverages.add("Tiramisu Latté");
        hotBeverages.add("Chocolate Cookies Latté");
        hotBeverages.add("Belgian Latté");
        hotBeverages.add("Crusty Mocha");
        hotBeverages.add("Flavored Hot Chocolate");
        hotBeverages.add("Flavored hot chocolate");
        hotBeverages.add("Tea With Milk");

        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(0), crossants);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(1), cakes);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(2), cakeAndPiece);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(3), freshJuice);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(4), coldBeverages);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(5), freshMocktails);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(6), frozenShakes);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(7), icecream);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(8), icedCoffeeDrinks);
        listDataChildForCroiassants.put(listDataHeaderForCroissants.get(9), hotBeverages);

    }

    private void prepareDateForDagher() {
        listDataHeaderForDagher = new ArrayList<>();
        listDataChildForDagher = new HashMap<>();

        listDataHeaderForDagher.add("Dagher Menu");

        List<String> dagher = new ArrayList<>();
        dagher.add("Samak");
        dagher.add("Chich Tawouk");
        dagher.add("Kabab");
        dagher.add("Lahme Che2af");
        dagher.add("2asabit Djej");
        dagher.add("Ma2ani2 Lahme");
        dagher.add("Crab");
        dagher.add("Sejou2");
        dagher.add("Marquise");
        dagher.add("Hot Dog");
        dagher.add("Hamburger");
        dagher.add("Cheese Burger");
        dagher.add("Chicken Burger");
        dagher.add("Potato");
        dagher.add("Machewe Mchakal (1 kilo)");
        dagher.add("Sa7en Machewe (small)");
        dagher.add("Sa7en Machewe (large)");
        dagher.add("Potato (big)");
        dagher.add("Shawarma Lahme");
        dagher.add("Shawarma Djej");
        dagher.add("Saniyit Shawarma Lahme (big)");
        dagher.add("Saniyit Shawarma Lahme (small)");
        dagher.add("Saniyit Shawarma Djej (big)");
        dagher.add("Saniyit Shawarma Djej (small)");
        dagher.add("Sandwich Falefil");
        dagher.add("Saniyit Falefil (big)");
        dagher.add("Saniyit Falefil (small)");
        dagher.add("Broasted");
        dagher.add("Broasted (sa7en)");
        dagher.add("Broasted Fish (1 kilo)");
        dagher.add("Broasted Fish (sa7en)");
        dagher.add("Pepsi");
        dagher.add("7-UP");
        dagher.add("Mirinda");
        dagher.add("Water (big)");
        dagher.add("Water (small)");
        dagher.add("Laban 3iran ");

        listDataChildForDagher.put(listDataHeaderForDagher.get(0), dagher);






    }



    private void prepareListDataForAdonis() {
        listDataHeaderForAdonis = new ArrayList<>();
        listDataChildForAdonis = new HashMap<>();

        // Adding child data Headers
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

        List<String> freshJuices = new ArrayList<>();
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
        listDataHeaderForLatour = new ArrayList<>();
        listDataChildForLatour = new HashMap<>();

        // headings
        listDataHeaderForLatour.add("Pizza");
        listDataHeaderForLatour.add("Pasta Fiesta");
        listDataHeaderForLatour.add("Hot Sandwishes");
        listDataHeaderForLatour.add("Platters");
        listDataHeaderForLatour.add("Mana’ish");
        listDataHeaderForLatour.add("Drinks");

        // Pizza from Latour
        List<String> pizza = new ArrayList<>();
        pizza.add("Pizza Marguerita");
        pizza.add("Pizza 3 Fromages");
        pizza.add("Pizza Vegetarian");
        pizza.add("Pizza Jambon / Dinde");
        pizza.add("Pizza Pepperoni");
        pizza.add("Pizza Mexichicken");
        pizza.add("Pizza Jambon / Dinde");
        pizza.add("Pizza Pepperoni");
        pizza.add("Pizza Mexichicken");


        // Pasta Fiesta from Latour
        List<String> pastaFiesta = new ArrayList<>();
        pastaFiesta.add("Penne Arabiata");
        pastaFiesta.add("Chicken Pesto");
        pastaFiesta.add("Carbonara");

        // Hot Sandwichies from Latour
        List<String> hotSandwchies = new ArrayList<>();
        hotSandwchies.add("Tawook");
        hotSandwchies.add("American Frankfurter");
        hotSandwchies.add("Hamburger");
        hotSandwchies.add("Fish Burger");
        hotSandwchies.add("Fish Filet");
        hotSandwchies.add("Chicken Burger");
        hotSandwchies.add("Fajita");
        hotSandwchies.add("Philadelphia");
        hotSandwchies.add("Chicken La Tour");
        hotSandwchies.add("Beef Shawarma");
        hotSandwchies.add("Chicken Shawarma");
        hotSandwchies.add("Chicken Submarine");
        hotSandwchies.add("Chicken Club Sandwish");

        // Platters from Latour
        List<String> platters = new ArrayList<>();
        platters.add("Tawook");
        platters.add("Beef Shawarma");
        platters.add("Chicken Shawarma");
        platters.add("Fish & Chips");
        platters.add("Grilled Hamour Filet");
        platters.add("Chicken Breast");
        platters.add("Chicken Escalope");
        platters.add("Chicken Fungi");

        /// Mana’ish from Latour
        List<String> manaish = new ArrayList<>();

        manaish.add("Zaatar");
        manaish.add("Zaatar light");
        manaish.add("Zaatar and Labneh");
        manaish.add("Cheese");
        manaish.add("Spinach");
        manaish.add("Keshek");
        manaish.add("Akkawi");
        manaish.add("Halloum");
        manaish.add("Bulgari");
        manaish.add("Bulgari harra");
        manaish.add("Cheese Mix");
        manaish.add("Labneh");
        manaish.add("Lahme & Kawarma");
        manaish.add("Lahme b3ajin");
        manaish.add("Lahme b3ajin & Cheese");
        manaish.add("Pesto Cheese");
        manaish.add("Feta Mix");
        manaish.add("Turkey & Cheese");
        manaish.add("Ham & Cheese");
        manaish.add("Salami & Cheese");
        manaish.add("Hotdog & Cheese");
        manaish.add("La Tour Special");
        manaish.add("Soujouk & Cheese");
        manaish.add("Halloum Bacon");
        manaish.add("Chocoba");

        //// Drinks
        List<String> drinks = new ArrayList<>();
        drinks.add("pepsi");
        drinks.add("7up");
        drinks.add("Water");
        listDataChildForLatour.put(listDataHeaderForLatour.get(0), pizza);
        listDataChildForLatour.put(listDataHeaderForLatour.get(1), pastaFiesta);
        listDataChildForLatour.put(listDataHeaderForLatour.get(2), hotSandwchies);
        listDataChildForLatour.put(listDataHeaderForLatour.get(3), platters);
        listDataChildForLatour.put(listDataHeaderForLatour.get(4), manaish);
        listDataChildForLatour.put(listDataHeaderForLatour.get(5), drinks);
    }

    private void preparedListDataForRicaardo() {

        listDataHeaderForRicaardo = new ArrayList<>();
        listDataChildForRicaardo = new HashMap<>();

        // Headings
        listDataHeaderForRicaardo.add("Sandwiches");
        listDataHeaderForRicaardo.add("Soft Drinks");

        List<String> sandwiches = new ArrayList<>();
        sandwiches.add("Philadelphia");
        sandwiches.add("Spanish Steak");
        sandwiches.add("Francisco");
        sandwiches.add("Hamburger");
        sandwiches.add("Shrimp");
        sandwiches.add("Calamari");
        sandwiches.add("Roast Beef");
        sandwiches.add("Grilled chicken breast");
        sandwiches.add("Canarias");
        sandwiches.add("Mexican chicken");
        sandwiches.add("Ricky");
        sandwiches.add("Fajita");

        //// Drinks
        List<String> softDrinks = new ArrayList<>();
        softDrinks.add("pepsi");
        softDrinks.add("Mirinda");
        softDrinks.add("Seven up");
        softDrinks.add("Water");

        listDataChildForRicaardo.put(listDataHeaderForRicaardo.get(0), sandwiches);
        listDataChildForRicaardo.put(listDataHeaderForRicaardo.get(1), softDrinks);

    }

    private void preparedListDataForMassaad() {

        listDataHeaderForMassaad = new ArrayList<>();
        listDataChildForMassaad = new HashMap<>();

        /// Headigs
        listDataHeaderForMassaad.add("Salatat");
        listDataHeaderForMassaad.add("Mou2abbalet");
        listDataHeaderForMassaad.add("Sandwishet");
        listDataHeaderForMassaad.add("S7oun");
        listDataHeaderForMassaad.add("Soft Drinks");

        List<String> salatat = new ArrayList<>();
        salatat.add("Salatat");
        salatat.add("Fattouch");
        salatat.add("Tabboulé");

        /// Mou2abbalet
        List<String> mou2abbalet = new ArrayList<>();
        mou2abbalet.add("7ommos");
        mou2abbalet.add("Mtabbal");
        mou2abbalet.add("Labné");
        mou2abbalet.add("Batata");
        mou2abbalet.add("Habra zened l 3abed");

        // sandwishet
        List<String> sandwishet = new ArrayList<>();
        sandwishet.add("Tawou2");
        sandwishet.add("La7em Mechwé");
        sandwishet.add("Kabab");
        sandwishet.add("Hamburger");
        sandwishet.add("Batata");

        /// S7oun
        List<String> s7oun = new ArrayList<>();
        s7oun.add("Law7et Tawou2");
        s7oun.add("Law7et la7em mechwé");
        s7oun.add("Law7et kabab");
        s7oun.add("3arayess kafta");
        s7oun.add("Hamburger");
        s7oun.add("Sa7n tawou2");
        s7oun.add("Sa7n la7em mechwé");
        s7oun.add("Sa7n kabab");
        s7oun.add("Sa7n machawé");
        s7oun.add("Kilo machawé mechwé mchakkal");
        s7oun.add("Kilo la7em mechwé");
        s7oun.add("Kilo tawou2");
        s7oun.add("Kilo kabab");

        // softDrinks
        List<String> softDrinks = new ArrayList<>();
        softDrinks.add("Pepsi");
        softDrinks.add("Mirinda");
        softDrinks.add("7up");
        softDrinks.add("3assir bourtoukal tabi3é");
        softDrinks.add("Perrier");
        softDrinks.add("Laymounada");
        softDrinks.add("May kbiré");
        softDrinks.add("May zghiré");
        softDrinks.add("3iran");

        listDataChildForMassaad.put(listDataHeaderForMassaad.get(0), salatat);
        listDataChildForMassaad.put(listDataHeaderForMassaad.get(1), mou2abbalet);
        listDataChildForMassaad.put(listDataHeaderForMassaad.get(2), sandwishet);
        listDataChildForMassaad.put(listDataHeaderForMassaad.get(3), s7oun);
        listDataChildForMassaad.put(listDataHeaderForMassaad.get(4), softDrinks);

    }

    private void preparedListDataForSubz() {

        listDataHeaderForSubz = new ArrayList<>();
        listDataChildForSubz = new HashMap<>();

        /// Headigs
        listDataHeaderForSubz.add("Salads");
        listDataHeaderForSubz.add("Burgers");
        listDataHeaderForSubz.add("SUBz SPECIAL");
        listDataHeaderForSubz.add("SUBZ FRIES");
        listDataHeaderForSubz.add("Drinks");


        // salads
        List<String> salads = new ArrayList<>();
        salads.add("Chicken Caesar");
        salads.add("Chef Salad");
        salads.add("Crab Salad");
        salads.add("Tuna Pasta Salad");
        salads.add("Subz Salad");


        // Burgers
        List<String> burgers = new ArrayList<>();
        burgers.add("Classic burger");
        burgers.add("Subz Burger");
        burgers.add("Chicken Burger");

        // SUBz SPECIAL
        List<String> subzSpecial = new ArrayList<>();
        subzSpecial.add("Fajitas");
        subzSpecial.add("Philadelphia");
        subzSpecial.add("Francisco");
        subzSpecial.add("Chicken Sub");
        subzSpecial.add("Mexican Chicken");
        subzSpecial.add("Chicken Escalope");
        subzSpecial.add("Torpedo");
        subzSpecial.add("Subz Steak");
        subzSpecial.add("Submarine");
        subzSpecial.add("Taouk");
        subzSpecial.add("Crispy");
        subzSpecial.add("Potato");
        subzSpecial.add("Crispy Plat");

        /// SUBZ FRIES
        List<String> subsFries = new ArrayList<>();
        subsFries.add("French Fries");
        subsFries.add("Wedges");
        subsFries.add("Twister");

        // Drinks
        List<String> drinks = new ArrayList<>();
        drinks.add("Pepsi");
        drinks.add("Seven Up");
        drinks.add("Mirinda");
        drinks.add("Water Small");
        drinks.add("Water Big");
        drinks.add("Beer Almaza");
        drinks.add("Ice Tea");

        listDataChildForSubz.put(listDataHeaderForSubz.get(0), salads);
        listDataChildForSubz.put(listDataHeaderForSubz.get(1), burgers);
        listDataChildForSubz.put(listDataHeaderForSubz.get(2), subzSpecial);
        listDataChildForSubz.put(listDataHeaderForSubz.get(3), subsFries);
        listDataChildForSubz.put(listDataHeaderForSubz.get(4), drinks);
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
