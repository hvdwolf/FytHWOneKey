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


public class OneKeyBTAV extends Activity {
    public static final String TAG = "OneKeyBTAV";
    public static Context mContext;
    private static PackageManager pm;
    Toast mToast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();

        Log.i(TAG, "Started OneKeyBTAV; in OnCreate void");

        String key_call_option = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.BTAV_CALL_OPTION, "");
        String key_call_entry = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.BTAV_CALL_ENTRY, "");

        Utils myUtils = new Utils();
        myUtils.whichActionToPerform(mContext, key_call_option, key_call_entry);
        //myUtils.checkAndRunOptions(mContext, key_call_option);

        finish();
    }

}
