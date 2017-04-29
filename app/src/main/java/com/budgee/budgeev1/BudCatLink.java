package com.budgee.budgeev1;

import java.math.BigDecimal;

/**
 * Created by Will on 22/04/2017.
 */

public class BudCatLink {
    private int budgetID;
    private int categoryID;
    private BigDecimal catBudgetAmount;

    public int getBudgetID() {
        return budgetID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public BigDecimal getCatBudgetAmount() {
        return catBudgetAmount;
    }

    public void setBudgetID(int budgetID) {
        this.budgetID = budgetID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public void setCatBudgetAmount(BigDecimal catBudgetAmount) {
        this.catBudgetAmount = catBudgetAmount;
    }
}
