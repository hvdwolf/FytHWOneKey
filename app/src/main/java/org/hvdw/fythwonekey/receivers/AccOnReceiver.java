package org.hvdw.fythwonekey;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

public class AccOnReceiver extends BroadcastReceiver {
    public static final String TAG = "FHWO-AccOnReceiver";
    //private boolean use_root_access;
    private boolean switch_wifi_on;
    private boolean restart_player;
    private String packagename_call;
    private String intent_call;
    private String sys_call;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();

        Utils myUtils = new Utils();

        //use_root_access = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(MySettings.USE_ROOT_ACCESS, true);
        switch_wifi_on = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(MySettings.SWITCH_WIFI_ON, true);
        restart_player = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(MySettings.RESTART_PLAYER, true);
        packagename_call = PreferenceManager.getDefaultSharedPreferences(context).getString(MySettings.ACCON_PACKAGENAME_ENTRY, "");
        intent_call = PreferenceManager.getDefaultSharedPreferences(context).getString(MySettings.ACCON_INTENT_ENTRY, "");
        sys_call = PreferenceManager.getDefaultSharedPreferences(context).getString(MySettings.ACCON_SYSCALL_ENTRY, "");


        Log.d(TAG, "Detected an ACCON broadcast");

            if (switch_wifi_on == true) {
                Log.d(TAG, "Switch On WiFi");
                WifiManager wifiManager = (WifiManager) context.getSystemService(context.WIFI_SERVICE);
                wifiManager.setWifiEnabled(true);
                Log.d(TAG, "Switched On WiFi");
            } else {
                Log.d(TAG, "It is not requested to switch on WiFi");
            }

            if (restart_player == true) {
                Log.d(TAG, "Restart the active default media player");
                myUtils.shellExec("input keyevent 126");
            }

            if (!"".equals(packagename_call)) {
                Log.d(TAG, "call apk by packagename");
                myUtils.startActivityByPackageName(context, packagename_call);
            }

            if (!"".equals(intent_call)) {
                Log.d(TAG, "call apk by intent");
                myUtils.startActivityByIntentName(context, intent_call);
            }

            if (!"".equals(sys_call)) {
                Log.d(TAG, "do a system call");
                myUtils.shellExec(sys_call);
            }
    } /* end of onReceive */
}
