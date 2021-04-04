package org.hvdw.fythwonekey.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.content.Context;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.widget.Toast;

import org.hvdw.fythwonekey.MySettings;
import org.hvdw.fythwonekey.Utils;


public class OneKeyAV extends Activity {
    public static final String TAG = "OneKeyAV";
    public static Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();

        Log.i(TAG, "Started OneKeyAV; in OnCreate void");

        String key_call_option = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.AV_CALL_OPTION, "");
        String key_call_entry = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.AV_CALL_ENTRY, "");

        Utils myUtils = new Utils();
        myUtils.whichActionToPerform(mContext, key_call_option, key_call_entry);
        //myUtils.checkAndRunOptions(mContext, key_call_option);

        finish();
    }

}
