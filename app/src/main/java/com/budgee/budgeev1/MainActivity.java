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

    private PurchaseDataSource purchaseDS;
    private BudgetDataSource budgetDS;
    private ItemDataSource itemDS;
    private CategoryDataSource categoryDS;
    private BudCatLinkDataSource budCatLinkDS;

    PieChart chart = (PieChart) findViewById(R.id.chart);

    int currentBudgetID;
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

                currentBudgetID = position;
                List<PieEntry> entries = new ArrayList<PieEntry>();
                ArrayList<BudCatLink> budCatLinks = new ArrayList<BudCatLink>(budCatLinkDS.getBudCatLinks(currentBudgetID));
                int totalAmount = 0;

//                List<Purchase> purchases = purchaseDS.getCurrentBudgetPurchases(budAdapt.getItem(position).getBudgetID());

//
//                for (Purchase p: purchases) {
//                    Item i = itemDS.getItem(p.getItemID());
//                    boolean sectionAlready = false;
//                    int sectionIndex = 0;
//
//                    for (BudCatLink ps : budCatLinks){
//                        if (i.getCategoryID() == ps.getSectionID()) {
//                            sectionAlready = true;
//                            sectionIndex = catIDs.indexOf(ps);
//                            break;
//                        }
//                    }
//
//                    if (sectionAlready == true) {
//                        catIDs.get(sectionIndex).addToSectionTotal(i.getItemPrice());
//                    } else {
//                        catIDs.add(new PieSection(i.getCategoryID(), i.getItemPrice()));
//                    }
//
//                    totalAmount += i.getItemPrice();
//                }
                for (BudCatLink bc : budCatLinks) {
                    totalAmount += bc.getCatBudgetAmount();
                }

                for (BudCatLink bc : budCatLinks) {
                    float val = bc.getCatBudgetAmount() / totalAmount * 100;
                    String name = categoryDS.getCategory(bc.getCategoryID()).getCategoryName();
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

        final Button checkCurrentSpendingButton = (Button) findViewById(R.id.checkCurrentSpendingButton);
        checkCurrentSpendingButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CurrentSpending.class);
                intent.putExtra("BudgetID", currentBudgetID);
                startActivity(new Intent(MainActivity.this, CurrentSpending.class));
            }
        });

        final Button newBudgetButton = (Button) findViewById(R.id.newBudgetButton);
        newBudgetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateBudget.class);
                startActivity(new Intent(MainActivity.this, CreateBudget.class));
            }
        });



    }




}
