package com.budgee.budgeev1;

/**
 * Created by Will on 22/04/2017.
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class BudCatLinkDataSource {
    private SQLiteDatabase database;
    private DBHelper dbHelper;
    private String[] allColumns = { DBHelper.columnBudgetIDFK,
            DBHelper.columnCategoryIDFK, DBHelper.columnCatBudgetAmount};

    public BudCatLinkDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public BudCatLink createBudCatLink(int budgetID, int categoryID, int catBudgetAmount){
        ContentValues values = new ContentValues();
        values.put(DBHelper.columnBudgetIDFK, budgetID);
        values.put(DBHelper.columnCategoryIDFK, categoryID);
        values.put(DBHelper.columnCatBudgetAmount, catBudgetAmount);
        long insertId = database.insert(DBHelper.tableBudgets, null, values);

        Cursor cursor = database.query(DBHelper.tableBudgets,
                allColumns, DBHelper.columnBudgetID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        BudCatLink newBudCatLink = cursorToBudCatLink(cursor);
        cursor.close();
        return newBudCatLink;
    }

    public void deleteBudCatLink(BudCatLink budCatLink) {
        long budgetID = budCatLink.getBudgetID();
        long categoryID = budCatLink.getCategoryID();
        System.out.println("Category deleted with id: (" + budgetID + ", " + categoryID + ")" );
        database.delete(DBHelper.tableCategories,DBHelper.columnBudgetIDFK
                + " = " + budgetID + " AND " + DBHelper.columnCategoryIDFK + " = " + categoryID, null);
    }

    public List<BudCatLink> getBudCatLinks(int budgetID) {
        String whereClause = "Budget_id EQUALS ?";
        String[] whereArgs = new String[] {Integer.toString(budgetID)};
        String orderBy = "Category_id";

        List<BudCatLink> budCatLinkList = new ArrayList<BudCatLink>();

        Cursor cursor = database.query(DBHelper.tableBudCatLink,
                allColumns, whereClause, whereArgs, orderBy, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            BudCatLink budCatLink = cursorToBudCatLink(cursor);
            budCatLinkList.add(budCatLink);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return budCatLinkList;

    }

    private BudCatLink cursorToBudCatLink(Cursor cursor) {
        BudCatLink budCatLink = new BudCatLink();
        budCatLink.setBudgetID(cursor.getInt(0));
        budCatLink.setCategoryID(cursor.getInt(1));
        budCatLink.setCatBudgetAmount(cursor.getInt(2));
        return budCatLink;
    }

}
