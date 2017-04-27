package com.budgee.budgeev1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by Will on 27/04/2017.
 */

public class CurrentSpending extends Activity {

    private BudgetDataSource budgetDS;
    private BudCatLinkDataSource budCatLinkDS;
    private PurchaseDataSource purchaseDS;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.current_spending);

        Bundle extras = getIntent().getExtras();

        int budgetID = extras.getInt("BudgetID");

        ArrayList<BudCatLink> budCatLinks = new ArrayList<BudCatLink>(budCatLinkDS.getBudCatLinks(budgetID));

        int catNumber = 0;

        for (BudCatLink budCat : budCatLinks ) {
            RelativeLayout relativeLayout = (RelativeLayout)getLayoutInflater().inflate(R.layout.spending_bar, null);

            ProgressBar progressBar = (ProgressBar) relativeLayout.findViewById(R.id.progressBarCurrentSpending);

            for (Purchase p : purchaseDS.getCurrentBudgetPurchases(budgetID)){

            }

//            progressBar.setProgress(budCat.);

//            ProgressBar progressBar = new ProgressBar(this);
//            progressBar.setId(catNumber);
//            ProgressBar
//            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) progressBar.getLayoutParams();
//            params.height = 20;
//            params.width = 320;
//            params.addRule(RelativeLayout.CENTER_HORIZONTAL);





        }


    }


}
