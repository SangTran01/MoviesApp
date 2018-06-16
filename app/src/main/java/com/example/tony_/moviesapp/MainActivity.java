package com.example.tony_.moviesapp;

import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String FRAGMENT_TAG_STRING = "fragment1";

    private MainFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v("Main", "activity create");
        setContentView(R.layout.activity_main);

        //check to see if its portrait
        if (findViewById(R.id.fragment_container) != null) {
            if(savedInstanceState == null) {
                fragment = new MainFragment();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment, FRAGMENT_TAG_STRING).commit(); // Use tags, it's simpler to deal with
            } else {
                fragment = (MainFragment) getFragmentManager().findFragmentByTag(FRAGMENT_TAG_STRING);
            }
        }
    }
}
