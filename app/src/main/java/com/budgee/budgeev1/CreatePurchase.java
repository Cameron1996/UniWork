package com.budgee.budgeev1;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.AccessibleObject;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Will on 27/04/2017.
 */

public class CreatePurchase extends Activity {

    private BudgetDataSource budgetDS;
    private ItemDataSource itemDS;
    private CategoryDataSource categoryDS;
    private PurchaseDataSource purchaseDS;

    Bundle extras;

    int budgetID;

    Item currentQuickBuyItem;
    Category currentQuickBuyCategory;
    Category currentNewPurchaseCategory;

    ArrayList<Item> items;
    ArrayList<Category> categories;

    final Calendar quickBuyCalendar = Calendar.getInstance();
    final Calendar newPurchaseCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener quickBuyDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            quickBuyCalendar.set(Calendar.YEAR, year);
            quickBuyCalendar.set(Calendar.MONTH, monthOfYear);
            quickBuyCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateQuickBuyLabel();
        }
    };

    DatePickerDialog.OnDateSetListener newPurchaseDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            newPurchaseCalendar.set(Calendar.YEAR, year);
            newPurchaseCalendar.set(Calendar.MONTH, monthOfYear);
            newPurchaseCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateNewPurchaseLabel();
        }
    };

    private void updateQuickBuyLabel(){

        String myFormat = "dd/mm/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        EditText ed = (EditText) findViewById(R.id.startDate);

        ed.setText(sdf.format(quickBuyCalendar.getTime()));
    }

    private void updateNewPurchaseLabel(){

        String myFormat = "dd/mm/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        EditText ed = (EditText) findViewById(R.id.finDate);

        ed.setText(sdf.format(newPurchaseCalendar.getTime()));
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_create);

        budgetDS = new BudgetDataSource(this);
        itemDS =  new ItemDataSource(this);
        categoryDS = new CategoryDataSource(this);
        purchaseDS = new PurchaseDataSource(this);

        extras = getIntent().getExtras();
        budgetID = extras.getInt("BudgetID");

        currentQuickBuyItem = new Item();
        currentQuickBuyCategory = new Category();
        currentNewPurchaseCategory = new Category();

        itemDS.open();
        items = new ArrayList<Item>(itemDS.getAllItems());
        itemDS.close();

        categoryDS.open();
        categories = new ArrayList<Category>(categoryDS.getAllCategories());
        categoryDS.close();

        Spinner quickBuyItemSpinner = (Spinner) findViewById(R.id.quickBuyItemSpinner);

        final ItemAdapter itemAdapt = new ItemAdapter(getApplicationContext(), items);
        itemAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quickBuyItemSpinner.setAdapter(itemAdapt);

        Spinner quickBuyCategorySpinner = (Spinner) findViewById(R.id.quickBuyCategorySpinner);
        Spinner newPurchaseCategorySpinner = (Spinner) findViewById(R.id.newPurchaseCategorySpinner);

        final CategoryAdapter catAdapt = new CategoryAdapter(getApplicationContext(), categories);
        catAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        quickBuyCategorySpinner.setAdapter(catAdapt);
        newPurchaseCategorySpinner.setAdapter(catAdapt);

        final Button confirmQuickBuyButton = (Button) findViewById(R.id.confirmQuickBuyButton);
        confirmQuickBuyButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                purchaseDS.open();
                purchaseDS.createPurchase(quickBuyCalendar.getTime(), currentQuickBuyItem.getItemID(), budgetID, currentQuickBuyCategory.getCategoryID());
                purchaseDS.close();
                finish();
            }
        });

        final Button confirmNewPurchaseButton = (Button) findViewById(R.id.confirmNewPurchaseButton);
        confirmNewPurchaseButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText newItemName = (EditText) findViewById(R.id.setNewItemName);
                EditText newItemPricePounds = (EditText) findViewById(R.id.priceInputPounds);
                EditText newItemPricePence = (EditText) findViewById(R.id.priceInputPence);

                itemDS.open();
                purchaseDS.open();
                Item item = itemDS.createItem(newItemName.getText().toString(), newItemPricePounds.toString() + "."  + newItemPricePence.toString());
                purchaseDS.createPurchase(newPurchaseCalendar.getTime(), item.getItemID(), budgetID, currentNewPurchaseCategory.getCategoryID());
                itemDS.close();
                itemDS.close();
                finish();
            }
        });

        quickBuyItemSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentQuickBuyItem = itemAdapt.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        quickBuyCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentQuickBuyCategory = catAdapt.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        newPurchaseCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentNewPurchaseCategory = catAdapt.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        final EditText quickBuyDate = (EditText) findViewById(R.id.setQuickBuyDate);
        quickBuyDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpl = new DatePickerDialog (CreatePurchase.this, CreatePurchase.this.quickBuyDate, quickBuyCalendar
                        .get(Calendar.YEAR), quickBuyCalendar.get(Calendar.MONTH),
                        quickBuyCalendar.get(Calendar.DAY_OF_MONTH));

                budgetDS.open();
                Calendar minCal = Calendar.getInstance().getInstance();
                minCal.setTime(budgetDS.getBudget(budgetID).getBudgetStartDate());
                long minDate = minCal.getTime().getTime();

                Calendar maxCal = Calendar.getInstance().getInstance();
                maxCal.setTime(budgetDS.getBudget(budgetID).getBudgetFinishDate());
                long maxDate = maxCal.getTime().getTime();
                budgetDS.close();

                dpl.getDatePicker().setMinDate(minDate);
                dpl.getDatePicker().setMaxDate(maxDate);
                dpl.show();
            }
        });

        final EditText newPurchaseDate = (EditText) findViewById(R.id.setNewPurchaseDate);
        newPurchaseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog dpl = new DatePickerDialog (CreatePurchase.this, CreatePurchase.this.newPurchaseDate, newPurchaseCalendar
                        .get(Calendar.YEAR), newPurchaseCalendar.get(Calendar.MONTH),
                        newPurchaseCalendar.get(Calendar.DAY_OF_MONTH));

                budgetDS.open();
                Calendar minCal = Calendar.getInstance().getInstance();
                minCal.setTime(budgetDS.getBudget(budgetID).getBudgetStartDate());
                long minDate = minCal.getTime().getTime();

                Calendar maxCal = Calendar.getInstance().getInstance();
                maxCal.setTime(budgetDS.getBudget(budgetID).getBudgetFinishDate());
                long maxDate = maxCal.getTime().getTime();
                budgetDS.close();

                dpl.getDatePicker().setMinDate(minDate);
                dpl.getDatePicker().setMaxDate(maxDate);
                dpl.show();
            }
        });


        final Button exitButton = (Button) findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                finish();
            }
        });

    }
}
