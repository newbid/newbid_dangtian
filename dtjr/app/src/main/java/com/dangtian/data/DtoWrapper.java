package com.dangtian.data;

/**
 * Created by asus on 2015/9/24.
 */
public class DtoWrapper<T> extends DtoObject {
    T info;
    String code;
    String msg;

    public T getInfo() {
        return info;
    }

    public void setInfo(T instance) {
        this.info = instance;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String errorMsg) {
        this.msg = errorMsg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String errorCode) {
        this.code = errorCode;
    }
}
