package org.hvdw.fythwonekey;

import android.util.Log;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.widget.Toast;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;

import android.os.Handler;
import android.widget.ProgressBar;
import android.util.AttributeSet;


public class SettingsFragment extends PreferenceFragment implements OnSharedPreferenceChangeListener {
    Context mContext;
    AttributeSet attrs;

    private ProgressBar pb;
    static Runnable myRunnable;
    private static Handler myHandler;
    private boolean zygote_reboot;

    private BroadcastReceiver broadcastReceiver;
    IntentFilter intentFilter = new IntentFilter();

    public static final String TAG = "FHWOK-prefs";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = getActivity();
    }


    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.N){
            Log.d(TAG, "onCreate: in Sofia 6.0.1 sdk23");
            //Running on Sofia 6.0.1 sdk23
            getPreferenceManager().setSharedPreferencesMode(Context.MODE_WORLD_READABLE);
            addPreferencesFromResource(R.xml.preferences);
        } else {
            Log.d(TAG, "onCreate: Running on Android 8.0.0 sdk26");
            getPreferenceManager().setSharedPreferencesMode(Context.MODE_PRIVATE);
            addPreferencesFromResource(R.xml.preferences);
        }

        getActivity().registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void onResume() {
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        getActivity().registerReceiver(broadcastReceiver, intentFilter);
        super.onResume();
    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Intent intent = new Intent();
        String toastText = "";
        String additionalText = "";

        switch (key) {
            case MySettings.AV_PACKAGENAME_ENTRY:
                intent.setAction(MySettings.ACTION_AV_PACKAGENAME_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_AV_PACKAGENAME_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.AV_INTENT_ENTRY:
                intent.setAction(MySettings.ACTION_AV_INTENT_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_AV_INTENT_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.AV_SYSCALL_ENTRY:
                intent.setAction(MySettings.ACTION_AV_SYSCALL_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_AV_SYSCALL_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.BAND_PACKAGENAME_ENTRY:
                intent.setAction(MySettings.ACTION_BAND_PACKAGENAME_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_BAND_PACKAGENAME_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.BAND_INTENT_ENTRY:
                intent.setAction(MySettings.ACTION_BAND_INTENT_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_BAND_INTENT_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.BAND_SYSCALL_ENTRY:
                intent.setAction(MySettings.ACTION_BAND_SYSCALL_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_BAND_SYSCALL_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.BTAV_PACKAGENAME_ENTRY:
                intent.setAction(MySettings.ACTION_BTAV_PACKAGENAME_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_BTAV_PACKAGENAME_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.BTAV_INTENT_ENTRY:
                intent.setAction(MySettings.ACTION_BTAV_INTENT_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_BTAV_INTENT_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.BTAV_SYSCALL_ENTRY:
                intent.setAction(MySettings.ACTION_BTAV_SYSCALL_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_BTAV_SYSCALL_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.BTPHONE_PACKAGENAME_ENTRY:
                intent.setAction(MySettings.ACTION_BTPHONE_PACKAGENAME_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_BTPHONE_PACKAGENAME_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.BTPHONE_INTENT_ENTRY:
                intent.setAction(MySettings.ACTION_BTPHONE_INTENT_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_BTPHONE_INTENT_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.BTPHONE_SYSCALL_ENTRY:
                intent.setAction(MySettings.ACTION_BTPHONE_SYSCALL_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_BTPHONE_SYSCALL_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.DVD_PACKAGENAME_ENTRY:
                intent.setAction(MySettings.ACTION_DVD_PACKAGENAME_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_DVD_PACKAGENAME_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.DVD_INTENT_ENTRY:
                intent.setAction(MySettings.ACTION_DVD_INTENT_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_DVD_INTENT_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.DVD_SYSCALL_ENTRY:
                intent.setAction(MySettings.ACTION_DVD_SYSCALL_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_DVD_SYSCALL_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.EQ_PACKAGENAME_ENTRY:
                intent.setAction(MySettings.ACTION_EQ_PACKAGENAME_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_EQ_PACKAGENAME_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.EQ_INTENT_ENTRY:
                intent.setAction(MySettings.ACTION_EQ_INTENT_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_EQ_INTENT_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.EQ_SYSCALL_ENTRY:
                intent.setAction(MySettings.ACTION_EQ_SYSCALL_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_EQ_SYSCALL_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.EQ_MEDIA_KEY_OPTION:
                intent.setAction(MySettings.ACTION_EQ_MEDIA_KEY_OPTION_CHANGED);
                intent.putExtra(MySettings.EXTRA_EQ_MEDIA_KEY_OPTION_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.MEDIA_PACKAGENAME_ENTRY:
                intent.setAction(MySettings.ACTION_MEDIA_PACKAGENAME_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_MEDIA_PACKAGENAME_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.MEDIA_INTENT_ENTRY:
                intent.setAction(MySettings.ACTION_MEDIA_INTENT_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_MEDIA_INTENT_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.MEDIA_SYSCALL_ENTRY:
                intent.setAction(MySettings.ACTION_MEDIA_SYSCALL_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_MEDIA_SYSCALL_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.VIDEO_PACKAGENAME_ENTRY:
                intent.setAction(MySettings.ACTION_VIDEO_PACKAGENAME_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_VIDEO_PACKAGENAME_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.VIDEO_INTENT_ENTRY:
                intent.setAction(MySettings.ACTION_VIDEO_INTENT_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_VIDEO_INTENT_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.VIDEO_SYSCALL_ENTRY:
                intent.setAction(MySettings.ACTION_VIDEO_SYSCALL_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_VIDEO_SYSCALL_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.ACCON_PACKAGENAME_ENTRY:
                intent.setAction(MySettings.ACTION_ACCON_PACKAGENAME_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_ACCON_PACKAGENAME_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.ACCON_INTENT_ENTRY:
                intent.setAction(MySettings.ACTION_ACCON_INTENT_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_ACCON_INTENT_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.ACCON_SYSCALL_ENTRY:
                intent.setAction(MySettings.ACTION_ACCON_SYSCALL_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_ACCON_SYSCALL_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            /* USB ON settings. This is when ACC_ON is generated. Available usb devices are reconnected, which gives the
            usb device attached broadcast. We can't use ACC_ON on Android >=7.0
             */
            case MySettings.USBON_PACKAGENAME_ENTRY:
                intent.setAction(MySettings.ACTION_USBON_PACKAGENAME_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_USBON_PACKAGENAME_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.USBON_INTENT_ENTRY:
                intent.setAction(MySettings.ACTION_USBON_INTENT_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_USBON_INTENT_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.USBON_SYSCALL_ENTRY:
                intent.setAction(MySettings.ACTION_USBON_SYSCALL_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_USBON_SYSCALL_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            case MySettings.SWITCH_WIFI_ON:
                intent.setAction(MySettings.ACTION_SWITCH_WIFI_ON_CHANGED);
                intent.putExtra(MySettings.EXTRA_SWITCH_WIFI_ON_ENABLED, sharedPreferences.getBoolean(key, true));
                toastText = "BOOLEAN_KEY";
                break;
            case MySettings.RESTART_PLAYER:
                intent.setAction(MySettings.ACTION_RESTART_PLAYER_CHANGED);
                intent.putExtra(MySettings.EXTRA_RESTART_PLAYER_ENABLED, sharedPreferences.getBoolean(key, true));
                toastText = "BOOLEAN_KEY";
                break;

                /* ACCOFF settings */
            case MySettings.SWITCH_WIFI_OFF:
                intent.setAction(MySettings.ACTION_SWITCH_WIFI_OFF_CHANGED);
                intent.putExtra(MySettings.EXTRA_SWITCH_WIFI_OFF_ENABLED, sharedPreferences.getBoolean(key, true));
                toastText = "BOOLEAN_KEY";
                break;
            case MySettings.PAUSE_PLAYER:
                intent.setAction(MySettings.ACTION_PAUSE_PLAYER_CHANGED);
                intent.putExtra(MySettings.EXTRA_PAUSE_PLAYER_ENABLED, sharedPreferences.getBoolean(key, true));
                toastText = "BOOLEAN_KEY";
                break;
            case MySettings.ACCOFF_SYSCALL_ENTRY:
                intent.setAction(MySettings.ACTION_ACCOFF_SYSCALL_ENTRY_CHANGED);
                intent.putExtra(MySettings.EXTRA_ACCOFF_SYSCALL_ENTRY_STRING, sharedPreferences.getString(key, ""));
                break;
            default:
                Log.d(TAG, "Invalid setting encountered");
                break;
       }

        Log.d(TAG, "updated key is " + key);
        if (toastText.equals("BOOLEAN_KEY")) {
            toastText = "You updated boolean key \"" + (String)key + "\" to \"" + String.valueOf(sharedPreferences.getBoolean(key, false)) + "\"";
        } else {
            Log.d(TAG, "updated string is " + sharedPreferences.getString(key, ""));
            toastText = "You updated key \"" + key + "\" to \"" + sharedPreferences.getString(key, "") + "\"";
        }
        if (additionalText != "") {
            toastText = toastText + additionalText;
        }
        Toast mToast = Toast.makeText(mContext, toastText, Toast.LENGTH_LONG);
        mToast.show();

        if (intent.getAction() != null) {
            getActivity().sendBroadcast(intent);
        }


    }

}