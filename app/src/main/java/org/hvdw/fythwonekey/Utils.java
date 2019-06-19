package org.hvdw.fythwonekey;

import android.app.Activity;

import android.content.Intent;
import android.content.Context;
import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.content.SharedPreferences;

import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

/* shellExec and rootExec methods */
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;


class Utils {
    private static Context mContext = null;
    public static final String TAG = "FFE-Utils";
    private boolean use_root_access;
    private static SharedPreferences sharedprefs = null;
    private Toast mToast;


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
                shellExec(cmd);
//            }
        }
    };

    //public void checkAndRunOptions(Context context, String packageName_Call, String intent_call, final String sys_Call) {
    public void checkAndRunOptions(Context context, String packageName_Call) {
        if ("".equals(packageName_Call)) {
            //packagename_call unknown, start setup
            Log.d(TAG, Resources.getSystem().getString(R.string.pkg_notconfigured_short));
            mToast = Toast.makeText(context, Resources.getSystem().getString(R.string.pkg_notconfigured_long), Toast.LENGTH_SHORT);
            mToast.show();
            startActivityByPackageName(context, "org.hvdw.fythwonekey");
        } else {
            Log.d(TAG, Resources.getSystem().getString(R.string.pkg_configured_short) + " " + packageName_Call);
            //Start AV app or whatever app the user has configured
            startActivityByPackageName(context, packageName_Call);
        }
    }



    /*  Copied from https://stackoverflow.com/questions/20932102/execute-shell-command-from-android/26654728
    from the code of CarloCannas
*/
    public static String shellExec(String... strings) {
        String res = "";
        DataOutputStream outputStream = null;
        InputStream response = null;
        try {
            Process sh = Runtime.getRuntime().exec("sh");
            outputStream = new DataOutputStream(sh.getOutputStream());
            response = sh.getInputStream();

            for (String s : strings) {
                s = s.trim();
                outputStream.writeBytes(s + "\n");
                outputStream.flush();
            }

            outputStream.writeBytes("exit\n");
            outputStream.flush();
            try {
                sh.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            res = readFully(response);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Closer.closeSilently(outputStream, response);
        }
        return res;
    }


    public static String rootExec(String... strings) {
        String res = "";
        DataOutputStream outputStream = null;
        InputStream response = null;
        try {
            Process su = Runtime.getRuntime().exec("su");
            outputStream = new DataOutputStream(su.getOutputStream());
            response = su.getInputStream();

            for (String s : strings) {
                s = s.trim();
                outputStream.writeBytes(s + "\n");
                outputStream.flush();
            }

            outputStream.writeBytes("exit\n");
            outputStream.flush();
            try {
                su.waitFor();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            res = readFully(response);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Closer.closeSilently(outputStream, response);
        }
        return res;
    }

    public static String readFully(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = is.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return baos.toString("UTF-8");
    }
/* end of shell and su call functions/methods */

    private static void executeBroadcast(String input) {
        StringBuffer output = new StringBuffer();
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