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

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        super(context, R.layout.current_category, categories);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Category category = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.current_category, parent, false);
        }

        TextView catName = (TextView) convertView.findViewById(R.id.tvCategory);

        catName.setText(category.getCategoryName());

        return convertView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        Category category = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.current_category, parent, false);
        }

        TextView catName = (TextView) convertView.findViewById(R.id.tvCategory);

        catName.setText(category.getCategoryName());

        return convertView;
    }


}
