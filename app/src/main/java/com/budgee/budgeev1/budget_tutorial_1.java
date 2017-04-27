package team3.budgee.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import team3.budgee.R;

public class budget_tutorial_1 extends AppCompatActivity {

    final CheckBox check1 = (CheckBox) findViewById(R.id.checkBox2);
    final CheckBox check2 = (CheckBox) findViewById(R.id.checkBox3);
    final CheckBox check3 = (CheckBox) findViewById(R.id.checkBox4);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_tutorial_1);

        /*if(savedInstanceState != null){
            //Restore state of checkboxes
            check1.setChecked(savedInstanceState.getBoolean("check"));
            check2.setChecked(savedInstanceState.getBoolean("check"));
            check3.setChecked(savedInstanceState.getBoolean("check"));
        }*/

        //Adapted from code by Nikhil Agrawal available at http://stackoverflow.com/questions/16636752/android-button-onclicklistener accessed 11/04/2017
        final Button button = (Button) findViewById(R.id.button6);      //Find right button by ID
        button.setOnClickListener(new OnClickListener() {               //When button clicked
            @Override
            public void onClick(View v) {
                Intent i = new Intent(budget_tutorial_1.this, budget_tutorial_2.class);         //Will switch to new activity
                budget_tutorial_1.this.startActivity(i);                //Start activity
            }
        });
    }
    //Storing state - the following based on code provided by user Wroclai
    //http://stackoverflow.com/questions/5692869/how-to-save-the-state-of-an-android-checkbox-when-the-users-exits-the-applicatio
    //Accessed 16/04/2017


    @Override
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
    }

    /*@Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        //Save state of checkboxes
        savedInstanceState.putBoolean("check", check1.isChecked());
        savedInstanceState.putBoolean("check", check2.isChecked());
        savedInstanceState.putBoolean("check", check3.isChecked());
        super.onSaveInstanceState(savedInstanceState);
    }*/


}
