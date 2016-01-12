package com.sevenrealm.base.imovies;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class MainActivity extends AppCompatActivity {
    MainActivityFragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.fragment_pane) != null){
            mTwoPane = true;
            if (savedInstanceState == null) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                if (fragment != null) {
                    fragmentTransaction.remove(fragment);
                }
                fragment = new MainActivityFragment();
                fragmentTransaction.replace(R.id.fragment, fragment);
//        fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
            }else {
            mTwoPane = false;
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                if (fragment != null) {
                    fragmentTransaction.remove(fragment);
                }
                fragment = new MainActivityFragment();
                fragmentTransaction.replace(R.id.fragment, fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



//    @Override
//    public void onBackPressed() {
//        if (fragmentManager.getBackStackEntryCount() == 1) {
//            this.finish();
//        } else {
//            fragmentManager.popBackStack();
//        }
//    }
}
