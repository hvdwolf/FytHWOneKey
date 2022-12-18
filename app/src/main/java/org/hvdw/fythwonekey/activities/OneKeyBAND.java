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
import org.hvdw.fythwonekey.Utils;


public class OneKeyBAND extends Activity {
    public static final String TAG = "OneKeyBAND";
    public static Context mContext;
    private static PackageManager pm;
    Toast mToast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();

        Log.i(TAG, "Started OneKeyBAND; in OnCreate void");

        String call_option = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.BAND_KEY_CALL_OPTION, "");
        String actionString = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.BAND_ACTIONSTRING_ENTRY, "");

        Utils myUtils = new Utils();
        myUtils.whichActionToPerform (mContext, call_option, actionString, "BAND");
        //myUtils.checkAndRunOptions(mContext, packagename_call);

        finish();
    }

}
