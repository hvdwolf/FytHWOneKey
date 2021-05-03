package org.hvdw.fythwonekey;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.AttributeSet;

public class SettingsActivity extends PreferenceActivity {
    AttributeSet attrs;
    Context mContext;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getFragmentManager().findFragmentByTag("settings") == null) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment(), "settings").commit();
        }
    }
}