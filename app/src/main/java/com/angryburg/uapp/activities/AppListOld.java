package com.angryburg.uapp.activities;

import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.angryburg.uapp.R;

import android.app.Activity;


public class AppListOld extends ListActivity {
    TextView selection;

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        ListView listView = findViewById(R.id.applist);
        String[] items = { "this", "is", "a", "really",
                "silly", "list" };
        super.onCreate(savedInstanceState);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                items
        );
        listView.setAdapter(adapter);
    }

    /*@Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String text = " position:" + position + "  " + items[position];
        selection.setText(text);
    }*/

}