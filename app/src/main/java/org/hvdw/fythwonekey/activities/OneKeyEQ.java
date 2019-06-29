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
import android.view.KeyEvent;
import android.widget.Toast;

import android.media.AudioManager;


public class OneKeyEQ extends Activity {
    public static final String TAG = "OneKeyEQ";
    public static Context mContext;
    private static PackageManager pm;
    private String packagename_call;
    private String intent_call;
    private String sys_call;
    private String media_key_option;
    Toast mToast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();

        Log.d(TAG, "Started OneKeyEQ; in OnCreate void");

        packagename_call = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.EQ_PACKAGENAME_ENTRY, "");
        //intent_call = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.EQ_INTENT_ENTRY, "");
        //sys_call = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.EQ_SYSCALL_ENTRY, "");
        media_key_option = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.EQ_MEDIA_KEY_OPTION, "");

        Utils myUtils = new Utils();

        if ("pkgname".equals(media_key_option)) {
            //We do not use a media key option but start a package
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
        } else {
            //We use one of the play/pause/next/previous options
            myUtils.sendMediaCommand(mContext, media_key_option, true);
            myUtils.sendMediaCommand(mContext, media_key_option, false);
        }


        finish();
    }

}
