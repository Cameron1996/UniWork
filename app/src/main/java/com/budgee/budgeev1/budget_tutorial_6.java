package com.budgee.budgeev1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;


public class budget_tutorial_6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_tutorial_6);


        //Adapted from code by Nikhil Agrawal available at http://stackoverflow.com/questions/16636752/android-button-onclicklistener accessed 11/04/2017
        final Button button = (Button) findViewById(R.id.button15);      //Find right button by ID
        button.setOnClickListener(new View.OnClickListener() {          //When button clicked
            @Override
            public void onClick(View v) {
                Intent i = new Intent(budget_tutorial_6.this, budget_tutorial_5.class); //Will switch to new activity
                budget_tutorial_6.this.startActivity(i);        //Start activity
            }
        });

        final Button button2 = (Button) findViewById(R.id.button16);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(budget_tutorial_6.this, budget_tutorial_1.class);         //This needs to change to point to home page
                budget_tutorial_6.this.startActivity(j);
            }
        });
    }
    //Storing state - the following based on code provided by user Wroclai
    //http://stackoverflow.com/questions/5692869/how-to-save-the-state-of-an-android-checkbox-when-the-users-exits-the-applicatio
    //Accessed 16/04/2017

    final CheckBox check1 = (CheckBox) findViewById(R.id.checkBox17);
    final CheckBox check2 = (CheckBox) findViewById(R.id.checkBox18);
    final CheckBox check3 = (CheckBox) findViewById(R.id.checkBox19);

    /*@Override
    public void onPause() {             //When app paused
        super.onPause();
        save(check1.isChecked());       //Save state of checkboxes
        save(check2.isChecked());
        save(check3.isChecked());
    }

    @Override
    public void onResume() {            //When resuming app
        super.onResume();
        check1.setChecked(load());      //Load state of checkboxes
        check2.setChecked(load());
        check3.setChecked(load());
    }

    private void save(final boolean isChecked) {            //Save state
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("check", isChecked);              //Store state in preferences
        editor.commit();
    }

    private boolean load() {
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        return preferences.getBoolean("check", false);      //Retrieve value with key "check", default boolean value false
    }*/
}
