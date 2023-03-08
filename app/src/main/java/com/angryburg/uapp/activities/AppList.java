package com.angryburg.uapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.angryburg.uapp.R;

import java.util.List;

public class AppList extends Activity {

    private List<ResolveInfo> pkgAppsList;
    private String[] pkgAppsListNames;
    private Drawable[] pkgAppsListIcons;

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
        pkgAppsList = getPackageManager().queryIntentActivities( mainIntent, 0);

        pkgAppsListNames = new String[pkgAppsList.size()];
        pkgAppsListIcons = new Drawable[pkgAppsList.size()];

        for (int i = 0; i < pkgAppsList.size(); i++) {
            pkgAppsListNames[i] = pkgAppsList.get(i).loadLabel(getPackageManager()).toString();
        }
        PackageManager pm = getPackageManager();
        for (int i = 0; i < pkgAppsList.size(); i++) {
            pkgAppsListIcons[i] = pkgAppsList.get(i).activityInfo.loadIcon(pm);
        }

        /*
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                R.layout.list_item, R.id.text_view_app_name, pkgAppsListNames);
        listView.setAdapter(adapter);*/

        listView.setAdapter(new AppListAdapter(this, R.layout.list_item, pkgAppsListNames));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                //startActivity(new Intent(pkgAppsList.get(position).resolvePackageName));
                //startActivity(new Intent(pkgAppsList.get(position).activityInfo.targetActivity));
                Log.println(Log.DEBUG, "tag", pkgAppsList.get(position).activityInfo.applicationInfo.packageName);
                Intent launchIntent = getPackageManager().getLaunchIntentForPackage(pkgAppsList.get(position).activityInfo.taskAffinity);
                startActivity(launchIntent);
            }
        });
    }

    private class AppListAdapter extends ArrayAdapter<String> {
        AppListAdapter(Context context, int textViewResourceId, String[] objects) {
            super(context, textViewResourceId, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.list_item, parent, false);
            TextView label = (TextView) row.findViewById(R.id.text_view_app_name);
            label.setText(pkgAppsListNames[position]);
            ImageView iconImageView = (ImageView) row.findViewById(R.id.image_view_icon);
            //iconImageView.setImageResource(pkgAppsListIcons[position]);
            if (!"com.angryburg.uapp".equals(pkgAppsList.get(position).activityInfo.applicationInfo.packageName)) {
                iconImageView.setImageDrawable(pkgAppsListIcons[position]);
            } else {
                iconImageView.setImageResource(R.mipmap.star_fill);
            }
            return row;
        }
    }
}
