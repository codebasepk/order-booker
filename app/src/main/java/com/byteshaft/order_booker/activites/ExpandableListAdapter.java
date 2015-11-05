package com.byteshaft.order_booker.activites;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.byteshaft.order_booker.AppGlobals;
import com.byteshaft.order_booker.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private Typeface typeFace;
    private HashMap<String, String> priceMap;

    public ExpandableListAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData,
                                 HashMap<String, String> price) {
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
        return this.listDataChild.get(this.listDataHeader.get(groupPosition))
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
            LayoutInflater infalInflater = (LayoutInflater) this.context
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
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_items, null);
        }

        TextView textListChild = (TextView) convertView.findViewById(R.id.product_name);
        TextView priceTextView = (TextView) convertView.findViewById(R.id.price);
        textListChild.setTypeface(typeFace);
        priceTextView.setTypeface(typeFace);
        textListChild.setText(childText);
        priceTextView.setText("price: " + priceMap.get(childText));
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        HashMap<String, String> ordersMap = AppGlobals.getFinalOrdersHashMap();
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    System.out.println(listDataChild.get(listDataHeader.get(groupPosition)).get(
                            childPosition));
                    AppGlobals.addOrderToHashMap(listDataChild.get(listDataHeader.
                                    get(groupPosition)).get(childPosition),
                            priceMap.get(listDataChild.get(listDataHeader.get(groupPosition))
                                    .get(childPosition)));
                    System.out.println(AppGlobals.getFinalOrdersHashMap());
                } else {
                    AppGlobals.removeOrderFromHashMap(listDataChild.get(listDataHeader.
                            get(groupPosition)).get(childPosition));
                    System.out.println(AppGlobals.getFinalOrdersHashMap());
                }
            }
        });
        System.out.println(ordersMap.containsKey(childText));
        if (ordersMap.containsKey(childText)) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
