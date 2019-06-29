package org.hvdw.fythwonekey;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import org.hvdw.fythwonekey.MySettings;
import org.hvdw.fythwonekey.R;


public class BTAVReceiver extends BroadcastReceiver {
    public static final String TAG = "FHWO-BTAVReceiver";
    private static PackageManager pm;
    private String packagename_call;
    private String intent_call;
    private String sys_call;
    Toast mToast;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();

        packagename_call = PreferenceManager.getDefaultSharedPreferences(context).getString(MySettings.BTAV_PACKAGENAME_ENTRY, "");
        intent_call = PreferenceManager.getDefaultSharedPreferences(context).getString(MySettings.BTAV_INTENT_ENTRY, "");
        sys_call = PreferenceManager.getDefaultSharedPreferences(context).getString(MySettings.BTAV_SYSCALL_ENTRY, "");

        Utils myUtils = new Utils();

        if ("".equals(packagename_call)) {
            //packagename_call unknown, start setup
            Log.d(TAG, context.getResources().getString(R.string.pkg_notconfigured_short));
            //String message = extras.getString(R.string.pkg_sys_notconfigured_long);
            //mToast = Toast.makeText(context, getString(R.string.pkg_sys_notconfigured_long), Toast.LENGTH_SHORT);
            mToast = Toast.makeText(context, "BTAV replacement package not configured", Toast.LENGTH_SHORT);
            mToast.show();
            myUtils.startActivityByPackageName(context, "org.hvdw.fythwonekey");
        } else {
            Log.d(TAG, context.getResources().getString(R.string.pkg_configured_short) + " " + packagename_call);
            //Start BTAV app or whatever app the user has configured
            myUtils.startActivityByPackageName(context, packagename_call);
        }
    } /* end of onReceive */
}
