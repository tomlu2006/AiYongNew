package com.yunbao.common.utils;

import android.util.Log;

/**
 * Created by cxf on 2017/8/3.
 */

public class L {

    public static boolean sDeBug = true;

    private final static String TAG = "log--->";

    public static void e(String s) {
        e(TAG, s);
    }

    public static void i(String s) {
        i(TAG, s);
    }

    public static void e(String tag, String s) {
        if (sDeBug) {
            Log.e(tag, s);
        }
    }

    public static void i(String tag, String s) {
        if (sDeBug) {
            Log.i(tag, s);
        }
    }

    public static void setDeBug(boolean deBug) {
        sDeBug = deBug;
    }
}
