package com.spinner.girmayilma.spinner;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    String[] stringArray = { "Amharic", "English", "Italian", "German", "Greece" };// String array to be adapted for spinner

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preventSoftKeyboardDisplayExceptEditText(findViewById(R.id.parent));//parent id refers to the id of the main activity layout
        /*comment the above line if you don't want
         to hide the keyboard for all instance of other view groups*/

        spinner = (Spinner) findViewById(R.id.spinner);//get spinner
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                                                                           stringArray);
        spinner.setAdapter(stringArrayAdapter);//adapt the spinner

        /* remove the block comment line below to prevent soft keyboard while the spinner view gets focused*/

  /*  spinner.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideSoftKeyboard(getFocusedView());
                return false;
            }
        });*/

    }

    /*function to hide keyboard*/
    public static void hideSoftKeyboard(View v) {
        if (v!=null) {
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    /*function to get focused view from activity*/
    private View getFocusedView() {
        View mWindow = (View) getCurrentFocus();
        return mWindow!=null ? mWindow : null;

    }

    /*Function to hide the soft keyboard except EditText instance*/
    public void preventSoftKeyboardDisplayExceptEditText(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(v);
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i<((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                preventSoftKeyboardDisplayExceptEditText(innerView);
            }
        }
    }

}
