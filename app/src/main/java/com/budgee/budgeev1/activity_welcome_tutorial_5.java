package com.budgee.budgeev1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_welcome_tutorial_5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_tutorial_5);
        //Adapted from code by Nikhil Agrawal available at http://stackoverflow.com/questions/16636752/android-button-onclicklistener accessed 11/04/2017
        final Button button = (Button) findViewById(R.id.tutorial_nextButton5);      //Find right button by ID
        button.setOnClickListener(new View.OnClickListener() {               //When button clicked
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_welcome_tutorial_5.this, activity_welcome_tutorial_6.class);         //Switch to homepage
                activity_welcome_tutorial_5.this.startActivity(i);                //Start activity
            }
        });
    }
}