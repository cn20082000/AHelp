package com.cn.ahelp3.core.util;

import android.util.Log;

public class Lg {

    /** Api Response Log*/
    public static void ar(int statusCode, String message) {
        Log.i("Api Response", statusCode + " " + HttpDefinition.getName(statusCode) + " - " + message);
    }

    /** Test Log*/
    public static void te(String message) {
        Log.i("Test", message);
    }

    /** Debug Log*/
    public static void de(String message) {
        Log.e("Debug", message);
    }

    /** Error Log*/
    public static void er(String message) {
        Log.e("Error", message);
    }
}
