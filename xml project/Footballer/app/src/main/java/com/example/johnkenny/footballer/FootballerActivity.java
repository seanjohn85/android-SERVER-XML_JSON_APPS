package com.example.johnkenny.footballer;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class FootballerActivity extends SingleFragmentActivity {


    //new fragment is created using the FootballerFragment class
    @Override
    protected Fragment createFragment() {

        return new FootballerListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //uses the super class
        super.onCreate(savedInstanceState);



    }





}
