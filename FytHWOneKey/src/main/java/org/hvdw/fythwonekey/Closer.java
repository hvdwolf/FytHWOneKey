package org.hvdw.fythwonekey;

import android.util.Log;

import java.io.Closeable;
import java.net.DatagramSocket;
import java.net.Socket;

//copied from https://stackoverflow.com/questions/20932102/execute-shell-command-from-android/26654728
// from the code of CarloCannas

public class Closer {
    public static final String TAG = "OneKey-Closer";

    public static void closeSilently(Object... xs) {
        // Note: on Android API levels prior to 19 Socket does not implement Closeable
        for (Object x : xs) {
            if (x != null) {
                try {
                    //Log.d("closing: "+x);
                    if (x instanceof Closeable) {
                        ((Closeable)x).close();
                    } else if (x instanceof Socket) {
                        ((Socket)x).close();
                    } else if (x instanceof DatagramSocket) {
                        ((DatagramSocket)x).close();
                    } else {
                        //Log.d("cannot close: "+x);
                        throw new RuntimeException("cannot close "+x);
                    }
                } catch (Throwable e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }
}