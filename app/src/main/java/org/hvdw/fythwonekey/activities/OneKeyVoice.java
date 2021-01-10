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

public class OneKeyVoice extends Activity {
    public static final String TAG = "OneKeyVoice";
    public static Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();

        Log.i(TAG, "Started OneKeyVoice; in OnCreate void");

        String key_call_option = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.VOICE_CALL_OPTION, "");
        String key_call_entry = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.AV_CALL_ENTRY, "");

        Utils myUtils = new Utils();
        myUtils.checkAndRunOptions(mContext, key_call_option);

        finish();
    }

}
