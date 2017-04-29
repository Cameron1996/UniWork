package com.budgee.budgeev1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Will on 18/04/2017.
 */

public class BudgetAdapter extends ArrayAdapter<Budget> {

    private static class ViewHolder {
        private TextView itemView;
    }

    public BudgetAdapter(Context context, ArrayList<Budget> budgets) {
        super(context, R.layout.current_budget, budgets);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        Budget budget = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.current_budget, parent, false);
        }

        TextView budName = (TextView) convertView.findViewById(R.id.tvBudget);

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        budName.setText(formatter.format(budget.getBudgetStartDate()) + " - " + formatter.format(budget.getBudgetFinishDate()));

        return convertView;
    }

    public View getDropDownView (int position, View convertView, ViewGroup parent) {

        Budget budget = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext())
                    .inflate(R.layout.current_budget, parent, false);
        }

        TextView budName = (TextView) convertView.findViewById(R.id.tvBudget);

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        budName.setText(formatter.format(budget.getBudgetStartDate()) + " - " + formatter.format(budget.getBudgetFinishDate()));

        return convertView;
    }

}
