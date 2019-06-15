package org.hvdw.fythwonekey.utils;

import android.content.Intent;
import android.content.Context;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.SharedPreferences;

import android.preference.PreferenceManager;
import android.util.Log;


import org.hvdw.fythwonekey.utils.ShellUtils;


public class AppStartUtils {
    private static Context mContext = null;
    public static final String TAG = "FFE-ShellUtils";
    private boolean use_root_access;
    private static SharedPreferences sharedprefs = null;

    //ShellUtils myShellUtils = new ShellUtils();

    public static void init (Context context) {
        if(mContext != null)
			return;
		mContext = context;
		sharedprefs = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

/**********************************************************************************************************************************************/

    public void whichActionToPerform (Context context, String callMethod, String actionString) {
        if (callMethod.equals("pkgname")) {
            //Log.d(TAG, " the callmethond is indeed pkgname");
            startActivityByPackageName(context, actionString);
        }
        if (callMethod.equals("pkg_intent")) {
            startActivityByIntentName(context, actionString);
        }
        if (callMethod.equals("sys_call")) {
            //SharedPreferences sharedprefs = new SharedPreferences("org.hvdw.fytfunctionalityextender");
            //sharedprefs.makeWorldReadable();
//            use_root_access = sharedprefs.getBoolean(MySettings.USE_ROOT_ACCESS, true);
            //executeSystemCall(actionString);
            String[] cmd = actionString.split(";");
//            if (use_root_access == true) {
//                rootExec(cmd);
//            } else {
            ShellUtils.shellExec(cmd);
//            }
        }
    };




    public void executeBroadcast(String input) {
        //StringBuffer output = new StringBuffer();
        String cmd = "am broadcast -a " + input;
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            Log.d(TAG, cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    };

    public void startActivityByIntentName(Context context, String component) {
        Intent sIntent = new Intent(Intent.ACTION_MAIN);
        sIntent.setComponent(ComponentName.unflattenFromString(component));
        sIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        sIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(sIntent);
    }


    public void startActivityByPackageName(Context context, String packageName) {
        PackageManager pManager = context.getPackageManager();
        Intent intent = pManager.getLaunchIntentForPackage(packageName);
        Log.d(TAG, " startActivityByPackageName: " + packageName);
        if (intent != null) {
            context.startActivity(intent);
        }
    }

}