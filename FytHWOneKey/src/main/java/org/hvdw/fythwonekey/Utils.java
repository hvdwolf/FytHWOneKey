package org.hvdw.fythwonekey;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.AudioManager;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

/* shellExec and rootExec methods */


public class Utils {
    private static Context mContext = null;
    public static final String TAG = "OneKey-Utils";
    private boolean use_root_access;
    private static SharedPreferences sharedprefs = null;
    private Toast mToast;


    public static void init(Context context) {
        if (mContext != null)
            return;
        mContext = context;
        sharedprefs = PreferenceManager.getDefaultSharedPreferences(mContext);
    }

    private static void executeBroadcast(String input) {
        StringBuffer output = new StringBuffer();
        String cmd = "am broadcast -a " + input;
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            Log.i(TAG, cmd);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    /**********************************************************************************************************************************************/

    public void whichActionToPerform(Context context, String callMethod, String actionString) {
        if (callMethod.equals("pkgname")) {
            //Log.i(TAG, " the callmethond is indeed pkgname");
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
    }

    /*public static void executeSystemCall(String input) {
        //String cmd = input;
        try {
            Process p = Runtime.getRuntime().exec(input);
            Log.i(TAG, input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    public static void executeSystemCall(String input) {
        final String cmd = input;
        Log.i(TAG, "Do a executeSystemCall with : " + cmd);
        Executor executor = java.util.concurrent.Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Process p = Runtime.getRuntime().exec(cmd);
                    //Process p = Runtime.getRuntime().exec("sh -c \"" + cmd + "\"");
                    Log.i(TAG, cmd);
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }

            }
        });
        Log.i(TAG, "Did the executeSystemCall with : " + cmd);
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
                Log.e(TAG, e.getMessage());
            }
            res = readFully(response);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
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
                Log.e(TAG, e.getMessage());
            }
            res = readFully(response);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
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

    //public void checkAndRunOptions(Context context, String packageName_Call, String intent_call, final String sys_Call) {
    public void checkAndRunOptions(Context context, String packageName) {
        if ("".equals(packageName)) {
            //packagename_call unknown, start setup
            //Log.i(TAG, Resources.getSystem().getString(R.string.pkg_notconfigured_short));
            Log.i(TAG, "no package configured");
            //mToast = Toast.makeText(context, Resources.getSystem().getString(R.string.pkg_notconfigured_long), Toast.LENGTH_SHORT);
            mToast = Toast.makeText(context, "No package configured, starting Config screen for setup.", Toast.LENGTH_SHORT);
            mToast.show();
            startActivityByPackageName(context, "org.hvdw.fythwonekey");
        } else {
            //Log.i(TAG, Resources.getSystem().getString(R.string.pkg_configured_short) + " " + packageName);
            Log.i(TAG, "Configured package : " + packageName);
            //Start whatever app the user has configured
            startActivityByPackageName(context, packageName);
        }
    }

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
        Log.i(TAG, " startActivityByPackageName: " + packageName);
        if (intent != null) {
            context.startActivity(intent);
        }
    }

    public void sendMediaCommand(Context context, String mediaCommand, boolean down) {
        KeyEvent event = null;
        AudioManager am = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        switch(mediaCommand) {
            case "KEYCODE_MEDIA_NEXT":
                event = new KeyEvent(0, 0, down ? KeyEvent.ACTION_DOWN : KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_NEXT, 0);
                break;
            case "KEYCODE_MEDIA_PREVIOUS":
                event = new KeyEvent(0, 0, down ? KeyEvent.ACTION_DOWN : KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PREVIOUS, 0);
                break;
            case "KEYCODE_MEDIA_PLAY":
                event = new KeyEvent(0, 0, down ? KeyEvent.ACTION_DOWN : KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY, 0);
                break;
            case "KEYCODE_MEDIA_PAUSE":
                event = new KeyEvent(0, 0, down ? KeyEvent.ACTION_DOWN : KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PAUSE, 0);
                break;
            case "KEYCODE_MEDIA_PLAY_PAUSE":
                event = new KeyEvent(0, 0, down ? KeyEvent.ACTION_DOWN : KeyEvent.ACTION_UP, KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE, 0);
                break;
        }
        am.dispatchMediaKeyEvent(event);

    }

}