package com.example.johnkenny.footballer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by johnkenny on 21/11/2016.
 */


//this is an abstract class and the to access a fragment that can be used for every activity that requires the fragment

public abstract class SingleFragmentActivity extends AppCompatActivity {

    //the main FootballerActivity will implement this to create the fragment
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this is the activity this class uses
        setContentView(R.layout.activity_footballer);


        /* the manager is used to handle the list of fragments for this activity
            and adds their views to the list view
          */
        FragmentManager manager = getSupportFragmentManager();

        //this container view informs the manager the fragment should appear in the activity view
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);

        //verifies the fragment is not equal to null
        if (fragment == null) {
            //assigns the fragment to new fragment
            fragment = createFragment();
            //starts the transaction and commits it
            manager.beginTransaction()
                    .add(R.id.fragmentContainer, fragment).commit();

        }


    }//closes onCreate



}//closes this class
