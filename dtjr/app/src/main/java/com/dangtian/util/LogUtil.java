package com.dangtian.util;

import android.util.Log;

/**
 * Created by Administrator on 2015/7/25.
 */
public class LogUtil {
    private static boolean sDebug = true;

    public static void exception(Object o) {
        if (sDebug) {
            throw new IllegalStateException(String.valueOf(o));
        } else {
            Log.e("debug", String.valueOf(o));
        }
    }

    public static void error(Object o) {
        if (sDebug) {
            Log.e("debug", String.valueOf(o));
        }
    }

    public static void debug(Object o) {
        if (sDebug) {
            Log.d("debug", String.valueOf(o));
        }
    }
}
