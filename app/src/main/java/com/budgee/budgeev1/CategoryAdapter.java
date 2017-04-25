package com.budgee.budgeev1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Will on 23/04/2017.
 */

public class CategoryAdapter extends ArrayAdapter<Category> {

    private static class ViewHolder {
        private TextView itemView;
    }

    public CategoryAdapter(Context context, ArrayList<Category> items) {
        super(context, R.layout.current_category, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        CategoryAdapter.ViewHolder vHolder = new CategoryAdapter.ViewHolder();
        vHolder.itemView = (TextView) convertView.findViewById(R.id.tvCategory);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.current_category, parent, false);

            convertView.setTag(vHolder);
        } else {
            vHolder = (CategoryAdapter.ViewHolder) convertView.getTag();
        }

        Category item = getItem(position);
        if (item!= null) {
            // My layout has only one TextView
            // do whatever you want with your string and long
            vHolder.itemView.setText(String.format("%s", item.getCategoryName()));
        }

        return convertView;
    }

}
