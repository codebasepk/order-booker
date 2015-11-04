package com.byteshaft.order_booker.activites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.byteshaft.order_booker.R;

public class CustomGrid extends BaseAdapter {
    private final int[] imagesId;
    private Context mContext;

    public CustomGrid(Context c, int[] imageId) {
        mContext = c;
        imagesId = imageId;
    }

    @Override
    public int getCount() {
        return imagesId.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.grid_single, null);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.grid_image);
            imageView.setImageResource(imagesId[position]);
        }
        return convertView;
    }
}
