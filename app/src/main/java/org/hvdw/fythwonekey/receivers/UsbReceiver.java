package org.hvdw.fythwonekey.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import org.hvdw.fythwonekey.MySettings;
import org.hvdw.fythwonekey.Utils;

public class UsbReceiver extends BroadcastReceiver {
    public static final String TAG = "FHWO-UsbReceiver";
    //private boolean use_root_access;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();

        Utils myUtils = new Utils();

        //use_root_access = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(MySettings.USE_ROOT_ACCESS, true);
        boolean switch_wifi_on = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(MySettings.SWITCH_WIFI_ON, true);
        boolean restart_player = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(MySettings.RESTART_PLAYER, true);
        String packagename_call = PreferenceManager.getDefaultSharedPreferences(context).getString(MySettings.USBON_PACKAGENAME_ENTRY, "");
        String intent_call = PreferenceManager.getDefaultSharedPreferences(context).getString(MySettings.USBON_INTENT_ENTRY, "");
        String sys_call = PreferenceManager.getDefaultSharedPreferences(context).getString(MySettings.USBON_SYSCALL_ENTRY, "");


        Log.i(TAG, "Detected a USB Device connect broadcast");

            if (switch_wifi_on == true) {
                Log.i(TAG, "Switch On WiFi");
                WifiManager wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(true);
                Log.i(TAG, "Switched On WiFi");
            } else {
                Log.i(TAG, "It is not requested to switch on WiFi");
            }

            if (restart_player == true) {
                Log.i(TAG, "Restart the active default media player");
                //myUtils.shellExec("input keyevent 126");
                myUtils.sendMediaCommand(context, "KEYCODE_MEDIA_PLAY", true);
                myUtils.sendMediaCommand(context, "KEYCODE_MEDIA_PLAY", false);
            }

            if (!"".equals(packagename_call)) {
                Log.i(TAG, "call apk by packagename");
                myUtils.startActivityByPackageName(context, packagename_call);
            }

            if (!"".equals(intent_call)) {
                Log.i(TAG, "call apk by intent");
                myUtils.startActivityByIntentName(context, intent_call);
            }

            if (!"".equals(sys_call)) {
                Log.i(TAG, "do a system call");
                myUtils.shellExec(sys_call);
            }
    } /* end of onReceive */
}
