package com.palmprintdestiny.android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class Base64Util {
    /**
     * Bitmap 通过Base64 转换为字符串
     * @param bitmap
     * @return
     */
    public static String getBitmapStrBase64(Bitmap bitmap){
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] bytes = bos.toByteArray();
        String string = Base64.encodeToString(bytes, Base64.DEFAULT);
        return string;
    }

    /**
     * 字符串 转换Bitmap
     * @param str
     * @return
     */
    public static Bitmap stringToBitmap(String str){
        byte[] input = null;
        input = Base64.decode(str, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(input, 0, input.length);
        return bitmap;
    }
}

