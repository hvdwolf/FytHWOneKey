package org.hvdw.fythwonekey.activities;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
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

        String packagename_call = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.BTAV_PACKAGENAME_ENTRY, "");
        //String intent_call = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.BTAV_INTENT_ENTRY, "");
        //String sys_call = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.BTAV_SYSCALL_ENTRY, "");

        Utils myUtils = new Utils();
        myUtils.checkAndRunOptions(mContext, packagename_call);

        finish();
    }

}
