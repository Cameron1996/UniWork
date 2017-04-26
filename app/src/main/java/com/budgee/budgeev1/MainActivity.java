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
import java.util.ListIterator;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

/**
 * Created by Will on 03/04/2017.
 */

public class MainActivity extends AppCompatActivity {

    private PurchaseDataSource purchaseDS;
    private BudgetDataSource budgetDS;
    private ItemDataSource itemDS;
    private CategoryDataSource categoryDS;

    PieChart chart = (PieChart) findViewById(R.id.chart);

    ArrayList<Budget> budgets = new ArrayList<Budget>(budgetDS.getAllBudgets());

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final BudgetAdapter budAdapt = new BudgetAdapter(getApplicationContext(), budgets);
        budAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(budAdapt);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                List<PieEntry> entries = new ArrayList<PieEntry>();
                List<Purchase> purchases = purchaseDS.getCurrentBudgetPurchases(budAdapt.getItem(position).getBudgetStartDate(), budAdapt.getItem(position).getBudgetFinishDate());
                ArrayList<PieSection> catIDs = new ArrayList<PieSection>();
                int totalAmount = 0;

                for (Purchase p: purchases) {
                    Item i = itemDS.getItem(p.getItemID());
                    boolean sectionAlready = false;
                    int sectionIndex = 0;

                    for (PieSection ps : catIDs){
                        if (i.getCategoryID() == ps.getSectionID()) {
                            sectionAlready = true;
                            sectionIndex = catIDs.indexOf(ps);
                            break;
                        }
                    }

                    if (sectionAlready == true) {
                        catIDs.get(sectionIndex).addToSectionTotal(i.getItemPrice());
                    } else {
                        catIDs.add(new PieSection(i.getCategoryID(), i.getItemPrice()));
                    }

                    totalAmount += i.getItemPrice();
                }

                for (PieSection ps : catIDs) {
                    float val = ps.getSectionTotal() / totalAmount * 100;
                    String name = categoryDS.getCategory(ps.getSectionID()).getCategoryName();
                    entries.add(new PieEntry(val, name));
                }

                PieDataSet set = new PieDataSet(entries, "Budget");
                PieData data = new PieData(set);
                chart.setData(data);
                chart.invalidate();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            public void onValueSelected(Entry e, Highlight h) {
                // display msg when value selected
                if (e == null)
                    return;


            }

            @Override
            public void onNothingSelected() {

            }
        });

        final Button button = (Button) findViewById(R.id.newBudgetButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateBudget.class);
                startActivity(new Intent(MainActivity.this, CreateBudget.class));
            }
        });



    }




}
