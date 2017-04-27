package com.budgee.budgeev1;

import java.util.Date;

/**
 * Created by Will on 16/02/2017.
 */

public class Purchase {
    private int purchaseID;
    private Date purchaseDate;
    private int itemID;
    private int budgetID;
    private int categoryID;

    public int getPurchaseID(){
        return purchaseID;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public int getItemID() {
        return itemID;
    }

    public int getBudgetID() {
        return budgetID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setPurchaseID(int purchaseID) {
        this.purchaseID = purchaseID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setBudgetID(int budgetID) {
        this.budgetID = budgetID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
