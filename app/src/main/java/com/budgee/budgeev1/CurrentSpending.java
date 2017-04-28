package com.budgee.budgeev1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Will on 27/04/2017.
 */

public class CurrentSpending extends Activity {

    private BudgetDataSource budgetDS;
    private BudCatLinkDataSource budCatLinkDS;
    private PurchaseDataSource purchaseDS;
    private ItemDataSource itemDS;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_spending);

        Bundle extras = getIntent().getExtras();

        int budgetID = extras.getInt("BudgetID");

        ArrayList<BudCatLink> budCatLinks = new ArrayList<BudCatLink>(budCatLinkDS.getBudCatLinks(budgetID));

        TextView currentPurchases = (TextView) findViewById(R.id.tvAllPurchases);
        ScrollView scrollView =  (ScrollView) findViewById(R.id.allSpendingBar);

        for (BudCatLink budCat : budCatLinks ) {
            RelativeLayout relativeLayout = (RelativeLayout)getLayoutInflater().inflate(R.layout.spending_bar, null);

            ProgressBar progressBar = (ProgressBar) relativeLayout.findViewById(R.id.progressBarCurrentSpending);
            TextView tvMax = (TextView) relativeLayout.findViewById(R.id.tvMax);

            int spendingPercentage = purchaseDS.getCurrentSpending(budCat) / budCat.getCatBudgetAmount() * 100;

            progressBar.setProgress(spendingPercentage);
            tvMax.setText(budCat.getCatBudgetAmount());

            if (spendingPercentage <= 50) {
                progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#0ef929"), PorterDuff.Mode.SRC_IN);
            } else if (50 < spendingPercentage && spendingPercentage <= 75) {
                progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#ff960a"), PorterDuff.Mode.SRC_IN);
            } else if (75 < spendingPercentage && spendingPercentage <= 100) {
                progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#fe2e2e"), PorterDuff.Mode.SRC_IN);
            } else {
                progressBar.getProgressDrawable().setColorFilter(Color.parseColor("#b62020"), PorterDuff.Mode.SRC_IN);
            }

            scrollView.addView(relativeLayout);
        }

        for (Purchase p : purchaseDS.getCurrentBudgetPurchases(budgetID)) {
            Item i = itemDS.getItem(p.getItemID());
            currentPurchases.setText(currentPurchases.getText() + i.getItemName() + " £" + Integer.toString(i.getItemPrice()).substring(0, 1)
                    + "." + Integer.toString(i.getItemPrice()).substring(1, 3) + "\r\n");
        }

        final Button exitButton = (Button) findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                finish();
            }
        });
    }


}
