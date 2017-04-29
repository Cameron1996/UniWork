package com.budgee.budgeev1;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Locale;


public class CreateBudget extends Activity {

    private BudgetDataSource budgetDS;
    private CategoryDataSource categoryDS;
    private BudCatLinkDataSource budCatLinkDS;

    Category currentCategory;
    ArrayList<Category> categories;
    ArrayList<BudCatLink> tempLinks;

    final Calendar startCalendar = Calendar.getInstance();
    final Calendar finCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener startDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            startCalendar.set(Calendar.YEAR, year);
            startCalendar.set(Calendar.MONTH, monthOfYear);
            startCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateStartLabel();
        }
    };

    DatePickerDialog.OnDateSetListener finDate = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            finCalendar.set(Calendar.YEAR, year);
            finCalendar.set(Calendar.MONTH, monthOfYear);
            finCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateFinLabel();
        }
    };

    private void updateStartLabel(){
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        EditText ed = (EditText) findViewById(R.id.startDate);

        ed.setText(sdf.format(startCalendar.getTime()));
    }

    private void updateFinLabel(){

        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.UK);

        EditText ed = (EditText) findViewById(R.id.finDate);

        ed.setText(sdf.format(finCalendar.getTime()));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_create);

        budgetDS = new BudgetDataSource(this);
        categoryDS = new CategoryDataSource(this);
        budCatLinkDS = new BudCatLinkDataSource(this);

        categoryDS.open();
        categories = new ArrayList<Category>(categoryDS.getAllCategories());
        categoryDS.close();

        tempLinks = new ArrayList<BudCatLink>();

        final Spinner spinner = (Spinner) findViewById(R.id.categorySpinner);
        final CategoryAdapter catAdapt = new CategoryAdapter(getApplicationContext(), categories);
        catAdapt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(catAdapt);

        final Button addCategoryButton = (Button) findViewById(R.id.confirmAddCategoryButton);
        addCategoryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView t = (TextView)findViewById(R.id.tvAllCategoriesCurrentBudget);
                EditText pounds = (EditText)findViewById(R.id.priceInputPounds);
                EditText pence = (EditText)findViewById(R.id.priceInputPence);

                String poundsString = pounds.getText().toString();
                String penceString = pounds.getText().toString();

                if (currentCategory.equals(null) || poundsString.matches("") || poundsString.matches("")) {
                    return; //If any category is not filled, the program will not add the category
                }

                t.setText(t.getText() + currentCategory.getCategoryName() + " - £"  + pounds.getText().toString() + "." + pence.getText().toString() + "\r\n");

                BudCatLink budCatLink = new BudCatLink();
                budCatLink.setCategoryID(currentCategory.getCategoryID());
                budCatLink.setCatBudgetAmount(new BigDecimal(pounds.getText().toString() + "." + pence.getText().toString()));

                tempLinks.add(budCatLink);

                spinner.setSelection(0); //Reset all values
                pounds.setText("");
                pence.setText("");

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentCategory = (Category) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        final Button confirmNewCategoryButton = (Button) findViewById(R.id.confirmNewCategoryButton);
        confirmNewCategoryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                TextView t = (TextView)findViewById(R.id.tvAllCategoriesCurrentBudget);
                EditText categoryName = (EditText)findViewById(R.id.categoryNameInput);
                EditText pounds = (EditText)findViewById(R.id.priceInputPounds2);
                EditText pence = (EditText)findViewById(R.id.priceInputPence2);

                String categoryNameString = categoryName.getText().toString();
                String poundsString = pounds.getText().toString();
                String penceString = pounds.getText().toString();

                if (categoryNameString.matches("") || poundsString.matches("") || penceString.matches("")) {
                    return; //If any category is not filled, the program will not add the category
                }

                categoryDS.open();
                Category c = categoryDS.createCategory(categoryName.getText().toString());
                categories = new ArrayList<Category>(categoryDS.getAllCategories());
                categoryDS.close();

                catAdapt.notifyDataSetChanged();

                t.setText(t.getText().toString() + c.getCategoryName() + " - £"  + pounds.getText().toString() + "." + pence.getText().toString() + "\r\n");

                BudCatLink budCatLink = new BudCatLink();
                budCatLink.setCategoryID(c.getCategoryID());
                budCatLink.setCatBudgetAmount(new BigDecimal(pounds.getText().toString() + "." + pence.getText().toString()));

                tempLinks.add(budCatLink);

                categoryName.setText("");
                pounds.setText("");
                pence.setText("");
            }
        });

       final EditText startDate = (EditText) findViewById(R.id.startDate);
       startDate.setOnClickListener(new View.OnClickListener() {
          @Override
           public void onClick(View v) {
              new DatePickerDialog(CreateBudget.this, CreateBudget.this.startDate, startCalendar
                     .get(Calendar.YEAR), startCalendar.get(Calendar.MONTH),
                       startCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final EditText finDate = (EditText) findViewById(R.id.finDate);
        finDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(CreateBudget.this, CreateBudget.this.finDate, finCalendar
                        .get(Calendar.YEAR), finCalendar.get(Calendar.MONTH),
                        finCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        final Button confirmBudgetButton = (Button) findViewById(R.id.confirmBudgetButton);
        confirmBudgetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                TextView t = (TextView)findViewById(R.id.tvAllCategoriesCurrentBudget);
                EditText startDateED = (EditText)findViewById(R.id.startDate);
                EditText finDateED = (EditText)findViewById(R.id.finDate);

                String tString = t.getText().toString();
                String startDateString = startDateED.getText().toString();
                String finDateString = finDateED.getText().toString();

                if (startDateString.matches("") || finDateString.matches("") || tString.matches("")) {
                    return;
                }

                budgetDS.open();
                Budget b = budgetDS.createBudget(startCalendar.getTime(), finCalendar.getTime());
                budgetDS.close();

                for(BudCatLink link: tempLinks){
                    budCatLinkDS.open();
                    budCatLinkDS.createBudCatLink(b.getBudgetID(), link.getCategoryID(), link.getCatBudgetAmount().toString());
                    budCatLinkDS.close();
                }
                finish();
        }
        });

        final Button clearBudgetButton = (Button) findViewById(R.id.clearBudgetButton);
        clearBudgetButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                TextView t = (TextView)findViewById(R.id.tvAllCategoriesCurrentBudget);
                t.setText("");
                tempLinks.clear();
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
