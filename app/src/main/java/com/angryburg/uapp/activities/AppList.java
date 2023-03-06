package com.angryburg.uapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.angryburg.uapp.R;

import java.util.List;

public class AppList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.apps_list);

        ListView listView = findViewById(R.id.applist);
        /*
        final String[] catNames = new String[] {
                "Рыжик", "Барсик", "Мурзик", "Мурка", "Васька",
                "Томасина", "Кристина", "Пушок", "Дымка", "Кузя",
                "Китти", "Масяня", "Симба"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, catNames);

        listView.setAdapter(adapter);

         */
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = getPackageManager().queryIntentActivities( mainIntent, 0);

        final String[] pkgAppsListNames = new String[pkgAppsList.size()];

        for (int i = 0; i < pkgAppsList.size(); i++) {
            pkgAppsListNames[i] = pkgAppsList.get(i).loadLabel(getPackageManager()).toString();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, pkgAppsListNames);
        listView.setAdapter(adapter);
    }
}
