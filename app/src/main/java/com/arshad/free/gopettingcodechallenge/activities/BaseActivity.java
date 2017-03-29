package com.arshad.free.gopettingcodechallenge.activities;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.arshad.free.gopettingcodechallenge.models.UpcomingGuide;

import java.util.ArrayList;

/**
 * Created by arshad on 29/3/17.
 */

public class BaseActivity extends AppCompatActivity {

    public void showSnackbar(String message, View view){
        Snackbar snackbar = Snackbar
                .make(view, message,  Snackbar.LENGTH_LONG);

        snackbar.show();
    }

}
