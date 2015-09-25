package com.dangtian.framework;

/**
 * Created by user on 2015/7/1.
 */
public class ServiceAction<T> {
    T resultData;
    NetCallback mlistener;

    public void setCallback(NetCallback listener){
        mlistener = listener;
    }

    public void setResultData(T data) {
        resultData = data;
    }

    public T getResultData(){
        return resultData;
    }
}