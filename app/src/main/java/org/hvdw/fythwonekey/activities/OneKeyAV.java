package org.hvdw.fythwonekey.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.content.Context;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.hvdw.fythwonekey.MySettings;
import org.hvdw.fythwonekey.R;
import org.hvdw.fythwonekey.utils.AppStartUtils;


public class OneKeyAV extends Activity {
    public static final String TAG = "OneKeyAV";
    public Context mContext;
    private static PackageManager pm;
    private String packagename_call;
    private String intent_call;
    private String sys_call;
    Toast mToast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();

        Log.d(TAG, "Started OneKeyAV; in OnCreate void");

        packagename_call = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.AV_PACKAGENAME_ENTRY, "");
        intent_call = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.AV_INTENT_ENTRY, "");
        sys_call = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.AV_SYSCALL_ENTRY, "");

        //Toast mToast = Toast.makeText(OneKeyAV.this, "In On Create", Toast.LENGTH_LONG);
        //mToast.show();
        AppStartUtils myAppUtils = new AppStartUtils();

        if ("".equals(packagename_call)) {
            //packagename_call unknown, start setup
            Log.d(TAG, getString(R.string.pkg_notconfigured_short));
            mToast = Toast.makeText(OneKeyAV.this, getString(R.string.pkg_notconfigured_long), Toast.LENGTH_SHORT);
            mToast.show();
            myAppUtils.startActivityByPackageName(mContext, "org.hvdw.fythwonekey");
        } else {
            Log.d(TAG, getString(R.string.pkg_configured_short) + " " + packagename_call);
            //Start AV app or whatever app the user has configured
            myAppUtils.startActivityByPackageName(mContext, packagename_call);
        }

        finish();
    }

}
