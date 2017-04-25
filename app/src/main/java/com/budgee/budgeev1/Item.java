package com.budgee.budgeev1;

/**
 * Created by Will on 16/02/2017.
 */

public class Item {
    private int itemID;
    private String itemName;
    private int itemPrice;
    private int categoryID;

    public int getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setItemID(int itemID){
        this.itemID = itemID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
