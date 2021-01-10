package org.hvdw.fythwonekey.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;

import org.hvdw.fythwonekey.MySettings;
import org.hvdw.fythwonekey.Utils;


public class BTAVReceiver extends BroadcastReceiver {
    public static final String TAG = "FHWO-BTAVReceiver";
    public static Context mContext;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();

        String key_call_option = PreferenceManager.getDefaultSharedPreferences(context).getString(MySettings.BTAV_CALL_OPTION, "");
        String key_call_entry = PreferenceManager.getDefaultSharedPreferences(context).getString(MySettings.BTAV_CALL_ENTRY, "");

        Utils myUtils = new Utils();

        myUtils.whichActionToPerform(mContext, key_call_option, key_call_entry);
    } /* end of onReceive */
}
