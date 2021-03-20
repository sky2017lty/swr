package com.poshing.swr.utils;

import com.alibaba.fastjson.JSONArray;

/**
 * @author: LiTianyi
 * @Date: 2021/2/21 0021 12:34
 * @Description
 */
public class JsonUtils {

    private static final JsonUtils INSTANCE = new JsonUtils();

    private JsonUtils() {
    }

    public static JsonUtils getInstance() {
        return INSTANCE;
    }

    public String formatLayerJson(int code, String msg, int count, String data) {
        return "{\n" +
                "\"code\": " + code + ",\n" +
                "\"msg\": \"" + msg + "\",\n" +
                "\"count\": " + count + ",\n" +
                "\"data\": " + data + "\n" +
                "} ";
    }

    public String formatLayerJson(int code, String msg, JSONArray data) {
        if (data != null) {
            return "{\n" +
                    "\"code\":" + code + ",\n" +
                    "\"msg\":\"" + msg + "\",\n" +
                    "\"count\":" + data.size() + ",\n" +
                    "\"data\":" + data + "\n" +
                    "} ";
        } else {
            return "{\n" +
                    "\"code\": " + code + ",\n" +
                    "\"msg\": \"" + msg + "\"\n" +
                    "} ";
        }
    }
}
