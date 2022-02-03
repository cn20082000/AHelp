package com.cn.ahelp3.core.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class FileIO {

    public static String readAsString(Context context, String url) {
        String string = "";

        try {
            InputStream inputStream = context.getAssets().open(url);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            string = new String(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return string;
    }
}
