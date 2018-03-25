package io.github.allaudin.demo;

import android.util.Log;

@SuppressWarnings({"unused", "WeakerAccess"})
public class Logger {
    private static final String TAG = "Coconut";

    private Logger() {
        throw new AssertionError("Can't instantiate Logger");
    }

    public static void d(Object obj, String format, Object... args) {
        try {
            Log.d(TAG, "[" + obj.getClass().getSimpleName() + "] - " + String.format(format, args));
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // d

    public static void i(Object obj, String format, Object... args) {
        try {
            Log.i(TAG, "[" + obj.getClass().getSimpleName() + "] - " + String.format(format, args));
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // i

    public static void v(Object obj, String format, Object... args) {
        try {
            Log.v(TAG, "[" + obj.getClass().getSimpleName() + "] - " + String.format(format, args));
        } catch (Exception e) {
            e.printStackTrace();
        }
    } // v
} // Logger