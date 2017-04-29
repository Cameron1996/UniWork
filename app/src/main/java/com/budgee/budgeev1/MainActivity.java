package com.budgee.budgeev1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.Spinner;
import android.widget.AdapterView;

import android.widget.Button;

import java.util.List;
import java.util.ArrayList;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

/**
 * Created by Will on 03/04/2017.
 */

public class MainActivity extends AppCompatActivity {

    private BudgetDataSource budgetDS;
    private CategoryDataSource categoryDS;
    private BudCatLinkDataSource budCatLinkDS;

    PieChart chart;

    int currentBudgetID;
    ArrayList<Budget> budgets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        budgetDS = new BudgetDataSource(this);

        categoryDS = new CategoryDataSource(this);

        budCatLinkDS = new BudCatLinkDataSource(this);

        chart = (PieChart) findViewById(R.id.chart);

        budgetDS.open();
        budgets = new ArrayList<Budget>(budgetDS.getAllBudgets());
        budgetDS.close();

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final BudgetAdapter budAdapt = new BudgetAdapter(getApplicationContext(), budgets);
        budAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(budAdapt);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentBudgetID = budgets.get(position).getBudgetID();
                List<PieEntry> entries = new ArrayList<PieEntry>();

                budCatLinkDS.open();
                ArrayList<BudCatLink> budCatLinks = new ArrayList<BudCatLink>(budCatLinkDS.getBudCatLinks(currentBudgetID));
                budCatLinkDS.close();

//                int totalAmount = 0;
//
//                for (BudCatLink bc : budCatLinks) {
//                    totalAmount += bc.getCatBudgetAmount();
//                }

                categoryDS.open();
                for (BudCatLink bc : budCatLinks) {
                    //float val = bc.getCatBudgetAmount() / totalAmount * 100;
                    float val = bc.getCatBudgetAmount().floatValue();
                    String name = categoryDS.getCategory(bc.getCategoryID()).getCategoryName();
                    entries.add(new PieEntry(val, name));
                }
                categoryDS.close();

                PieDataSet set = new PieDataSet(entries, "Budget");
                PieData data = new PieData(set);
                chart.setData(data);
                chart.invalidate();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final Button checkCurrentSpendingButton = (Button) findViewById(R.id.checkCurrentSpendingButton);
        checkCurrentSpendingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CurrentSpending.class);
                intent.putExtra("BudgetID", currentBudgetID);
                startActivity(intent);
            }
        });

        final Button addPurchaseButton = (Button) findViewById(R.id.newPurchaseButton);
        addPurchaseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreatePurchase.class);
                intent.putExtra("BudgetID", currentBudgetID);
                startActivity(intent);
            }
        });

        final Button newBudgetButton = (Button) findViewById(R.id.newBudgetButton);
        newBudgetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateBudget.class);
                startActivity(intent);
            }
        });



    }




}
