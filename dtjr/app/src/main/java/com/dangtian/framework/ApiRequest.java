package com.dangtian.framework;

import android.os.Looper;

import com.dangtian.data.DtoWrapper;
import com.dangtian.data.UrlEnum;
import com.dangtian.module.UrlModel;
import com.dangtian.util.LogUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;


/**
 * Created by user on 2015/7/1.
 */
public class ApiRequest<T> implements Runnable {
    protected static final String CODE_SUCCESS = "200";
    protected static final String CODE_FAIlURE = "400";

    private UrlEnum url;
    private HttpParam httpParam;
    private Method method;
    private ServiceAction<T> action;
    private Class<T> clazz;

    public ApiRequest(UrlEnum url, HttpParam httpParam, Class<T> clazz, ServiceAction<T> action) {
        this.url = url;
        this.httpParam = httpParam;
        method = UrlModel.getType(url);
        this.action = action;
        this.clazz = clazz;
    }

    public void startTask() {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        Looper.prepare();
        String url = UrlModel.getUrl(this.url);
        if (url == null) {
            action.mlistener.onFailure("null pointer");
            return;
        }
        RequestParams params = new RequestParams("UTF-8");
 /*       try {
            params.setBodyEntity(new StringEntity(httpParam.toString(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            action.mlistener.onFailure(e.toString());
            return;
        }*/


        LogUtil.debug(url);
        LogUtil.debug(httpParam.toString());

        HttpUtils httpUtils = new HttpUtils();
        httpUtils.configRequestRetryCount(0);
        httpUtils.configCurrentHttpCacheExpiry(1000 * 10);
        RequestCallBack<String> callBack = new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                try {
                    String result = responseInfo.result;
                    LogUtil.debug(result);
                    if (clazz != null) {
                        DtoWrapper dto = new Gson().fromJson(result, DtoWrapper.class);
                        if (dto.getCode().equals(CODE_SUCCESS)) {
                            T dataObject = (T) new Gson().fromJson(new Gson().toJson(dto.getInfo()), clazz);
                            action.setResultData(dataObject);
                            action.mlistener.onSuccess();
                        } else {
                            action.mlistener.onFailure(dto.getMsg() + " " + dto.getCode());
                        }
                    } else {
                            DtoWrapper<T> dto = new Gson().fromJson(result, new TypeToken<DtoWrapper<T>>() {
                            }.getType());
                            if (dto.getCode().equals(CODE_SUCCESS)) {
                                action.setResultData(dto.getInfo());
                                action.mlistener.onSuccess();
                            } else {
                                action.mlistener.onFailure(dto.getMsg() + " " + dto.getCode());
                            }
                    }
                } catch (Exception e) {
                    action.mlistener.onFailure(e.toString());
                    throw e;
                }
            }


            @Override
            public void onFailure(HttpException e, String s) {
                action.mlistener.onFailure(e.toString());
            }
        };


        switch (method) {
            case get:
                httpUtils.send(HttpRequest.HttpMethod.GET, url, params, callBack);
                break;
            case post:
                httpUtils.send(HttpRequest.HttpMethod.POST, url, params, callBack);
                break;
            default:
                break;
        }
        Looper.loop();
    }

    public enum Method {
        get,
        post
    }
}