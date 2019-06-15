package org.hvdw.fythwonekey.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import org.hvdw.fythwonekey.MySettings;
import org.hvdw.fythwonekey.utils.AppStartUtils;

import static org.hvdw.fythwonekey.utils.ShellUtils.shellExec;

public class AccOnReceiver extends BroadcastReceiver {
    public static final String TAG = "FHWO-AccOnReceiver";
    private boolean use_root_access;
    private boolean switch_wifi_on;
    private boolean restart_player;
    private String packagename_call;
    private String intent_call;
    private String sys_call;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();

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
                AppStartUtils myAppUtils = new AppStartUtils();
                shellExec("input keyevent 126");
            }

            if (packagename_call != "") {
                Log.d(TAG, "call apk by packagename");
                AppStartUtils myAppUtils = new AppStartUtils();
                myAppUtils.startActivityByPackageName(context, packagename_call);
            }

            if (intent_call != "") {
                Log.d(TAG, "call apk by intent");
                AppStartUtils myAppUtils = new AppStartUtils();
                myAppUtils.startActivityByIntentName(context, intent_call);
            }

            if (sys_call != "") {
                Log.d(TAG, "do a system call");
                AppStartUtils myAppUtils = new AppStartUtils();
                shellExec(sys_call);
            }
    } /* end of onReceive */
}
