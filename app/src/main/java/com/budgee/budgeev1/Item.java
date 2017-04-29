package com.budgee.budgeev1;

import java.math.BigDecimal;

/**
 * Created by Will on 16/02/2017.
 */

public class Item {
    private int itemID;
    private String itemName;
    private BigDecimal itemPrice; // should it not be double?
    public int getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemID(int itemID){
        this.itemID = itemID;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

}
