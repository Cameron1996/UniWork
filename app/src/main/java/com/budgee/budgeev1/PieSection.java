package com.budgee.budgeev1;

/**
 * Created by Will on 21/04/2017.
 */

public class PieSection {

    private int sectionID;
    private int sectionTotal;

    public PieSection(int sectionID, int sectionTotal) {
        this.sectionID = sectionID;
        this.sectionTotal = sectionTotal;
    }

    public int getSectionID() {
        return sectionID;
    }

    public int getSectionTotal() {
        return sectionTotal;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public void setSectionTotal(int sectionTotal) {
        this.sectionTotal = sectionTotal;
    }

    public void addToSectionTotal(int amountToAdd) {
        this.sectionTotal += amountToAdd;
    }

}
