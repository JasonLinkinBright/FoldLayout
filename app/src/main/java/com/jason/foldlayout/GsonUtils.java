package com.jason.foldlayout;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * ClassName:GsonUtils
 * Description:
 * Created by Jason on 17/6/20.
 */

public class GsonUtils {

    /**
     * 解析json对象
     *
     * @param json 字符串
     * @param t    的类型
     * @return
     */
    public static <T> T parseJSON(String json, Class<T> t) {
        Gson gson = new Gson();
        return gson.fromJson(json, t);
    }

    /**
     * 解析json对象
     *
     * @param jsonObject
     * @param t          的类型
     * @return
     */
    public static <T> T parseJSON(JsonObject jsonObject, Class<T> t) {
        Gson gson = new Gson();
        return gson.fromJson(jsonObject, t);
    }

    public static <T> T parseJSON(JsonElement jsonElement, Class<T> t) {
        Gson gson = new Gson();
        return gson.fromJson(jsonElement, t);
    }

    public static <T> T parseJSON(JsonElement jsonElement, Type type) {
        Gson gson = new Gson();
        try {
            return gson.fromJson(jsonElement, type);
        } catch (Exception e) {
//            Logger.e(Log.getStackTraceString(e));
        }
        return null;
    }

    /**
     * 解析模板类对象
     *
     * @return
     */
    public static <T> T parseJSON(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    /**
     * 解析json数组
     *
     * @param json
     * @param type 例如： Type type = new TypeToken&ltArrayList&ltJavaBean>>() {}.getType();
     * @return
     */
    public static <T> T parseJSONArray(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }

    public static JsonArray getAsJSONArray(String json) {
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        return jsonElement.getAsJsonArray();
    }

    public static String object2Json(Object object) {
        String json = "";
        if (object != null) {
            Gson gson = new Gson();
            json = gson.toJson(object);
        }
        return json;
    }

    public static Map<String, String> object2Map(Object object) {
        Map<String, String> resultMap = null;
        try {
            if (object != null) {
                Gson gson = new Gson();
                String json = gson.toJson(object);
                Type type = new TypeToken<Map<String, String>>() {
                }.getType();
                resultMap = gson.fromJson(json, type);
            }
        } catch (JsonSyntaxException e) {
//            Logger.e(Log.getStackTraceString(e));
        }
        return resultMap;
    }

}