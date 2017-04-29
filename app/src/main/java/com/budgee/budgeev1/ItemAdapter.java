package com.budgee.budgeev1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Will on 28/04/2017.
 */

public class ItemAdapter extends ArrayAdapter<Item> {

    private static class ViewHolder {
        private TextView itemView;
    }

    public ItemAdapter(Context context, ArrayList<Item> items) {
        super(context, R.layout.current_item, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Item item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.current_item, parent, false);
        }

        TextView itemName = (TextView) convertView.findViewById(R.id.tvItem);

        itemName.setText(item.getItemName() + " - " + item.getItemPrice());

        return convertView;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        Item item = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.current_item, parent, false);
        }

        TextView itemName = (TextView) convertView.findViewById(R.id.tvItem);

        itemName.setText(item.getItemName() + " - " + item.getItemPrice());

        return convertView;
    }

}
