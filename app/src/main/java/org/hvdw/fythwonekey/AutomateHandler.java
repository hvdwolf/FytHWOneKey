package org.hvdw.fythwonekey;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


public class AutomateHandler {
    public static final String TAG = "OneKey-AutomateHandler";
    private static final Handler handler = new Handler();

    public static void startAutomateFlow(Context context, String[] parts, String buttonName) {
        if (parts.length >= 3) {
            Uri shortPressUri = Uri.parse(parts[0]);
            long timeForLongPress = parts[1].trim().length() == 0 ? -1 : Math.abs(Long.parseLong(parts[1].trim()));
            Uri longPressUri = Uri.parse(parts[2]);

            handleAutomateButtonPress(context, shortPressUri, timeForLongPress, longPressUri, buttonName);
        } else {
            Uri shortPressUri = Uri.parse(parts[0]);
            startAutomateFlowUri(context, shortPressUri);
        }
    }

    private static SharedPreferences getPreferences(Context context, String key) {
        return context.getSharedPreferences(key, Context.MODE_PRIVATE);
    }

    private static void startAutomateFlowUri(Context context, Uri flowUri) {
        Intent intent = new Intent("com.llamalab.automate.intent.action.START_FLOW");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(flowUri);

        context.startActivity(intent);
    }

    private static void handleAutomateButtonPress(Context context, Uri shortPressUri, long timeForLongPress, Uri longPressUri, String buttonName) {
        SharedPreferences delayedPressToCallbackId = getPreferences(context, MySettings.AUTOMATE_DELAYED_PRESS_TO_CALLBACK_ID);
        SharedPreferences.Editor delayedPressToCallbackIdEditor = delayedPressToCallbackId.edit();

        // the button was already short pressed, but the action still hasn't been executed
        if (delayedPressToCallbackId.contains(buttonName)) {
            // invalidate the delayed action
            delayedPressToCallbackIdEditor.remove(buttonName);
            delayedPressToCallbackIdEditor.commit();

            Log.i(TAG, "longPress: button:" + buttonName + " timeForPress:" + timeForLongPress);
            startAutomateFlowUri(context, longPressUri);

            SharedPreferences lastButtonCallTime = getPreferences(context, MySettings.AUTOMATE_LAST_BUTTON_CALL_TIME);
            if (timeForLongPress < 0 && lastButtonCallTime.contains(buttonName)) {
                final long currentPressTime = System.currentTimeMillis();
                final long firstPressTime = lastButtonCallTime.getLong(buttonName, -1);
                final long timeSinceFirstPress = currentPressTime - firstPressTime;

                if (firstPressTime >= 0)
                    Toast.makeText(context, "Time since last press: " + timeSinceFirstPress + "ms", Toast.LENGTH_SHORT).show();
            }
        } else { // this is the first press
            final String delayedCallbackId = "" + System.currentTimeMillis() + "-" + Math.random();
            delayedPressToCallbackIdEditor.putString(buttonName, delayedCallbackId);
            delayedPressToCallbackIdEditor.commit();

            // save the time of the first press
            SharedPreferences lastButtonCallTime = getPreferences(context, MySettings.AUTOMATE_LAST_BUTTON_CALL_TIME);
            SharedPreferences.Editor lastButtonCallTimeEditor = lastButtonCallTime.edit();
            lastButtonCallTimeEditor.remove(buttonName);
            lastButtonCallTimeEditor.putLong(buttonName, System.currentTimeMillis());
            lastButtonCallTimeEditor.commit();

            Log.i(TAG, "shortPress: button:" + buttonName + " callbackId:" + delayedCallbackId + " timeForPress:" + timeForLongPress);

            if (timeForLongPress >= 0) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SharedPreferences delayedPressToCallbackId = getPreferences(context, MySettings.AUTOMATE_DELAYED_PRESS_TO_CALLBACK_ID);
                        SharedPreferences.Editor delayedPressToCallbackIdEditor = delayedPressToCallbackId.edit();

                        // check if the current delayed task was invalidated
                        if (!delayedPressToCallbackId.contains(buttonName) || !delayedPressToCallbackId.getString(buttonName, "").equals(delayedCallbackId))
                            return;

                        // mark the delayed task as executed
                        delayedPressToCallbackIdEditor.remove(buttonName);
                        delayedPressToCallbackIdEditor.commit();

                        startAutomateFlowUri(context, shortPressUri);
                    }
                }, timeForLongPress);
            }
        }
    }
}
