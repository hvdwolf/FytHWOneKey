package org.hvdw.fythwonekey;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class AppAdapter extends ArrayAdapter<ApplicationInfo> {

private List<ApplicationInfo> applist = null;
private Context context;
private PackageManager packageManager;
private String appNameV;
AppAdapter(Context context, int resource, List<ApplicationInfo> objects) {
        super(context, resource,  objects);

        this.context=context;
        this.applist=objects;
        packageManager=context.getPackageManager();
        }

@Override
public int getCount() {
        return ((null != applist) ? applist.size() : 0);
        }

@Override
public ApplicationInfo getItem(int position) {
        return ((null != applist) ? applist.get(position) : null);
        }

@Override
public long getItemId(int position) {
        return position;
        }

@Override
public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;

        if(null == view){
        LayoutInflater layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.listitem,null);
        }
        ApplicationInfo data=applist.get(position);
        if(null != data){
        TextView appName=(TextView) view.findViewById(R.id.appName);

        TextView packaName=(TextView) view.findViewById(R.id.appPackage);
        ImageView iconView=(ImageView) view.findViewById(R.id.appIcon);

        appName.setText(data.loadLabel(packageManager));
        appNameV= (String) data.loadLabel(packageManager);
         packaName.setText(data.packageName);
        iconView.setImageDrawable(data.loadIcon(packageManager));
        }
        return view;
      }
    }