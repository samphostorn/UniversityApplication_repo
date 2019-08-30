package com.example.universityapplication.model;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class FragmentHelper {

    private static AppCompatActivity appActivity;

    public static void addFragment(AppCompatActivity activity, @IdRes int container, Fragment fragment)
    {
        if(fragment != null)
        {
            appActivity = activity;
            FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
            transaction.add(container, fragment);
            transaction.commit();
        }
    }

    public static void replaceFragment(@IdRes int container, Fragment fragment, boolean addToBackStack)
    {
        if(fragment != null)
        {
            FragmentTransaction transaction = appActivity.getSupportFragmentManager().beginTransaction();
            transaction.replace(container,fragment);
            if(addToBackStack) transaction.addToBackStack(fragment.getClass().getName());
            transaction.commit();
        }
    }
}
