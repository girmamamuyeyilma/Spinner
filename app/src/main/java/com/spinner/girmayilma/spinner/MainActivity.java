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
   // EditText mEditText;
    String[] stringArray = { "Amharic", "English", "Italian", "German", "Greece" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preventSoftKeyboardDisplayExceptEditText(findViewById(R.id.parent));

        spinner = (Spinner) findViewById(R.id.spinner);
        //mEditText = (EditText) findViewById(R.id.edit_text);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,
                                                                           stringArray);
        spinner.setAdapter(stringArrayAdapter);

  /*     spinner.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                hideSoftKeyboard(getFocusedView());
                return false;
            }
        });*/

    }

    public static void hideSoftKeyboard(View v) {
        if (v!=null) {
            InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }


   private View getFocusedView(){
       View mWindow = (View) getCurrentFocus();
       return mWindow != null ? mWindow : null;

   }



    public void preventSoftKeyboardDisplayExceptEditText(View view) {

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            view.setOnTouchListener(new View.OnTouchListener() {

                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(v);
                    return false;
                }

            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                preventSoftKeyboardDisplayExceptEditText(innerView);
            }
        }
    }

}
