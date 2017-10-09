package com.jason.foldlayout;

import android.content.Context;
import android.content.res.AssetManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * ClassName:FileUtils
 * Description:
 * Created by Jason on 17/6/20.
 */

public class FileUtils {

    /**
     * 从Assets中读取文件
     */
    public static String readFileFromAssetsFile(Context context, String fileName) {
        if (context == null || TextUtils.isEmpty(fileName)) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            Charset cs = Charset.forName("UTF-8");
            InputStreamReader isr = null;
            BufferedReader br = null;
            try {
                isr = new InputStreamReader(is, cs);
                br = new BufferedReader(isr);
                String temp;
                while ((temp = br.readLine()) != null) {
                    result.append(temp);
                    result.append("\r\n");
                }
            } catch (Exception e) {
//                Logger.e("readFileFromAssetsFile error:" + e.getMessage());
            } finally {
                try {
                    if (br != null) {
                        br.close();
                    }
                    if (isr != null) {
                        isr.close();
                    }
                } catch (IOException e) {
//                    Logger.e("readFileFromAssetsFile IOError:" + e.getMessage());
                }
            }
            is.close();
        } catch (Exception e) {
//            Logger.e("readFileFromAssetsFile error:" + e.getMessage());
        }
        return result.toString();
    }

}

