package com.angryburg.uapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.media.Image;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
        final List<ResolveInfo> pkgAppsList = getPackageManager().queryIntentActivities( mainIntent, 0);

        final String[] pkgAppsListNames = new String[pkgAppsList.size()];
        final int[] pkgAppsListIcons = new int[pkgAppsList.size()];

        for (int i = 0; i < pkgAppsList.size(); i++) {
            pkgAppsListNames[i] = pkgAppsList.get(i).loadLabel(getPackageManager()).toString();
        }
        for (int i = 0; i < pkgAppsList.size(); i++) {
            pkgAppsListIcons[i] = pkgAppsList.get(i).icon;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.list_item, R.id.text_view_cat_name, pkgAppsListNames);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                //startActivity(new Intent(pkgAppsList.get(position).resolvePackageName));
                //startActivity(new Intent(pkgAppsList.get(position).activityInfo.targetActivity));
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(pkgAppsList.get(position).activityInfo.taskAffinity);
                startActivity(launchIntent);
            }
        });
    }
}
