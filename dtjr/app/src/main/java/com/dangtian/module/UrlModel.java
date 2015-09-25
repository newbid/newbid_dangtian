package com.dangtian.module;

import com.dangtian.data.UrlEnum;
import com.dangtian.framework.ApiRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by asus on 2015/9/24.
 */
public class UrlModel {

    static private boolean sInitialized = false;
    static private Map<UrlEnum, String> sUrlMap;
    static private Map<UrlEnum, ApiRequest.Method> sMethodTypeMap;
    static private Map<UrlEnum, UrlType> sUrlTypeMap;
     //1 为测试服务器
    //2 为生产环境
    static final int ServiceType = 1;


    static public String getBaseUrl(UrlEnum urlEnum) {
        UrlType type = sUrlTypeMap.get(urlEnum);
        String url = null;
        switch (type){
            case base:
                if(ServiceType == 1)
                    url = "http://app.test.dtd365.com";
                else
                    url = "https://api.dtd365.com";
                break;
            case passPort:
                if(ServiceType == 1)
                    url = "http://passport.test.dtd365.com";
                else
                    url = "https://passport.dtd365.com";
                break;
            case customer:
                if(ServiceType == 1)
                    url = "http://customer.test.dtd365.com";
                else
                    url = "https://customer.dtd365.com";
                break;
            case finance:
                if(ServiceType == 1)
                    url = "http://finance.dev.dtd365.com";
                else
                    url = "https://finance.dtd365.com";
                break;
            default:
                break;
        }
        return url;
    }

    static public String getVersionString() {
        return "v1";
    }

    static public ApiRequest.Method getType(UrlEnum urlEnum) {
        if (!sInitialized) {
            init();
        }

        if (!sMethodTypeMap.containsKey(urlEnum)) {
            return null;
        }

        return sMethodTypeMap.get(urlEnum);
    }


    static public String getUrl(UrlEnum urlEnum) {
        if (!sInitialized) {
            init();
        }

        if (!sUrlMap.containsKey(urlEnum)) {
            return null;
        }

        return getBaseUrl(urlEnum) + sUrlMap.get(urlEnum);
    }

    static private void init() {
        sUrlMap = new HashMap<>();
        sUrlTypeMap = new HashMap<>();
        sUrlMap.put(UrlEnum.authorize, "/authorize");
        sMethodTypeMap.put(UrlEnum.authorize, ApiRequest.Method.get);
        sUrlTypeMap.put(UrlEnum.authorize, UrlType.base);
        //checkLoginName
        sUrlMap.put(UrlEnum.checkLoginName, "/api/v1/user/checkLoginName");
        sMethodTypeMap.put(UrlEnum.checkLoginName, ApiRequest.Method.get);
        sUrlTypeMap.put(UrlEnum.checkLoginName, UrlType.customer);

        //smsRegister
        sUrlMap.put(UrlEnum.smsRegister, "/api/v1/activation/register");
        sMethodTypeMap.put(UrlEnum.smsRegister, ApiRequest.Method.get);
        sUrlTypeMap.put(UrlEnum.smsRegister, UrlType.customer);

        //register
        sUrlMap.put(UrlEnum.register, "/api/v1/user/register");
        sMethodTypeMap.put(UrlEnum.register, ApiRequest.Method.post);
        sUrlTypeMap.put(UrlEnum.register, UrlType.customer);

        sInitialized = true;
    }

    public enum UrlType{
        base,
        passPort,
        customer,
        finance
    }

}
