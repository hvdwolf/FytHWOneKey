package org.hvdw.fythwonekey.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.content.Context;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.widget.Toast;

import android.media.AudioManager;

import org.hvdw.fythwonekey.MySettings;
import org.hvdw.fythwonekey.R;
import org.hvdw.fythwonekey.Utils;


public class OneKeyEQ extends Activity {
    public static final String TAG = "OneKeyEQ";
    public static Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();

        Log.i(TAG, "Started OneKeyEQ; in OnCreate void");

        String key_call_option = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.EQ_CALL_OPTION, "");
        /*String media_key_option = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.EQ_MEDIA_KEY_OPTION, "");

        Utils myUtils = new Utils();

        if ("pkgname".equals(key_call_option)) {
            //We do not use a media key option but start a package
            if ("".equals(key_call_option)) {
                //key_call_option unknown, start setup
                Log.i(TAG, getString(R.string.pkg_notconfigured_short));
                mToast = Toast.makeText(OneKeyEQ.this, getString(R.string.pkg_notconfigured_long), Toast.LENGTH_SHORT);
                mToast.show();
                myUtils.startActivityByPackageName(mContext, "org.hvdw.fythwonekey");
            } else {
                Log.i(TAG, getString(R.string.pkg_configured_short) + " " + key_call_option);
                //Start EQ app or whatever app the user has configured
                myUtils.startActivityByPackageName(mContext, key_call_option);
            }
        } else {
            //We use one of the play/pause/next/previous options
            myUtils.sendMediaCommand(mContext, media_key_option, true);
            myUtils.sendMediaCommand(mContext, media_key_option, false);
        } */


        finish();
    }

}
