package com.budgee.budgeev1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Will on 18/04/2017.
 */

public class BudgetAdapter extends ArrayAdapter<Budget> {

    private static class ViewHolder {
        private TextView itemView;
    }

    public BudgetAdapter(Context context, ArrayList<Budget> items) {
        super(context, R.layout.current_budget, items);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder vHolder = new ViewHolder();
        vHolder.itemView = (TextView) convertView.findViewById(R.id.tvBudget);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.current_budget, parent, false);

            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }

        Budget item = getItem(position);
        if (item!= null) {
            // My layout has only one TextView
            // do whatever you want with your string and long
            vHolder.itemView.setText(String.format("%s - %d", item.getBudgetStartDate(), item.getBudgetFinishDate()));
        }

        return convertView;
    }

}
