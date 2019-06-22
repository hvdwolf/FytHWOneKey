package org.hvdw.fythwonekey;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.ComponentName;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;


public class OneKeyEQ extends Activity {
    public static final String TAG = "OneKeyEQ";
    public static Context mContext;
    private static PackageManager pm;
    private String packagename_call;
    private String intent_call;
    private String sys_call;
    Toast mToast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();

        Log.d(TAG, "Started OneKeyEQ; in OnCreate void");

        Utils myUtils = new Utils();
        packagename_call = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.EQ_PACKAGENAME_ENTRY, "");
        intent_call = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.EQ_INTENT_ENTRY, "");
        sys_call = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.EQ_SYSCALL_ENTRY, "");

        //Toast mToast = Toast.makeText(OneKeyEQ.this, "In On Create", Toast.LENGTH_LONG);
        //mToast.show();

        if ("".equals(packagename_call)) {
            //packagename_call unknown, start setup
            Log.d(TAG, getString(R.string.pkg_notconfigured_short));
            mToast = Toast.makeText(OneKeyEQ.this, getString(R.string.pkg_notconfigured_long), Toast.LENGTH_SHORT);
            mToast.show();
            myUtils.startActivityByPackageName(mContext, "org.hvdw.fythwonekey");
        } else {
            Log.d(TAG, getString(R.string.pkg_configured_short) + " " + packagename_call);
            //Start EQ app or whatever app the user has configured
            myUtils.startActivityByPackageName(mContext, packagename_call);
        }

        /*if (!"".equals(sys_call)) {
            Log.d(TAG, "do a system call");
            myUtils.shellExec("input keyevent 87");
        }*/
        //myUtils.checkAndRunOptions(packagename_call, intent_call, sys_call);
        //Log.d(TAG, "did the myAppUtils.checkAndRunOptions with pkg and syscall: " + packagename_call + " " + sys_call);

        /*Log.d(TAG, "Start myUtils.executeSystemCall with : " + sys_call);
        myUtils.executeSystemCall(sys_call); */

        finish();
    }

}
