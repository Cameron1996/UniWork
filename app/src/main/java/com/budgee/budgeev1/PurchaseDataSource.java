package com.budgee.budgeev1;

/**
 * Created by Will on 20/02/2017.
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PurchaseDataSource {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = { DBHelper.columnPurchaseID,
            DBHelper.columnPurchaseDate, DBHelper.columnItemIDFK, DBHelper.columnBudgetIDFK, DBHelper.columnCategoryIDFK};

    public PurchaseDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Purchase createPurchase(Date purchaseDate, int itemID, int budgetID, int categoryID){
        ContentValues values = new ContentValues();
        values.put(DBHelper.columnPurchaseDate, purchaseDate.getTime());
        values.put(DBHelper.columnItemIDFK, itemID);
        values.put(DBHelper.columnBudgetIDFK, budgetID);
        values.put(DBHelper.columnCategoryIDFK, categoryID);
        long insertId = database.insert(DBHelper.tablePurchases, null, values);
        Cursor cursor = database.query(DBHelper.tablePurchases,
                allColumns, DBHelper.columnPurchaseID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Purchase newPurchase = cursorToPurchase(cursor);
        cursor.close();
        return newPurchase;
    }

    public void deletePurchase(Purchase purchase) {
        long id = purchase.getPurchaseID();
        System.out.println("Purchase deleted with id: " + id);
        database.delete(DBHelper.tablePurchases,DBHelper.columnPurchaseID
                + " = " + id, null);
    }

    public List<Purchase> getAllPurchases() {
        List<Purchase> purchaseList = new ArrayList<Purchase>();

        Cursor cursor = database.query(DBHelper.tablePurchases,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Purchase purchase = cursorToPurchase(cursor);
            purchaseList.add(purchase);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return purchaseList;
    }

    public List<Purchase> getCurrentBudgetPurchases(int budgetID){
        String whereClause = "Budget_id = ?";
        String[] whereArgs = new String[] {Integer.toString(budgetID)};
        String orderBy = "PurchaseDate";

        List<Purchase> purchaseList = new ArrayList<Purchase>();

        Cursor cursor = database.query(DBHelper.tablePurchases,
                allColumns, whereClause, whereArgs, null, null, orderBy);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Purchase purchase = cursorToPurchase(cursor);
            purchaseList.add(purchase);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return purchaseList;
    }

    public BigDecimal getCurrentSpending(BudCatLink budCatLink){
        BigDecimal totalSpending = new BigDecimal(0.0);
        BigDecimal expenditure;

        Cursor cursor = database.rawQuery("SELECT ItemPrice FROM Items INNER JOIN Purchases ON Items.Item_id = Item_id WHERE Budget_id = ? AND Category_id = ?;",
                new String[] {Integer.toString(budCatLink.getBudgetID()), Integer.toString(budCatLink.getCategoryID())});

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            expenditure = new BigDecimal(cursor.getString(0));
            totalSpending = totalSpending.add(expenditure);
        }
        // make sure to close the cursor
        cursor.close();
        return totalSpending;
    }

//    public List<Purchase> getCurrentBudgetPurchases(Date budgetStartDate, Date budgetFinDate) {
//        String whereClause = "PurchaseDate BETWEEN ? AND ?";
//        String[] whereArgs = new String[] {Long.toString(budgetStartDate.getTime()), Long.toString(budgetFinDate.getTime())};
//        String orderBy = "PurchaseDate";
//
//        List<Purchase> purchaseList = new ArrayList<Purchase>();
//
//        Cursor cursor = database.query(DBHelper.tablePurchases,
//                allColumns, whereClause, whereArgs, orderBy, null, null);
//
//        cursor.moveToFirst();
//        while (!cursor.isAfterLast()) {
//            Purchase purchase = cursorToPurchase(cursor);
//            purchaseList.add(purchase);
//            cursor.moveToNext();
//        }
//        // make sure to close the cursor
//        cursor.close();
//        return purchaseList;
//    }

    private Purchase cursorToPurchase(Cursor cursor) {
        Purchase purchase = new Purchase();
        Date date = new Date(cursor.getLong(1)); //Must convert from int to Date
        purchase.setPurchaseID(cursor.getInt(0));
        purchase.setPurchaseDate(date);
        purchase.setItemID(cursor.getInt(2));
        purchase.setBudgetID(cursor.getInt(3));
        purchase.setCategoryID(cursor.getInt(4));
        return purchase;
    }
}
