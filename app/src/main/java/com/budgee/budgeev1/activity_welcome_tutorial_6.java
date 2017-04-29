package com.budgee.budgeev1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_welcome_tutorial_6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_tutorial_6);

        //Set boolean so tutorial not repeated on future start-ups
        //Based on code provided by user harrakiss at http://stackoverflow.com/questions/4636141/determine-if-android-app-is-the-first-time-used
        //(Accessed 27/04/2017)
        SharedPreferences prefs = getSharedPreferences("login", 0);     //Change string to whatever file is called
        prefs.edit().putBoolean("first_launch", false).commit();

        //Adapted from code by Nikhil Agrawal available at http://stackoverflow.com/questions/16636752/android-button-onclicklistener accessed 11/04/2017
        final Button button = (Button) findViewById(R.id.tutorial_nextButton6);      //Find right button by ID
        button.setOnClickListener(new View.OnClickListener() {               //When button clicked
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_welcome_tutorial_6.this, MainActivity.class);         //Switch to homepage
                activity_welcome_tutorial_6.this.startActivity(i);                //Start activity
            }
        });
    }
}