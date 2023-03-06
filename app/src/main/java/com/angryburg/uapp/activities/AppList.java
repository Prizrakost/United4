package com.angryburg.uapp.activities;

import android.app.Activity;
import android.os.Bundle;

import com.angryburg.uapp.R;

public class AppList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.apps_list);
    }
}
