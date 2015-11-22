package com.byteshaft.order_booker.activites;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.R;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private Typeface typeFace;
    private HashMap<String, String[]> priceMap;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData,
                                 HashMap<String, String[]> price) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
        this.priceMap = price;
        typeFace = Typeface.createFromAsset(AppGlobals.getContext().getAssets(),"fonts/BradBunR.ttf");
    }

    @Override
    public int getGroupCount() {
        return this.listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        System.out.println(groupPosition);
        return listDataChild.get(listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }
        TextView lblListHeader = (TextView) convertView.findViewById(R.id.sub_category);
        lblListHeader.setTypeface(typeFace);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_items, null);
        }
        TextView textListChild = (TextView) convertView.findViewById(R.id.product_name);
        TextView priceTextView = (TextView) convertView.findViewById(R.id.price);
        Spinner spinner = (Spinner) convertView.findViewById(R.id.quantity_spinner);
        TextView ingredients = (TextView) convertView.findViewById(R.id.description);
        ingredients.setTypeface(typeFace);
        final EditText editText = (EditText) convertView.findViewById(R.id.with_out);
        editText.setTypeface(typeFace);
        RadioGroup radioGroup = (RadioGroup) convertView.findViewById(R.id.radio_group);
        if (AppGlobals.getCurrentSelectedStore().equals("latour")) {
            radioGroup.setVisibility(View.VISIBLE);
        }
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.one_person:
                        AppGlobals.addPRToHashMap(AppGlobals.getCurrentSelectedStore() + "_" +
                                listDataHeader.get(groupPosition) + "_" +
                                listDataChild.get(listDataHeader.get(groupPosition)).get(
                                        childPosition), "1PR");
                        break;
                    case R.id.two_person:
                        AppGlobals.addPRToHashMap(AppGlobals.getCurrentSelectedStore() + "_" +
                                listDataHeader.get(groupPosition) + "_" +
                                listDataChild.get(listDataHeader.get(groupPosition)).get(
                                        childPosition), "2PR");
                }
            }
        });
        spinner.setSelection(0);
        textListChild.setTypeface(typeFace);
        priceTextView.setTypeface(typeFace);
        textListChild.setText(childText);
        if (AppGlobals.getCurrentSelectedStore().equals("Adonis")) {
            priceTextView.setText("price: " + priceMap.get(childText)[0]);
        } else if (AppGlobals.getCurrentSelectedStore().equals("latour")) {
            String[] array = priceMap.get(childText);
            System.out.println(childText);
            System.out.println(Arrays.toString(array));
            priceTextView.setText("price: " + array[0] + "PR," + array[1]);
            ingredients.setText(array[2]);
        }
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        HashMap<String, String> orderMap = AppGlobals.getFinalOrdersHashMap();
        HashMap<String, Integer> quantityMap = AppGlobals.getQuantityHashMap();
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                System.out.println(listDataHeader == null);
                System.out.println(listDataChild == null);
                if (buttonView.isChecked()) {
                    AppGlobals.addOrderToHashMap(AppGlobals.getCurrentSelectedStore() + "_" +
                            listDataHeader.get(groupPosition)
                            + "_" + listDataChild.get(listDataHeader.get(groupPosition))
                            .get(childPosition),priceMap.get(
                            listDataChild.get(listDataHeader.get(groupPosition))
                                    .get(childPosition))[0]);
                    AppGlobals.withOutHashMap(AppGlobals.getCurrentSelectedStore() + "_" +
                            listDataHeader.get(groupPosition)
                            + "_" + listDataChild.get(listDataHeader.get(groupPosition))
                            .get(childPosition), editText.getText().toString());
                    System.out.println(AppGlobals.getFinalOrdersHashMap());
                } else {
                    AppGlobals.removeOrderFromHashMap(AppGlobals.getCurrentSelectedStore() + "_"
                            + listDataHeader.get(groupPosition)
                            + "_" + listDataChild.get(listDataHeader.get(groupPosition))
                            .get(childPosition));
                    AppGlobals.removeFromWithOutHashMap(AppGlobals.getCurrentSelectedStore() + "_"
                            + listDataHeader.get(groupPosition)
                            + "_" + listDataChild.get(listDataHeader.get(groupPosition))
                            .get(childPosition));
                    AppGlobals.removeQuantityFromHashMap(AppGlobals.getCurrentSelectedStore() + "_" + listDataHeader.get(groupPosition)
                            + "_" + listDataChild.get(listDataHeader.get(groupPosition))
                            .get(childPosition));
                    if (AppGlobals.getPersonHashMap().containsKey(AppGlobals.getCurrentSelectedStore() + "_" + listDataHeader.get(groupPosition)
                            + "_" + listDataChild.get(listDataHeader.get(groupPosition))
                            .get(childPosition))) {
                        AppGlobals.removeFromWithOutHashMap(AppGlobals.getCurrentSelectedStore() + "_" + listDataHeader.get(groupPosition)
                                + "_" + listDataChild.get(listDataHeader.get(groupPosition))
                                .get(childPosition));
                    }
                    System.out.println(AppGlobals.getFinalOrdersHashMap());
                }
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (Integer.valueOf(parent.getItemAtPosition(position).toString()) > 1) {
                    AppGlobals.addQuantityToHashMap(AppGlobals.getCurrentSelectedStore() + "_" +
                            listDataHeader.get(groupPosition) + "_" +
                            listDataChild.get(listDataHeader.get(groupPosition)).get(
                                    childPosition), Integer.valueOf(parent.getItemAtPosition(position).toString()));
                    System.out.println(AppGlobals.getQuantityHashMap());
                } else if (Integer.valueOf(parent.getItemAtPosition(position).toString()) == 1
                        && AppGlobals.getQuantityHashMap().containsKey(AppGlobals.
                        getCurrentSelectedStore() + "_" + listDataHeader.get(groupPosition) + "_" + listDataChild.get(listDataHeader.get(groupPosition)).get(
                        childPosition))) {
                    AppGlobals.removeQuantityFromHashMap(AppGlobals.getCurrentSelectedStore() + "_" +
                            listDataHeader.get(groupPosition) + "_" + listDataChild.get(
                            listDataHeader.get(groupPosition)).get(
                            childPosition));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        if (orderMap.containsKey(AppGlobals.getCurrentSelectedStore() + "_" + listDataHeader.
                get(groupPosition) + "_" + childText)) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }

        if (quantityMap.containsKey(AppGlobals.getCurrentSelectedStore() + "_" + listDataHeader.
                get(groupPosition) + "_" + childText)) {
            spinner.setSelection((AppGlobals.getQuantityHashMap().
                    get(listDataHeader.get(groupPosition) + "_" + childText) - 1));
        }
        if (AppGlobals.getPersonHashMap().containsKey(AppGlobals.getCurrentSelectedStore() + "_" +
                listDataHeader.get(groupPosition) + "_" + childText)) {
            switch (AppGlobals.getPersonHashMap().get(AppGlobals.getCurrentSelectedStore() + "_" +
                    listDataHeader.get(groupPosition) + "_" + childText)) {
                case "1PR":
                    radioGroup.check(R.id.one_person);
                    break;
                case "2PR":
                    radioGroup.check(R.id.two_person);
                    break;
            }
            }
            return convertView;
        }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
