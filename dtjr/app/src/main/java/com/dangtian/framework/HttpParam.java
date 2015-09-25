package com.dangtian.framework;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2015/7/1.
 */
public class HttpParam {

    private Map<String, Object> mMap = new HashMap<>();

    public HttpParam() {
    }

    public HttpParam addParam(String name, Object value) {
        try {
            mMap.put(name, value);
        } finally {
            return this;
        }
    }

    public HttpParam addParam(Map map) {
        try {
            mMap.putAll(map);
        } finally {
            return this;
        }
    }


    public HttpParam addPageParam(int start) {
        mMap.put("pageIndex", start);
        return this;
    }

 /*   public HttpParam addBasicParams() {
        try {
            mMap.put("deviceToken", DeviceInfo.getDeviceToken());
            mMap.put("deviceType", DeviceInfo.getOsType());
            mMap.put("clientVersion", DeviceInfo.getClientVersion());
        } finally {
            return this;
        }
    }*/

/*    @Override
    public String toString() {
        if (mMap.size() == 0) {
            return "";
        }

        Gson gson = new Gson();
        return gson.toJson(mMap);
    }*/
}