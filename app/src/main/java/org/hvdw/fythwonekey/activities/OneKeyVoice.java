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
    private static PackageManager pm;
    Toast mToast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();

        Log.i(TAG, "Started OneKeyVoice; in OnCreate void");

        String call_option = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.VOICE_KEY_CALL_OPTION, "");
        String actionString = PreferenceManager.getDefaultSharedPreferences(mContext).getString(MySettings.VOICE_ACTIONSTRING_ENTRY, "");

        Utils myUtils = new Utils();
        myUtils.whichActionToPerform (mContext, call_option, actionString, "Voice");
        //myUtils.checkAndRunOptions(mContext, packagename_call);

        finish();
    }

}
