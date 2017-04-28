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

        ItemAdapter.ViewHolder vHolder = new ItemAdapter.ViewHolder();
        vHolder.itemView = (TextView) convertView.findViewById(R.id.tvItem);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.current_item, parent, false);

            convertView.setTag(vHolder);
        } else {
            vHolder = (ItemAdapter.ViewHolder) convertView.getTag();
        }

        Item item = getItem(position);
        if (item!= null) {
            vHolder.itemView.setText(String.format("%s (£%s.%s)", item.getItemName(), Integer.toString(item.getItemPrice()).substring(0,1),
                    Integer.toString(item.getItemPrice()).substring(1,3)));
        }

        return convertView;
    }

}
